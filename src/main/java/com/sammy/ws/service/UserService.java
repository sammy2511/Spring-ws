package com.sammy.ws.service;

import org.springframework.stereotype.Service;

import com.sammy.ws.shared.dto.UserDto;

@Service
public interface UserService {
	
	public UserDto createUser (UserDto userDto);

}
