package com.hotelres.inventory;

public class NoInventoryException extends RuntimeException {
    public NoInventoryException(String message) {
        super(message);
    }
}
