package com.Pizzeria.backend.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
private final FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    //GET
    public FoodItem getFoodItem(Long foodId){
        return foodItemRepository.findByFoodId(foodId);
    }
    public List<FoodItem> getMenu(){
        return foodItemRepository.findAll();
    }


    //CREATE
    public void addNewFoodItem(FoodItem foodItem) {
        foodItemRepository.save(foodItem);
    }

    //DELETE
    public void deleteFoodItem(Long foodId) {
        boolean exists = foodItemRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food item with id " + foodId + " does not exist");
        }
        foodItemRepository.deleteById(foodId);
    }

    //UPDATE
    public void updateFoodItem(Long foodId, FoodItem foodItem) {
        boolean exists = foodItemRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food item with id " + foodId + " does not exist");
        }
        foodItem.setFood_Id(foodId);
        foodItemRepository.save(foodItem);
    }



}
