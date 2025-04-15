package com.springboot.Main.Payload;

import java.util.HashSet;
import java.util.Set;

import com.springboot.Main.Entity.Comment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto 
{
	private int id;
	
	@NotEmpty(message = "username shouldn't be null")
	@Size(min=4, message = "username must be min of four characters")
	private String name;
	
	@Email(message = "email address is not valid")
	private String email;
	
	@NotEmpty(message = "password shouldn't be null")
	@Size(min = 3 ,max = 10 , message = "password must be min 3 characters and max must be 10 characters")
	private String password;
	
	@NotEmpty(message = "about shouldn't be null")
	private String about;
	
	private Set<Comment> comment= new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	public UserDto(int id,
			@NotEmpty(message = "username shouldn't be null") @Size(min = 4, message = "username must be min of four characters") String name,
			@Email(message = "email address is not valid") String email,
			@NotEmpty(message = "password shouldn't be null") @Size(min = 3, max = 10, message = "password must be min 3 characters and max must be 10 characters") String password,
			@NotEmpty(message = "about shouldn't be null") String about, Set<Comment> comment) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.comment = comment;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + ", comment=" + comment + "]";
	}
	
	
}
