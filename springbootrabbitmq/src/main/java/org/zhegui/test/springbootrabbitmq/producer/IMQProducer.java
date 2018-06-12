package org.zhegui.test.springbootrabbitmq.producer;

import org.springframework.amqp.core.MessagePostProcessor;
import org.zhegui.test.springbootrabbitmq.message.IMQMessage;

public interface IMQProducer {

	/**
	 * 创建队列
	 * @param exchangeName  交换机名称
	 * @param queueName     队列名称
	 */
	public void createQueue(String exchangeName,String queueName);
	
	/**
	 * 延时队列
	 * @param exchangeName  
	 * @param queueName
	 * @param delay         延迟时间
	 */
	public void createDelayQueue(String exchangeName,String queueName, int delay);
	
	/**
	 * 队列是否存在
	 * @param queueName
	 */
	public boolean existsQueue(String queueName);
	
	/**
	 * 
	 * @param exchangeName
	 * @param queueName
	 * @param map
	 */
	public void sendMessage(String exchangeName, String queueName, final IMQMessage mqMessage);
	
	/**
	 * 
	 * @param exchangeName
	 * @param queueName
	 * @param map
	 */
	public void sendMessage(String exchangeName, String queueName, final IMQMessage mqMessage, MessagePostProcessor messagePostProcessor);
	
	/**
	 * 发送延迟消息
	 * @param exchangeName
	 * @param queueName
	 * @param mqMessage
	 */
	public void sendToDelayQueue(String exchangeName, String queueName, final IMQMessage mqMessage);
	
}
