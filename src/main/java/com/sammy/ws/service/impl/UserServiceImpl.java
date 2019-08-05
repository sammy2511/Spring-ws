package com.sammy.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sammy.ws.io.entity.UserEntity;
import com.sammy.ws.repository.UserRepository;
import com.sammy.ws.service.UserService;
import com.sammy.ws.shared.dto.UserDto;
import com.sammy.ws.shared.dto.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;

	@Override
	public UserDto createUser(UserDto userDto) {

		UserEntity storedUser = userRepository.findUserByEmail(userDto.getEmail());
		
		if(storedUser != null) throw new RuntimeException("Record Already Exists");

		// copy from request var
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDto, entity);

		// Generating Encrypted password and UserId
		entity.setEncryptedPassword(bcrypt.encode(userDto.getPassword()));
		
		String userId = utils.generateUserId(30);
		
		entity.setUserId(userId);

		// Save them to db
		UserEntity userEntity = userRepository.save(entity);

		// Return response
		UserDto response = new UserDto();
		BeanUtils.copyProperties(userEntity, response);
		return response;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findUserByEmail(username);
		
		if(entity == null) throw new UsernameNotFoundException(username);
		
		return new User(entity.getEmail(),entity.getEncryptedPassword(),new ArrayList<>());
	}

}
