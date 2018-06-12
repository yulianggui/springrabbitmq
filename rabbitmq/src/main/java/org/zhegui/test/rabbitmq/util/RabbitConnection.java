package org.zhegui.test.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitConnection {

	public static Connection getConnectionFactory() throws Exception{
		//1.获取一个连接工厂，可以理解为jdbc数据源
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.81.15");
		factory.setPort(5672);
		factory.setUsername("admin");
		factory.setPassword("123456");
		factory.setVirtualHost("/");
		return factory.newConnection();
	}
	
}
