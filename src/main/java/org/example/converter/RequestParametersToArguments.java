package org.example.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.example.model.Developer;

public class RequestParametersToArguments {
    private static RequestParametersToArguments instance;

    public Arguments convert(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String requestHttpMethod = req.getMethod();

        Arguments arguments = new Arguments();

        arguments.getHashMap().put(RequestArgument.HTTP_PATH, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD, requestHttpMethod);

        String firstName = req.getParameter(Developer.COLUMN_NAME_FIRST_NAME);
        arguments.getHashMap().put(RequestArgument.FIRST_NAME, firstName);

        String lastName = req.getParameter(Developer.COLUMN_NAME_SECOND_NAME);
        arguments.getHashMap().put(RequestArgument.LASTNAME, lastName);

        String age = req.getParameter(Developer.COLUMN_NAME_AGE);
        arguments.getHashMap().put(RequestArgument.AGE, age);

        String programmingLanguage = req.getParameter(Developer.COLUMN_NAME_PROGRAMMING_LANGUAGE);
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
