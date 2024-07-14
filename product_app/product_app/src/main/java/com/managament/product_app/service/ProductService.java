package com.managament.product_app.service;

import com.managament.product_app.module.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    void deleteProductById(Long id);
    Page<Product> getProducts(int page, int size, String sortBy, String sortDir);
}
