package org.example.model;
public class Developer {

    public static final String COLUMN_NAME_ID = "Id";
    public static final String COLUMN_NAME_FIRST_NAME = "firstName";
    public static final String COLUMN_NAME_SECOND_NAME = "lastName";
    public static final String COLUMN_NAME_AGE = "age";
    public static final String COLUMN_NAME_PROGRAMMING_LANGUAGE = "programmingLanguage";
    private int id;
    private String firstName;
    private String secondName;
    private int age;
    private String programmingLanguage;


    public Developer(int id, String firstName, String secondName, int age, String programmingLanguage) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                '}';
    }
}
