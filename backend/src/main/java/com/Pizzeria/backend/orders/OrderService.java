package com.Pizzeria.backend.orders;

import com.Pizzeria.backend.Finances.Finance;
import com.Pizzeria.backend.Finances.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FinanceService financeService;
    @Autowired
    public OrderService(OrderRepository orderRepository, FinanceService financeService) {
        this.orderRepository = orderRepository;
        this.financeService = financeService;
    }

    public Order getOrder(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        return order;
    }

    public void addNewOrder(Order order) {
        Order savedOrder = orderRepository.save(order); // Save the order and get the saved instance
        long price = savedOrder.getTotal();
        Finance finance = Finance.builder()
                .transactionId(savedOrder.getOrderId()) // Use the generated orderId from the saved order
                .transactionDate(java.time.LocalDateTime.now())
                .transactionType("Order Sale")
                .amount(price)
                .build();
        financeService.addFinance(finance);
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
