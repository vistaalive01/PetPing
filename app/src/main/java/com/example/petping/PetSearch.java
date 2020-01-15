package com.example.petping;

public class PetSearch {
    private String ID;
    private String name;
    private String type;
    private String colour;
    private String sex;
    private String age;
    private String breed;
    private String size;
    private String url;
    private String weight;
    private String character;
    private String marking;
    private String health;
    private String foundLoc;
    private String status;
    private String story;


    public PetSearch(String ID, String name, String type, String colour, String sex, String age, String breed, String size, String url,
                     String weight, String character, String marking, String health, String foundLoc, String status, String story) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.colour = colour;
        this.sex = sex;
        this.age = age;
        this.breed = breed;
        this.size = size;
        this.url = url;
        this.weight = weight;
        this.character = character;
        this.marking = marking;
        this.health = health;
        this.foundLoc = foundLoc;
        this.status = status;
        this.story = story;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getFoundLoc() {
        return foundLoc;
    }

    public void setFoundLoc(String foundLoc) {
        this.foundLoc = foundLoc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getColour() {
        return colour;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
