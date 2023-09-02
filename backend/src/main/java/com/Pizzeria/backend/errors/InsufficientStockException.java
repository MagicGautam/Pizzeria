package com.Pizzeria.backend.errors;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String ingredientName) {
        super("Insufficient stock for ingredient: " + ingredientName);
    }
}