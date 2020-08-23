package ru.otus.atm.receiver;

public enum State {

    OPEN {
        @Override
        public State next() {
            return CLOSE;
        }
    }, CLOSE {
        @Override
        public State next() {
            return OPEN;
        }
    };

    public abstract State next();
}