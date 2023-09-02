package com.Pizzeria.backend.Inventory.FoodItem;

import com.Pizzeria.backend.Inventory.Ingregient.FoodItemIngredientDto;
import com.Pizzeria.backend.Inventory.Ingregient.Ingredient;
import com.Pizzeria.backend.Inventory.Ingregient.IngredientRepository;
import com.Pizzeria.backend.errors.IngredientNotFoundException;
import com.Pizzeria.backend.errors.InsufficientStockException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public FoodItemDto getFoodItemDTO(Long foodId) {
        FoodItem foodItem = foodItemRepository.findByFoodId(foodId);
        return mapToDto(foodItem);
    }

    public FoodItem getFoodItem(Long foodId) {
        FoodItem foodItem = foodItemRepository.findByFoodId(foodId);
        return foodItem;
    }

    public List<FoodItemDto> getMenu() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        return foodItems.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void addNewFoodItem(FoodItem foodItem) {
        List<FoodItemIngredientDto> ingredients = foodItem.getIngredients();
        List<FoodItemIngredientDto> updatedIngredients = new ArrayList<>();

        for (FoodItemIngredientDto ingredientDto : ingredients) {
            Long ingredientId = ingredientDto.getIngredientId();
            int quantity = ingredientDto.getQuantity();

            Ingredient ingredient = ingredientRepository.findById(ingredientId)
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

            FoodItemIngredientDto updatedIngredientDto = new FoodItemIngredientDto();
            updatedIngredientDto.setIngredientId(ingredientId);
            updatedIngredientDto.setQuantity(quantity);

            updatedIngredients.add(updatedIngredientDto);
        }

        foodItem.setIngredients(updatedIngredients);
        foodItemRepository.save(foodItem);
    }

    public void deleteFoodItem(Long foodId) {
        boolean exists = foodItemRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food item with id " + foodId + " does not exist");
        }
        foodItemRepository.deleteById(foodId);
    }

    public void updateFoodItem(Long foodId, FoodItem foodItem) {
        boolean exists = foodItemRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food item with id " + foodId + " does not exist");
        }

        foodItem.setFood_Id(foodId);
        foodItemRepository.save(foodItem);
    }

    public void reduceIngredientsFromInventory(FoodItem foodItem) throws IngredientNotFoundException {
        List<FoodItemIngredientDto> foodItemIngredientDtos = foodItem.getIngredients();

        for (FoodItemIngredientDto foodItemIngredientDto : foodItemIngredientDtos) {
            Long ingredientId = foodItemIngredientDto.getIngredientId();
            int requiredQuantity = foodItemIngredientDto.getQuantity(); // Use the quantity from the DTO

            // Fetch the ingredient from the database
            Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);

            if (ingredient != null) {
                int currentStock = ingredient.getStock();

                if (currentStock >= requiredQuantity) {
                    ingredient.setStock(currentStock - requiredQuantity);
                    ingredientRepository.save(ingredient);
                } else {
                    throw new InsufficientStockException("Insufficient stock for ingredient: " + ingredient.getName());
                    // Handle insufficient stock scenario
                }
            } else {
                throw new IngredientNotFoundException("Ingredient not found with ID: " + ingredientId);
                // Handle ingredient not found scenario
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
