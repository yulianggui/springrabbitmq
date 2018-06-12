package org.zhegui.test.springbootrabbitmq.listener;
import java.lang.reflect.ParameterizedType;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.zhegui.test.springbootrabbitmq.message.IMQMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

public abstract class AbstractMQMessageListener<T extends IMQMessage> implements ChannelAwareMessageListener {

	/**
	 * 获取Object转工具
	 */
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * T类
	 */
	private Class<T>                       messageClass; 
	
	@SuppressWarnings("unchecked")
	public AbstractMQMessageListener(){
		// 获取泛型参数类型，返回第一个参数，即T的类型
		messageClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		System.out.println("className="+messageClass.getName());
	}
	
	/**
	 * 监听处理器
	 */
	public void onMessage(Message message, Channel channel) {
		T t = null;
		try {
			byte[] body = message.getBody();
			t = objectMapper.readValue(body, messageClass);
			System.out.println("开始处理消息");
			this.handleMessage(t);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消费
		}catch (Exception e) {
			System.out.println("处理失败！");
			this.handleFailuer(t, e);
		}
		
	}
	
	/**
	 * 处理逻辑
	 * @param message
	 * @throws Exception
	 */
	public abstract void handleMessage(T message) throws Exception;

	/**
	 * 处理失败逻辑
	 * @param message
	 * @param e
	 */
	public abstract void handleFailuer(T message, Exception e);
	
}
