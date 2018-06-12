package org.zhegui.test.springbootrabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zhegui.test.springbootrabbitmq.constant.QueueContant;
import org.zhegui.test.springbootrabbitmq.messagedto.MQGoods;
import org.zhegui.test.springbootrabbitmq.messagedto.MQUser;
import org.zhegui.test.springbootrabbitmq.producer.IMQProducer;
import org.zhegui.test.springbootrabbitmq.testmq.HelloSender;

@Controller
@RequestMapping("/test")
public class TestMQ {

	@Autowired
	private HelloSender helloSender;
	
	@Autowired
	private IMQProducer mqProducer;
	
	/**
	 * 发送简单队列
	 */
	@RequestMapping("/mq01")
	public void test1(){
		helloSender.send();
	}
	
	/**
	 * 发送简单订阅队列
	 */
	@RequestMapping("/mq02")
	public void test2(){
		System.out.println("发送消息:Fanout");
		helloSender.sendFanout();
	}
	
	/**
	 * 发送direct队列
	 */
	@RequestMapping("/mq03")
	public void test3(){
		System.out.println("发送消息:Direct");
		helloSender.sendDirect();
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public void test02(){
		System.out.println("helloe");
	}
	
	/**
	 * 发送direct队列
	 */
	@RequestMapping("/mq05")
	public void test5(){
		System.out.println("发送消息:Direct 555555");
		MQUser mqUser = new MQUser();
		mqUser.setMsgId("111111111111");
		mqUser.setSex("n");
		mqUser.setUserName("余");
		mqUser.setUserNo("1300710336");
		mqProducer.sendMessage(QueueContant.EXCANGE_QUEUE_RECV_TEST_USER, QueueContant.QUEUE_RECV_TEST_USER, mqUser);
	}
	
	/**
	 * 发送direct队列
	 */
	@RequestMapping("/mq06")
	public void test6(){
		System.out.println("发送消息:Direct 6666666666");
		MQGoods goods = new MQGoods();
		goods.setMsgId("6666666666");
		goods.setGoodName("甜甜的芒果");
		goods.setGoodPrice("5.00");
		goods.setGoodShortName("芒果");
		mqProducer.sendMessage(QueueContant.EXCANGE_GOODS_TEST, QueueContant.QUEUE_GOODS_TEST, goods);
	}
	
}
