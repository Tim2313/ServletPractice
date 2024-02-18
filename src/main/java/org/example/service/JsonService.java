package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.example.model.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;


public class JsonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonService.class);
    private static JsonService instance;

    private static final Gson GSON = new Gson();

    public String getDevelopers(List<Developer> developerList) {
        return GSON.toJson(developerList);
    }

    public JsonNode getNode(HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = null;

        try {
            jsonNode = objectMapper.readTree(request.getReader());
        } catch (IOException e) {
            LOGGER.error("Object jsonNode is not not valid: {}", e.getMessage());
        }

        return jsonNode;
    }

    public static JsonService getInstance() {
        if (instance == null) {
            instance = new JsonService();
        }
        return instance;
    }
}
