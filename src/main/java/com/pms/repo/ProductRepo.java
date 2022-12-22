package com.pms.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pms.entity.Category;
import com.pms.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

	
	@Query(value="SELECT * FROM PRODUCT WHERE CREATED_DATE < :date",nativeQuery = true)
	public Page<Product> findProductsByFilteredDate(String date,Pageable pageable );
	
	@Query(value="SELECT * FROM PRODUCT WHERE CREATED_DATE > :date",nativeQuery = true)
	public Page<Product> findProductsByFilteredDateAfter(String date,Pageable pageable );

	@Query(value="SELECT * FROM PRODUCT WHERE :fieldName = :searchString",nativeQuery = true)
	public Page<Product> filterProductBySearchString(@Param("fieldName")String fieldName,@Param("searchString")String searchString, Pageable pageable);

	public Page<Product> findBypNameContaining(String searchString, Pageable pageable);

	public Page<Product> findBypDisplayNameContaining(String searchString, PageRequest of);

	public Page<Product> findBypPriceContaining(Double long1, PageRequest of);

	public Page<Product> findBypAuditFieldsContaining(String searchString, PageRequest of);

	public Page<Product> findByCategoryContaining(Category category, PageRequest of);
	
}
