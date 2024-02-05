package org.example.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;

import java.io.IOException;

public class JsonToArgumentsConverter {
    private static JsonToArgumentsConverter instance;

    private JsonToArgumentsConverter() {
    }

    public Arguments convert(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String requestHttpMethod = req.getMethod();

        Arguments arguments = new Arguments();

        arguments.getHashMap().put(RequestArgument.HTTP_PATH_ARG, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD_ARG, requestHttpMethod);


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(req.getReader());
        } catch (IOException e) {
            throw new RuntimeException();
        }

        String firstName = jsonNode.get("firstName").asText();
        arguments.getHashMap().put(RequestArgument.FIRSTNAME_ARG, firstName);

        String lastName = jsonNode.get("lastName").asText();
        arguments.getHashMap().put(RequestArgument.LASTNAME_ARG, lastName);

        String age = jsonNode.get("age").asText();
        arguments.getHashMap().put(RequestArgument.AGE_ARG, age);

        String programmingLanguage = jsonNode.get("programmingLanguage").asText();
        arguments.getHashMap().put(RequestArgument.PROGRAMMING_LANGUAGE_ARG, programmingLanguage);


        return arguments;
    }

    public static JsonToArgumentsConverter getInstance() {
        if (instance == null) {
            instance = new JsonToArgumentsConverter();
        }
        return instance;
    }
}
