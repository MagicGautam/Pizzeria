package com.Pizzeria.backend.Inventory.Ingregient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addNewIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void restock (Long ingredientId, int amount) {
        Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);
        int currentStock = ingredient.getStock();
        ingredient.setStock(currentStock + amount);
        ingredientRepository.save(ingredient);
    }

    public void restockAll(long amount) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        for (Ingredient ingredient : ingredients) {
            int currentStock = ingredient.getStock();
            ingredient.setStock(currentStock + (int) amount);
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

    public void updateIngredient(Long ingredientId){
        boolean exists = ingredientRepository.existsById(ingredientId);
        if (!exists) {
            throw new IllegalStateException("Ingredient with id " + ingredientId + " does not exist");
        }
        Ingredient ingredient = ingredientRepository.findByIngredientId(ingredientId);
        ingredientRepository.save(ingredient);
    }

}
