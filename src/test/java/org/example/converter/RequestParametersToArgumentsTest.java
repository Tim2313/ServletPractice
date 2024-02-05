package org.example.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.api.Assertions;
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
        when(httpServletRequest.getParameter(FIRSTNAME_ARG.getValue())).thenReturn(FIRST_NAME);
        when(httpServletRequest.getParameter(LASTNAME_ARG.getValue())).thenReturn(LAST_NAME);
        when(httpServletRequest.getParameter(AGE_ARG.getValue())).thenReturn(AGE);
        when(httpServletRequest.getParameter(PROGRAMMING_LANGUAGE_ARG.getValue())).thenReturn(PROGRAMMING_LANGUAGE);

        Arguments actual = testInstance.convert(httpServletRequest);

        Assertions.assertThat(actual.getHashMap())
                .containsEntry(FIRSTNAME_ARG, FIRST_NAME)
                .containsEntry(LASTNAME_ARG, LAST_NAME)
                .containsEntry(AGE_ARG, AGE)
                .containsEntry(PROGRAMMING_LANGUAGE_ARG, PROGRAMMING_LANGUAGE);
    }
}
