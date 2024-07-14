package com.managament.product_app.nosql.repository;

import com.managament.product_app.nosql.model.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDocumentRepository extends MongoRepository<ProductDocument, String> {
}
