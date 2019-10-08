package com.leo.app.base.common;

import lombok.Data;

@Data
public class ResponseBase {

	private Integer rtnCode;
	private String msg;
	private Object data;

	public ResponseBase() {

	}

	public ResponseBase(Integer rtnCode, String msg, Object data) {
		super();
		this.rtnCode = rtnCode;
		this.msg = msg;
		this.data = data;
	}
	public ResponseBase(Integer rtnCode,String msg) {
		super();
		this.rtnCode = rtnCode;
		this.msg = msg;
		this.data =data;
	}

	public static  ResponseBase success( String msg, Object data) {
		return new ResponseBase(0,msg,data);
	}
	public static  ResponseBase fail( String msg) {
		return new ResponseBase(-1,msg);
	}

	@Override
	public String toString() {
		return "ResponseBase [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
	}

}
