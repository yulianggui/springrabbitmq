package org.zhegui.test.springbootrabbitmq.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		CachingConnectionFactory cachingConnectionFactory = (CachingConnectionFactory)connectionFactory;
		cachingConnectionFactory.setPublisherConfirms(true);
		RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
		
		// 设置消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.setConfirmCallback(new ConfirmCallback(){

			//消息确认ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调。
			public void confirm(CorrelationData correData, boolean ack, String error) {
				System.out.println("消息确认回调：dataId="+(correData==null?"null":correData.getId()));
				if(ack){
					System.out.println("消费成功！， error="+error+"  "+(correData==null?"null":correData.getId()));
				}else{
					System.out.println("消费失败！，error="+error);
				}
			}
			
		});
		return rabbitTemplate;
	}
	
}
