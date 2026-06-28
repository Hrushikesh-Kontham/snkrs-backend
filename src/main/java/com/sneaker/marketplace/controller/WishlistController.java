package com.sneaker.marketplace.controller;

import com.sneaker.marketplace.dto.WishlistRequest;
import com.sneaker.marketplace.entity.Wishlist;
import com.sneaker.marketplace.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<Wishlist>> getWishlist(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(wishlistService.getWishlist(userDetails.getUsername()));
    }

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addToWishlist(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody WishlistRequest request) {
        return ResponseEntity.ok(wishlistService.addToWishlist(userDetails.getUsername(), request));
    }

    @DeleteMapping("/remove/{sneakerId}")
    public ResponseEntity<String> removeFromWishlist(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long sneakerId) {
        wishlistService.removeFromWishlist(userDetails.getUsername(), sneakerId);
        return ResponseEntity.ok("Removed from wishlist");
    }
}