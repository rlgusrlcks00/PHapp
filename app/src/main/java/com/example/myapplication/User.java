package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.Index;


@Entity(indices = {@Index(value = {"email"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private int User_id=0;

    @ColumnInfo(name = "email")
    private String email;
    private String PW;
    private String gender;
    private String address;
    private String name;
    private String age;
    private String PhoneNumber;
    private String weight;
    private String height;
    private String BMI;
    private String muscle_mass;
    private String body_fat_percentage;

    //getter, setter how: alt+Insert

    public String getMuscle_mass() {
        return muscle_mass;
    }

    public void setMuscle_mass(String muscle_mass) {
        this.muscle_mass = muscle_mass;
    }

    public String getBody_fat_percentage() {
        return body_fat_percentage;
    }

    public void setBody_fat_percentage(String body_fat_percentage) {
        this.body_fat_percentage = body_fat_percentage;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

}
