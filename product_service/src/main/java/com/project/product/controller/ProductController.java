package com.project.product.controller;

import com.project.product.dto.ProductResponse;
import com.project.product.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.product.dto.ProductRequest;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	private ProductServiceImpl productService;

	@PostMapping
	public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest productRequest) throws Exception {
		if(productRequest != null) {
			productService.add(productRequest);
			return new ResponseEntity<>(productRequest, HttpStatus.CREATED);
		}
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

//	@GetMapping("/product/{id}")
//	public ResponseEntity<?> fetchByNameOrId(@RequestParam(value = "Name") String name, @PathVariable("id") String id) throws Exception {
//		ProductRequest prd = productService.fetchByNameAndId(name, id);
//		if(prd != null) {
//			productService.add(prd);
//			return new ResponseEntity<>(prd, HttpStatus.CREATED);
//		}
//		else
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> fetchAll() throws Exception {
		List<ProductResponse> list = productService.fetchAll();
		return list;
//		if(list.isEmpty())
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		else
//			return new ResponseEntity<>(list, HttpStatus.OK);
	}

}

