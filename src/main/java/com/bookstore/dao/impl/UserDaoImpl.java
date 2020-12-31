package com.bookstore.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bookstore.dao.IUserDao;
import com.bookstore.dao.entity.User;
import com.bookstore.dao.repository.UserRepository;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean updateUser(User user) {
		if (userRepository.existsById(user.getId())) {
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUserById(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		if (userRepository.existsById(user.getId())) {
			userRepository.delete(user);
			return true;
		}
		return false;
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

}
