package com.steelslayer.application.ordersservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.steelslayer.application.ordersservice.entity.OrderItem;

public interface OrderRepository extends JpaRepository<OrderItem, Long> {

}
