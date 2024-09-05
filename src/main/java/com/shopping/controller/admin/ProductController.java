package com.shopping.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.model.Category;
import com.shopping.model.Product;
import com.shopping.service.CategoryService;
import com.shopping.service.ProductService;
import com.shopping.service.StorageService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	StorageService storageService;
	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public String index(Model model,@Param("keyword") String keyword,@RequestParam(defaultValue = "0" ) Integer pageNo,
			@RequestParam(defaultValue = "5") int pageSize)  {
		
		Page<Product> listProduct = this.productService.getAll(pageNo,pageSize);
		// tim kiem
		if(keyword != null) {
			listProduct = this.productService.searchProduct(keyword, pageNo,pageSize);

			model.addAttribute("keyword",keyword);
		}
		
		model.addAttribute("totalPage", listProduct.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("listProduct", listProduct);
		
		return "admin/product/indexProduct";
	}
		/*
		 * List<Product> listproduct = productService.getAll();
		 * model.addAttribute("listproduct", listproduct);
		 * 
		 * return "admin/product/indexProduct"; }
		 */
		
		/*List<Product> listCategory = this.productService.getAll();
		model.addAttribute("listCategory", listCategory);
		return "admin/product/indexProduct";*/
	

	@RequestMapping("/product-add")
	public String add(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);

		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);

		return "admin/product/add";
	}

	@PostMapping("/product-add")
	public String save(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {

		// upload file
		this.storageService.store(file);
		String fileName = file.getOriginalFilename();
		product.setImage(fileName);
		if (this.productService.create(product)) {
			return "redirect:/admin/product";

		}
		return "redirect:/admin/add";

	}

	@GetMapping("/edit-product/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {

		// Product product = this.productService.findById(id);
		// model.addAttribute("product",product);
		Product product = productService.findById(id);
		List<Category> listCate = categoryService.getAll();
		model.addAttribute("product", product);

		model.addAttribute("listCate", listCate);
		return "admin/product/edit";
	}

	@PostMapping("/edit-product")
	public String update(@ModelAttribute("product") Product product,@RequestParam("fileImage") MultipartFile file,HttpServletRequest request) {
		
		//upload file image
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if(!isEmpty) {
			String path = request.getServletContext().getRealPath("uploads/");
			File files = new File(path);
			
			File upImage = new File(files.getAbsolutePath()+"/"+fileName);
			if(!upImage.exists()) {
				try {
					Files.write(upImage.toPath(), file.getBytes(), StandardOpenOption.CREATE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			product.setImage(fileName);
		}
		
		if(productService.update(product)) {
			return "redirect:/admin/product";
		} else {
			return "redirect:/admin/product";
		}

	}

	@GetMapping("/delete-product/{id}")
	public String delete(@PathVariable("id") Integer id) {

		if (this.productService.delete(id)) {
			return "redirect:/admin/product";
		} else {
			return "redirect:/admin/product";
		}

	}
}
