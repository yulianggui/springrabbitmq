package org.zhegui.test.springbootrabbitmq.testmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Configuration
@Component
public class MessageRecv {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	//如果相同，则只会在一个地方拿到
	/**
	 * 通过注解的方式
	 * @param message
	 */
	@RabbitListener(queues="springboot.fanout.hello.queue")
	public void recvFanout(String message){
		System.out.println("Fanout Receiver  : " + message);
	}
	
	/**
	 * 通过注解的方式
	 * @param message
	 */
	@RabbitListener(queues="springboot.direct.hello.queue")
	public void recvDirect(String message){
		System.out.println("direct Receiver  : " + message);
		return;
	}
	
	/**
	 * 通过监听的方式
	 * @return
	 */
	@Bean  
    public SimpleMessageListenerContainer messageContainer() {  
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());  
        container.setQueueNames(new String[]{"springboot.fanout.hello.queue"});  
        container.setMaxConcurrentConsumers(1);  
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认  
        container.setMessageListener(new ChannelAwareMessageListener() {  
  
            public void onMessage(Message message, Channel channel) throws Exception {  
                byte[] body = message.getBody();  
                System.out.println("aabbbbb receive msg : " + new String(body));  
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费  
            }  
        });  
        //手动开启监听
        container.start();
        return container;  
    } 
	
	@Bean  
    public SimpleMessageListenerContainer messageContainer2() {  
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());  
        container.setQueueNames(new String[]{"springboot.direct.hello.queue"});  
        container.setMaxConcurrentConsumers(1);  
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认  
        container.setMessageListener(new ChannelAwareMessageListener() {  
  
            public void onMessage(Message message, Channel channel) throws Exception {  
                byte[] body = message.getBody();  
                System.out.println("cccccc receive msg : " + new String(body));  
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费  
            }  
        });  
        return container;  
    } 
}
