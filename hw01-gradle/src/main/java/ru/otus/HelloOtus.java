package ru.otus;

import com.google.common.collect.Lists;

import java.util.List;

public class HelloOtus {

    public static void main(String[] args) {
        final String message = "Hello Otus";
        final List<Character> messageAsCharacters = Lists.charactersOf(message);
        messageAsCharacters.forEach(System.out::println);
    }
}
