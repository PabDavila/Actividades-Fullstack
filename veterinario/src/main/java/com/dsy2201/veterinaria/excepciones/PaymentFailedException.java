package com.dsy2201.veterinaria.excepciones;


public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }
}