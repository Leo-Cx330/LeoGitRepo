package com.leo.app.base.constants;

public interface Constants {
	// 响应请求成功
		String HTTP_RES_CODE_200_VALUE = "success";
		// 系统错误
		String HTTP_RES_CODE_500_VALUE = "fail";
		// 响应请求成功code
		Integer HTTP_RES_CODE_200 = 200;
		// 系统错误
		Integer HTTP_RES_CODE_500 = 500;
		
		// 发送邮件
		String MSG_EMAIL ="email";
		// 会员token
		String USER_TOKEN ="USER_TOKEN";
		// 用户有效期 90天
		Long TOKEN_USER_TIME =(long) (60*60*24*90);
	    int COOKIE_TOKEN_USER_TIME =(60*60*24*90);
			
				
}
