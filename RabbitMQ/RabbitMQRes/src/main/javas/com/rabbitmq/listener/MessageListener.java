package com.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*@RabbitListener(admin="annotationAdmin", queues={"springQueue","springQueue1"}
	bindings=@QueueBinding(value=@Queue(autoDelete="false",durable="true",exclusive="false",value="springQueue"),
	exchange=@Exchange(autoDelete="false",durable="true",type=ExchangeTypes.DIRECT,value="annotationDirect" )
	,key=""))*/

@Component
/*@RabbitListener(id="muilt", queues={"springQueue"})*/
public class MessageListener {

	/*@RabbitHandler*/
	public void getData(String data){
		System.out.println(data);
	}
	
	/*@RabbitHandler*/
	public void getData(User user){
		System.out.println(user.toString());
	}
}
