package ru.otus.listener;

import ru.otus.Message;

public interface Listener {

    void onUpdated(Message oldMsg, Message newMsg);

}
