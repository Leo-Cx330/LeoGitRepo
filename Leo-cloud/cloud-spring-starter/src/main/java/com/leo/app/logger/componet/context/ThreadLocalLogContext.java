package com.leo.app.logger.componet.context;

import java.util.Map;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/26 5:05 PM
 * @Description:
 * @author: lihao
 */
public class ThreadLocalLogContext {

    public ThreadLocalLogContext(){}

    public static void put(ThreadLocal<Map<String, Object>> threadLocal, String key, Object value) {
        threadLocal.get().put(key, value);
    }

    public static <T> T get(ThreadLocal<Map<String, Object>> threadLocal, String key) {
        return (T) threadLocal.get().get(key);
    }

    public static void remove(ThreadLocal<Map<String, Object>> threadLocal) {
        threadLocal.remove();
    }
}
