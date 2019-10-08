/**
 * 
 */
package com.leo.core.validate.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的短信验证码发送器
 *
 * 配置第三方短信接口
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
	
	private Logger logger = LoggerFactory.getLogger(getClass());


	@Override
	public void send(String mobile, String code) {
		logger.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		logger.info("向手机"+mobile+"发送短信验证码"+code);
	}

}
