package ru.otus.bson.handlers;

import ru.otus.bson.Handler;
import ru.otus.bson.JsonNode;
import ru.otus.bson.exceptions.BsonException;

import java.io.IOException;
import java.io.Writer;

import static ru.otus.bson.handlers.JsonHandler.Bracket.*;


public class JsonHandler implements Handler {

    enum Bracket {

        OPENING_CURLY("{"), CLOSING_CURLY("}"),
        OPENING_SQUARE("["), CLOSING_SQUARE("]");

        Bracket(String bracket) {
            this.bracket = bracket;
        }

        private final String bracket;

        public String getValue() {
            return bracket;
        }
    }

    private final Writer writer;

    private JsonHandler(Writer writer) {
        this.writer = writer;
    }

    public static JsonHandler from(Writer writer) {
        return new JsonHandler(writer);
    }

    @Override
    public void write(JsonNode node) {
        writeObject(node);
    }

    private void writeObject(JsonNode node) {
        startObject();
        try {
            if (node.isNull()) {
                writer.write(node.toString());
            }
        } catch (IOException ex) {
            throw new BsonException("Write Error", ex);
        }
        endObject();
    }

    public void startObject() {
        write(OPENING_CURLY.getValue());
    }

    public void endObject() {
        write(CLOSING_CURLY.getValue());
    }

    public void startArray() {
        write(OPENING_SQUARE.getValue());
    }

    public void endArray() {
        write(CLOSING_SQUARE.getValue());
    }


    private void write(String str) {
        try {
            writer.write(str);
        } catch (IOException ex) {
            throw new BsonException("Error writing string", ex);
        }
    }
}
