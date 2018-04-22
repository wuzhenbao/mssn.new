package com.wuzhenbao.sb.it.commons.vo.kafka;

import com.wuzhenbao.sb.it.commons.vo.base.BaseVO;

public class KafkaMessageVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
