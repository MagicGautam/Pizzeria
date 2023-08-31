package com.Pizzeria.backend.Inventory.FoodItem;

import com.Pizzeria.backend.Inventory.Ingregient.FoodItemIngredientDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    public void setFood_Id(Long foodId) {
        this.foodId = foodId;
    }
    public Long getFood_Id() {
        return foodId;
    }
    private String name;
    private long price;
    private String description;
    private String category;
    @ElementCollection
    private List<String> imageUrl;
    @Transient
    private List<FoodItemIngredientDto> ingredients;
}
