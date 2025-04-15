package com.springboot.Main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Main.Entity.Category;
import com.springboot.Main.Entity.Post;
import com.springboot.Main.Entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>
{
	// All post of user show
	public List<Post> findByUser(User user);
	
	// All category of user show
	public List<Post> findByCategory(Category category);
	
	
	//Seraching by title
	public List<Post> findByTitleContaining(String title);
}
