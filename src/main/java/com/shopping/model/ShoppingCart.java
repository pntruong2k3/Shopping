package com.shopping.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;
    private int totalItems;
    private double totalPrices;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItem;

    
	public ShoppingCart() {
		super();
	}


	public ShoppingCart(Long id, int totalItems, double totalPrices, User user, Set<CartItem> cartItem) {
		super();
		this.id = id;
		this.totalItems = totalItems;
		this.totalPrices = totalPrices;
		this.user = user;
		this.cartItem = cartItem;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getTotalItems() {
		return totalItems;
	}


	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}


	public double getTotalPrices() {
		return totalPrices;
	}


	public void setTotalPrices(double totalPrices) {
		this.totalPrices = totalPrices;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<CartItem> getCartItem() {
		return cartItem;
	}


	public void setCartItem(Set<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
    
	
    
    
}