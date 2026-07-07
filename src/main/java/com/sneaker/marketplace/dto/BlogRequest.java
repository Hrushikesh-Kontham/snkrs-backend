package com.sneaker.marketplace.dto;

import lombok.Data;

@Data
public class BlogRequest {
    private String title;
    private String slug;
    private String content;
    private String coverImage;
    private String author;
    private String category;
    private Integer readTime;
    private String status;
}