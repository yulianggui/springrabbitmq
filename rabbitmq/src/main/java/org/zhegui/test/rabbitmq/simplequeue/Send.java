package org.zhegui.test.rabbitmq.simplequeue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 简单队列，生产者1
 * @author Zhegui
 *
 */
public class Send {

	private final static String QUEUE_NAME = "SIMPLE_HELLOE";
	
	public static void main(String[] args){
		
		
		Connection connection = null;  
        Channel channel = null;  
		//1.获取一个连接工厂，可以理解为jdbc数据源
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.81.15");
		factory.setPort(5672);
		factory.setUsername("admin");
		factory.setPassword("123456");
		factory.setVirtualHost("/");
		//2.获取一个连接
		try {
			connection = factory.newConnection();
			//3.创建了一个通道（channel），大部分的API操作均在这里完成。
			//理解为sql 的Statement 
			channel = connection.createChannel();
			//4.生明一个队列
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			String message = "Hello,你好！aaa";
			//发送消息
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println("send1 发送已经消息： [hello,你好！]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
