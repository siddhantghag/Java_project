package com.springboot.Main.Services.Impl;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Main.Entity.Comment;
import com.springboot.Main.Entity.Post;
import com.springboot.Main.Exceptions.ResourceNotFoundException;
import com.springboot.Main.Payload.CommentDto;
import com.springboot.Main.Repository.CommentRepo;
import com.springboot.Main.Repository.PostRepo;
import com.springboot.Main.Services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentDto createComment(CommentDto commnetDto, int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("postId", "post id", postId));

		Comment comment = this.modelMapper.map(commnetDto, Comment.class);
		comment.setPost(post);
		Comment saveComment = this.commentRepo.save(comment);
		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("commentId", "comment id", commentId));
		this.commentRepo.delete(comment);	
	}

}
