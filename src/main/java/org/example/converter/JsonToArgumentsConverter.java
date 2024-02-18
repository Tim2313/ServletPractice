package org.example.converter;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.example.service.JsonService;

public class JsonToArgumentsConverter {

    private static final JsonService JSON_SERVICE = JsonService.getInstance();
    private static JsonToArgumentsConverter instance;

    private JsonToArgumentsConverter() {
    }

    public Arguments convert(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String requestHttpMethod = req.getMethod();

        Arguments arguments = new Arguments();

        arguments.getHashMap().put(RequestArgument.HTTP_PATH, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD, requestHttpMethod);


        JsonNode jsonNode = JSON_SERVICE.getNode(req);

        String firstName = jsonNode.get("firstName").asText();
        arguments.getHashMap().put(RequestArgument.FIRSTNAME, firstName);

        String lastName = jsonNode.get("lastName").asText();
        arguments.getHashMap().put(RequestArgument.LASTNAME, lastName);

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
