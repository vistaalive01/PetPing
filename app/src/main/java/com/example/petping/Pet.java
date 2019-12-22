package com.example.petping;

public class Pet {
    private String name;
    private String sex;
    private String weight;
    private String breed;
    private String character;
    private String story;
    private String health;
    private String DOB;

    public Pet(String name, String breed, String sex, String weight, String character, String story, String health, String DOB) {
        this.name = name;
        this.sex = sex;
        this.weight = weight;
        this.breed = breed;
        this.character = character;
        this.story = story;
        this.health = health;
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getWeight() {
        return weight;
    }

    public String getBreed() {
        return breed;
    }

    public String getCharacter() {
        return character;
    }

    public String getStory() {
        return story;
    }

    public String getHealth() {
        return health;
    }

    public String getDOB() {
        return DOB;
    }
}
