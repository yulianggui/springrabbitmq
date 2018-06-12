package org.zhegui.test.springbootrabbitmq.listener;

import org.springframework.stereotype.Component;
import org.zhegui.test.springbootrabbitmq.messagedto.MQGoods;

@Component
public class RecvGoodsMessage extends AbstractMQMessageListener<MQGoods>{

	@Override
	public void handleMessage(MQGoods message) throws Exception {
		System.out.println("处理成功信息，MQGoods = "+message);
		//System.out.println(1/0);
	}

	@Override
	public void handleFailuer(MQGoods message, Exception e) {
		System.out.println("处理失败信息，MQGoods = "+message);
		e.printStackTrace();
	}

}
