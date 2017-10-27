package com.rabbitmq.res;

import org.springframework.util.ErrorHandler;


public class RabbitMQResExceptionHandler implements ErrorHandler  {

	public void handleError(Throwable e) {
		System.out.println(e.getMessage());
	}

}
