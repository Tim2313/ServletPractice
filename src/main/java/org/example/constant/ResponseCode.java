package org.example.constant;

public enum ResponseCode {

    HTTP_OK(200), HTTP_NOT_FOUND(404);

    private final int value;

    ResponseCode(int responseCodes) {
        this.value = responseCodes;
    }

    public int getValue() {
        return value;
    }

}
