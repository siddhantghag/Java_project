package com.springboot.Main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Main.Config.AppConstants;
import com.springboot.Main.Payload.Apiresponces;
import com.springboot.Main.Payload.PostDto;
import com.springboot.Main.Payload.PostResponces;
import com.springboot.Main.Services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController
{
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto ,@PathVariable int userId ,@PathVariable int categoryId)
	{
		PostDto createPost =this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost ,HttpStatus.CREATED) ;	
	}
	
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId )
	{
		List<PostDto> getpostbycat = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(getpostbycat , HttpStatus.OK);
	}
	
	@GetMapping("/user/{UserId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int UserId)
	{
		List<PostDto> getpostbyuser = this.postService.getPostByUser(UserId);
		return new ResponseEntity<List<PostDto>>(getpostbyuser , HttpStatus.OK);
	}
	
	@GetMapping("/allgetpost/post")
	public ResponseEntity< PostResponces >getAllPost(
			@RequestParam(value="pageNumber" , defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value="pageSize" , defaultValue = AppConstants.PAGE_SIZE ,required = false) int pageSize,
	        @RequestParam(value="sortBy" , defaultValue = AppConstants.SORT_BY ,required = false) String sortBy,
	        @RequestParam(value="sortDirection" , defaultValue = AppConstants.SORT_DIRECTION ,required = false) String sortDirection ) 
	{  
		PostResponces getallpost = this.postService.getAllPost(pageNumber ,pageSize ,sortBy , sortDirection);
		return new ResponseEntity< PostResponces>(getallpost ,HttpStatus.OK); 
	}
	
	@GetMapping("/getpostbypost/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable  int postId )
	{
		PostDto postDto =this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto ,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletepostbyid/{postId}")
	public Apiresponces deletePostById(@PathVariable int postId)
	{
		 this.postService.deletePost(postId);
		return new Apiresponces("Post is sucessfully deleted....." ,true);
	}
	
	@PutMapping("/updatepostbyid/{postId}")
	public ResponseEntity<PostDto> updatePost( @RequestBody PostDto dto , @PathVariable int postId)
	{
		PostDto updatepost =this.postService.updatePost(dto, postId);
		return new ResponseEntity<PostDto>(updatepost ,HttpStatus.OK);
	}
	
	@GetMapping("/searchbytitle/{keywords}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keywords)
	{ 
		List<PostDto> searchresult= this.postService.serachPosts(keywords);
		return new ResponseEntity<List<PostDto>>(searchresult ,HttpStatus.OK); 
	}
}
  


