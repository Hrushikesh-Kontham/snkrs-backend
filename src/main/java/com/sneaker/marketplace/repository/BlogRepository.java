package com.sneaker.marketplace.repository;

import com.sneaker.marketplace.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByStatusOrderByCreatedAtDesc(String status);
    Optional<Blog> findBySlug(String slug);
    List<Blog> findByCategory(String category);
}