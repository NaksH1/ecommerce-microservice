package com.project.product.serviceImpl;

import com.project.product.dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;

import com.project.product.dto.ProductRequest;
import com.project.product.entity.Product;
import com.project.product.repository.ProductRepository;
import com.project.product.service.ProductService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository){
		this.productRepository = productRepository;
	}
	
//	MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
//	BoundMapperFacade<ProductModel, Product> mapper = mapperFactory.getMapperFacade(ProductModel.class, Product.class);
//
	@Override
	public void add(ProductRequest productRequest) throws Exception {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}

	@Override
	public List<ProductResponse> fetchAll() throws Exception {
		List<Product> list= productRepository.findAll();
		return list.stream().map(product -> mapToProductResponse(product)).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

//	@Override
//	public ProductModel fetchById(String id) throws Exception {
//		Optional<Product> prd = productRepository.findById(id);
//		if(prd.isEmpty())
//			return null;
//		else
//			return mapper.mapReverse(prd.get());
//	}

//	@Override
//	public ProductRequest fetchByName(String name) throws Exception {
//		Optional<Product> prd = productRepository.findByName(name);
//		if(prd.isEmpty())
//			return null;
//		else
//			return mapper.mapReverse(prd.get());
//	}
//
//	@Override
//	public ProductRequest fetchByNameAndId(String name, String id) throws Exception {
//		Optional<Product> prd;
//		if(name == null)
//			prd = productRepository.findById(id);
//		else
//			prd = productRepository.findByNameAndId(name, id);
//		if(prd.isEmpty())
//			return null;
//		else
//			return mapper.mapReverse(prd.get());
//	}

}
