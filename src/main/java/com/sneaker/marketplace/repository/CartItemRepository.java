package com.sneaker.marketplace.repository;

import com.sneaker.marketplace.entity.CartItem;
import com.sneaker.marketplace.entity.User;
import com.sneaker.marketplace.entity.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndSneaker(User user, Sneaker sneaker);
    void deleteByUser(User user);
}