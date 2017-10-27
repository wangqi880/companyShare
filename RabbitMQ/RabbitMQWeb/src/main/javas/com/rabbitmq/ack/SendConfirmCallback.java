package com.rabbitmq.ack;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class SendConfirmCallback implements ConfirmCallback {

	//CorrelationData ��Ҫ�������� ��Ϣ����ϢID�ġ�����Ϊ�����ò�����Ϣ�ģ���ô����Ϣû�з��͵�ָ����exchange����ô�죿
	public void confirm(CorrelationData correlationData, boolean ack,
			String cause) {
		if(ack){
			System.out.println(correlationData.toString() +"��Ϣ    ��   �͵�ָ����EXchange"+cause);
			System.out.println();System.out.println();
		}else{
			System.out.println(correlationData.toString() +"��Ϣ    δ   �͵�ָ����EXchange"+cause);
			System.out.println();System.out.println();
		}
	}

}
