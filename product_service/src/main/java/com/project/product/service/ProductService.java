package com.project.product.service;

import com.project.product.dto.ProductRequest;
import com.project.product.dto.ProductResponse;

import java.util.List;


public interface ProductService {
	void add(ProductRequest product) throws Exception;
	List<ProductResponse> fetchAll() throws Exception;
//	public ProductModel fetchById(String id) throws Exception;

//	ProductRequest fetchByName(String name) throws Exception;
//
//	ProductRequest fetchByNameAndId(String name, String id) throws Exception;
}
