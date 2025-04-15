package com.springboot.Main.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Main.Entity.Category;
import com.springboot.Main.Exceptions.ResourceNotFoundException;
import com.springboot.Main.Payload.CategoryDto;
import com.springboot.Main.Repository.CategoryRepo;
import com.springboot.Main.Services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
	{
		//Convert the CategoryDto to Category
		Category cat =this.mapper.map(categoryDto, Category.class);
		Category addCat =this.categoryRepo.save(cat);
		//Convert the Category to CategoryDto
		return this.mapper.map(addCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) 
	{
		Category cat =this.categoryRepo.findById(categoryId)
		.orElseThrow(()-> new ResourceNotFoundException("Category" ,"Category ID",categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescripition(categoryDto.getCategoryDescripition());
		
		Category updateCat =this.categoryRepo.save(cat);
		
		return this.mapper.map(updateCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) 
	{
		Category cat =this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category" ,"Category ID",categoryId));
		
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public List<CategoryDto> getAllCategory() 
	{
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> Dtolist = categories.stream().map((cat)->this.mapper.map(cat, CategoryDto.class))
				                   .collect(Collectors.toList());
		
		return Dtolist;
	}

	@Override
	public CategoryDto getCategoryByID(int categoryId) 
	{
		Category cat =this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category" ,"Category ID",categoryId));
		
		return this.mapper.map(cat, CategoryDto.class);
	}

}
