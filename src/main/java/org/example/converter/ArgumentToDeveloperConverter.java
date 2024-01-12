package org.example.converter;

import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.example.model.Developer;
import org.example.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArgumentToDeveloperConverter {

    private static ArgumentToDeveloperConverter instance;
    private static final Logger LOGGER = LoggerFactory.getLogger(ArgumentToDeveloperConverter.class);

    public Developer convert(Arguments arguments) {

        Developer developer = new Developer();

        String firstName = arguments.getHashMap().get(RequestArgument.FIRSTNAME);
        developer.setFirstName(firstName);

        String secondName = arguments.getHashMap().get(RequestArgument.LASTNAME);
        developer.setSecondName(secondName);

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
