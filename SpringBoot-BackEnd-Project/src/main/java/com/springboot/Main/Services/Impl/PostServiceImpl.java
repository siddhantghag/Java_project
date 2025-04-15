package com.springboot.Main.Services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.Main.Entity.Category;
import com.springboot.Main.Entity.Post;
import com.springboot.Main.Entity.User;
import com.springboot.Main.Exceptions.ResourceNotFoundException;
import com.springboot.Main.Payload.PostDto;
import com.springboot.Main.Payload.PostResponces;
import com.springboot.Main.Repository.CategoryRepo;
import com.springboot.Main.Repository.PostRepo;
import com.springboot.Main.Repository.UserRepo;
import com.springboot.Main.Services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto dto, int userId, int categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));

		Post post = this.mapper.map(dto, Post.class);
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);
		return this.mapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto, int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());

		Post updatepost = this.postRepo.save(post);
		return this.mapper.map(updatepost, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponces getAllPost(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		Sort sort = null;

		// Sort.by(sortBy) -> we can sort by the descending or ascending.

		// By using ternary operator - one line use
		// sortDirection.equalsIgnoreCase("asc") ? sort = Sort.by(sortBy).ascending() :
		// sort = Sort.by(sortBy).descending();

		if (sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}

		// Use Pagination ( Pageable - Abstract interface for pagination information)
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePosts = this.postRepo.findAll(pageable);

		List<Post> posts = pagePosts.getContent();

		List<PostDto> postsDto = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponces postResponces = new PostResponces();

		postResponces.setContent(postsDto);
		// pagePosts we can store all data , Means page no , page size , total element,
		// etc.
		postResponces.setPageNumber(pagePosts.getNumber());
		postResponces.setPageSize(pagePosts.getSize());
		postResponces.setTotalElement(pagePosts.getTotalElements());
		postResponces.setLastpage(pagePosts.isLast());

		return postResponces;
	}

	@Override 
	public PostDto getPostById(int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postsDto = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postsDto = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public List<PostDto> serachPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDto = posts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}
}
