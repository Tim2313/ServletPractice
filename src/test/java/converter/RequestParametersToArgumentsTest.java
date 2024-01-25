package converter;

import jakarta.servlet.http.HttpServletRequest;
import org.example.constant.RequestArgument;
import org.example.converter.RequestParametersToArguments;
import org.example.model.Arguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RequestParametersToArgumentsTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Test
    void shouldConvert() {

        Mockito.when(httpServletRequest.getParameter(RequestArgument.FIRSTNAME.getRequestArgument())).thenReturn("Jonh");
        Mockito.when(httpServletRequest.getParameter(RequestArgument.LASTNAME.getRequestArgument())).thenReturn("Jakik");
        Mockito.when(httpServletRequest.getParameter(RequestArgument.AGE.getRequestArgument())).thenReturn("23");
        Mockito.when(httpServletRequest.getParameter(RequestArgument.PROGRAMMING_LANGUAGE.getRequestArgument())).thenReturn("Java");

        RequestParametersToArguments requestParametersToArguments = new RequestParametersToArguments();

        Arguments arguments = requestParametersToArguments.convert(httpServletRequest);

        Assertions.assertEquals("Jonh", arguments.getHashMap().get(RequestArgument.FIRSTNAME));
        Assertions.assertEquals("Jakik", arguments.getHashMap().get(RequestArgument.LASTNAME));
        Assertions.assertEquals("23", arguments.getHashMap().get(RequestArgument.AGE));
        Assertions.assertEquals("Java", arguments.getHashMap().get(RequestArgument.PROGRAMMING_LANGUAGE));
    }
}
