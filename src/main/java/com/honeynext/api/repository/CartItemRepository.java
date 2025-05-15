package com.honeynext.api.repository;

import com.honeynext.api.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for CartItem entity.
 * Provides CRUD operations and custom queries.
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	/**
	 * Finds all cart items for a given user.
	 * @param userId User ID
	 * @return List of cart items
	 */
	List<CartItem> findByUserId(String userId);

	/**
	 * Deletes all cart items for a given user.
	 * @param userId User ID
	 */
	void deleteByUserId(String userId);
}