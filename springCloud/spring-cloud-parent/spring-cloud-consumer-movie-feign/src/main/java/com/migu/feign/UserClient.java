package com.migu.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.migu.entity.User;

@FeignClient("spring-cloud-provider-user")
public interface UserClient {

	@RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable("id") Long id);
	// 2个坑：
	// 1.getMapping不支持，需手动加
	// 2.@PathVariable 需要加上value

}
