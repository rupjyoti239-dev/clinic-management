package com.example.clinic_management.exception;

public class ResourceAlreadyExistException extends  RuntimeException{
    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
