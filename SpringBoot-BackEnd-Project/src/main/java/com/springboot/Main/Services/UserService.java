package com.springboot.Main.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.springboot.Main.Payload.UserDto;

@Service
public interface UserService 
{
	public UserDto createUser(UserDto dto);
	public UserDto updateUser(UserDto dto ,int id);
	public UserDto getUserById(int id);
	public List<UserDto> getAllUser();
	public void deteleUser(int id);
}
