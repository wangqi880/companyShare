package com.rabbitmq.ack;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;


/**
 * 
 * @author Administrator
 *
 */
public class SendReturnCallback implements ReturnCallback{

	//��Ϣ    δ   ���͵�ָ���� ���� 
	public void returnedMessage(Message message, int replyCode,
			String replyText, String exchange, String routingKey) {
			System.out.println(message+"��Ϣ  δ  ���͵�ָ����  ����"+replyCode+"--replyText--"+replyText);
	}

}
