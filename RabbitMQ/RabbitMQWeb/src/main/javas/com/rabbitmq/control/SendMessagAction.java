package com.rabbitmq.control;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rabbitmq.listener.User;

@Controller
public class SendMessagAction {
	
	public void ini(){
		System.out.println("---------------");
	}
	@Autowired
	private AmqpTemplate template;
	
	@Autowired
	private RabbitTemplate rabbit;
	
	@RequestMapping("/testDirect")
	@ResponseBody
	public String testDirect(String msg){
		Map map = new HashMap();
		map.put("≤‚ ‘1", "≤‚ ‘÷µ1");
		map.put("≤‚ ‘2", "≤‚ ‘÷µ2");
		map.put("≤‚ ‘3", "≤‚ ‘÷µ3");
		
		Message msgs  = new Message(JSONObject.fromObject(map).toString().getBytes(),new MessageProperties());
		
		Message msgs1  = new Message("≤‚ ‘springRoutingkey1".getBytes(),new MessageProperties());
		template.send("springRoutingkey", msgs); 
		template.send("springRoutingkey1", msgs1); 
		return "sended";
	}
	
	@RequestMapping("/testTopic")
	@ResponseBody
	public String testTopic(String msg){
		Map map = new HashMap();
		map.put("≤‚ ‘1", "≤‚ ‘÷µ1");
		map.put("≤‚ ‘2", "≤‚ ‘÷µ2");
		map.put("≤‚ ‘3", "≤‚ ‘÷µ3");
		
		Message msgs  = new Message(JSONObject.fromObject(map).toString().getBytes(),new MessageProperties());
		
		Message msgs1  = new Message("≤‚ ‘rabbitmq.topic2.one.an".getBytes(),new MessageProperties());
		template.send("rabbitmq.topic2.an", msgs); 
		template.send("rabbitmq.topic2.one.an", msgs1); 
		return "sended";
	}
	
	@RequestMapping("/testReSend")
	@ResponseBody
	public String testReSend(String msg){
		Map map = new HashMap();
		map.put("≤‚ ‘1", "≤‚ ‘÷µ1");
		map.put("≤‚ ‘2", "≤‚ ‘÷µ2");
		map.put("≤‚ ‘3", "≤‚ ‘÷µ3");
		
		Message msgs  = new Message(JSONObject.fromObject(map).toString().getBytes(),new MessageProperties());
		
		Message msgs1  = new Message("rabbitmq.topic2.an".getBytes(),new MessageProperties());
		//template.send("rabbitmq.topic2.an", msgs); 
		
		rabbit.setRoutingKey("springRoutingkey");
		rabbit.correlationConvertAndSend("CorrelationData message Send",new CorrelationData("111"));
		return "sended";
	}
	
	
	@RequestMapping("/testAnnotation")
	@ResponseBody
	public String testAnnotation(String msg){
		if("pojo".equals(msg)){
			template.convertAndSend("springRoutingkey", new User("zhansan", 14)); 	
		}else{
			template.convertAndSend("springRoutingkey", "≤‚ ‘springRoutingkey1");	
		}
		
		
		return "sended";
	}
}
