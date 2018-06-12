package org.zhegui.test.springbootrabbitmq.message;

public abstract class BaseMQMessage implements IMQMessage{

	private String msgId;
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgId() {
		return this.msgId;
	}
	
}
