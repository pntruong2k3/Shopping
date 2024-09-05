package com.shopping.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.model.Category;
import com.shopping.DTO.UserDTO;
import com.shopping.model.User;
import com.shopping.service.CategoryService;
import com.shopping.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;

	
	@GetMapping("/register")
	public String getRegistrationPage(@ModelAttribute("user") User user, Model model) {
		
		List<Category> categoryPage = this.categoryService.getAll();
		model.addAttribute("categoryPage", categoryPage);
		
		return "user/register";
	}
	
	@PostMapping("/register")
	public String saveUser(@ModelAttribute("user") User user, Model model, @RequestParam("confirmPassword") String confirmPassword) {
	
		
		if (!user.getPassword().equals(confirmPassword)) {
	        model.addAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp.");
	        return "user/register";
	    } else {
	        try {
	            User savedUser = userService.saveUser(user);
	            model.addAttribute("success", "Đăng ký thành công");
	            return "user/login";
	        } catch (RuntimeException e) {
	            model.addAttribute("errorusername", "Tài khoản đã tồn tại");
	            return "user/register";
	        }
	    }
	}
	@GetMapping("/login")
	public String login(Model model,@RequestParam(value = "error", required = false) String error) {
		List<Category> categoryPage = this.categoryService.getAll();
		model.addAttribute("categoryPage", categoryPage);
		 if (error != null) {
		        model.addAttribute("loginError", "Sai tài khoản hoặc mật khẩu.");
		    }
		return "user/login";
	}
	

}

