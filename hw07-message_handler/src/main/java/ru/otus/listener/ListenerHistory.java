package ru.otus.listener;

import ru.otus.Message;

public class ListenerHistory implements Listener {

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        System.out.printf("Old message:%s, New message:%s\n", oldMsg, newMsg);
    }
}
