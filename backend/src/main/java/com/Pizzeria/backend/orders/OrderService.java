package com.Pizzeria.backend.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrder(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        return order;
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("Order with id " + orderId + " does not exist");
        }
        orderRepository.deleteById(orderId);
    }

    public void updateOrder(Order order) {
        long orderId=order.getOrderId();
        boolean exists = orderRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("Order with id " + orderId + " does not exist");
        }
        orderRepository.save(order);
    }


}
