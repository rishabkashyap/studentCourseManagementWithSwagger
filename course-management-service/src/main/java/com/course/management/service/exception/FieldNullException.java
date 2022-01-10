package com.course.management.service.exception;

public class FieldNullException extends RuntimeException {
    public FieldNullException(String errorMessage) {
        super(errorMessage);
    }
}
