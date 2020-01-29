package com.example.petping;

public class PetHistory {
    private String petID;
    private int count;
    private String url;
    private String sex;
    private String name;
    private String age;
    private String breed;

    public PetHistory(String petID){
//    public void PetHistory(String petID, int count,String url,String sex, String name, String age, String breed){
        this.petID = petID;

//        this.url = url;
//        this.sex = sex;
//        this.name = name;
//        this.age = age;
//        this.breed = breed;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
}