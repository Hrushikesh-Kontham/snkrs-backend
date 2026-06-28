package com.sneaker.marketplace.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SneakerRequest {
    private String name;
    private String brand;
    private BigDecimal price;
    private String description;
    private String category;
    private String imageUrl;
    private Integer stock;
}