package com.sneaker.marketplace.repository;

import com.sneaker.marketplace.entity.Wishlist;
import com.sneaker.marketplace.entity.User;
import com.sneaker.marketplace.entity.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUser(User user);
    Optional<Wishlist> findByUserAndSneaker(User user, Sneaker sneaker);
    void deleteByUserAndSneaker(User user, Sneaker sneaker);
}