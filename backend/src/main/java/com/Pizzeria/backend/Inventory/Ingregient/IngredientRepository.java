package com.Pizzeria.backend.Inventory.Ingregient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByIngredientId(Long ingredientId);

    Ingredient findByName(String name);
}