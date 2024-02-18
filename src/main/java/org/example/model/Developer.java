package org.example.model;

import java.util.Objects;

public class Developer {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_PROGRAMMING_LANGUAGE = "programmingLanguage";
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String programmingLanguage;

    public Developer(int id, String firstName, String lastName, int age, String programmingLanguage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.programmingLanguage = programmingLanguage;
    }

    public Developer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }


    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id && age == developer.age && Objects.equals(firstName, developer.firstName) && Objects.equals(lastName, developer.lastName) && Objects.equals(programmingLanguage, developer.programmingLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, programmingLanguage);
    }
}
