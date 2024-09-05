package com.shopping.service;

import java.util.Map;

import com.shopping.DTO.ProductDTO;
import com.shopping.model.Product;
import com.shopping.model.ShoppingCart;
import com.shopping.model.User;

public interface ShoppingCartService {
		ShoppingCart addItemToCart(ProductDTO productDTO, int quantity, User user);
		
		ShoppingCart updateItemInCart(ProductDTO productDTO, int quantity, User user);
		
		ShoppingCart deleteItemFromCart(ProductDTO productDTO, User user);

		void deleteCartById(Long id);

		

}
