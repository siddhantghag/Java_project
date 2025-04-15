 package com.springboot.Main.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.Main.Entity.Post;
import com.springboot.Main.Payload.PostDto;
import com.springboot.Main.Payload.PostResponces;

@Service
public interface PostService 
{
	public PostDto createPost(PostDto dto , int userId ,int categoryId);
	
	public PostDto updatePost(PostDto dto ,int postId);
	
	public void deletePost(int postId);
	
	public PostResponces getAllPost(int pageNumber , int pageSize ,String sortBy ,String sortDirection);
	
	public PostDto getPostById(int postId);
	
	//get all posts by category
	public List<PostDto> getPostByCategory(int categoryId);
	
	//get all posts by user
	public List<PostDto> getPostByUser(int userId);
	
	//search posts
	public List<PostDto> serachPosts(String keyword);
}
