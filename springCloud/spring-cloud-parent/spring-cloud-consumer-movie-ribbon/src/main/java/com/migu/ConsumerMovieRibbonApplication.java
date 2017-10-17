package com.migu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.config.TestConfig;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "spring-cloud-provider-user", configuration = TestConfig.class)
public class ConsumerMovieRibbonApplication {

	@Bean
	@LoadBalanced // 加入负载均衡ribbon
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieRibbonApplication.class, args);
	}
}
