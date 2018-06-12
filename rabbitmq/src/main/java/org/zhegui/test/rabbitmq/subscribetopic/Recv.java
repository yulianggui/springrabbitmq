package org.zhegui.test.rabbitmq.subscribetopic;

import java.io.IOException;

import org.zhegui.test.rabbitmq.util.RabbitConnection;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recv {

	private final static String EXCHANGE_NAME = "TOIC_EXCHANGE_TEST";
	
	private final static String QUEUE_NAME = "TOIC_recv1";
	
	public static void main(String[] args) throws Exception {
		Connection connection = RabbitConnection.getConnectionFactory();
		Channel channel = connection.createChannel();
		
		//声明一个交换机,类型为FANOUT
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String recv1 = "#"; //将会受到所有消息
		//绑定队列到交换机
		channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, recv1);
		
		System.out.println("11111 [*] Waiting for messages. To exit press CTRL+C");
		
		//开始监听消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("11111 [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);

	}

}
