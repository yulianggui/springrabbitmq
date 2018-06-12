package org.zhegui.test.springbootrabbitmq.messagedto;

import org.zhegui.test.springbootrabbitmq.message.BaseMQMessage;

public class MQGoods extends BaseMQMessage{

	private String goodName;
	
	private String goodPrice;
	
	private String goodShortName;

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(String goodPrice) {
		this.goodPrice = goodPrice;
	}

	public String getGoodShortName() {
		return goodShortName;
	}

	public void setGoodShortName(String goodShortName) {
		this.goodShortName = goodShortName;
	}

	@Override
	public String toString() {
		return "MQGoods [goodName=" + goodName + ", goodPrice=" + goodPrice + ", goodShortName=" + goodShortName + "]";
	}
	
}
