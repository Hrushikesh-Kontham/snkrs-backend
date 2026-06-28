package com.sneaker.marketplace.repository;

import com.sneaker.marketplace.entity.Order;
import com.sneaker.marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByCreatedAtDesc(User user);
}