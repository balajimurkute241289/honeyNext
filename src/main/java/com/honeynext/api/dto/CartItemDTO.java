package com.honeynext.api.dto;

import lombok.Data;

/**
 * Data Transfer Object for CartItem.
 * Includes product details and quantity.
 */
@Data
public class CartItemDTO {
	private Long id;
	private Long productId;
	private String productName;
	private Double productPrice;
	private Integer quantity;
}