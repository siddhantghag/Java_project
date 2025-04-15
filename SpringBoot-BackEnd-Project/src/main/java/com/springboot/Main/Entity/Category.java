package com.springboot.Main.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId; 
	private String categoryTitle;
	private String categoryDescripition;
	
	@OneToMany(mappedBy ="category", fetch=FetchType.EAGER)
	private List<Post> postlist =new ArrayList<>();

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

	public List<Post> getPostlist() {
		return postlist;
	}

	public void setPostlist(List<Post> postlist) {
		this.postlist = postlist;
	}

	public Category(int categoryId, String categoryTitle, String categoryDescripition, List<Post> postlist) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescripition = categoryDescripition;
		this.postlist = postlist;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDescripition="
				+ categoryDescripition + ", postlist=" + postlist + "]";
	}
}
