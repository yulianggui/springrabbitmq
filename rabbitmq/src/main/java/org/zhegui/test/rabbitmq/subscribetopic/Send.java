package org.zhegui.test.rabbitmq.subscribetopic;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

	private final static String EXCHANGE_NAME = "TOIC_EXCHANGE_TEST";
	
	public static void main(String[] args) throws Exception {
		
		Connection connection = RabbitConnection.getConnectionFactory();
		Channel channel = connection.createChannel();
		
		//声明一个交换机,类型为DIRECT
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		
		String message = "subscribe fanout，模式的消息！";
		
		// 路由key
		String routKey = "item";  
		String routKey2 = "item.aab";
		String routKey3 = "aab.item.aab.c";
		String routKey4 = "aab.aab.item";
		//第二个值为路由键
		channel.basicPublish(EXCHANGE_NAME, routKey, null, (message+routKey).getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, routKey2, null, (message+routKey2).getBytes("UTF-8")); //同时发送对个routKey
		channel.basicPublish(EXCHANGE_NAME, routKey3, null, (message+routKey3).getBytes("UTF-8"));
		channel.basicPublish(EXCHANGE_NAME, routKey4, null, (message+routKey4).getBytes("UTF-8"));
		System.out.println("subscribe fanout 类型的消息已经发送！");
		
		channel.close();
		connection.close();
		
	}

}
