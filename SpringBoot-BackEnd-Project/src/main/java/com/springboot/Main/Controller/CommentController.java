package com.springboot.Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Main.Payload.Apiresponces;
import com.springboot.Main.Payload.CommentDto;
import com.springboot.Main.Services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/postId/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId) {
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/commentId/{commentId}")
	public ResponseEntity<Apiresponces> deleteComment(@PathVariable int commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<Apiresponces>(new Apiresponces("Delete comment successfully", true), HttpStatus.OK);
	}
}
