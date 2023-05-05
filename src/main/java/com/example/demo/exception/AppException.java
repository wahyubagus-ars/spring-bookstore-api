package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException{
    private static final long serialVersionUID = 8082319414278002853L;

    private String responseKey;
    private String responseMessage;

    public AppException(String responseKey, String responseMessage) {
        super();
        this.responseKey = responseKey;
        this.responseMessage = responseMessage;
    }
}
