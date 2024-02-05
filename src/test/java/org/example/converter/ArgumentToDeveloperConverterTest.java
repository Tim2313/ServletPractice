package org.example.converter;

import org.assertj.core.api.Assertions;
import org.example.model.Arguments;
import org.example.model.Developer;

import org.junit.jupiter.api.Test;

import static org.example.constant.RequestArgument.*;

class ArgumentToDeveloperConverterTest {
    private static final String FIRST_NAME = "Tima";
    private static final String LAST_NAME = "Java";
    private static final String AGE_STRING = "27";
    private static final int AGE_NUMBER = 27;
    private static final String PROGRAMMING_LANGUAGE = "Java";
    private final ArgumentToDeveloperConverter testInstance = ArgumentToDeveloperConverter.getInstance();

    @Test
    void shouldConvert() {
        Arguments arguments = new Arguments();

        arguments.getHashMap().put(FIRSTNAME_ARG, FIRST_NAME);
        arguments.getHashMap().put(LASTNAME_ARG, LAST_NAME);
        arguments.getHashMap().put(AGE_ARG, AGE_STRING);
        arguments.getHashMap().put(PROGRAMMING_LANGUAGE_ARG, PROGRAMMING_LANGUAGE);

        Developer actual = testInstance.convert(arguments);

        Assertions.assertThat(actual.getFirstName()).isEqualTo(FIRST_NAME);
        Assertions.assertThat(actual.getLastName()).isEqualTo(LAST_NAME);
        Assertions.assertThat(actual.getAge()).isEqualTo(AGE_NUMBER);
        Assertions.assertThat(actual.getProgrammingLanguage()).isEqualTo(PROGRAMMING_LANGUAGE);
    }
}
