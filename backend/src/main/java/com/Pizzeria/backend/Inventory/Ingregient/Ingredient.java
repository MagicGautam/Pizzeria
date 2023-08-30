package com.Pizzeria.backend.Inventory.Ingregient;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Ingredient {
    @Id
    private Long ingredientId;

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }
    private String name;
    private double price; // Price per unit
    private int stock; // Number of units in stock

}
