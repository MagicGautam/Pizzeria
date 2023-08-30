package com.Pizzeria.backend.Inventory.Ingregient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory/ingredient")
public class IngredientController {

    public final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/getIngredient")
    public Ingredient getIngredient(Long ingredientId) {
        return ingredientService.getIngredient(ingredientId);
    }


}
