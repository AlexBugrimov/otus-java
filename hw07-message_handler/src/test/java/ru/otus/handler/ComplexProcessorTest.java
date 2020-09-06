package ru.otus.handler;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.Message;
import ru.otus.listener.Listener;
import ru.otus.processor.Processor;
import ru.otus.processor.ProcessorWithException;
import ru.otus.processor.exceptions.TimeSecondsException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ComplexProcessorTest {

    @Test
    @DisplayName("Тестируем последовательность вызова процессоров")
    void handleProcessorsTest() {
        //given
        var message = new Message.Builder().field7("field7").build();

        var processor1 = mock(Processor.class);
        when(processor1.process(eq(message))).thenReturn(message);

        var processor2 = mock(Processor.class);
        when(processor2.process(eq(message))).thenReturn(message);

        var processors = List.of(processor1, processor2);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
        });

        //when
        var result = complexProcessor.handle(message);

        //then
        verify(processor1, times(1)).process(eq(message));
        verify(processor2, times(1)).process(eq(message));
        assertThat(result).isEqualTo(message);
    }

    @Test
    @DisplayName("Тестирование изменение значений полей местами")
    void swapProcessorsTest() {
        //given
        var message = new Message.Builder().field11("field11").field13("field13").build();

        var processor = mock(Processor.class);
        when(processor.process(eq(message))).thenReturn(message.toBuilder()
                .field11("field13")
                .field13("field11")
                .build());

        var processors = List.of(processor);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
        });

        //when
        var result = complexProcessor.handle(message);

        //then
        verify(processor, times(1)).process(eq(message));
        assertThat(result.getField11()).isEqualTo("field13");
        assertThat(result.getField13()).isEqualTo("field11");
    }

    @Test
    @DisplayName("Тестируем исключение на четной секунде")
    void timeExceptionTest() {
        //given
        var message = new Message.Builder().field8("field8").build();

        final Processor processor = spy(
                new ProcessorWithException(() -> LocalDateTime.of(2020, 12, 10, 10, 10, 4)));
        var processors = List.of(processor);
        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new TimeSecondsException(ex);
        });

        //when
        assertThatExceptionOfType(TimeSecondsException.class).isThrownBy(() -> complexProcessor.handle(message));

        //then
        verify(processor, times(1)).process(eq(message));
    }

    @Test
    @DisplayName("Тестируем обработку исключения")
    void handleExceptionTest() {
        //given
        var message = new Message.Builder().field8("field8").build();

        var processor1 = mock(Processor.class);
        when(processor1.process(eq(message))).thenThrow(new RuntimeException("Test Exception"));

        var processor2 = mock(Processor.class);
        when(processor2.process(eq(message))).thenReturn(message);

        var processors = List.of(processor1, processor2);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new TestException(ex.getMessage());
        });

        //when
        assertThatExceptionOfType(TestException.class).isThrownBy(() -> complexProcessor.handle(message));

        //then
        verify(processor1, times(1)).process(eq(message));
        verify(processor2, never()).process(eq(message));
    }

    @Test
    @DisplayName("Тестируем уведомления")
    void notifyTest() {
        //given
        var message = new Message.Builder().field9("field9").build();

        var listener = mock(Listener.class);

        var complexProcessor = new ComplexProcessor(new ArrayList<>(), (ex) -> {
        });

        complexProcessor.addListener(listener);

        //when
        complexProcessor.handle(message);
        complexProcessor.removeListener(listener);
        complexProcessor.handle(message);

        //then
        verify(listener, times(1)).onUpdated(eq(message), eq(message));
    }

    private static class TestException extends RuntimeException {
        public TestException(String message) {
            super(message);
        }
    }
}