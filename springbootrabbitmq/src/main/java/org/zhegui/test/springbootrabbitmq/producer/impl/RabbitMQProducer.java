package org.zhegui.test.springbootrabbitmq.producer.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhegui.test.springbootrabbitmq.constant.MQContants;
import org.zhegui.test.springbootrabbitmq.message.IMQMessage;
import org.zhegui.test.springbootrabbitmq.producer.IMQProducer;

@Component
public class RabbitMQProducer implements IMQProducer{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
    private AmqpAdmin      rabbitAdmin;
	
	public void createQueue(String exchangeName, String queueName) {
		if(!this.existsQueue(queueName)){
			Map<String, Object> map = new HashMap();
	        //消息最大保存量1000,消息队列中最多只能有1000条消息，当第1001条消息发送过来的时候，会删除最早的那条消息
			map.put("x-max-length", 1000);
			
			//创建并声明一个direct的交换机
			DirectExchange directExchange = new DirectExchange(exchangeName);
			rabbitAdmin.declareExchange(directExchange);
			//创建并声明一个队列
			Queue queue = new Queue(queueName, true, false, false, map);
			rabbitAdmin.declareQueue(queue);
			// 将queue绑定到directExchange上
			rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(queueName));
		}
	}

	public void createDelayQueue(String exchangeName, String queueName, int delay) {
		if(!this.existsQueue(queueName)){
			//创建并声明一个direct的交换机
			DirectExchange directExchange = new DirectExchange(MQContants.X_DEAD_LETTER_EXCHANGE_PREFIX + exchangeName);
			rabbitAdmin.declareExchange(directExchange);
			
			/**
			 * 设置延时队列需要的
			 *     dlx 以及对于的 dead-letter-routing-key
			 */
			Map<String, Object> map = new HashMap();
            // 将上述声明的exchange作为此队列的Dead Letter exchanges（DLXs）
			map.put("x-dead-letter-exchange", exchangeName);
            // 重新设置队列中所产生dead-letter message的route-key
			map.put("x-dead-letter-routing-key", queueName);
			map.put("x-message-ttl", delay);
			// 真正的消费队列
			String delayQueueName = MQContants.DELAY_QUEUE_PREFIX + queueName;
			Queue queue = new Queue(delayQueueName, true, false, false, map);
			rabbitAdmin.declareQueue(queue);
			rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(delayQueueName));
		}
	}

	public boolean existsQueue(String queueName) {
		return rabbitAdmin.getQueueProperties(queueName) !=null;
	}

	public void sendMessage(String exchangeName, String queueName, IMQMessage mqMessage) {
		CorrelationData data = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(exchangeName, queueName, mqMessage,data);
	}

	public void sendMessage(String exchangeName, String queueName, IMQMessage mqMessage,
			MessagePostProcessor messagePostProcessor) {
		CorrelationData data = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(exchangeName, queueName, mqMessage, messagePostProcessor, data);
	}

	public void sendToDelayQueue(String exchangeName, String queueName, IMQMessage mqMessage) {
		CorrelationData data = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(MQContants.X_DEAD_LETTER_EXCHANGE_PREFIX + exchangeName, 
				MQContants.DELAY_QUEUE_PREFIX + queueName, mqMessage, data);
	}

}
