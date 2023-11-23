package org.example.model;

import org.example.constant.RequestArgument;

import java.util.HashMap;
import java.util.Map;

public class Arguments {

   private final Map<RequestArgument, String> hashMap = new HashMap<>();

    public Map<RequestArgument, String> getHashMap() {
        return hashMap;
    }

}
