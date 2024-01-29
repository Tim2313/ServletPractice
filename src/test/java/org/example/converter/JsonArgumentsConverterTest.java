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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JsonArgumentsConverterTest {

    @Mock
    HttpServletRequest httpServletRequest;
    @Mock
    JsonToArgumentsConverter jsonToArgumentsConverter;

    @Test
    void shouldConvert() {
        when(httpServletRequest.getRequestURI()).thenReturn("/example");
        when(httpServletRequest.getMethod()).thenReturn("POST");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.createObjectNode()
                .put("firstName", "John")
                .put("secondName", "Doe")
                .put("age", "30")
                .put("programmingLanguage", "Java");

        // Mock the behavior of req.getReader()
        BufferedReader reader = new BufferedReader(new StringReader(jsonNode.toString()));
        try {
            when(httpServletRequest.getReader()).thenReturn(new BufferedReader(reader));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        jsonToArgumentsConverter = JsonToArgumentsConverter.getInstance();

        Arguments argumentsActual = jsonToArgumentsConverter.convert(httpServletRequest);

        assertEquals("/example", argumentsActual.getHashMap().get(RequestArgument.HTTP_PATH));
        assertEquals("POST", argumentsActual.getHashMap().get(RequestArgument.HTTP_METHOD));
        assertEquals("John", argumentsActual.getHashMap().get(RequestArgument.FIRSTNAME));
        assertEquals("Doe", argumentsActual.getHashMap().get(RequestArgument.LASTNAME));
        assertEquals("30", argumentsActual.getHashMap().get(RequestArgument.AGE));
        assertEquals("Java", argumentsActual.getHashMap().get(RequestArgument.PROGRAMMING_LANGUAGE));
    }
}

