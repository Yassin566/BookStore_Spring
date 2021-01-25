package com.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.dao.IUserDao;
import com.bookstore.dao.entity.User;
import com.bookstore.dao.repository.UserRepository;
import com.bookstore.dto.UserDto;
import com.bookstore.mapper.UserMapper;
import com.bookstore.service.IUserService;

@Service
public class UserService implements IUserService {
	

	@Autowired
	private IUserDao userDao;
	@Autowired
	private UserMapper userMapper;
	
	public PasswordEncoder passwordEncod() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public List<UserDto> getAll() {
		return userMapper.enityToDtoList(userDao.getAllUser());
	}

	@Override
	public Optional<User> dtoToUser(UserDto userDto) {
		return userMapper.dtoToEntity(userDto);
	}

	@Override
	public UserDto UserToDto(User user) {
		return userMapper.entityToDto(user);
	}

	@Override
	public User add(User user) {
		user.setPassword(passwordEncod().encode(user.getPassword()));
		return userDao.addUser(user);
	}

	@Override
	public boolean update(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteById(Long id) {
		return userDao.deleteUserById(id);
	}

	@Override
	public boolean deleteByUser(User user) {
		return userDao.deleteUser(user);
	}

	@Override
	public User findById(Long id) {
		Optional<User> userOptional = userDao.findById(id);
		return userOptional.orElseThrow(() -> new EntityNotFoundException("User not found with id : " + id));
	}
	
	public Optional<User> findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}
	

}
