package com.leo.app.logger.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import net.logstash.logback.decorate.JsonFactoryDecorator;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/29 5:43 下午
 * @Description:
 * @author: lihao
 */
public class LogJsonFactoryDecorator implements JsonFactoryDecorator {
    public LogJsonFactoryDecorator() {
    }

    @Override
    public MappingJsonFactory decorate(MappingJsonFactory factory) {
        factory.disable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
        return factory;
    }
}
