package com.sammy.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sammy.ws.shared.dto.UserDto;

@Service
public interface UserService extends UserDetailsService{
	
	public UserDto createUser (UserDto userDto);

}
