package com.migu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.migu.entity.User;
import com.migu.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/simple/{id}")
	public User getUserById(@PathVariable Long id) {
		return userRepository.findOne(id);
	}
}
