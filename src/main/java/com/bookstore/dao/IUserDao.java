package com.bookstore.dao;

import java.util.List;
import java.util.Optional;
import com.bookstore.dao.entity.User;

public interface IUserDao {
	
	List<User> getAllUser();

	User addUser(User user);

	boolean updateUser(User user);

	boolean deleteUserById(Long id);

	boolean deleteUser(User user);

	Optional<User> findById(Long id);

}
