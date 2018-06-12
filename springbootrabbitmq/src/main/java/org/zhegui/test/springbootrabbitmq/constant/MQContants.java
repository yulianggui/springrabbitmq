package org.zhegui.test.springbootrabbitmq.constant;

public interface MQContants {

	/**
	 * 延时dlx（交换机前缀）
	 */
	String X_DEAD_LETTER_EXCHANGE_PREFIX = "dlx_";

	/**
	 * 延时队列（缓存）
	 */
    String DELAY_QUEUE_PREFIX = "DELAY_";
}
