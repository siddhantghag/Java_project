package com.springboot.Main.Services;

import org.springframework.stereotype.Service;

import com.springboot.Main.Payload.CommentDto;

@Service
public interface CommentService 
{
	public CommentDto createComment(CommentDto commnetDto , int postId  );
	
	public void deleteComment(int commentId);
}
