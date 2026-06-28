package com.sneaker.marketplace.repository;

import com.sneaker.marketplace.entity.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
    List<Sneaker> findByBrand(String brand);
    List<Sneaker> findByCategory(String category);
    List<Sneaker> findByNameContainingIgnoreCase(String name);
}