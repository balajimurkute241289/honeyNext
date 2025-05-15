package com.honeynext.api.controller;

import com.honeynext.api.dto.CartItemDTO;
import com.honeynext.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for cart-related endpoints.
 * Handles adding, removing, and clearing cart items.
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {
	private final CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	/**
	 * Adds a product to the cart.
	 * @param userId User ID (hardcoded "user1" for testing)
	 * @param productId Product ID
	 * @return Updated list of CartItemDTOs
	 */
	@PostMapping
	public List<CartItemDTO> addToCart(@RequestParam String userId, @RequestParam Long productId) {
		return cartService.addToCart(userId, productId);
	}

	/**
	 * Retrieves all cart items for a user.
	 * @param userId User ID
	 * @return List of CartItemDTOs
	 */
	@GetMapping
	public List<CartItemDTO> getCart(@RequestParam String userId) {
		return cartService.getCart(userId);
	}

	/**
	 * Removes a cart item.
	 * @param userId User ID
	 * @param itemId Cart item ID
	 * @return Updated list of CartItemDTOs
	 */
	@DeleteMapping("/item/{itemId}")
	public List<CartItemDTO> removeFromCart(@RequestParam String userId, @PathVariable Long itemId) {
		return cartService.removeFromCart(userId, itemId);
	}

	/**
	 * Clears all cart items for a user.
	 * @param userId User ID
	 */
	@DeleteMapping
	public void clearCart(@RequestParam String userId) {
		cartService.clearCart(userId);
	}
}