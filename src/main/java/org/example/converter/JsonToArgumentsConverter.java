package org.example.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonToArgumentsConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonToArgumentsConverter.class);
    private static JsonToArgumentsConverter instance;

    public Arguments convert(HttpServletRequest req) throws IOException {
        String requestURI = req.getRequestURI();
        String requestHttpMethod = req.getMethod();

        Arguments arguments = new Arguments();

        arguments.getHashMap().put(RequestArgument.HTTP_PATH, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD, requestHttpMethod);

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader = req.getReader();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(stringBuilder.toString());

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
