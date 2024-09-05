package com.shopping.service;

import java.util.List;

import com.shopping.model.Order;
import com.shopping.model.OrderDetail;
import com.shopping.model.ShoppingCart;

public interface OrderService {
	void saveOrder(ShoppingCart cart);
	
    Order acceptOrder(Long id);
	
	void cancelOrder(Long id);
	
    void deleteCartById(Long id);
    
    List<OrderDetail> getAll();
    List<Order> getAllOrder();
}
