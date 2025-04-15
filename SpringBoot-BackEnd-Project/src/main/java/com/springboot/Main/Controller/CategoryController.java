package com.springboot.Main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Main.Payload.Apiresponces;
import com.springboot.Main.Payload.CategoryDto;
import com.springboot.Main.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
public class CategoryController 
{
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/createcat")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto categoryDto1 = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto1 ,HttpStatus.CREATED);
	}
	
	@PutMapping("/updatecat/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable int categoryId)
	{
		CategoryDto categoryDto2 =this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecat/{categoryId}")
	public ResponseEntity<Apiresponces> deleteCategory(@PathVariable int categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<Apiresponces>(new Apiresponces("Category is deleted ",true), HttpStatus.OK);
	}
	
	@GetMapping("/getcatbyid/{categoryId}")
	public ResponseEntity<CategoryDto> getById(@PathVariable int categoryId)
	{
		CategoryDto categoryDto =this.categoryService.getCategoryByID(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCat")
	public ResponseEntity<List<CategoryDto>> getAllCat()
	{
		List<CategoryDto> categoryDto =this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categoryDto,HttpStatus.OK);
	}
	
}
