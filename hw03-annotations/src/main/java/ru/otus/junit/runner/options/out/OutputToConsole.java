package ru.otus.junit.runner.options.out;

import ru.otus.junit.runner.ResultOfRunning;
import ru.otus.junit.runner.TestClass;
import ru.otus.junit.runner.options.utils.Mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.otus.junit.runner.options.utils.Mapper.toCountResults;

public class OutputToConsole implements Output {

    private static final char ESC = (char) 27;
    private static final String CLASS_TEMPLATE = "\n- %s\n";
    private static final String TEST_TEMPLATE = "\t%d. %s: %s\n";
    private static final String REPORT_TEMPLATE = """
            
            ----------------------------------------------------
            Были запущены классы:
            %s
            ----------------------------------------------------
            Успешных тестов:    %s
            Проваленных тестов: %s
            Всего:              %s
            ----------------------------------------------------
            """;

    @Override
    public void printTestTrace(List<ResultOfRunning> results) {
        results.forEach(result -> {
            print(Color.YELLOW, CLASS_TEMPLATE, result.getClazz().getName());
            final int[] index = {0};
            result.getResults().forEach(test -> {
                final var type = test.getType();
                print(Mapper.toColor(type), TEST_TEMPLATE, ++index[0], type, test.getDescription());
            });
        });
    }

    @Override
    public void printReport(List<ResultOfRunning> results) {
        final String testClasses = results
                .stream()
                .map(result -> result.getClazz().getSimpleName())
                .collect(Collectors.joining(", "));

        long successCount = toCountResults.apply(results, result -> result.getType().equals(TestClass.Result.Type.SUCCESS));
        long failCount = toCountResults.apply(results, result -> result.getType().equals(TestClass.Result.Type.ERROR));
        long totalCount = toCountResults.apply(results, Objects::nonNull);
        print(Color.DEFAULT, REPORT_TEMPLATE, testClasses, successCount, failCount, totalCount);
    }

    private void print(Color color, String template, Object... values) {
        System.out.printf(ESC + color.getCode() + template, values);
    }
}
