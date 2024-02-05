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

        arguments.getHashMap().put(RequestArgument.HTTP_PATH_ARG, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD_ARG, requestHttpMethod);

        String firstName = req.getParameter(RequestArgument.FIRSTNAME_ARG.getValue());
        arguments.getHashMap().put(RequestArgument.FIRSTNAME_ARG, firstName);

        String lastName = req.getParameter(RequestArgument.LASTNAME_ARG.getValue());
        arguments.getHashMap().put(RequestArgument.LASTNAME_ARG, lastName);

        String age = req.getParameter(RequestArgument.AGE_ARG.getValue());
        arguments.getHashMap().put(RequestArgument.AGE_ARG, age);

        String programmingLanguage = req.getParameter(RequestArgument.PROGRAMMING_LANGUAGE_ARG.getValue());
        arguments.getHashMap().put(RequestArgument.PROGRAMMING_LANGUAGE_ARG, programmingLanguage);

        return arguments;
    }

    public static RequestParametersToArguments getInstance() {
        if (instance == null) {
            instance = new RequestParametersToArguments();
        }
        return instance;
    }
}
