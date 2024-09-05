package com.shopping.controller.client;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.DTO.ProductDTO;
import com.shopping.model.Category;
import com.shopping.model.Product;
import com.shopping.model.ShoppingCart;
import com.shopping.model.User;
import com.shopping.service.CategoryService;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingCartService;
import com.shopping.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShoppingCartService cartService;
	@Autowired
	private ProductService productService;

	 @GetMapping("/cart")
	    public String cart(Model model, Principal principal, HttpSession session){
			List<Category> categoryPage = this.categoryService.getAll();
			model.addAttribute("categoryPage", categoryPage);
	        if(principal == null){
	            return "redirect:/login";
	        }
	        String username = principal.getName();
	        User user = userService.findByUserName(username);
	        ShoppingCart shoppingCart = user.getShoppingCart();
	        if(shoppingCart == null){
	            model.addAttribute("check", "No item in your cart");
	        }
	        session.setAttribute("totalItems", shoppingCart.getTotalItems());
	        model.addAttribute("subTotal", shoppingCart.getTotalPrices());
	        model.addAttribute("shoppingCart", shoppingCart);
	        return "client/cart";
	    }

	@PostMapping("/add-to-cart")
	public String addItemToCart(@RequestParam("id") Integer productId,
			@RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity, Principal principal, Model model,
			HttpServletRequest request) {
		
		ProductDTO productDTO = productService.getById(productId);
		if(principal == null) {
			return "redirect:/login";
		}
		Product product = productService.findById(productId);
		String username =principal.getName();
		User user = userService.findByUserName(username);
		
		ShoppingCart cart = cartService.addItemToCart(productDTO, quantity, user);
		
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
	public String updateCart(@RequestParam("quantity") int quantity,
			@RequestParam("id") Integer id,
			Model model,
			Principal principal) {
		
		if (principal == null) {
	        // Xử lý khi thiếu tham số 'quantity'
	        return "redirect:/login";
	    }

	    if (principal == null) {
	        return "redirect:/login";
	    } else {
	        String username = principal.getName();
	        User user = userService.findByUserName(username);
	        ProductDTO productDTO = productService.getById(id);

	        ShoppingCart cart = cartService.updateItemInCart(productDTO, quantity, user);

	        model.addAttribute("shoppingCart", cart);
	        return "redirect:/cart";
	    }
		
	}
	
	@RequestMapping( value="/update-cart", method = RequestMethod.POST, params = "action=delete")
	public String deleteItemFromCart(@RequestParam("id") Integer id,Model model , Principal principal) {
		
		if(principal == null) {
			return "redirect:/login";

		}else {
			String username = principal.getName();
			User user = userService.findByUserName(username);
			ProductDTO productDTO = productService.getById(id);
			
			ShoppingCart cart = cartService.deleteItemFromCart(productDTO, user);
			
			model.addAttribute("shoppingCart", cart);
			return "redirect:/cart";

		}
	}
	
}
