package com.Pizzeria.backend.orders;

import com.Pizzeria.backend.Inventory.FoodItem;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Data
public class Order {

    @Id
    private long orderId;

    public void setId(Long order_id) {
        this.orderId = order_id;
    }

    public Long getId() {
        return orderId;
    }
    private long foodId;

    @ElementCollection
    private List<Long> foodIds; // List of Food IDs
    private LocalDateTime dateTime;
    private long total;

}
