package com.Pizzeria.backend.Inventory.FoodItem;

import com.Pizzeria.backend.Inventory.Ingregient.FoodItemIngredientDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    private String name;
    private long price;
    private String description;
    private String category;

    @ElementCollection
    private List<String> imageUrl;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodItemIngredientDto> ingredients;

    public void setFood_Id(Long foodId) {
        this.foodId = foodId;
    }
    public Long getFood_Id() {
        return foodId;
    }
}
