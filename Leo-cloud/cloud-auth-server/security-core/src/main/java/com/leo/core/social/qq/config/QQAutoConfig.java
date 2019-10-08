/**
 * 
 */
package com.leo.core.social.qq.config;


import com.leo.core.properties.QQProperties;
import com.leo.core.properties.SecurityProperties;
import com.leo.core.social.qq.connet.QQConnectionFactory;
import com.leo.core.support.SocialAutoConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "yyh.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;


	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
