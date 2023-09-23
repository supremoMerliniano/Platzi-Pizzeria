package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();

        orders.forEach(order -> System.out.println(order.getCustomer().getName()));

        return orders;
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);

        return this.orderRepository.findAllByDateBefore(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);

        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer) {
        return this.orderRepository.findCustomerOrders(idCustomer);
    }
}
