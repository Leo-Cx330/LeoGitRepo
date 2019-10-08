/**
 * 
 */
package com.leo.core.validate.sms;

/**
 *
 */
public interface SmsCodeSender {
	
	/**
	 * @param mobile
	 * @param code
	 */
	void send(String mobile, String code);

}
