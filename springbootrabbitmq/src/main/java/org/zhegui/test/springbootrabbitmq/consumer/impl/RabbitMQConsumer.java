package org.zhegui.test.springbootrabbitmq.consumer.impl;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhegui.test.springbootrabbitmq.consumer.IRabbitMQConsumer;
import org.zhegui.test.springbootrabbitmq.listener.AbstractMQMessageListener;

@Component
public class RabbitMQConsumer implements IRabbitMQConsumer{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public SimpleMessageListenerContainer startListener(String queueName, int maxConcurrentConsumers,
			AbstractMQMessageListener mqMessageListener) {
		System.out.println("设置监听.... 8888888888");
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());
		container.setQueueNames(queueName);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMaxConcurrentConsumers(maxConcurrentConsumers);
		container.setMessageListener(mqMessageListener);
		container.start();
		return container;
	}

}
