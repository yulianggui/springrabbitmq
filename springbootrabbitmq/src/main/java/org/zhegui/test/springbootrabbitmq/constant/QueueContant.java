package org.zhegui.test.springbootrabbitmq.constant;

public interface QueueContant {
	
	String QUEUE_PREFIX = "QUEUE_";
	
	String EXCANGE_PREFIX = "EXCANGE_";
	
	String QUEUE_RECV_TEST_USER = QUEUE_PREFIX + "FZ_DIRECT_RECV_TEST";
	
	String EXCANGE_QUEUE_RECV_TEST_USER = EXCANGE_PREFIX + "FZ_DIRECT_RECV_TEST";
	
	String QUEUE_GOODS_TEST = QUEUE_PREFIX + "QUEUE_GOODS_TEST";
	
	String EXCANGE_GOODS_TEST = EXCANGE_PREFIX + "EXCANGE_GOODS_TEST";
}
