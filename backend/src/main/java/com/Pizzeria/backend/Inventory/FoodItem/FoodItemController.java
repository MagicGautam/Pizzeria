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
    public ResponseEntity<FoodItemDto> getFoodItem(@PathVariable Long foodId) {
        FoodItemDto foodItemDto = foodItemService.getFoodItem(foodId);
        return ResponseEntity.ok(foodItemDto);
    }

    @GetMapping
    public ResponseEntity<List<FoodItemDto>> getMenu() {
        List<FoodItemDto> foodItems = foodItemService.getMenu();
        return ResponseEntity.ok(foodItems);
    }

    @PostMapping
    public ResponseEntity<Void> addNewFoodItem(@RequestBody FoodItemDto foodItemDto) {
        foodItemService.addNewFoodItem(foodItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<Void> updateFoodItem(@PathVariable Long foodId, @RequestBody FoodItemDto foodItemDto) {
        foodItemService.updateFoodItem(foodId, foodItemDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long foodId) {
        foodItemService.deleteFoodItem(foodId);
        return ResponseEntity.ok().build();
    }
}