package org.example.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

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
        Mockito.when(httpServletRequest.getParameter(RequestArgument.FIRSTNAME.getRequestArgument())).thenReturn(FIRST_NAME);
        Mockito.when(httpServletRequest.getParameter(RequestArgument.LASTNAME.getRequestArgument())).thenReturn(LAST_NAME);
        Mockito.when(httpServletRequest.getParameter(RequestArgument.AGE.getRequestArgument())).thenReturn(AGE);
        Mockito.when(httpServletRequest.getParameter(RequestArgument.PROGRAMMING_LANGUAGE.getRequestArgument())).thenReturn(PROGRAMMING_LANGUAGE);

        Arguments actual = testInstance.convert(httpServletRequest);

        assertThat(actual.getHashMap().get(RequestArgument.FIRSTNAME)).contains(FIRST_NAME);
        assertThat(actual.getHashMap().get(RequestArgument.LASTNAME)).contains(LAST_NAME);
        assertThat(actual.getHashMap().get(RequestArgument.AGE)).contains(AGE);
        assertThat(actual.getHashMap().get(RequestArgument.PROGRAMMING_LANGUAGE)).contains(PROGRAMMING_LANGUAGE);
    }
}
