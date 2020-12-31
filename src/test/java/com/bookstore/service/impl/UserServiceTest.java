package com.bookstore.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bookstore.dao.IUserDao;
import com.bookstore.dao.entity.User;
import com.bookstore.dao.impl.UserDaoImpl;
import com.bookstore.dao.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
@DataJpaTest
@Transactional
class UserServiceTest {
	
	
	@Mock
	private UserDaoImpl userDaoImpl;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetAllUser() {
		User user = new User(5L,"yassin","yassin","ROLE_ADMIN",true);
		Mockito.when(userDaoImpl.getAllUser()).thenReturn(Arrays.asList(user));
		Assert.assertEquals(1, userService.getAllUser().size());

	}

	@Test
	void testGetAll() {
	}

	@Test
	void testDeleteById() {
		User user = new User(5L,"yassin","yassin","ROLE_ADMIN",true);
		Mockito.when(userDaoImpl.deleteUserById(any(Long.class))).thenReturn(true);
		Assert.assertTrue(userService.deleteById(user.getId()));
	}

	@Test
	void testDeleteByUser() {
		User user = new User(5L,"yassin","yassin","ROLE_ADMIN",true);

		Mockito.when(userDaoImpl.deleteUser(any(User.class))).thenReturn(true);
		Assert.assertTrue(userService.deleteByUser(user));

	}

	@Test
	void testFindById() {
		User user = new User(5L,"yassin","yassin","ROLE_ADMIN",true);

		Mockito.when(userDaoImpl.findById(any(Long.class))).thenReturn(Optional.of(user));
		Assert.assertEquals(user, userService.findById(user.getId()));
	}

}
