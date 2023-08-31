package com.Pizzeria.backend.Inventory.Ingregient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient getIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);
        return ingredient;
    }
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient addNewIngredient(Ingredient ingredient) {
       return ingredientRepository.save(ingredient);
    }

    public void restock (Long ingredientId, int amount) {
        Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);
        int currentStock = ingredient.getStock();
        double price= (ingredient.getPrice())*amount;
        ingredient.setStock(currentStock + amount);
        ingredientRepository.save(ingredient);

    }

    public void restockAll(long amount) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        double price=0;
        for (Ingredient ingredient : ingredients) {
            int currentStock = ingredient.getStock();
            ingredient.setStock(currentStock + (int) amount);
            price=+(ingredient.getPrice())*amount;
            ingredientRepository.save(ingredient);
        }
    }

    public void deleteIngredient(Long ingredientId) {
        boolean exists = ingredientRepository.existsById(ingredientId);
        if (!exists) {
            throw new IllegalStateException("Ingredient with id " + ingredientId + " does not exist");
        }
        ingredientRepository.deleteById(ingredientId);
    }

    public Ingredient updateIngredient(Ingredient ingredient){
        long ingredientId=ingredient.getIngredientId();
        boolean exists = ingredientRepository.existsById(ingredientId);
        if (!exists) {
            throw new IllegalStateException("Ingredient with id " + ingredientId + " does not exist");
        }
        return ingredientRepository.save(ingredient);
    }

}
