package org.zhegui.test.springbootrabbitmq.consumer;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.zhegui.test.springbootrabbitmq.listener.AbstractMQMessageListener;

public interface IRabbitMQConsumer {

	/**
	 * 开启监听器
	 * @param queueName
	 * @param maxConcurrentConsumers
	 * @param mqMessageListener
	 */
	public SimpleMessageListenerContainer startListener(String queueName, final int maxConcurrentConsumers, AbstractMQMessageListener mqMessageListener);
}
