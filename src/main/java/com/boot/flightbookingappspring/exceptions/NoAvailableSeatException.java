package com.boot.flightbookingappspring.exceptions;

public class NoAvailableSeatException extends CustomValidationException{
    public NoAvailableSeatException(String code, String message) {
        super(code, message);
    }
}
