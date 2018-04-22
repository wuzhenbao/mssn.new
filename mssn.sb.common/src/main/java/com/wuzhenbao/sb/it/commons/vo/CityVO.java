package com.wuzhenbao.sb.it.commons.vo;

import com.wuzhenbao.sb.it.commons.vo.base.BaseVO;

public class CityVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	
	private Integer cityId;
	private String cityNameCn;
	private String cityNameEn;
	public Integer getCityId() {
	
		return cityId;
	}
	public void setCityId(Integer cityId) {
	
		this.cityId = cityId;
	}
	public String getCityNameCn() {
	
		return cityNameCn;
	}
	public void setCityNameCn(String cityNameCn) {
	
		this.cityNameCn = cityNameCn;
	}
	public String getCityNameEn() {
	
		return cityNameEn;
	}
	public void setCityNameEn(String cityNameEn) {
	
		this.cityNameEn = cityNameEn;
	}
	   
}
