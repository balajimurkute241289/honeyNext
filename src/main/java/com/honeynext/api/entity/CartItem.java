package com.honeynext.api.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing an item in the cart.
 * Maps to the 'cart_items' table.
 */
@Entity
@Table(name = "cart_items")
@Data
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userId; // Hardcoded for testing (e.g., "user1")

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(nullable = false)
	private Integer quantity;
}