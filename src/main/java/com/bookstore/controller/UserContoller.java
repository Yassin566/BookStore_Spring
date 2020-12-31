package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.dao.entity.User;
import com.bookstore.dto.UserDto;
import com.bookstore.service.IUserService;

@RestController
@RequestMapping(value = "/user")
public class UserContoller {
	@Autowired
	IUserService userService;
	
	@GetMapping("/")
	public List<UserDto> getAllUserDto() {
		return userService.getAll();
	}
	
	@GetMapping("/all")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/find")
	public User getUser(@RequestParam Long id) {
		return userService.findById(id);
	}
	
	@PostMapping("/findByDto")
	public Optional<User> getUserFromDto(@RequestBody UserDto userDto) {
		return userService.dtoToUser(userDto); 
	}

	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		return userService.add(user);
	}

	@DeleteMapping("/remove")
	public boolean deleteUser(@RequestParam Long id) {
		return userService.deleteById(id);
	}

	@PutMapping("/update")
	public boolean updateUser(@RequestBody User user) {
		return userService.update(user);
	}

}
