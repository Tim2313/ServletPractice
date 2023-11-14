package org.example.model;

import org.example.constant.RequestArguments;

import java.util.HashMap;
import java.util.Map;

public class Arguments {

   private final Map<RequestArguments, String> hashMap = new HashMap<>();

    public Map<RequestArguments, String> getHashMap() {
        return hashMap;
    }
}
