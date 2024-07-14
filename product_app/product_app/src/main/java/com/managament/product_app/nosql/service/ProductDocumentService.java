package com.managament.product_app.nosql.service;

import com.managament.product_app.nosql.model.ProductDocument;
import com.managament.product_app.nosql.repository.ProductDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDocumentService {
    @Autowired
    private ProductDocumentRepository productDocumentRepository;

    public List<ProductDocument> getAllProducts() {
        return productDocumentRepository.findAll();
    }

    public Optional<ProductDocument> getProductById(String id) {
        return productDocumentRepository.findById(id);
    }

    public ProductDocument saveProduct(ProductDocument product) {
        return productDocumentRepository.save(product);
    }

    public void deleteProductById(String id) {
        productDocumentRepository.deleteById(id);
    }
}
