package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.dao.entity.User;
import com.bookstore.dto.UserDto;

public interface IUserService {

	List<User> getAllUser();

	List<UserDto> getAll();

	Optional<User> dtoToUser(UserDto userDto);

	UserDto UserToDto(User user);

	User add(User user);

	boolean update(User user);

	boolean deleteById(Long id);

	boolean deleteByUser(User user);

	User findById(Long id);
	
	Optional<User> findByUserName(String userName);

}
