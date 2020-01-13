package com.example.petping;

public class PetSearch {
    private String ID;
    private String name;
    private String type;
    private String colour;
    private String sex;
    private String age;
    private String breed;


    public PetSearch(String ID, String name, String type, String colour, String sex, String age, String breed) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.colour = colour;
        this.sex = sex;
        this.age = age;
        this.breed = breed;
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
