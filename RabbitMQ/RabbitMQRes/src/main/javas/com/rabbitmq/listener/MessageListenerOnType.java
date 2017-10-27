package com.rabbitmq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/*
@RabbitListener(
	bindings=@QueueBinding(value=@Queue(autoDelete="false",durable="true",exclusive="false",value="annotationTopicQueue"),
	exchange=@Exchange(autoDelete="false",durable="true",type=ExchangeTypes.TOPIC,value="annotationTopicExChange" ),
	key="annotation.routingkey.#"))*/
/*@Component*/
public class MessageListenerOnType {

	/*@RabbitHandler*/
	public void getData(Message data){
		System.out.println(new String(data.getBody()));
	}
	
	/*@RabbitHandler*/
	public void getData(User data){
		System.out.println(data.toString());
	}
	
	/*@RabbitHandler*/
	public void getData(String msg){
		System.out.println(msg);
	}
}
