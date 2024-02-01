package org.example.service;

import com.google.gson.Gson;
import org.example.model.Developer;

import java.util.List;


public class JsonService {
    private static JsonService instance;

    private static final Gson GSON = new Gson();

    public String getDevelopers(List<Developer> developerList) {
        return GSON.toJson(developerList);
    }

    public static JsonService getInstance() {
        if (instance == null) {
            instance = new JsonService();
        }
        return instance;
    }
}
