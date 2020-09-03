package ru.otus.entities;

public class Repository {

    private String name;
    private String description;
    private Visibility visibility = Visibility.PUBLIC;
    private Branch[] branches = new Branch[] { new Branch("master")};

    public Visibility getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Branch[] getBranches() {
        return branches;
    }

    public Repository setName(String name) {
        this.name = name;
        return this;
    }

    public Repository setDescription(String description) {
        this.description = description;
        return this;
    }

    public Repository setVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public Repository setBranches(Branch[] branches) {
        this.branches = branches;
        return this;
    }
}
