package com.Pizzeria.backend.Inventory.Ingregient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class FoodItemIngredientDto {
    private String name;
    private int quantity; // The quantity of this ingredient used in the food item
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}