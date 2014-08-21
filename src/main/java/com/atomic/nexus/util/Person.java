package com.atomic.nexus.util;

/**
 * Dante Pasionek created: com.atomic.nexus.util on Jul. 30, 2014 *
 */
public class Person {

    String name = null;
    int age = -1;
    String[] symptoms = null;

    public Person(String name, int age, String[] symptoms) {
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
    }

    public String getName() {
        return name;
    }

    public int getAge () {
        return age;
    }

    public String[] getSymptoms() {
        return symptoms;
    }
}
