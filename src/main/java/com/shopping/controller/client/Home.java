package com.shopping.controller.client;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.model.Category;
import com.shopping.model.Product;
import com.shopping.model.ShoppingCart;
import com.shopping.model.User;
import com.shopping.service.CategoryService;
import com.shopping.service.ProductService;
import com.shopping.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Home {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String HomePage(Model model,@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "14") int pageSize,Principal principal, HttpSession session,@Param("keyword") String keyword
			) {
		
		Page<Product> productPage = this.productService.getAll(pageNo,pageSize);

	
		List<Category> categoryPage = this.categoryService.getAll();
		model.addAttribute("productPage",productPage);
		model.addAttribute("categoryPage", categoryPage);
		
		if(keyword != null) {
			productPage = this.productService.searchProduct(keyword, pageNo,pageSize);

			model.addAttribute("keyword",keyword);
		}
		
		// Hiển thị số item giỏ hàng
		/*if (principal != null) {
			String username = principal.getName();

			User user = userService.findByUserName(username);
	          ShoppingCart shoppingCart = user.getShoppingCart();
	          session.setAttribute("totalItems", shoppingCart.getTotalItems());
	      		
		} else{
            session.removeAttribute("username");
        }*/
		
		return "index";
	}
	

	
}
