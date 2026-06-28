package com.sneaker.marketplace.service;

import com.sneaker.marketplace.dto.SneakerRequest;
import com.sneaker.marketplace.entity.Sneaker;
import com.sneaker.marketplace.repository.SneakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SneakerService {

    private final SneakerRepository sneakerRepository;

    public List<Sneaker> getAllSneakers() {
        return sneakerRepository.findAll();
    }

    public Sneaker getSneakerById(Long id) {
        return sneakerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sneaker not found"));
    }

    public List<Sneaker> getSneakersByBrand(String brand) {
        return sneakerRepository.findByBrand(brand);
    }

    public List<Sneaker> getSneakersByCategory(String category) {
        return sneakerRepository.findByCategory(category);
    }

    public Sneaker addSneaker(SneakerRequest request) {
        Sneaker sneaker = Sneaker.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .stock(request.getStock())
                .build();
        return sneakerRepository.save(sneaker);
    }

    public Sneaker updateSneaker(Long id, SneakerRequest request) {
        Sneaker sneaker = getSneakerById(id);
        sneaker.setName(request.getName());
        sneaker.setBrand(request.getBrand());
        sneaker.setPrice(request.getPrice());
        sneaker.setDescription(request.getDescription());
        sneaker.setCategory(request.getCategory());
        sneaker.setImageUrl(request.getImageUrl());
        sneaker.setStock(request.getStock());
        return sneakerRepository.save(sneaker);
    }

    public void deleteSneaker(Long id) {
        sneakerRepository.deleteById(id);
    }
}