package com.rabbitmq.one;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQTwoSend {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setUsername("wq");
		cf.setPassword("wq");
		cf.setHost("192.168.137.129");
		Connection con = cf.newConnection();
		Channel channel = con.createChannel();
		channel.exchangeDeclare("directExchange", "direct");
		channel.queueDeclare("queue.direct", true, false, false, null);
		
		channel.basicPublish("directExchange", "direct.routingKey", null, "测试一个简单dirct队列创建".getBytes("utf-8"));
		
		channel.close();
		con.close();
		
	}
}
