package com.Pizzeria.backend.Inventory.FoodItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu/")
public class FoodController {
    private final FoodItemService foodItemService;

    @Autowired
    public FoodController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("/item/{foodId}")
    public ResponseEntity<FoodItemDto> getFoodItem(@PathVariable Long foodId) {
        FoodItemDto foodItemDto = foodItemService.getFoodItemDTO(foodId);
        return ResponseEntity.ok(foodItemDto);
    }

    @GetMapping
    public ResponseEntity<List<FoodItemDto>> getMenu() {
        List<FoodItemDto> foodItems = foodItemService.getMenu();
        return ResponseEntity.ok(foodItems);
    }

}
