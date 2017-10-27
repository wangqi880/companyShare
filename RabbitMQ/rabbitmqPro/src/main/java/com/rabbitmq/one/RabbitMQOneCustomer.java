package com.rabbitmq.one;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RabbitMQOneCustomer {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setHost("192.168.206.202");
		cf.setPort(AMQP.PROTOCOL.PORT);
		//cf.setVirtualHost("/");
		cf.setPassword("rabbitmq");
		cf.setUsername("rabbitmq");
		Connection con =  cf.newConnection();
		Channel c = con.createChannel();
		c.queueDeclare("rabbitmq.queueOne", false, false, false, null);
		Consumer consumer = new DefaultConsumer(c){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
			System.out.println(new String(body));
			}
		};
		c.basicConsume("rabbitmq.queueOne", true, consumer);
	}
}
