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

        String firstName = arguments.getHashMap().get(RequestArgument.FIRSTNAME_ARG);
        developer.setFirstName(firstName);

        String lastName = arguments.getHashMap().get(RequestArgument.LASTNAME_ARG);
        developer.setLastName(lastName);

        String inputAge = arguments.getHashMap().get(RequestArgument.AGE_ARG);
        int age = Integer.parseInt(inputAge);
        developer.setAge(age);

        String programmingLang = arguments.getHashMap().get(RequestArgument.PROGRAMMING_LANGUAGE_ARG);
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
