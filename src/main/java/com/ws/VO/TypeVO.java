package com.ws.VO;

import java.io.Serializable;

/**
 * @ClassName:  TypeVO
 * @Description:
 * @author: ws
 * @date:   2019年10月9日 下午3:26:16
 *
 */
public class TypeVO implements Serializable {
	
	private Integer value;
	private String name;

	public TypeVO() {
	}

	public TypeVO(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

}
