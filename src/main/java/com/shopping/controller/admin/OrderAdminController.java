package com.shopping.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.model.Order;
import com.shopping.model.OrderDetail;
import com.shopping.model.ShoppingCart;
import com.shopping.model.User;
import com.shopping.service.CategoryService;
import com.shopping.service.OrderService;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingCartService;
import com.shopping.service.UserService;

@Controller
@RequestMapping("/admin")
public class OrderAdminController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShoppingCartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orderAdmin")
	public String orderDetails(Model model,Principal principal) {
		// Lấy toàn bộ danh sách Order
		List<Order> orders = this.orderService.getAllOrder();
		model.addAttribute("orders", orders);
	
		List<OrderDetail> orderDetails = this.orderService.getAll();
		model.addAttribute("orderDetails",orderDetails);
		return "admin/orderAdmin";
	}
	   @PostMapping("/orders/{id}/accept")
	    public String acceptOrder(@PathVariable Long id, Model model) {
	        Order order = orderService.acceptOrder(id);
	        
	        model.addAttribute("order", order);
	        
	        return "redirect:/admin/orderAdmin"; // Trả về view để hiển thị thông tin đơn hàng
	    }
	
}
