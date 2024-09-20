package com.newproject.activity.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newproject.activity.entity.Users;
import com.newproject.activity.payload.UserDto;
import com.newproject.activity.repository.UserRepository;
import com.newproject.activity.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		Users users = userDtoEntity(userDto);

		// Save the user entity to the repository
		 Users savedUser = userRepository.save(users);

		return entityToUserDto(savedUser);
	}
	
	// Method to convert UserDto to Users entity
	private Users userDtoEntity(UserDto userDto) {
		Users users = new Users();
		users.setName(userDto.getName()); 
		users.setEmail(userDto.getEmail());
		users.setPassword(userDto.getPassword());
		return users;
	}
	//method to convert Entity to UserDto
	private UserDto entityToUserDto(Users savedUser) {
		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setEmail(savedUser.getEmail());
		userDto.setName(savedUser.getName());
		userDto.setPassword(savedUser.getPassword());
		return userDto;
	}
}
