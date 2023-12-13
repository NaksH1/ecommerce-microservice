package com.project.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.product.entity.Product;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String>{

    public Optional<Product> findByName(String name);
    Optional<Product> findByNameAndId(String name, String id);
}
