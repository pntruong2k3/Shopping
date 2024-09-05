package com.shopping.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shopping.DTO.ProductDTO;
import com.shopping.model.Category;
import com.shopping.model.Product;

public interface ProductService {
	List<Product> getAll(int pageNo,int pageSize);


	Boolean create(Product product);
	Product findById(Integer id);
	Boolean update(Product product);
	Boolean delete(Integer id);
	
	List<Product> searchProduct(String keyword);
	
	Page<Product> getAll(Integer pageNo,int pageSize);
	Page<Product> searchProduct(String keyword, Integer pageNo,int pageSize);
	
    ProductDTO getById(Integer id);

}
