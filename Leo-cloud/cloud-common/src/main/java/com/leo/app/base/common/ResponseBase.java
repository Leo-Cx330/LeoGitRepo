package com.leo.app.base.common;

import lombok.Data;

@Data
public class ResponseBase {

	private Integer code;
	private String msg;
	private Object data;

	public ResponseBase() {

	}

	public ResponseBase(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public ResponseBase(Integer code,String msg) {
		super();
		this.code = code;
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
		return "ResponseBase [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
