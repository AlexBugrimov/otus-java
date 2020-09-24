package ru.otus.gc;

import java.util.List;

public interface Observer {

    Observer observe(Executor executor);

    List<GcObserver.Result> getResults();
}
