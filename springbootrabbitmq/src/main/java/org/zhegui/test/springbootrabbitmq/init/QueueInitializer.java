package org.zhegui.test.springbootrabbitmq.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhegui.test.springbootrabbitmq.constant.QueueContant;
import org.zhegui.test.springbootrabbitmq.consumer.IRabbitMQConsumer;
import org.zhegui.test.springbootrabbitmq.listener.RecvGoodsMessage;
import org.zhegui.test.springbootrabbitmq.listener.RecvTestMessage;
import org.zhegui.test.springbootrabbitmq.producer.IMQProducer;

@Component
public class QueueInitializer {

	@Autowired
	private IMQProducer mQProducer;
	
	@Autowired
	private IRabbitMQConsumer rabbitMQConsumer;
	
	@Autowired
	private RecvTestMessage recvTestMessage;
	
	@Autowired
	private RecvGoodsMessage recvGoodsMessage;
	
	public void init(){
		System.out.println("创建测试队列"+QueueContant.QUEUE_RECV_TEST_USER);
		mQProducer.createQueue(QueueContant.EXCANGE_QUEUE_RECV_TEST_USER, QueueContant.QUEUE_RECV_TEST_USER);
		rabbitMQConsumer.startListener(QueueContant.QUEUE_RECV_TEST_USER, 1, recvTestMessage);
		System.out.println("创建测试队列"+QueueContant.QUEUE_RECV_TEST_USER+"完毕");
		
		System.out.println("------------------------------------");
		System.out.println("创建测试队列"+QueueContant.QUEUE_GOODS_TEST);
		mQProducer.createQueue(QueueContant.EXCANGE_GOODS_TEST, QueueContant.QUEUE_GOODS_TEST);
		rabbitMQConsumer.startListener(QueueContant.QUEUE_GOODS_TEST, 1, recvGoodsMessage);
		System.out.println("创建测试队列"+QueueContant.QUEUE_GOODS_TEST+"完毕");
	}
}
