package org.example.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.example.model.Developer.*;
import static org.example.constant.RequestArgument.*;

@ExtendWith(MockitoExtension.class)
class JsonArgumentsConverterTest {

    private static final String HTTP_PATH = "/example";
    private static final String METHOD = "POST";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String AGE = "30";
    private static final String PROGRAMMING_LANGUAGE = "Java";
    @Mock
    private HttpServletRequest httpServletRequest;

    private final JsonToArgumentsConverter testInstance = JsonToArgumentsConverter.getInstance();

    @Test
    void shouldConvert() {
        when(httpServletRequest.getRequestURI()).thenReturn(HTTP_PATH);
        when(httpServletRequest.getMethod()).thenReturn(METHOD);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.createObjectNode()
                .put(COLUMN_FIRST_NAME, FIRST_NAME)
                .put(COLUMN_LAST_NAME, LAST_NAME)
                .put(COLUMN_AGE, AGE)
                .put(COLUMN_PROGRAMMING_LANGUAGE, PROGRAMMING_LANGUAGE);

        // Mock the behavior of req.getReader()
        BufferedReader reader = new BufferedReader(new StringReader(jsonNode.toString()));
        try {
            when(httpServletRequest.getReader()).thenReturn(new BufferedReader(reader));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Arguments actual = testInstance.convert(httpServletRequest);

        assertThat(actual.getHashMap().get(RequestArgument.HTTP_PATH)).contains(HTTP_PATH);
        assertThat(actual.getHashMap().get(HTTP_METHOD)).contains(METHOD);
        assertThat(actual.getHashMap().get(FIRSTNAME)).contains(FIRST_NAME);
        assertThat(actual.getHashMap().get(LASTNAME)).contains(LAST_NAME);
        assertThat(actual.getHashMap().get(RequestArgument.AGE)).contains(AGE);
        assertThat(actual.getHashMap().get(RequestArgument.PROGRAMMING_LANGUAGE)).contains(PROGRAMMING_LANGUAGE);
    }
}

