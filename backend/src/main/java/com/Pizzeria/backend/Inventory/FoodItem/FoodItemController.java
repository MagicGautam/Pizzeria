package com.Pizzeria.backend.Inventory.FoodItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/food-items")
public class FoodItemController {

    private final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodItem> getFoodItem(@PathVariable Long foodId) {
        FoodItem foodItem = foodItemService.getFoodItem(foodId);
        return ResponseEntity.ok(foodItem);
    }

    @PostMapping("/addFoodItem")
    public ResponseEntity<Void> addNewFoodItem(@RequestBody FoodItem foodItem) {
        foodItemService.addNewFoodItem(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<Void> updateFoodItem(@PathVariable Long foodId, @RequestBody FoodItem foodItem) {
        foodItemService.updateFoodItem(foodId, foodItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long foodId) {
        foodItemService.deleteFoodItem(foodId);
        return ResponseEntity.ok().build();
    }
}