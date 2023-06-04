package com.ua.vinn.GammaLight.trashFiles;

import lombok.Getter;
import lombok.Setter;

public class TestExample {

    public static void main(String[] args) {
//     Child ch = new Child("Hor", "Kozachenko", 19, "online games", true);
//     String name = ch.getName();
//     System.out.println(name);

    }//main

}//class
@Getter
@Setter
class Parent{
    private String name;
    private String surname;
    private int age;

    public Parent(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}

@Getter
@Setter
class Child extends Parent{
    private String hobby;
    private boolean playOnlineGame;

    public Child(String name, String surname, int age, String hobby, boolean playOnlineGame) {
        super(name, surname, age);
        this.hobby = hobby;
        this.playOnlineGame = playOnlineGame;
    }
}