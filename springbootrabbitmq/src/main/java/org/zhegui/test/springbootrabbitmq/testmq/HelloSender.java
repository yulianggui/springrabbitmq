package org.zhegui.test.springbootrabbitmq.testmq;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	public void send(){
		String message = "spring boot test rabbitmq";
		this.rabbitTemplate.convertAndSend("springboothello", message);
		System.out.println("发送消息, message = "+message);
	}
	
	public void sendFanout(){
		String message = "spring boot hello fanout";
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString()); 
		this.rabbitTemplate.convertAndSend("springboot.fanout.hello.exchange", "", message,correlationId);
		System.out.println("发送消息，扇形订阅模式， mesage = "+message);
	}
	
	public void sendDirect(){
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString()); 
		String message = "spring boot hello direct";
		this.rabbitTemplate.convertAndSend("springboot.fanout.direct.exchange", "springboot.direct.hello.queue", message,correlationId);
		System.out.println("发送消息，direct订阅模式， mesage = "+message);
	}

	

}
