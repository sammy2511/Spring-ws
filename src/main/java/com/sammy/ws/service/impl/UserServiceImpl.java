package com.sammy.ws.service.impl;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sammy.ws.io.entity.UserEntity;
import com.sammy.ws.repository.UserRepository;
import com.sammy.ws.service.UserService;
import com.sammy.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		//copy from request var
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDto, entity);
		
		//hardcode some values
		entity.setEncryptedPassword("test");
		entity.setUserId("testUserId");

		//Save them to db
		UserEntity userEntity = userRepository.save(entity);
		
		//Return response
		UserDto response = new UserDto();
		BeanUtils.copyProperties(userEntity, response);
		return response;
	}

}
