package org.zhegui.test.rabbitmq.simplequeue;

import java.io.IOException;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Recv {

	private final static String QUEUE_NAME = "SIMPLE_HELLOE";
	
	public static void main(String[] args) throws Exception {
		//1.获取连接
		Connection connection = RabbitConnection.getConnectionFactory();
		//2.创建通道
		Channel channel = connection.createChannel();
		//3.声明一个队列，幂等性。 如果存在则不处理
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		//4.创建一个消费者,这种做法实际上没有没有确认已经消费
		Consumer consumer = new DefaultConsumer(channel){
			//处理队列的消息，socket长连接
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");  
				System.out.println("接收到消息： "+message);
			}
		};
		//5.监听队列
		channel.basicConsume(QUEUE_NAME, consumer);
		//这样，消费者就会一直监听声明的队列
	}

}
