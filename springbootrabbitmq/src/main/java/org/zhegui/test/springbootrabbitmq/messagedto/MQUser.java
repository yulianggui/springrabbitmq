package org.zhegui.test.springbootrabbitmq.messagedto;

import org.zhegui.test.springbootrabbitmq.message.BaseMQMessage;

public class MQUser extends BaseMQMessage{

	private String userName;
	
	private String userNo;
	
	private String sex;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "MQUser [userName=" + userName + ", userNo=" + userNo + ", sex=" + sex + "]";
	}
	

}
