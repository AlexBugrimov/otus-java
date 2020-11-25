### Тестовый фреймворк

#### Настройка запуска:
```java
final Options options = new RunnerOptions(
    new ClassLoader(new ClassPath("ru.ru.otus.tests")),
    new OutputToConsole(),
    true,
    true
);
```
* ClassLoader - loader для загрузки классов
* Output - вывод результатов
* isPrintTestTrace - печать trace запуска тестов (true/false)
* isPrintReport - печатать отчет (true/false)

#### Результаты:

![Results](https://github.com/AlexBugrimov/ru.otus-java/blob/hw03-annotations/hw03-annotations/result.png)