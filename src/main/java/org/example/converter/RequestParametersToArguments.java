package org.example.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;

public class RequestParametersToArguments {
    private static RequestParametersToArguments instance;

    private RequestParametersToArguments() {
    }

    public Arguments convert(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String requestHttpMethod = req.getMethod();

        Arguments arguments = new Arguments();

        arguments.getHashMap().put(RequestArgument.HTTP_PATH, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD, requestHttpMethod);

        String firstName = req.getParameter(RequestArgument.FIRSTNAME.getRequestArgument());
        arguments.getHashMap().put(RequestArgument.FIRSTNAME, firstName);

        String lastName = req.getParameter(RequestArgument.LASTNAME.getRequestArgument());
        arguments.getHashMap().put(RequestArgument.LASTNAME, lastName);

        String age = req.getParameter(RequestArgument.AGE.getRequestArgument());
        arguments.getHashMap().put(RequestArgument.AGE, age);

        String programmingLanguage = req.getParameter(RequestArgument.PROGRAMMING_LANGUAGE.getRequestArgument());
        arguments.getHashMap().put(RequestArgument.PROGRAMMING_LANGUAGE, programmingLanguage);

        return arguments;
    }

    public static RequestParametersToArguments getInstance() {
        if (instance == null) {
            instance = new RequestParametersToArguments();
        }
        return instance;
    }
}
