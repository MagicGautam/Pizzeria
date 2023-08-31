package com.Pizzeria.backend.Inventory.Ingregient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/ingredient")
public class IngredientController {

    public final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/getIngredient")
    public Ingredient getIngredient(@PathVariable Long ingredientId) {
        return ingredientService.getIngredient(ingredientId);
    }
    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getIngredients();
    }
    @PostMapping("/addIngredient")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addNewIngredient(ingredient);
    return ResponseEntity.ok(ingredient);
    }
    @PostMapping("/restock")
    public void restock(@RequestParam Long ingredientId,@RequestParam int amount) {
        ingredientService.restock(ingredientId, amount);
    }
    @PostMapping("/restockAll")
    public void restockAll(@RequestParam long amount) {
        ingredientService.restockAll(amount);
    }
    @DeleteMapping("/deleteIngredient")
    public void deleteIngredient(@PathVariable Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
    }
    @PutMapping("/updateIngredient")
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.updateIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }
}
