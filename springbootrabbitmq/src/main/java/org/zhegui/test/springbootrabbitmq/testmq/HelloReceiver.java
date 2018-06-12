package org.zhegui.test.springbootrabbitmq.testmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "springboothello")
public class HelloReceiver {

	@RabbitHandler
	public void recv(String message){
		System.out.println("Receiver  : " + message);
	}
}
