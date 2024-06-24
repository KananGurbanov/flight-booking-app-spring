package com.boot.flightbookingappspring.exceptions;

public class RecordNotFoundException extends CustomValidationException {
    public RecordNotFoundException(String code, String message) {
        super(code, message);
    }
}
