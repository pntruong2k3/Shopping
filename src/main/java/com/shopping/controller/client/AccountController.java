package com.shopping.controller.client;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.model.Category;
import com.shopping.model.User;
import com.shopping.service.CategoryService;
import com.shopping.service.UserService;

@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/account")
	public String accountHome(Model model,Principal principal) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		
		String username = principal.getName();
		User user = userService.findByUserName(username);
		
		
		model.addAttribute("user", user);
		
		return "client/account";
	}
	@RequestMapping(value="/update-infor", method = {RequestMethod.GET, RequestMethod.PUT} )
	public String updateUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes,
			Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		}
		User userSaved= userService.saveInfor(user);
		redirectAttributes.addFlashAttribute("user", userSaved);
		return "redirect:/account";
	}
	
	@GetMapping("/my-account")
	public String accCount(Model model,Principal principal) {
		List<Category> categoryPage = this.categoryService.getAll();
		model.addAttribute("categoryPage", categoryPage);

		if (principal == null) {
			return "redirect:/login";
		}
		String username = principal.getName();
		User user = userService.findByUserName(username);
		model.addAttribute("user", user);
		return "client/my-account";
	}
}
