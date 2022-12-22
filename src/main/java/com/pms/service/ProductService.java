package com.pms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import com.pms.entity.Category;
import com.pms.entity.Product;

public interface ProductService {
	
	Product create(Product product);

	Page<Product> fetchProductList(Integer offSet,Integer pageSize,HttpServletRequest request);

	Product fetchProductById(Long productId);

	void deleteProduct(Long productId);

	List<Category> fetchAllCategory();

	void createMultipleProducts();

	Page<Product> filterProductByDate(String date, String operator, Integer offSet, Integer pageSize);
}
