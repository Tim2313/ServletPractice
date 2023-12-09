package converter;

import org.assertj.core.api.Assertions;
import org.example.constant.RequestArgument;
import org.example.converter.ArgumentToDeveloperConverter;
import org.example.model.Arguments;
import org.example.model.Developer;

import org.junit.jupiter.api.Test;

public class ArgumentToDeveloperConverterTest {
    private static final String FIRST_NAME = "Tima";
    private static final String SECOND_NAME = "Jav";
    private static final String AGE_STRING = "27";
    private static final int AGE_NUMBER = 27;
    private static final String PROGRAMMING_LANGUAGE = "Java";

    private static final ArgumentToDeveloperConverter argumentToDeveloperConverter = new ArgumentToDeveloperConverter();


    @Test
    public void shouldConvert() {
        Arguments arguments = new Arguments();

        arguments.getHashMap().put(RequestArgument.FIRST_NAME, FIRST_NAME);
        arguments.getHashMap().put(RequestArgument.LASTNAME, SECOND_NAME);
        arguments.getHashMap().put(RequestArgument.AGE, AGE_STRING);
        arguments.getHashMap().put(RequestArgument.PROGRAMMING_LANGUAGE, PROGRAMMING_LANGUAGE);

        Developer actualDeveloper = argumentToDeveloperConverter.convert(arguments);

        Assertions.assertThat(actualDeveloper.getFirstName()).isEqualTo(FIRST_NAME);
        Assertions.assertThat(actualDeveloper.getSecondName()).isEqualTo(SECOND_NAME);
        Assertions.assertThat(actualDeveloper.getAge()).isEqualTo(AGE_NUMBER);
        Assertions.assertThat(actualDeveloper.getProgrammingLanguage()).isEqualTo(PROGRAMMING_LANGUAGE);
    }
}
