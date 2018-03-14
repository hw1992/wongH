package com.wong.common;


public enum ResultCode {
	/** 成功 */
	SUCCESS("200", "成功"),
	
	/** 没有登录 */
	NOT_LOGIN("400", "没有登录"),
	
	/** 发生异常 */
	EXCEPTION("401", "发生异常"),
	
	/** 系统错误 */
	SYS_ERROR("402", "系统错误"),
	
	/** 参数错误 */
	PARAMS_ERROR("403", "参数错误 "),
	
	/** 不支持或已经废弃 */
	NOT_SUPPORTED("410", "不支持或已经废弃"),
	
	/** AuthCode错误 */
	INVALID_AUTHCODE("444", "无效的AuthCode"),

	/** 太频繁的调用 */
	TOO_FREQUENT("445", "太频繁的调用"),
	
	/** 用户名或密码错误 */
	INVALID_USERNAMEORPASSWORD("446","用户名或密码错误"),

	/** 用户不存在*/
	NOT_EXIST_USER("447","用户不存在"),

	/** 资源不存在*/
	NOT_EXIST_RESOURCE("448","资源不存在"),
	
	/** 用户已存在*/
	EXIST_USER("449","用户已存在"),

	/** 验证码错误*/
	INVALID_CODE("450","验证码错误"),

	/** 手机已存在*/
	EXIST_MOBILE("451","手机号已存在"),
	
	/** 资源已存在*/
	EXIST_RESOURCE("452","资源不存在"),
	
	/** 未知的错误 */
	UNKNOWN_ERROR("499", "未知错误"),
	
	/** 禁止的操作 */
	FORBBINDEN_DO("500", "禁止的操作"),
	
	/** 手机号黑名单 */
	BLOCKED("520", "手机号黑名单"),
	
	/** 书籍信息找不到 */
	UNKNOWN_BOOK("521", "获取书籍信息失败"),
	
	/** UUID不一致 */
	UUID_EXCEPTION("522", "UUID不一致");

	private ResultCode(String value, String msg){
		this.val = value;
		this.msg = msg;
	}
	
	public String val() {
		return val;
	}

	public String msg() {
		return msg;
	}
	
	private String val;
	private String msg;
}
