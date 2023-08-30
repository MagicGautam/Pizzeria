package com.Pizzeria.backend.Inventory.FoodItem;

import com.Pizzeria.backend.Inventory.Ingregient.Ingredient;
import com.Pizzeria.backend.Inventory.Ingregient.IngredientRepository;
import com.Pizzeria.backend.errors.InsufficientStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository, IngredientRepository ingredientRepository) {
        this.foodItemRepository = foodItemRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public FoodItemDto getFoodItem(Long foodId) {
        FoodItem foodItem = foodItemRepository.findByFoodId(foodId);
        return mapToDto(foodItem);
    }

    public List<FoodItemDto> getMenu() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        return foodItems.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void addNewFoodItem(FoodItemDto foodItemDto) {
        FoodItem foodItem = mapToEntity(foodItemDto);
        foodItemRepository.save(foodItem);
    }

    public void deleteFoodItem(Long foodId) {
        boolean exists = foodItemRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food item with id " + foodId + " does not exist");
        }
        foodItemRepository.deleteById(foodId);
    }

    public void updateFoodItem(Long foodId, FoodItemDto foodItemDto) {
        boolean exists = foodItemRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food item with id " + foodId + " does not exist");
        }

        FoodItem foodItem = mapToEntity(foodItemDto);
        foodItem.setFood_Id(foodId);
        foodItemRepository.save(foodItem);
    }

    public void reduceIngredientsFromInventory(FoodItemDto foodItemDto) {
        FoodItem foodItem = mapToEntity(foodItemDto);
        List<Ingredient> ingredients = foodItem.getIngredients();

        for (Ingredient ingredient : ingredients) {
            int currentStock = ingredient.getStock();
            int requiredQuantity = 1; // Assuming one food item reduces one ingredient quantity

            if (currentStock >= requiredQuantity) {
                ingredient.setStock(currentStock - requiredQuantity);
                ingredientRepository.save(ingredient);
            } else {
                throw new InsufficientStockException("Insufficient stock for ingredient: " + ingredient.getName());
                // Handle insufficient stock scenario
            }
        }
    }

    private FoodItemDto mapToDto(FoodItem foodItem) {
        FoodItemDto dto = new FoodItemDto();
        dto.setFoodId(foodItem.getFood_Id());
        dto.setName(foodItem.getName());
        dto.setPrice(foodItem.getPrice());
        dto.setDescription(foodItem.getDescription());
        dto.setCategory(foodItem.getCategory());
        dto.setImageUrl(foodItem.getImageUrl());
        return dto;
    }

    private FoodItem mapToEntity(FoodItemDto dto) {
        FoodItem foodItem = new FoodItem();
        foodItem.setFood_Id(dto.getFoodId());
        foodItem.setName(dto.getName());
        foodItem.setPrice((long) dto.getPrice());
        foodItem.setDescription(dto.getDescription());
        foodItem.setCategory(dto.getCategory());
        foodItem.setImageUrl(dto.getImageUrl());
        return foodItem;
    }

}
