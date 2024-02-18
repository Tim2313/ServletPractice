package org.example.converter;

import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.example.model.Developer;

public class ArgumentToDeveloperConverter {

    private static ArgumentToDeveloperConverter instance;

    private ArgumentToDeveloperConverter() {
    }

    public Developer convert(Arguments arguments) {

        Developer developer = new Developer();

        String firstName = arguments.getHashMap().get(RequestArgument.FIRSTNAME);
        developer.setFirstName(firstName);

        String lastName = arguments.getHashMap().get(RequestArgument.LASTNAME);
        developer.setLastName(lastName);

        String inputAge = arguments.getHashMap().get(RequestArgument.AGE);
        int age = Integer.parseInt(inputAge);
        developer.setAge(age);

        String programmingLang = arguments.getHashMap().get(RequestArgument.PROGRAMMING_LANGUAGE);
        developer.setProgrammingLanguage(programmingLang);

        return developer;
    }

    public static ArgumentToDeveloperConverter getInstance() {
        if (instance == null) {
            instance = new ArgumentToDeveloperConverter();
        }
        return instance;
    }
}
