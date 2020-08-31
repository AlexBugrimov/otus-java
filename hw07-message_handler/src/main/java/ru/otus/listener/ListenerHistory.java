package ru.otus.listener;

import ru.otus.Message;

public class ListenerHistory implements Listener {

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        System.out.printf("Old: %s,\nNew: %s\n", oldMsg, newMsg);
    }
}
