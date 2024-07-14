package com.managament.product_app.nosql;

import com.managament.product_app.nosql.model.ProductDocument;
import com.managament.product_app.nosql.service.ProductDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongodb/products")
public class ProductDocumentController {


    @Autowired
    private ProductDocumentService productDocumentService;

    @GetMapping
    public List<ProductDocument> getAllProducts() {
        return productDocumentService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductDocument> getProductById(@PathVariable String id) {
        return productDocumentService.getProductById(id);
    }

    @PostMapping
    public ProductDocument saveProduct(@RequestBody ProductDocument product) {
        return productDocumentService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable String id) {
        productDocumentService.deleteProductById(id);
    }
}
