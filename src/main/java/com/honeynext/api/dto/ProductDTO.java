package com.honeynext.api.dto;

import lombok.Data;

/**
 * Data Transfer Object for Product.
 * Matches the frontend's expected product format.
 */
@Data
public class ProductDTO {
	private Long id;
	private String name;
	private Double price;
	private String image;
	private String status;
	private String category;
}