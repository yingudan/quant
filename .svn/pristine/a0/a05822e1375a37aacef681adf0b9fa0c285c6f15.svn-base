/*
*@Title:  ValidateType.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月19日 上午9:51:59   
 * @version 
*/
package com.ujuit.quant.user.constants;


/*
 * 验证类型   
 * @author: cly     
 * @date:   2017年4月19日 上午9:51:59   
 * @version 
*/
public enum ValidateType {
	/**
	 * 登陆连续失败三次  需要图片验证
	 */
	Login(1,"login"),
	/**
	 * 注册
	 */
	Register(2,"register"),
	
	/**
	 * 修改密码
	 */
	ChangePwd(3,"change_pwd"),
	
	;
	
	private ValidateType(int value,String key){
		setValue(value);
		setKey(key);
	}
	
	int value;
	
	String key;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public static ValidateType select(int value){
		for(ValidateType validateType:values()){
			if(value == validateType.getValue()){
				return validateType;
			}
		}
		return Login;
	}
}
