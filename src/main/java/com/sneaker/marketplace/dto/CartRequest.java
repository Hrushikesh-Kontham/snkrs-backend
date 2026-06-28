package com.sneaker.marketplace.dto;

import lombok.Data;

@Data
public class CartRequest {
    private Long sneakerId;
    private Integer quantity;
}