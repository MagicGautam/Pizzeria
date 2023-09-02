package com.Pizzeria.backend.orders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
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
