package com.honeynext.api.controller;

import com.honeynext.api.dto.ProductDTO;
import com.honeynext.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for product-related endpoints.
 * Handles product listing and search.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Retrieves all products.
	 * @return List of ProductDTOs
	 */
	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * Searches products by name.
	 * @param query Search term
	 * @return List of matching ProductDTOs
	 */
	@GetMapping("/search")
	public List<ProductDTO> searchProducts(@RequestParam String query) {
		return productService.searchProducts(query);
	}
}