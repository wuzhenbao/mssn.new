package com.wuzhenbao.sb.it.commons.vo.kafka;

import com.wuzhenbao.sb.it.commons.vo.base.JqueryTreeVO;

public class KafkaTopicVO extends JqueryTreeVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2595502413588710129L;
	private String topicName;
	private String message;
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
