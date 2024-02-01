package org.example.web;

import org.example.constant.RequestArgument;
import org.example.constant.HttpMapping;
import org.example.model.Arguments;
import org.example.model.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;


class PathMapperTest {

    private static final String METHOD = "GET";

    private final HttpMapper testInstance = HttpMapper.getInstance();

    private static Function<Arguments, Response> method;

    private static Arguments arguments;

    private static Response response;

    @BeforeAll
    static void setup() {
        arguments = new Arguments();
        arguments.getHashMap().put(RequestArgument.HTTP_PATH, HttpMapping.GET_DEVELOPERS_REST.getUrl());
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD, METHOD);

        response = new Response();

        method = (arguments) -> response;
    }

    @BeforeEach
    void init() {
        testInstance.clearMapping();
    }

    @Test
    void shouldGetResponse() {
        testInstance.addMapping(HttpMapping.GET_DEVELOPERS_REST, method);

        Response actualResponse = testInstance.getResponse(arguments);

        assertThat(actualResponse).isEqualTo(response);
    }

    @Test
    void shouldNotGetResponse() {
        testInstance.addMapping(HttpMapping.NOT_FOUND_HTML, method);

        Response actualResponse = testInstance.getResponse(arguments);

        assertThat(actualResponse).isEqualTo(response);
    }

}
