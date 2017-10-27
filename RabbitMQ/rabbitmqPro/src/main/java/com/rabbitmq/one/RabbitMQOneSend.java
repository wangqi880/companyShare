package com.rabbitmq.one;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQOneSend {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory cf = new ConnectionFactory();
		//cf.setVirtualHost("/");
		cf.setUsername("rabbitmq");
		cf.setPassword("rabbitmq");
		cf.setHost("192.168.206.202");
		//cf.setPort("");
		Connection c =  cf.newConnection();
		Channel channel = c.createChannel();
		//channel.queueDelete("rabbitmq.queueOne");
		channel.queueDeclare("rabbitmq.queueOne", false, false, false, null);
		
		channel.basicPublish("", "rabbitmq.queueOne", null,"测试一个简单队列创建".getBytes("utf-8"));
		
		System.out.println("----------------");
		channel.close();
		c.close();
	}
}
