package com.leo.app.logger.common;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.google.common.io.Closer;
import org.apache.commons.beanutils.BeanMap;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/29 2:11 下午
 * @Description:
 * @author: lihao
 */
public class MDCUtil {
    private static Converter<String, String> converter;

    public MDCUtil() {
    }

    public static void registerMDC(Closer closer, Object payload) {
        Map map = new BeanMap(payload);
        Iterator var3 = map.keySet().iterator();

        while(true) {
            while(var3.hasNext()) {
                Object key = var3.next();
                Object val = map.get(key);
                if (val != null && !StringUtils.isEmpty(val.toString())) {
                    closer.register(MDC.putCloseable((String)converter.convert(key.toString()), val.toString()));
                } else {
                    closer.register(MDC.putCloseable((String)converter.convert(key.toString()), "-"));
                }
            }

            return;
        }
    }

    static {
        converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
    }
}
