package com.migu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.migu.entity.User;

@RestController
public class MovieController {
	@Value("${user.userPath}")
	private String userPath;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/movie/{id}")
	public User getUserById(@PathVariable Long id) {
		return restTemplate.getForObject("http://localhost:7900/simple/" + id, User.class);
	}
}
