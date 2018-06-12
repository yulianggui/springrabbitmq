package org.zhegui.test.rabbitmq.simplequeue;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Recv2 {

private final static String QUEUE_NAME = "SIMPLE_HELLOE";
	
	public static void main(String[] args) throws Exception {
		//1.获取连接
		Connection connection = RabbitConnection.getConnectionFactory();
		//2.创建通道
		Channel channel = connection.createChannel();
		//3.声明一个队列，幂等性。 如果存在则不处理
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		//4.定义队列的消费者
		QueueingConsumer concumer = new QueueingConsumer(channel);
		//监听队列
		channel.basicConsume(QUEUE_NAME, true,concumer);
		
		while(true){
			QueueingConsumer.Delivery deliver = concumer.nextDelivery();
			String message = new String(deliver.getBody());
			System.out.println("[x] received "+message);
		}
	}


}
