package org.zhegui.test.rabbitmq.subscribefanout;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

	private final static String EXCHANGE_NAME = "FANOUT_EXCHANGE";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = RabbitConnection.getConnectionFactory();
		Channel channel = connection.createChannel();
		
		//声明一个交换机,类型为FANOUT
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
		
		String message = "subscribe fanout，模式的消息！";
		
		//第二个值为路由键
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
		
		System.out.println("subscribe fanout 类型的消息已经发送！");
		
		channel.close();
		connection.close();
		
	}

}
