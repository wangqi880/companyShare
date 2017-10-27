package com.rabbitmq.res;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendMessagAction {
	
	public void ini(){
		System.out.println("---------------");
	}
	@Autowired
	private AmqpTemplate template;
	
	@RequestMapping("/sendMessage")
	@ResponseBody
	public String sendMessage(String msg){
		Map map = new HashMap();
		map.put("≤‚ ‘1", "≤‚ ‘÷µ1");
		map.put("≤‚ ‘2", "≤‚ ‘÷µ2");
		map.put("≤‚ ‘3", "≤‚ ‘÷µ3");
		
		Message msgs  = new Message(JSONObject.fromObject(map).toString().getBytes(),new MessageProperties());
		template.send("springRoutingkey", msgs); 
		
		return "∑¢ÀÕÕÍ±œ£°";
	}
}
