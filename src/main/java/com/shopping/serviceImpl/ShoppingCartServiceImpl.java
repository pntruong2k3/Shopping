package com.shopping.serviceImpl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shopping.DTO.ProductDTO;
import com.shopping.model.CartItem;
import com.shopping.model.Product;
import com.shopping.model.ShoppingCart;
import com.shopping.model.User;
import com.shopping.repository.CartItemRepository;
import com.shopping.repository.ShoppingCartRepository;
import com.shopping.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private CartItemRepository itemRepository;
	
	@Autowired
	private ShoppingCartRepository cartRepository;
	
	@Override
	public ShoppingCart addItemToCart(ProductDTO productDTO, int quantity, User user) {
		// TODO Auto-generated method stub
		ShoppingCart cart = user.getShoppingCart();
		
		if(cart == null) {
			cart = new ShoppingCart();
		}
		Set<CartItem> cartItems = cart.getCartItem();
		CartItem cartItem = findCartItem(cartItems, productDTO.getId());
		Product product = transfer(productDTO);
		
		if(cartItems == null) {
			cartItems =new HashSet<>();
			if(cartItem == null) {
				cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setTotalPrice(quantity * productDTO.getPrice());
				cartItem.setQuantity(quantity);
				cartItem.setCart(cart);
				cartItems.add(cartItem);
				itemRepository.save(cartItem);
				
			}
		}else {
				if(cartItem == null) {
					cartItem = new CartItem();
					cartItem.setProduct(product);
					cartItem.setTotalPrice(quantity * product.getPrice());
					cartItem.setQuantity(quantity);
					cartItem.setCart(cart);
					cartItems.add(cartItem);
					itemRepository.save(cartItem);
			}else {
				cartItem.setQuantity(cartItem.getQuantity() + quantity);
				cartItem.setTotalPrice(cartItem.getTotalPrice() + (quantity * product.getPrice()));
				itemRepository.save(cartItem);
			}
				
		}
		cart.setCartItem(cartItems);
		
		int totalItems = totalItems(cart.getCartItem());
		double totalPrice = totalPrice(cart.getCartItem());
		
		cart.setTotalPrices(totalPrice);
		cart.setTotalItems(totalItems);
		cart.setUser(user);
		
		return cartRepository.save(cart);
	}
	
	private Product transfer(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setCategory(productDTO.getCategory());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setPrice(productDTO.getPrice());
		product.setProductName(productDTO.getProductName());
		product.setQuantity(productDTO.getQuantity());
		return product;
	}

	private CartItem findCartItem(Set<CartItem> cartItems, Integer productId) {
		if(cartItems == null) {
			return null;
		}
		CartItem cartItem = null;
		
		for(CartItem item : cartItems) {
			if(item.getProduct().getId() == productId) {
				cartItem = item;
			}
		}
		return cartItem;
	}
	private int totalItems(Set<CartItem> cartItems) {
		int totalItems = 0;
		for(CartItem item : cartItems) {
			totalItems += item.getQuantity();
		}
		return totalItems;
	}
	private double totalPrice(Set<CartItem> cartItems) {
		double totalPrice = 0.0;
		
		for(CartItem item : cartItems) {
			totalPrice += item.getTotalPrice();
		}
		return totalPrice;
	}

	@Override
	public ShoppingCart updateItemInCart(ProductDTO productDTO, int quantity, User user) {
	// TODO Auto-generated method stub
	ShoppingCart cart = user.getShoppingCart();

	
		Set<CartItem> cartItems = cart.getCartItem();
		
		CartItem item = findCartItem(cartItems, productDTO.getId());
		
		item.setQuantity(quantity);
		item.setTotalPrice(quantity * productDTO.getPrice());
		
		itemRepository.save(item);
		
		int totalItems = totalItems(cartItems);
		double totalPrice = totalPrice(cartItems);
		
		cart.setTotalItems(totalItems);
		cart.setTotalPrices(totalPrice);
		
		
		return cartRepository.save(cart);
	}
	@Override
	public ShoppingCart deleteItemFromCart(ProductDTO productDTO, User user) {
		// TODO Auto-generated method stub
		ShoppingCart cart = user.getShoppingCart();
		
		Set<CartItem> cartItems = cart.getCartItem();
		
		CartItem item = findCartItem(cartItems, productDTO.getId());
		
		cartItems.remove(item);
		
		itemRepository.delete(item);
		
		int totalItems = totalItems(cartItems);
		double totalPrice = totalPrice(cartItems);
		
		cart.setCartItem(cartItems);
		cart.setTotalItems(totalItems);
		cart.setTotalPrices(totalPrice);
		
		return cartRepository.save(cart);
	}

	@Override
	public void deleteCartById(Long id) {
		// TODO Auto-generated method stub
		   ShoppingCart shoppingCart = cartRepository.getById(id);
		   if(!ObjectUtils.isEmpty(shoppingCart) && !ObjectUtils.isEmpty(shoppingCart.getCartItem())){
	            itemRepository.deleteAll(shoppingCart.getCartItem());
	        }
		   shoppingCart.getCartItem().clear();
	        shoppingCart.setTotalPrices(0);
	        shoppingCart.setTotalItems(0);
	        cartRepository.save(shoppingCart);
	}


}
