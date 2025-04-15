package com.springboot.Main.Payload;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.springboot.Main.Entity.Comment;


public class PostDto implements Serializable
{
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date date;
	private UserDto user;
	private CategoryDto category;
	private Set<CommentDto> comment = new HashSet<>();
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public Set<CommentDto> getComment() {
		return comment;
	}
	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}
	public PostDto(int postId, String title, String content, String imageName, Date date, UserDto user,
			CategoryDto category, Set<CommentDto> comment) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.date = date;
		this.user = user;
		this.category = category;
		this.comment = comment;
	}
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PostDto [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", date=" + date + ", user=" + user + ", category=" + category + ", comment=" + comment + "]";
	}	
}
