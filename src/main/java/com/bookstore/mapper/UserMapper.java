package com.bookstore.mapper;

import com.bookstore.dao.IUserDao;
import com.bookstore.dao.entity.User;
import com.bookstore.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	@Autowired
	IUserDao userDao;

	public UserDto entityToDto(User user) {
		return UserDto.builder().id(user.getId()).userName(user.getUserName()).build();
	}

	public Optional<User> dtoToEntity(UserDto userDto) {
		return userDao.findById(userDto.getId());
	}

	public List<UserDto> enityToDtoList(List<User> users) {
		return users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
	}

	public List<Optional<User>> dtoToEntity(List<UserDto> listUserDto) {
		return listUserDto.stream().map(userDto -> dtoToEntity(userDto)).collect(Collectors.toList());
	}

}
