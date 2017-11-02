package com.rabbitmq.three;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQThreeSend2 {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setUsername("wq");
		cf.setPassword("wq");
		cf.setHost("192.168.137.129");
		Connection con = cf.newConnection();
		Channel channel = con.createChannel();
		//channel.exchangeDeclare("topicExchange", "topic");
		//channel.queueDeclare("queue.topic", true, false, false, null);
		
		channel.basicPublish("topicExchange", "topic.test", null, "测试一个简单topictest队列创建".getBytes("utf-8"));
		
		channel.close();
		con.close();
		
	}
}
