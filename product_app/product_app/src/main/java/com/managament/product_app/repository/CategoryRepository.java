package com.managament.product_app.repository;

import com.managament.product_app.module.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
