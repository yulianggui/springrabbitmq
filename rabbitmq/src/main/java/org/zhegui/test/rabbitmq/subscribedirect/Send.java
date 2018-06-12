package org.zhegui.test.rabbitmq.subscribedirect;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

	private final static String EXCHANGE_NAME = "DERECT_EXCHANGE_TEST";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = RabbitConnection.getConnectionFactory();
		Channel channel = connection.createChannel();
		
		//声明一个交换机,类型为DIRECT
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		String message = "subscribe fanout，模式的消息！";
		
		// 路由key
		String routKey = "recv1";
		
		//第二个值为路由键
		channel.basicPublish(EXCHANGE_NAME, routKey, null, message.getBytes("UTF-8"));
		//channel.basicPublish(EXCHANGE_NAME, "recv2", null, message.getBytes("UTF-8")); 同时发送对个routKey
		System.out.println("subscribe fanout 类型的消息已经发送！");
		
		channel.close();
		connection.close();
		
	}

}
