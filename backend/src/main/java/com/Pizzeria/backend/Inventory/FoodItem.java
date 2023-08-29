package com.Pizzeria.backend.Inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class FoodItem {
    @Id
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
    private List<String> imageUrl;
}
