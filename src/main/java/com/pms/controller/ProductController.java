package com.pms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pms.entity.Product;
import com.pms.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcomePage(){
		return new ModelAndView("/WEB-INF/jsp/welcome.jsp");
	}
	
	@RequestMapping(value = "/ProductForm", method = RequestMethod.GET)
	public void getForm(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setAttribute("categoryList", productService.fetchAllCategory());
			String message="";
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/WEB-INF/jsp/ProductForm.jsp").forward(request, response);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public Product createProduct(@Validated Product product,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
		return productService.create(product);
		}catch(Exception e) {
			String message=e.getLocalizedMessage();
			request.setAttribute("errorMessage", message);
			request.getRequestDispatcher("/WEB-INF/jsp/List.jsp").forward(request, response);
		}
		return product;
	}
	
	@RequestMapping(value ="/getProducts", method = RequestMethod.GET)	  
	public void fetchProductList(@RequestParam(value="page",required=false)Integer offSet,
			@RequestParam(value="size",required=false,defaultValue = "10")Integer page, 
			HttpServletRequest request, HttpServletResponse response) {
	
//		Page<Product> product= productService.fetchProductList(offSet,page,request);
//		return new ResponseEntity<Product>(product,HttpStatus.OK);
		try {
			Page<Product> fetchProductList = productService.fetchProductList(offSet,page,request);
			request.setAttribute("size", fetchProductList.getSize());
			request.setAttribute("number", fetchProductList.getNumber());
			request.setAttribute("totalPages", fetchProductList.getTotalPages());
			request.setAttribute("totalElements", fetchProductList.getTotalElements());
			request.setAttribute("productList", fetchProductList);
			request.getRequestDispatcher("/WEB-INF/jsp/List.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@RequestMapping(value ="/getProductsPost", method = RequestMethod.POST)	  
	public void fetchProductListPost(@RequestParam(value="page",required=false)Integer offSet,
			@RequestParam(value="size",required=false,defaultValue = "10")Integer page,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<Product> fetchProductList = productService.fetchProductList(offSet,page,request);
			request.setAttribute("size", fetchProductList.getSize());
			request.setAttribute("number", fetchProductList.getNumber());
			request.setAttribute("totalPages", fetchProductList.getTotalPages());
			request.setAttribute("totalElements", fetchProductList.getTotalElements());
			request.setAttribute("productList", fetchProductList);
			request.getRequestDispatcher("/WEB-INF/jsp/List.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value ="/getProductById/{id}", method = RequestMethod.GET)	  
    public void fetchProductById(@PathVariable("id") Long productId,HttpServletRequest request, 
    		HttpServletResponse response){
		try {
			request.setAttribute("categoryList", productService.fetchAllCategory());
			request.setAttribute("product", productService.fetchProductById(productId));
			request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping(value ="/deleteProduct/{id}", method = RequestMethod.GET)	  
	public Page<Product> deleteProduct(@RequestParam(value="page",required=false)Integer offSet,
			@RequestParam(value="size",required=false,defaultValue = "10")int page,
			@PathVariable("id") Long productId,
			HttpServletRequest request, HttpServletResponse response){
		if(productId!=null)
		productService.deleteProduct(productId);
		
		Page<Product> fetchProductList = productService.fetchProductList(offSet,page,request);
		request.setAttribute("size", fetchProductList.getSize());
		request.setAttribute("number", fetchProductList.getNumber());
		request.setAttribute("totalPages", fetchProductList.getTotalPages());
		request.setAttribute("totalElements", fetchProductList.getTotalElements());
		return fetchProductList;
	}
	
	@RequestMapping(value ="/filterProductByDate", method = RequestMethod.POST)	  
	public void filterProductByDate(@RequestParam(value="page",required=false)Integer offSet,
			@RequestParam(value="size",required=false,defaultValue = "10")Integer page,
			@RequestParam("date")String date,HttpServletRequest request, HttpServletResponse response) throws ParseException{
			try {
				
				Page<Product> fetchProductList = productService.filterProductByDate(date,request.getParameter("dateFilterType"), offSet, page);
				request.setAttribute("size", fetchProductList.getSize());
				request.setAttribute("number", fetchProductList.getNumber());
				request.setAttribute("totalPages", fetchProductList.getTotalPages());
				request.setAttribute("totalElements", fetchProductList.getTotalElements());
				request.setAttribute("date", request.getParameter("date"));
				request.setAttribute("dateFilterType", request.getParameter("dateFilterType"));
				request.setAttribute("productList", fetchProductList);
				request.getRequestDispatcher("/WEB-INF/jsp/List.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	@RequestMapping(value ="/createMultipleProducts", method = RequestMethod.POST)	  
	public void createMultipleProducts(){
		try {
			productService.createMultipleProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> DefaultHandlerExceptionResolver(ConstraintViolationException e){
		
		List<String> error= new ArrayList<>(e.getConstraintViolations().size());
		e.getConstraintViolations().forEach(ConstraintViolation ->{
			error.add(ConstraintViolation.getPropertyPath()+"");
		});
		return new ResponseEntity("/WEB-INF/jsp/ProductForm.jsp",HttpStatus.BAD_REQUEST);
	}
}
