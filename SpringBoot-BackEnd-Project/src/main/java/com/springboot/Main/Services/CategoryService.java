package com.springboot.Main.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.Main.Payload.CategoryDto;

@Service
public interface CategoryService 
{
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId );
	public void deleteCategory(int categoryId);
	public List<CategoryDto> getAllCategory();
	public CategoryDto getCategoryByID(int categoryId);
}
