package com.leo.app.logger.componet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/27 1:57 PM
 * @Description:
 * @author: lihao
 */
@Getter
@Setter
@ToString
public class ApiLogInfo {

    /**
     * 应用id
     */
    private String appId;
    /**
     * 类名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 请求url
     */
    private String requestUrl;
    /**
     * 查询入参
     */
    private String queryParam;
    /**
     * 请求头参数
     */
    private String headerParam;
    /**
     * 输入参数
     */
    private String inputParam;
    /**
     * 响应参数
     */
    private String outputParam;

    private Long elapsedTime;
    /**
     * 追踪链路id
     */
    private String traceId;

    private Boolean success;

    private String codeVal;
}
