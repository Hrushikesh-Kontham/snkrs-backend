package com.sneaker.marketplace.service;

import com.sneaker.marketplace.dto.WishlistRequest;
import com.sneaker.marketplace.entity.Sneaker;
import com.sneaker.marketplace.entity.User;
import com.sneaker.marketplace.entity.Wishlist;
import com.sneaker.marketplace.repository.SneakerRepository;
import com.sneaker.marketplace.repository.UserRepository;
import com.sneaker.marketplace.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final SneakerRepository sneakerRepository;

    public List<Wishlist> getWishlist(String email) {
        User user = getUser(email);
        return wishlistRepository.findByUser(user);
    }

    public Wishlist addToWishlist(String email, WishlistRequest request) {
        User user = getUser(email);
        Sneaker sneaker = sneakerRepository.findById(request.getSneakerId())
                .orElseThrow(() -> new RuntimeException("Sneaker not found"));

        if (wishlistRepository.findByUserAndSneaker(user, sneaker).isPresent()) {
            throw new RuntimeException("Sneaker already in wishlist");
        }

        Wishlist wishlist = Wishlist.builder()
                .user(user)
                .sneaker(sneaker)
                .build();

        return wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(String email, Long sneakerId) {
        User user = getUser(email);
        Sneaker sneaker = sneakerRepository.findById(sneakerId)
                .orElseThrow(() -> new RuntimeException("Sneaker not found"));
        wishlistRepository.deleteByUserAndSneaker(user, sneaker);
    }

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}