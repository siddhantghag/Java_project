 package com.springboot.Main.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Main.Entity.User;
import com.springboot.Main.Exceptions.ResourceNotFoundException;
import com.springboot.Main.Payload.UserDto;
import com.springboot.Main.Repository.UserRepo;
import com.springboot.Main.Services.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDto createUser(UserDto dto) 
	{
		User user =this.dtoToUser(dto);
		User saveUser =this.repo.save(user);
		return this.UserTodto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto dto, int id) 
	{
		User user = this.repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id" ,id));
		
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		
		User updateuser = this.repo.save(user);
		UserDto dto1 =this.UserTodto(updateuser);
		return dto1;
	}

	@Override
	public UserDto getUserById(int id) 
	{
		User user =this.repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id" ,id));
		return this.UserTodto(user);
	}

	@Override
	public List<UserDto> getAllUser() 
	{
		List<User> users = this.repo.findAll();
		List<UserDto> dto =users.stream().map(user->this.UserTodto(user)).collect(Collectors.toList());
		return dto;
	}

	@Override
	public void deteleUser(int id) 
	{
		User user =this.repo.findById(id)
		            .orElseThrow(()->new ResourceNotFoundException("User","id" ,id));
		this.repo.delete(user); 
	}
	
	private User dtoToUser(UserDto dto)
	{
		User user = this.mapper	.map(dto, User.class) ;
		/*
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		*/
		return user;
	}
	
	private UserDto UserTodto(User user)
	{
		UserDto dto = this.mapper.map(user, UserDto.class); 
		
		/*
		 * dto.setId(user.getId()); 
		 * dto.setName(user.getName());
		 * dto.setEmail(user.getEmail()); 
		 * dto.setPassword(user.getPassword());
		 * dto.setAbout(user.getAbout());
		 */
		
		return dto;
	}
}
