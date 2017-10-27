package com.rabbitmq.ack;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;


/**
 * 
 * @author Administrator
 *
 */
public class SendReturnCallback implements ReturnCallback{

	//消息    未   发送到指定的 队列 
	public void returnedMessage(Message message, int replyCode,
			String replyText, String exchange, String routingKey) {
			System.out.println(message+"消息  未  发送到指定的  队列"+replyCode+"--replyText--"+replyText);
	}

}
