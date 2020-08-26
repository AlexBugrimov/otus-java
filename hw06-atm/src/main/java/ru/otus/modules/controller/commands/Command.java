package ru.otus.modules.controller.commands;


import ru.otus.tinkoff.Modules;

public interface Command {

    Result execute(Modules modules);
}
