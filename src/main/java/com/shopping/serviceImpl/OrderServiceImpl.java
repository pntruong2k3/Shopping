package com.shopping.serviceImpl;



import java.util.Date;
import java.util.ArrayList;


import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.model.CartItem;
import com.shopping.model.Order;
import com.shopping.model.OrderDetail;
import com.shopping.model.ShoppingCart;
import com.shopping.repository.CartItemRepository;
import com.shopping.repository.OrderDetailRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.ShoppingCartRepository;
import com.shopping.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ShoppingCartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Override
	public void saveOrder(ShoppingCart cart) {
		// TODO Auto-generated method stub
		Order order = new Order();
		  order.setAccept(false);
	    order.setPaymentMethod("Cash");
		order.setOrderStatus("Đang chờ xác nhận");
		order.setOrderDate(new Date());
		order.setUser(cart.getUser());
		order.setTotalPrice(cart.getTotalPrices());
		
		List<OrderDetail> orderDetailList =  new ArrayList<>();
		for(CartItem item : cart.getCartItem()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setQuantity(item.getQuantity());
			
			orderDetail.setProduct(item.getProduct());
			double unitPrice = item.getProduct().getPrice() * item.getQuantity();
		    orderDetail.setUnitPrice(unitPrice);
			orderDetailRepository.save(orderDetail);
			orderDetailList.add(orderDetail);
			cartItemRepository.delete(item);
		}
		order.setOrderDetailList(orderDetailList);
		cart.setCartItem(new HashSet<>());
		cart.setTotalItems(0);
		cart.setTotalPrices(0);
		cartRepository.save(cart);
		orderRepository.save(order);
	}

	@Override
	public Order acceptOrder(Long id) {
	    Order order = orderRepository.getById(id);
	    
	    if (order.getOrderStatus().equals("Đang chờ xác nhận")) {
	        order.setAccept(true);
	       
	        order.setOrderStatus("Đang chuẩn bị hàng");
	        
	        return orderRepository.save(order);
	    } else {
	        // Xử lý khi đơn hàng không ở trạng thái "Pending"
	        // Ví dụ: throw new Exception("Đơn hàng không ở trạng thái chờ xử lý");
	        // Hoặc có thể cập nhật trạng thái hoặc thực hiện một hành động khác tùy theo yêu cầu của bạn
	        order.setAccept(false); // Đánh dấu là chưa chấp nhận
	        order.setDeliveryDate(new Date());
	        order.setOrderStatus("Đã nhận hàng");
	        return orderRepository.save(order);
	    } 
	
	}
	@Override
	public void cancelOrder(Long id) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(id);
	}
	@Override
	public void deleteCartById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<OrderDetail> getAll() {
		// TODO Auto-generated method stub
		return this.orderDetailRepository.findAll();

	}

	@Override
	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		return this.orderRepository.findAll();
	}

}
