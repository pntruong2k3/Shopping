package com.shopping.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shopping.DTO.ProductDTO;
import com.shopping.model.Category;
import com.shopping.model.Product;
import com.shopping.repository.ProductRepository;
import com.shopping.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	private Object categoryRepository;
	@Override
	public List<Product> getAll(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo ,pageSize);

		return this.productRepository.findAll();
	}

	@Override
	public Boolean create(Product product) {
		// TODO Auto-generated method stub
		try {
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return this.productRepository.findById(id).get();
	}

	@Override
	public Boolean update(Product product) {
		// TODO Auto-generated method stub
		try {
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			 this.productRepository.delete(findById(id));
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		return this.productRepository.searchProduct(keyword);	
		}

	@Override
	public Page<Product> getAll(Integer pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo,pageSize);
		return this.productRepository.findAll(pageable);
	}

	@Override
	public Page<Product> searchProduct(String keyword, Integer pageNo,int pageSize) {
		// TODO Auto-generated method stub
	List list = this.searchProduct(keyword);
		
		Pageable pageable = PageRequest.of(pageNo ,pageSize);
		
		Integer start = (int) pageable.getOffset();
		
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() : pageable.getOffset() + pageable.getPageSize());
		
		list = list.subList(start, end);
		
		return new PageImpl<Product>(list, pageable, this.searchProduct(keyword).size() );
		
	}

	@Override
	public ProductDTO getById(Integer id) {
		// TODO Auto-generated method stub
		ProductDTO productDTO = new ProductDTO();
		Product product = productRepository.findById(id).get();
		productDTO.setId(product.getId());
		productDTO.setProductName(product.getProductName());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setCategory(product.getCategory());
		productDTO.setPrice(product.getPrice());
		productDTO.setQuantity(product.getQuantity());
	
		return productDTO;
	}

	


	}


