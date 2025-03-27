package com.dsy2201.veterinaria.excepciones;


public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}