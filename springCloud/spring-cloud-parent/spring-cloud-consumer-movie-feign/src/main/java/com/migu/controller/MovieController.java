package com.migu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.migu.entity.User;
import com.migu.feign.UserClient;

@RestController
public class MovieController {

	@Autowired
	private UserClient userClient;

	@GetMapping("/movie/{id}")
	public User getUserById(@PathVariable Long id) {
		return this.userClient.getUserById(id);
	}
}