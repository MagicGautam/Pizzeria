package com.Pizzeria.backend.Inventory.FoodItem;

import lombok.Data;

import java.util.List;

@Data
public class FoodItemDto {
    private Long foodId;
    private String name;
    private long price;
    private String description;
    private String category;
    private List<String> imageUrl; // You can use a single URL or a List<String> if multiple images are possible
}