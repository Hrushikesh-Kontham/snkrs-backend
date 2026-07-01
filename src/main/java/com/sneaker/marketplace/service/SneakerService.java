package com.sneaker.marketplace.service;

import com.sneaker.marketplace.dto.SneakerRequest;
import com.sneaker.marketplace.entity.Sneaker;
import com.sneaker.marketplace.entity.SneakerImage;
import com.sneaker.marketplace.repository.SneakerRepository;
import com.sneaker.marketplace.repository.SneakerImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SneakerService {

    private final SneakerRepository sneakerRepository;
    private final SneakerImageRepository sneakerImageRepository;

    public List<Sneaker> getAllSneakers() {
        return sneakerRepository.findAll();
    }

    public Sneaker getSneakerById(Long id) {
        return sneakerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sneaker not found"));
    }

    public List<Sneaker> getSneakersByBrand(String brand) {
        return sneakerRepository.findByBrandIgnoreCase(brand);
    }

    public List<Sneaker> getSneakersByCategory(String category) {
        return sneakerRepository.findByCategoryIgnoreCase(category);
    }

    @Transactional
    public Sneaker createSneaker(SneakerRequest request) {
        Sneaker sneaker = Sneaker.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .stock(request.getStock())
                .build();

        sneakerRepository.save(sneaker);

        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            List<SneakerImage> images = IntStream.range(0, request.getImageUrls().size())
                    .mapToObj(i -> SneakerImage.builder()
                            .sneaker(sneaker)
                            .imageUrl(request.getImageUrls().get(i))
                            .displayOrder(i)
                            .build())
                    .collect(Collectors.toList());
            sneakerImageRepository.saveAll(images);
            sneaker.setImages(images);
        }

        return sneaker;
    }

    @Transactional
    public Sneaker updateSneaker(Long id, SneakerRequest request) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sneaker not found"));

        sneaker.setName(request.getName());
        sneaker.setBrand(request.getBrand());
        sneaker.setPrice(request.getPrice());
        sneaker.setDescription(request.getDescription());
        sneaker.setCategory(request.getCategory());
        sneaker.setImageUrl(request.getImageUrl());
        sneaker.setStock(request.getStock());

        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
            sneakerImageRepository.deleteBySneaker(sneaker);
            List<SneakerImage> images = IntStream.range(0, request.getImageUrls().size())
                    .mapToObj(i -> SneakerImage.builder()
                            .sneaker(sneaker)
                            .imageUrl(request.getImageUrls().get(i))
                            .displayOrder(i)
                            .build())
                    .collect(Collectors.toList());
            sneakerImageRepository.saveAll(images);
            sneaker.setImages(images);
        }

        return sneakerRepository.save(sneaker);
    }

    @Transactional
    public void deleteSneaker(Long id) {
        sneakerRepository.deleteById(id);
    }
}