package org.example.constant;

public enum ResponseCodes {

    HTTP_OK(200), HTTP_NOT_FOUND(404);

    private final int responseCodes;

    ResponseCodes(int responseCodes) {
        this.responseCodes = responseCodes;
    }

    public int getResponseCodes() {
        return responseCodes;
    }

}
