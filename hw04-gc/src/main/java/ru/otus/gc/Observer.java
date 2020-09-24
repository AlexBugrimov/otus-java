package ru.otus.gc;

import java.util.Map;

public interface Observer {

    Observer observe(Executor executor);

    Map<String, Long> getResults();
}
