package com.springboot.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Main.Entity.Category;
import com.springboot.Main.Payload.CategoryDto;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>
{
	
}
