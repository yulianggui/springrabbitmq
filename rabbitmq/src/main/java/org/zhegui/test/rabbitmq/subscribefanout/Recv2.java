package org.zhegui.test.rabbitmq.subscribefanout;

import java.io.IOException;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recv2 {

	private final static String EXCHANGE_NAME = "FANOUT_EXCHANGE";
	
	public static void main(String[] args) throws Exception {
		Connection connection = RabbitConnection.getConnectionFactory();
		Channel channel = connection.createChannel();
		
		//声明一个交换机,类型为FANOUT
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
		
		//声明一个随机的队列名,这个队列是一个临时队列，消费完成关闭后会消失
		String queueName = channel.queueDeclare().getQueue();
		
		//绑定队列到交换机
		channel.queueBind(queueName,EXCHANGE_NAME, "");
		
		System.out.println("22222 [*] Waiting for messages. To exit press CTRL+C");
		
		//开始监听消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);

	}

}
