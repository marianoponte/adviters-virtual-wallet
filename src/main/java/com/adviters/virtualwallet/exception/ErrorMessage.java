package com.adviters.virtualwallet.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {

    private int statusCode;
    private LocalDateTime timestamp;
    private String message;

    public ErrorMessage(int statusCode, LocalDateTime timestamp, String message) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
    }
}

