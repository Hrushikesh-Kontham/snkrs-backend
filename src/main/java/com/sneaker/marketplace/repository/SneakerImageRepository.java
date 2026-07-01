package com.sneaker.marketplace.repository;

import com.sneaker.marketplace.entity.Sneaker;
import com.sneaker.marketplace.entity.SneakerImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SneakerImageRepository extends JpaRepository<SneakerImage, Long> {
    void deleteBySneaker(Sneaker sneaker);
}