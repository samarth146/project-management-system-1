package com.pms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

	Category findBycmDisplayName(String searchString);

}
