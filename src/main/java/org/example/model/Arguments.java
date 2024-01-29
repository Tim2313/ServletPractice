package org.example.model;

import org.example.constant.RequestArgument;

import java.util.EnumMap;
import java.util.Map;

public class Arguments {

    private final Map<RequestArgument, String> hashMap = new EnumMap<>(RequestArgument.class);

    public Map<RequestArgument, String> getHashMap() {
        return hashMap;
    }
}
