package com.rabbitmq.ack;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class SendConfirmCallback implements ConfirmCallback {

	//CorrelationData 主要是用来给 消息加消息ID的。。因为这里拿不到消息的，那么当消息没有发送到指定的exchange上怎么办？
	public void confirm(CorrelationData correlationData, boolean ack,
			String cause) {
		if(ack){
			System.out.println(correlationData.toString() +"消息    发   送到指定的EXchange"+cause);
			System.out.println();System.out.println();
		}else{
			System.out.println(correlationData.toString() +"消息    未   送到指定的EXchange"+cause);
			System.out.println();System.out.println();
		}
	}

}
