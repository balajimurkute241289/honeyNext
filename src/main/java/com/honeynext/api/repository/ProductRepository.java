package com.honeynext.api.repository;

import com.honeynext.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Product entity.
 * Provides CRUD operations and custom queries.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
	/**
	 * Finds products by name containing the search term (case-insensitive).
	 * @param query Search term
	 * @return List of matching products
	 */
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))")
	List<Product> searchByName(@Param("query") String query);
}