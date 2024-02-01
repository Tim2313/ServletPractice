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

        arguments.getHashMap().put(RequestArgument.HTTP_PATH, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD, requestHttpMethod);


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(req.getReader());
        } catch (IOException e) {
            throw new RuntimeException();
        }

            String firstName = jsonNode.get("firstName").asText();
            arguments.getHashMap().put(RequestArgument.FIRSTNAME, firstName);

            String secondName = jsonNode.get("secondName").asText();
            arguments.getHashMap().put(RequestArgument.LASTNAME, secondName);

            String age = jsonNode.get("age").asText();
            arguments.getHashMap().put(RequestArgument.AGE, age);

            String programmingLanguage = jsonNode.get("programmingLanguage").asText();
            arguments.getHashMap().put(RequestArgument.PROGRAMMING_LANGUAGE, programmingLanguage);


        return arguments;
    }

    public static JsonToArgumentsConverter getInstance() {
        if (instance == null) {
            instance = new JsonToArgumentsConverter();
        }
        return instance;
    }
}
