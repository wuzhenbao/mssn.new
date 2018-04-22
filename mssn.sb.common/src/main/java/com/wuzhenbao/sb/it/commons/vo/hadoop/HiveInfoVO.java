package com.wuzhenbao.sb.it.commons.vo.hadoop;

import com.wuzhenbao.sb.it.commons.vo.base.JqueryTreeVO;

public class HiveInfoVO extends JqueryTreeVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 表名称
	 */
	private String desc;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
