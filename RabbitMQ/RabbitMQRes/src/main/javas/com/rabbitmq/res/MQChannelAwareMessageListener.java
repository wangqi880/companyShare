package com.rabbitmq.res;

import java.util.Random;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

public class MQChannelAwareMessageListener implements ChannelAwareMessageListener  {

	public void onMessage(Message msg, Channel channel) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			Random d = new Random();
			int a = d.nextInt(10);
			if( a>5){
				System.out.println(a+"��Ϣ��������У�"+new String(msg.getBody()));
				throw new Exception("�����������һ���쳣����");
			}else{
				System.out.println("�յ���Ϣ��"+new String(msg.getBody()));
				channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
			}
		} catch (Exception e) {
			channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
		}
		
		
		
	}

}
