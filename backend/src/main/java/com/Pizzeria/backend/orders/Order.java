package com.Pizzeria.backend.orders;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    public void setId(Long order_id) {
        this.orderId = order_id;
    }

    public Long getId() {
        return orderId;
    }

    @ElementCollection
    private List<Long> foodIds; // List of Food IDs
    private LocalDateTime dateTime;
    private long total;
    private long customer_id;
}
