package com.pms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.entity.Category;
import com.pms.entity.Product;
import com.pms.repo.CategoryRepo;
import com.pms.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	 public static final String pName="pName";
	 public static final String pDisplayName="pDisplayName";
	 public static final String pPrice="pPrice";
	 public static final String pAuditFields="pAuditFields";
	 public static final String category="category";

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	ObjectMapper mapper;
	
	@Override
	@Transactional
	public Product create(Product product) {
		if(product.getpId()!=null) {
//			Category category = new Category();
//			category.setCmId(product.getCategory().getCmId());
			SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String stringDate= DateFor.format(new Date());
			Date date1;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(stringDate);
				product.setpId(product.getpId());
				product.setCreatedDate(date1);
				return productRepo.save(product);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			
			return null;
		}else {
			SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String stringDate= DateFor.format(new Date());
			
			Date date1;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(stringDate);
				product.setCreatedDate(date1);
				return productRepo.save(product);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			return null;
		}
	}

	@Override
	@Cacheable(cacheNames = "productListCache")
	public Page<Product> fetchProductList(Integer offSet,Integer pageSize,HttpServletRequest request) {
		
		if(request.getParameter("fieldId")!=null&& request.getParameter("searchString")!=null && !"".equals(request.getParameter("searchString"))) {
			String fieldName=request.getParameter("fieldId").toString();
			String searchString=request.getParameter("searchString").toString();
		
			if(pName.equals(fieldName)) {
				return productRepo.findBypNameContaining(searchString,PageRequest.of(0, 10, Sort.Direction.ASC,"pPrice"));
			
			}else if(pDisplayName.equals(fieldName)) {
				return productRepo.findBypDisplayNameContaining(searchString,PageRequest.of(0, 10, Sort.Direction.ASC,"pPrice"));
			
			}else if(pPrice.equals(fieldName)) {
				return productRepo.findBypPriceContaining(Double.valueOf(searchString),PageRequest.of(0, 10, Sort.Direction.ASC,"pPrice"));
			
			}else if(pAuditFields.equals(fieldName)) {
				return productRepo.findBypAuditFieldsContaining(searchString,PageRequest.of(0, 10, Sort.Direction.ASC,"pPrice"));
			
			}else if(category.equals(fieldName)){
				
				Category category=new Category();
				category.setCmDisplayName(searchString);
				 category=categoryRepo.findBycmDisplayName(searchString);
				return productRepo.findByCategoryContaining(category,PageRequest.of(0, 10, Sort.Direction.ASC,"pPrice"));
			}
			
//			if(offSet!=null)
//			return productRepo.filterProductBySearchString(fieldName,searchString,PageRequest.of(offSet, pageSize, Sort.Direction.ASC,"pPrice"));
//
//			return productRepo.findBypName(searchString,PageRequest.of(0, 10, Sort.Direction.ASC,"pPrice"));
//		
		
		}
		if(offSet!=null) {
			return productRepo.findAll(PageRequest.of(offSet, pageSize, Sort.Direction.ASC,"pPrice"));
		}else {
			return productRepo.findAll(PageRequest.of(0, 10, Sort.Direction.ASC,"pPrice"));
		}
	}

	@Override
	@Cacheable(cacheNames = "productCache",key = "#productId")
	public Product fetchProductById(Long productId) {
		 Optional<Product> product  = productRepo.findById(productId);
		 if(product.isPresent()) {
			 return product.get();
		 }
		return null;
	}

	@Override
	public void deleteProduct(Long productId) {
		 productRepo.deleteById(productId);
	}

	@Override
	public List<Category> fetchAllCategory() {
		return categoryRepo.findAll();
	}

	@Override
	public Page<Product> filterProductByDate(String date,String operator,Integer offSet,Integer pageSize) {
		try {
			date=date.replace("T", " ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date convertedCurrentDate = sdf.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String format = formatter.format(convertedCurrentDate);
			
			if(offSet ==null|| pageSize ==null) {
				return ("before".equals(operator)?productRepo.findProductsByFilteredDate(format,PageRequest.of(0, 10, Sort.Direction.ASC,"P_PRICE")):
					productRepo.findProductsByFilteredDateAfter(format,PageRequest.of(0, 10, Sort.Direction.ASC,"P_PRICE")));
			}else {
				
				return ("before".equals(operator)?productRepo.findProductsByFilteredDate(format,PageRequest.of(offSet, pageSize, Sort.Direction.ASC,"P_PRICE")):
					productRepo.findProductsByFilteredDateAfter(format,PageRequest.of(offSet, pageSize, Sort.Direction.ASC,"P_PRICE")));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
		return null;
	}

	@Override()
	public void createMultipleProducts() {
		List<Product> products=IntStream.rangeClosed(1, 50)
			.mapToObj(i->new Product("product"+i,"productDisplay"+i,new Category().builder().cmId(Long.valueOf(i)).cmDisplayName("Display"+i).build(),Double.valueOf(i*i),"audit fields"+i,new Date()))
			.collect(Collectors.toList());
				productRepo.saveAll(products);
	}
	
}
