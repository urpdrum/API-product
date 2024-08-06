package com.example.springboot.repositories;

import com.example.springboot.models.OrderModel;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderId(Long orderId);
    Order findByOrderCode(String orderCode);

    List<OrderModel> List();
}
