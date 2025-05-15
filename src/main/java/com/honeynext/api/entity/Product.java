package com.honeynext.api.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing a Product in the database.
 * Maps to the 'products' table.
 */
@Entity
@Table(name = "products")
@Data // Lombok: Generates getters, setters, toString, etc.
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private String image;

	@Column(nullable = false)
	private String status; // "In Stock" or "Sold Out"

	@Column(nullable = false)
	private String category; // "Honey", "Combos", "Ayurvedic"
}