/**
 * 
 */
package com.leo.app.social;

import com.leo.app.AppSecretException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * app环境下替换providerSignInUtils，避免由于没有session导致读不到社交用户信息的问题
 *
 *
 */
@Component
public class AppSingUpUtils {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private UsersConnectionRepository usersConnectionRepository;
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;


	private static final String DEVICEID="deviceId";

	/**
	 * 缓存社交网站用户信息到redis
	 * @param request
	 * @param connectionData
	 */
	public void saveConnectionData(WebRequest request, ConnectionData connectionData) {
		redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
	}

	/**
	 * 将缓存的社交网站用户信息与系统注册用户信息绑定
	 * @param request
	 * @param userId
	 */
	public void doPostSignUp(WebRequest request, String userId) {
		String key = getKey(request);
		if(!redisTemplate.hasKey(key)){
			throw new AppSecretException("无法找到缓存的用户社交账号信息");
		}
		ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
		Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
				.createConnection(connectionData);
		usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
		
		redisTemplate.delete(key);
	}

	/**
	 * 获取redis key
	 * @param request
	 * @return
	 */
	private String getKey(WebRequest request) {
		String deviceId = request.getHeader(DEVICEID);
		if (StringUtils.isBlank(deviceId)) {
			throw new AppSecretException("设备id参数不能为空");
		}
		return AppSingConst.SOCIAL_KEY + deviceId;
	}

}
