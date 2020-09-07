package ru.otus;

import com.google.gson.Gson;
import ru.otus.bson.ClassAdapter;
import ru.otus.entities.Avatar;
import ru.otus.entities.GitProfile;
import ru.otus.bson.Bson;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        GitProfile gitProfile = new GitProfile(
                new Avatar("http://avatars.com/git_profile.png"),
                "Alex",
                Collections.emptyList()
        );

        final Bson bson = new Bson();
        final String json = bson.toJson(gitProfile);

        System.out.println(json);

        Gson gson = new Gson();
        final String toJson = gson.toJson(gitProfile);
        System.out.println(toJson);
    }
}
