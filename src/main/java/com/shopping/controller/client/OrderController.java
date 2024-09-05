package com.shopping.controller.client;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.shopping.model.CartItem;
import com.shopping.model.Category;
import com.shopping.model.Order;
import com.shopping.model.OrderDetail;
import com.shopping.model.Product;
import com.shopping.model.ShoppingCart;
import com.shopping.model.User;
import com.shopping.service.CategoryService;
import com.shopping.service.OrderService;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingCartService;
import com.shopping.service.UserService;

@Controller
public class OrderController {
	
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
	

	@GetMapping("/check-out")
	public String checkout(Model model, Principal principal) {
		List<Category> categoryPage = this.categoryService.getAll();
		model.addAttribute("categoryPage", categoryPage);

		if (principal == null) {
			return "redirect:/login";
		}
		String username = principal.getName();
		User user = userService.findByUserName(username);

		
	
		if(user.getTelephone() == null || user.getAddress() == null || user.getCity() == null) {
			
			
			model.addAttribute("user",user);
			model.addAttribute("error", "Bạn cần cập nhật thông tin");
			return "client/account";
			
		}else{
			model.addAttribute("user", user);
			ShoppingCart shoppingCart = user.getShoppingCart();
			model.addAttribute("shoppingCart", shoppingCart);
	        model.addAttribute("subTotal", shoppingCart.getTotalPrices());

		}
		//toDo:
		return "client/check-out";
	}
	

	@GetMapping("/order")
	public String order(Principal principal, Model model) {
		List<Category> categoryPage = this.categoryService.getAll();
		model.addAttribute("categoryPage", categoryPage);
		if (principal == null) {
			return "redirect:/login";
		} else {
			String username = principal.getName();
			User user = userService.findByUserName(username);
			List<Order> orderList = user.getOrders();
			model.addAttribute("orders", orderList);
			ShoppingCart shoppingCart = user.getShoppingCart();
			model.addAttribute("shoppingCart", shoppingCart);
			List<OrderDetail> orderDetails = this.orderService.getAll();
			model.addAttribute("orderDetails",orderDetails);
			return "client/order";
		}

	}
	
	
	@PostMapping("/save-order")
	public String saveOrder(Principal principal, Model model) {
		
		if (principal == null) {
			return "redirect:/login";

		} else {
			String username = principal.getName();
			User user = userService.findByUserName(username);
			ShoppingCart cart = user.getShoppingCart();
			orderService.saveOrder(cart);
			
			
			return "redirect:/order";
		}
	}
	
	
}
