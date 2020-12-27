package ru.otus.appcontainer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.config.AppConfig;
import ru.otus.services.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест контейнера компонентов")
class AppComponentsContainerImplTest {

    private static AppComponentsContainer container;

    @BeforeAll
    public static void setUp() {
        container = new AppComponentsContainerImpl(AppConfig.class);
    }

    @ParameterizedTest
    @DisplayName("Получить экземпляр по классу")
    @MethodSource("provideClassComponents")
    void getClassByClassTest(Class<?> clazz) throws Exception {
        final Object component = container.getAppComponent(clazz);
        assertThat(component).isInstanceOf(clazz);
    }

    private static Stream<Arguments> provideClassComponents() {
        return Stream.of(
                Arguments.of(GameProcessor.class),
                Arguments.of(GameProcessorImpl.class),
                Arguments.of(EquationPreparer.class),
                Arguments.of(EquationPreparerImpl.class),
                Arguments.of(IOService.class),
                Arguments.of(IOServiceConsole.class),
                Arguments.of(PlayerService.class),
                Arguments.of(PlayerServiceImpl.class)
        );
    }

    @ParameterizedTest
    @DisplayName("Получить экземпляр по имени компонента")
    @MethodSource("provideNameComponents")
    void testGetAppComponent(Class<?> clazz, String componentName) throws Exception {
        final Object component = container.getAppComponent(componentName);
        assertThat(component).isInstanceOf(clazz);
    }

    private static Stream<Arguments> provideNameComponents() {
        return Stream.of(
                Arguments.of(GameProcessor.class, "gameProcessor"),
                Arguments.of(EquationPreparer.class, "equationPreparer"),
                Arguments.of(IOService.class, "ioService"),
                Arguments.of(PlayerService.class, "playerService")
        );
    }
}