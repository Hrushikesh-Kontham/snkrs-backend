package com.sneaker.marketplace.controller;

import com.sneaker.marketplace.dto.SneakerRequest;
import com.sneaker.marketplace.entity.Sneaker;
import com.sneaker.marketplace.service.SneakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SneakerController {

    private final SneakerService sneakerService;

    // Public endpoints
    @GetMapping("/sneakers")
    public ResponseEntity<List<Sneaker>> getAllSneakers() {
        return ResponseEntity.ok(sneakerService.getAllSneakers());
    }

    @GetMapping("/sneakers/{id}")
    public ResponseEntity<Sneaker> getSneakerById(@PathVariable Long id) {
        return ResponseEntity.ok(sneakerService.getSneakerById(id));
    }

    @GetMapping("/sneakers/brand/{brand}")
    public ResponseEntity<List<Sneaker>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(sneakerService.getSneakersByBrand(brand));
    }

    @GetMapping("/sneakers/category/{category}")
    public ResponseEntity<List<Sneaker>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(sneakerService.getSneakersByCategory(category));
    }

    // Admin endpoints
    @PostMapping("/admin/sneakers")
    public ResponseEntity<Sneaker> addSneaker(@RequestBody SneakerRequest request) {
        return ResponseEntity.ok(sneakerService.addSneaker(request));
    }

    @PutMapping("/admin/sneakers/{id}")
    public ResponseEntity<Sneaker> updateSneaker(@PathVariable Long id,
                                                 @RequestBody SneakerRequest request) {
        return ResponseEntity.ok(sneakerService.updateSneaker(id, request));
    }

    @DeleteMapping("/admin/sneakers/{id}")
    public ResponseEntity<String> deleteSneaker(@PathVariable Long id) {
        sneakerService.deleteSneaker(id);
        return ResponseEntity.ok("Sneaker deleted successfully");
    }
}