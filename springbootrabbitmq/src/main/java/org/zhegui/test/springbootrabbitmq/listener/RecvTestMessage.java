package org.zhegui.test.springbootrabbitmq.listener;

import org.springframework.stereotype.Component;
import org.zhegui.test.springbootrabbitmq.messagedto.MQUser;

@Component
public class RecvTestMessage extends AbstractMQMessageListener<MQUser>{

	@Override
	public void handleMessage(MQUser message) throws Exception {
		System.out.println("处理成功消息， MQUser = "+message);
	}

	@Override
	public void handleFailuer(MQUser message, Exception e) {
		System.out.println("处理失败消息, MQUser= "+message);
		e.printStackTrace();
	}

}
