package com.honeynext.api.service;

import com.honeynext.api.dto.ProductDTO;
import com.honeynext.api.entity.Product;
import com.honeynext.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing Product operations.
 * Handles business logic and data transformation.
 */
@Service
public class ProductService {
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Retrieves all products.
	 * @return List of ProductDTOs
	 */
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Searches products by name.
	 * @param query Search term
	 * @return List of matching ProductDTOs
	 */
	public List<ProductDTO> searchProducts(String query) {
		return productRepository.searchByName(query).stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Converts Product entity to ProductDTO.
	 * @param product Product entity
	 * @return ProductDTO
	 */
	private ProductDTO convertToDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setImage(product.getImage());
		dto.setStatus(product.getStatus());
		dto.setCategory(product.getCategory());
		return dto;
	}
}