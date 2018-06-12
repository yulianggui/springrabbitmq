package org.zhegui.test.rabbitmq.workqueue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.swing.plaf.SliderUI;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 简单队列，生产者1
 * @author Zhegui
 *
 */
public class Send {

	private final static String QUEUE_NAME = "WORK_HELLOE";
	
	public static void main(String[] args){
		
		
		Connection connection = null;  
        Channel channel = null;  
		//2.获取一个连接
		try {
			connection = RabbitConnection.getConnectionFactory();;
			//3.创建了一个通道（channel），大部分的API操作均在这里完成。
			//理解为sql 的Statement 
			channel = connection.createChannel();
			//4.生明一个队列
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			for(int i=0; i<50; i++){
				String message = "work工作队列，Hello,你好！aaa + " + i;
				//发送消息
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
				System.out.println("send1 发送已经消息： [hello,你好！] + message = " + message);
				Thread.sleep(i*10);
			}
			
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
