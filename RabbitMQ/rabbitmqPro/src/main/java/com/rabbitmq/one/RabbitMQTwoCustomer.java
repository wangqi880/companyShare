package com.rabbitmq.one;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RabbitMQTwoCustomer {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setUsername("rabbitmq");
		cf.setPassword("rabbitmq");
		cf.setHost("192.168.206.202");
		Connection con = cf.newConnection();
		Channel channel = con.createChannel();
		channel.exchangeDeclare("directExchange", "direct");
		
		//System.out.println(channel.queueDeclare().getQueue()); 
		
		channel.queueDeclare("queue.direct", true, false, false, null);
		channel.queueBind("queue.direct", "directExchange", "direct.routingKey");
		
		channel.basicConsume("queue.direct", false, new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				
				System.out.println(new String(body,"utf-8"));
				
			}
		});
		
	}
}
