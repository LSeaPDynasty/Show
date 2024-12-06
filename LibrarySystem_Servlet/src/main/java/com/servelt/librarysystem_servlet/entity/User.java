package com.servelt.librarysystem_servlet.entity;

public class User {
    private String username;
    private String password;
    private String name;
    private String contact;
    private String grade;
    private String gender;
    private int age;

    public User() {
        this.username = "username";
        this.password = "password";
        this.name = "name";
        this.contact = "contact";
        this.grade = "grade";
        this.gender = "gender";
        this.age = 0;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "";
        this.contact = "";
        this.grade = "";
        this.gender = "";
        this.age = 0;
    }

    public User(String username, String password, String name, String contact, String grade, String gender, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.grade = grade;
        this.gender = gender;
        this.age = age;
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAge() {
        return Integer.toString(age);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
