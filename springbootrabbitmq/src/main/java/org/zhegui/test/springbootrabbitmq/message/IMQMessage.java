package org.zhegui.test.springbootrabbitmq.message;

public interface IMQMessage {

	/**
	 * 设置消息Id
	 */
	public void setMsgId(String msgId);
	
	/**
	 * 获取消息ID
	 * @return
	 */
	public String getMsgId();
	
}
