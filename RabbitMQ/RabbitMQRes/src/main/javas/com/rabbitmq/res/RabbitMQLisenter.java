package com.rabbitmq.res;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;



public class RabbitMQLisenter implements MessageListener{

	public void onMessage(Message message) {
			System.out.println(new String(message.getBody()));
			int q = 1/0;
	}

}
