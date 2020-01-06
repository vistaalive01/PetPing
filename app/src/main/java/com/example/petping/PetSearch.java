package com.example.petping;

public class PetSearch {
    private String type;
    private String colour;
    private String sex;
    private String age;

    public PetSearch(String type, String colour, String sex, String age) {
        this.type = type;
        this.colour = colour;
        this.sex = sex;
        this.age = age;
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
}
