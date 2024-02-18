package org.example.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.api.Assertions;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.example.constant.RequestArgument.*;

@ExtendWith(MockitoExtension.class)
class RequestParametersToArgumentsTest {

    private static final String FIRST_NAME = "Tima";
    private static final String LAST_NAME = "Java";
    private static final String AGE = "27";
    private static final String PROGRAMMING_LANGUAGE = "Java";

    @Mock
    private HttpServletRequest httpServletRequest;

    private final RequestParametersToArguments testInstance = RequestParametersToArguments.getInstance();

    @Test
    void shouldConvert() {
        when(httpServletRequest.getParameter(FIRSTNAME.getValue())).thenReturn(FIRST_NAME);
        when(httpServletRequest.getParameter(LASTNAME.getValue())).thenReturn(LAST_NAME);
        when(httpServletRequest.getParameter(RequestArgument.AGE.getValue())).thenReturn(AGE);
        when(httpServletRequest.getParameter(RequestArgument.PROGRAMMING_LANGUAGE.getValue())).thenReturn(PROGRAMMING_LANGUAGE);

        Arguments actual = testInstance.convert(httpServletRequest);

        Assertions.assertThat(actual.getHashMap())
                .containsEntry(FIRSTNAME, FIRST_NAME)
                .containsEntry(LASTNAME, LAST_NAME)
                .containsEntry(RequestArgument.AGE, AGE)
                .containsEntry(RequestArgument.PROGRAMMING_LANGUAGE, PROGRAMMING_LANGUAGE);
    }
}
