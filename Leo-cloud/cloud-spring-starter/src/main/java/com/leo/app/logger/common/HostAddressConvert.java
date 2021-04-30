package com.leo.app.logger.common;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/31 6:02 下午
 * @Description:
 * @author: lihao
 */
public class HostAddressConvert  extends ClassicConverter {
    public HostAddressConvert(){}
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
