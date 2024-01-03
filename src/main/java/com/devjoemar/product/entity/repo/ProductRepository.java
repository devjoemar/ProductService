package com.devjoemar.product.entity.repo;

import com.devjoemar.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
