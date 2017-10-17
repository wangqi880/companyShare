package com.migu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/movie/{id}")
	public User getUserById(@PathVariable Long id) {
		return restTemplate.getForObject("http://spring-cloud-provider-user/simple/" + id, User.class);
	}

	@GetMapping("test")
	public String testRibbon() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("spring-cloud-provider-user");
		System.out.println("服务user");
		System.out.println(
				serviceInstance.getHost() + ":" + serviceInstance.getPort() + ":" + serviceInstance.getServiceId());
		ServiceInstance serviceInstance2 = loadBalancerClient.choose("spring-cloud-provider-user2");
		System.out.println("服务user2");
		System.out.println(
				serviceInstance2.getHost() + ":" + serviceInstance2.getPort() + ":" + serviceInstance2.getServiceId());
		return "1";
	}
}
