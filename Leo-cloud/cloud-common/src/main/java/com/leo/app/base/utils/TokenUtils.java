package com.leo.app.base.utils;

import com.leo.app.base.constants.Constants;

import java.util.UUID;

public class TokenUtils {

	 public static String getUserToken(){
		 return Constants.USER_TOKEN+"-"+UUID.randomUUID();
	 }
	
}
