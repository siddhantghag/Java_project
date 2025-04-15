package com.springboot.Main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Main.Payload.Apiresponces;
import com.springboot.Main.Payload.UserDto;
import com.springboot.Main.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
	@Autowired
	private UserService service;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto)
	{
		UserDto createdto = this.service.createUser(dto);
		return new ResponseEntity<>(createdto,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto , @PathVariable int id)
	{
		UserDto updateuser = this.service.updateUser(dto, id);
		return new ResponseEntity<>(updateuser,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Apiresponces> deleteUser(@PathVariable int id)
	{
		  this.service.deteleUser(id);
		  return new ResponseEntity<Apiresponces>(new Apiresponces("user deleted sucessfullt" ,true), HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> getDto =this.service.getAllUser();
		return new ResponseEntity<List<UserDto>>(getDto ,HttpStatus.OK);	
	}
	 	
	@GetMapping("/getsingleuser/{id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int id)
	{
		 UserDto getsingleuser = this.service.getUserById(id);
		 return new ResponseEntity<UserDto>(getsingleuser ,HttpStatus.OK);
    }	
}
