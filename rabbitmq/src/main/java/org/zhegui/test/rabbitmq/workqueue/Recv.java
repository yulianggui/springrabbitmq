package org.zhegui.test.rabbitmq.workqueue;

import java.io.IOException;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Recv {

	private final static String QUEUE_NAME = "WORK_HELLOE";
	
	public static void main(String[] args) throws Exception {
		//1.获取连接
		Connection connection = RabbitConnection.getConnectionFactory();
		//2.创建通道
		final Channel channel = connection.createChannel();
		//3.声明一个队列，幂等性。 如果存在则不处理
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1); // 每一次只拿一条，实现能者多劳，如果不是这样，每次平摊拿
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		
		//4.创建一个消费者
		final Consumer consumer = new DefaultConsumer(channel){
			//处理队列的消息，socket长连接
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");  
				System.out.println("接收到消息： "+message);
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//手动确认消息被消费
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		//5.监听队列，（第二个参数默认为自动应答true，即消费后回自动告知生产者，消息已经被消费）,false为手动确认
		channel.basicConsume(QUEUE_NAME,false, consumer);
		//这样，消费者就会一直监听声明的队列
	}

}
