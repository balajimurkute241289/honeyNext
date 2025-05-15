package com.honeynext.api.service;

import com.honeynext.api.dto.CartItemDTO;
import com.honeynext.api.entity.CartItem;
import com.honeynext.api.entity.Product;
import com.honeynext.api.repository.CartItemRepository;
import com.honeynext.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing Cart operations.
 * Handles adding, removing, and clearing cart items.
 */
@Service
public class CartService {
	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;

	@Autowired
	public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
	}

	/**
	 * Adds a product to the cart.
	 * @param userId User ID (e.g., "user1")
	 * @param productId Product ID
	 * @return Updated list of CartItemDTOs
	 */
	public List<CartItemDTO> addToCart(String userId, Long productId) {
		Optional<Product> productOpt = productRepository.findById(productId);
		if (productOpt.isEmpty() || !"In Stock".equals(productOpt.get().getStatus())) {
			throw new IllegalArgumentException("Product not found or out of stock");
		}

		List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
		Optional<CartItem> existingItem = cartItems.stream()
				.filter(item -> item.getProduct().getId().equals(productId))
				.findFirst();

		if (existingItem.isPresent()) {
			CartItem item = existingItem.get();
			item.setQuantity(item.getQuantity() + 1);
			cartItemRepository.save(item);
		} else {
			CartItem newItem = new CartItem();
			newItem.setUserId(userId);
			newItem.setProduct(productOpt.get());
			newItem.setQuantity(1);
			cartItemRepository.save(newItem);
		}

		return getCart(userId);
	}

	/**
	 * Retrieves all cart items for a user.
	 * @param userId User ID
	 * @return List of CartItemDTOs
	 */
	public List<CartItemDTO> getCart(String userId) {
		return cartItemRepository.findByUserId(userId).stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Removes a cart item.
	 * @param userId User ID
	 * @param itemId Cart item ID
	 * @return Updated list of CartItemDTOs
	 */
	public List<CartItemDTO> removeFromCart(String userId, Long itemId) {
		cartItemRepository.deleteById(itemId);
		return getCart(userId);
	}

	/**
	 * Clears all cart items for a user.
	 * @param userId User ID
	 */
	public void clearCart(String userId) {
		cartItemRepository.deleteByUserId(userId);
	}

	/**
	 * Converts CartItem entity to CartItemDTO.
	 * @param item CartItem entity
	 * @return CartItemDTO
	 */
	private CartItemDTO convertToDTO(CartItem item) {
		CartItemDTO dto = new CartItemDTO();
		dto.setId(item.getId());
		dto.setProductId(item.getProduct().getId());
		dto.setProductName(item.getProduct().getName());
		dto.setProductPrice(item.getProduct().getPrice());
		dto.setQuantity(item.getQuantity());
		return dto;
	}
}