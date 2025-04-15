package com.springboot.Main.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto 
{
	private int categoryId;
	
	@NotBlank
	@Size(min = 4 , message = "Min size of Category Title is 4 char... ")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10 , message = " Min size of Category Title is 10 char....  ")
	private String categoryDescripition;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescripition() {
		return categoryDescripition;
	}
	public void setCategoryDescripition(String categoryDescripition) {
		this.categoryDescripition = categoryDescripition;
	}
	public CategoryDto(int categoryId, String categoryTitle, String categoryDescripition) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescripition = categoryDescripition;
	}
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDescripition="
				+ categoryDescripition + "]";
	}
}
