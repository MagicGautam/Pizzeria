package com.Pizzeria.backend.orders;

import com.Pizzeria.backend.Inventory.FoodItem.FoodItem;
import com.Pizzeria.backend.Inventory.FoodItem.FoodItemDto;
import com.Pizzeria.backend.Inventory.FoodItem.FoodItemService;
import com.Pizzeria.backend.errors.IngredientNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    private final FoodItemService foodItemService;

    public OrderController(OrderService orderService, FoodItemService foodItemService) {
        this.orderService = orderService;
        this.foodItemService = foodItemService;
    }

    @PostMapping(path = "/add")
    public void addNewOrder(@RequestBody Order order) throws IngredientNotFoundException {
        List<Long> foodids= order.getFoodIds();
        for (Long foodid : foodids) {
            FoodItem foodItem = foodItemService.getFoodItem(foodid);
            foodItemService.reduceIngredientsFromInventory(foodItem);
        }
        orderService.addNewOrder(order);
    }

    @DeleteMapping(path = "/delete/{orderId}")
    public void deleteOrder(@RequestParam Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping(path = "/update")
    public void updateOrder(@RequestParam Order order) {
        orderService.updateOrder(order);
    }

    @GetMapping(path = "/get/{orderId}")
    public Order getOrder(@RequestParam Long orderId) {
        return orderService.getOrder(orderId);
    }
}
