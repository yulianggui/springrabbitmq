package org.zhegui.test.springbootrabbitmq.testmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这里是最简单的demo
 * @author Zhegui
 *
 */
@Configuration
public class RabbitConfig {

	// 简单的队列
	@Bean
	public Queue queue(){
		return new Queue("springboothello");
	}
	
	@Bean
	public Queue fanoutQueue(){
		return new Queue("springboot.fanout.hello.queue");
	}
	
	// 创建交换机，订阅模式
	@Bean
	public FanoutExchange fanoutExchange(){
		return new FanoutExchange("springboot.fanout.hello.exchange");
	}
	
	//绑定队列到交换机
	@Bean
	public Binding bindingFanout(){
		return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
	}
	
	//注入direct交换机
	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange("springboot.fanout.direct.exchange");
	}
	
	//注入队列
	@Bean
	public Queue directQueue(){
		return new Queue("springboot.direct.hello.queue");
	}
	
	@Bean
	public Binding bingDirectQueue(){
		return BindingBuilder.bind(directQueue()).to(directExchange()).with("springboot.direct.hello.queue");
	}
	
}
