package com.sneaker.marketplace.controller;

import com.sneaker.marketplace.dto.BlogRequest;
import com.sneaker.marketplace.entity.Blog;
import com.sneaker.marketplace.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllPublished() {
        return ResponseEntity.ok(blogService.getAllPublished());
    }

    @GetMapping("/blogs/{slug}")
    public ResponseEntity<Blog> getBlogBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(blogService.getBlogBySlug(slug));
    }

    @GetMapping("/admin/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @PostMapping("/admin/blogs")
    public ResponseEntity<Blog> createBlog(@RequestBody BlogRequest request) {
        return ResponseEntity.ok(blogService.createBlog(request));
    }

    @PutMapping("/admin/blogs/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody BlogRequest request) {
        return ResponseEntity.ok(blogService.updateBlog(id, request));
    }

    @DeleteMapping("/admin/blogs/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}