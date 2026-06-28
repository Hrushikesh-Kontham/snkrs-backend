package com.sneaker.marketplace.controller;

import com.sneaker.marketplace.dto.CartRequest;
import com.sneaker.marketplace.entity.CartItem;
import com.sneaker.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.getCart(userDetails.getUsername()));
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(userDetails.getUsername(), request));
    }

    @DeleteMapping("/remove/{sneakerId}")
    public ResponseEntity<String> removeFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long sneakerId) {
        cartService.removeFromCart(userDetails.getUsername(), sneakerId);
        return ResponseEntity.ok("Item removed from cart");
    }
}