package com.bookstore.dao.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.dao.entity.User;
import com.bookstore.dao.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
class UserDaoImplTest {
	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDaoImpl userDao;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetAllUser() {
		User user = new User("yassin","yassin","ROLE_ADMIN",true);
        Mockito.when((userRepository.findAll())).thenReturn(Arrays.asList(user));
        assertEquals(user, userDao.getAllUser().get(0));
	}

	@Test
	void testAddUser() {
		//User to saved : with id = null
		User user = new User("yassin","yassin","ROLE_ADMIN",true);

        //User saved with auto generated id
		User savedUser = new User(5L,"yassin","yassin","ROLE_ADMIN",true);

        Mockito.when((userRepository.save(any(User.class)))).thenReturn(savedUser);

        assertEquals(savedUser, userDao.addUser(savedUser));
	}

	@Test
	void testUpdateUser() {
		User userToUpdate = new User(1L,"yassin","yassin","ROLE_ADMIN",true);

        User updatedUser = new User(1L,"yassin","yassin","ROLE_ADMIN,ROLE_USER",true);
        Mockito.when((userRepository.existsById(any(Long.class)))).thenReturn(true);
        Mockito.when((userRepository.save(any(User.class)))).thenReturn(userToUpdate);

        assertTrue(userDao.updateUser(updatedUser));

	}

	@Test
	void testDeleteUserById() {
		User user = new User(5L,"yassin","yassin","ROLE_ADMIN",true);

        /**
         *  method deleteUser use id as param to delete user by id : in this method we use existById
         *  to check if the id exist in the database , if true we call delete user by id
         */

        Mockito.when(userRepository.existsById(any(Long.class))).thenReturn(true);

        userDao.deleteUserById(user.getId());

        Mockito.verify(userRepository).deleteById(user.getId());

//        assertEquals(userToUpdate,userDao.addUser(updatedUser));

	}

	@Test
	void testDeleteUser() {
	}

	@Test
	void testFindById() {
		User user = new User(5L,"yassin","yassin","ROLE_ADMIN",true);

        Mockito.when((userRepository.findById(any(Long.class)))).thenReturn(Optional.of(user));

        assertEquals(user, userDao.findById(user.getId()).get());
	}

}
