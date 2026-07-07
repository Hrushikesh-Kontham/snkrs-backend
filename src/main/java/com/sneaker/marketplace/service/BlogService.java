package com.sneaker.marketplace.service;

import com.sneaker.marketplace.dto.BlogRequest;
import com.sneaker.marketplace.entity.Blog;
import com.sneaker.marketplace.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public List<Blog> getAllPublished() {
        return blogRepository.findByStatusOrderByCreatedAtDesc("PUBLISHED");
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogBySlug(String slug) {
        return blogRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Blog createBlog(BlogRequest request) {
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(request.getSlug())
                .content(request.getContent())
                .coverImage(request.getCoverImage())
                .author(request.getAuthor())
                .category(request.getCategory())
                .readTime(request.getReadTime())
                .status(request.getStatus() != null ? request.getStatus() : "DRAFT")
                .build();
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Long id, BlogRequest request) {
        Blog blog = getBlogById(id);
        blog.setTitle(request.getTitle());
        blog.setSlug(request.getSlug());
        blog.setContent(request.getContent());
        blog.setCoverImage(request.getCoverImage());
        blog.setAuthor(request.getAuthor());
        blog.setCategory(request.getCategory());
        blog.setReadTime(request.getReadTime());
        blog.setStatus(request.getStatus());
        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}