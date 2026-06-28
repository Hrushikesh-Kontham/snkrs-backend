package com.sneaker.marketplace.service;

import com.sneaker.marketplace.dto.CartRequest;
import com.sneaker.marketplace.entity.CartItem;
import com.sneaker.marketplace.entity.Sneaker;
import com.sneaker.marketplace.entity.User;
import com.sneaker.marketplace.repository.CartItemRepository;
import com.sneaker.marketplace.repository.SneakerRepository;
import com.sneaker.marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final SneakerRepository sneakerRepository;

    public List<CartItem> getCart(String email) {
        User user = getUser(email);
        return cartItemRepository.findByUser(user);
    }

    public CartItem addToCart(String email, CartRequest request) {
        User user = getUser(email);
        Sneaker sneaker = sneakerRepository.findById(request.getSneakerId())
                .orElseThrow(() -> new RuntimeException("Sneaker not found"));

        Optional<CartItem> existing = cartItemRepository.findByUserAndSneaker(user, sneaker);

        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            return cartItemRepository.save(item);
        }

        CartItem cartItem = CartItem.builder()
                .user(user)
                .sneaker(sneaker)
                .quantity(request.getQuantity())
                .build();

        return cartItemRepository.save(cartItem);
    }

    public void removeFromCart(String email, Long sneakerId) {
        User user = getUser(email);
        Sneaker sneaker = sneakerRepository.findById(sneakerId)
                .orElseThrow(() -> new RuntimeException("Sneaker not found"));
        CartItem cartItem = cartItemRepository.findByUserAndSneaker(user, sneaker)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItemRepository.delete(cartItem);
    }

    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}