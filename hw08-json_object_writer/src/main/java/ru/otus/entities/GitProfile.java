package ru.otus.entities;

import java.util.List;

public class GitProfile {

    private static final String VERSION = "1.0";

    private final Avatar avatar;
    private final String nickname;
    private int followers;
    private int following;
    private short starts;
    private String bio;
    private final List<Repository> repositories;
    private final int[] contributionsInLastYear = new int[] {2001, 2003};

    public GitProfile(Avatar avatar, String nickname, List<Repository> repositories) {
        this.avatar = avatar;
        this.nickname = nickname;
        this.repositories = repositories;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getStarts() {
        return starts;
    }

    public String getBio() {
        return bio;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public int[] getContributionsInLastYear() {
        return contributionsInLastYear;
    }
}
