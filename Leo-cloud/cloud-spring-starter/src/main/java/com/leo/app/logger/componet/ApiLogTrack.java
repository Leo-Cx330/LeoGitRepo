package com.leo.app.logger.componet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.io.Closer;
import com.leo.app.logger.annotation.ApiLog;
import com.leo.app.logger.common.MDCUtil;
import com.leo.app.logger.componet.context.ThreadLocalLogContext;
import org.apache.commons.beanutils.BeanMap;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/8/26 4:46 PM
 * @Description: api 日志采集
 * @author: lihao
 */
@Aspect
public class ApiLogTrack {

    private static ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(() -> Maps.newHashMap());

    private static final Logger logger=LoggerFactory.getLogger("API-LOGGER");



    @Pointcut("@annotation(com.leo.app.logger.annotation.ApiLog) || @within(com.leo.app.logger.annotation.ApiLog)")
    public void apiLog() {
    }

    @Before("apiLog()")
    public void before(JoinPoint joinPoint){
        ThreadLocalLogContext.put(threadLocal,"joinPoint",joinPoint);
        ThreadLocalLogContext.put(threadLocal,"startTime",System.currentTimeMillis());
        ThreadLocalLogContext.put(threadLocal,"joinPoint",joinPoint);
        ThreadLocalLogContext.put(threadLocal,"apiLogInfo",buildApiLogInfo(joinPoint));
    }

    @AfterReturning(returning = "ret",pointcut = "apiLog()")
    public void  doAfterReturning(Object ret){
        try {
            this.buildApiLogAfter(ret,true);
        } catch (Throwable var6) {
            logger.error("API-LOG doAfterReturning Error:", var6);
        } finally {
            ThreadLocalLogContext.remove(threadLocal);
        }
    }

    private ApiLogInfo buildApiLogInfo(JoinPoint joinPoint){
        Method method = this.extractMethod(joinPoint);
        Class<?> clazz = method.getDeclaringClass();
        ApiLogInfo apiLogInfo=new ApiLogInfo();
        apiLogInfo.setTraceId(TraceContext.traceId());
        apiLogInfo.setClassName(clazz.getSimpleName());
        apiLogInfo.setMethodName(method.getName());
        if(checkSkipArgs(method,clazz)){
            return apiLogInfo;
        }
        assembleInputParam(joinPoint,apiLogInfo);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        if (Objects.nonNull(request)) {
            this.assembleRequestHeaderParam(request, apiLogInfo);
            this.assembleRequestUrl(request, apiLogInfo);
            this.assembleRequestQueryParam(request, apiLogInfo);
        }
       return apiLogInfo;
    }


    private Method extractMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        return methodSignature.getMethod();
    }

    /**
     * 检查方法或者类上是否存在@ApiLog
     * @param method
     * @param clazz
     * @return
     */
    private boolean checkSkipArgs(Method method, Class clazz) {
        ApiLog apiLogMethod = (ApiLog)method.getAnnotation(ApiLog.class);
        if (Objects.nonNull(apiLogMethod) && apiLogMethod.skipArgs()) {
            return true;
        } else {
            ApiLog apiLogClass = (ApiLog)clazz.getAnnotation(ApiLog.class);
            return Objects.nonNull(apiLogClass) && apiLogClass.skipArgs();
        }
    }

    /**
     * 采集方法入参
     * @param joinPoint
     * @param apiLogInfo
     */
    private void assembleInputParam(JoinPoint joinPoint, ApiLogInfo apiLogInfo) {
        Method method = this.extractMethod(joinPoint);
        Annotation[][] annotations = method.getParameterAnnotations();
        if (annotations.length != 0) {
            Object[] args = joinPoint.getArgs();
            ArrayList list = new ArrayList(args.length);

            for(int i = 0; i < annotations.length; ++i) {
                if (this.validAnnotation(annotations[i])) {
                    list.add(args[i]);
                }
            }
            apiLogInfo.setInputParam(JSON.toJSONString(list));
        }
    }

    private boolean validAnnotation(Annotation[] annotations) {
        return (annotations != null && annotations.length != 0) && Arrays.stream(annotations).anyMatch((annotation) -> {
            return annotation instanceof RequestBody || annotation instanceof RequestParam || annotation instanceof CookieValue || annotation instanceof PathVariable || annotation instanceof ModelAttribute || annotation instanceof RequestAttribute || annotation instanceof RequestHeader || annotation instanceof SessionAttribute;
        });
    }

    /**
     * 采集请求头参数
     * @param request
     * @param apiLogInfo
     */
    private void assembleRequestHeaderParam(HttpServletRequest request, ApiLogInfo apiLogInfo) {
        Enumeration<String> headerNames = request.getHeaderNames();
        if (!Objects.isNull(headerNames)) {
            JSONObject header = new JSONObject();

            while(headerNames.hasMoreElements()) {
                String headerName = (String)headerNames.nextElement();
                if (!Strings.isNullOrEmpty(headerName)) {
                    String headerValue = request.getHeader(headerName);
                    if ("n-d-service-app-id".equalsIgnoreCase(headerName)) {
                        apiLogInfo.setAppId(headerValue);
                    }

                    header.put(headerName, headerValue);
                }
            }
            apiLogInfo.setHeaderParam(header.toString());
        }
    }

    private void assembleRequestUrl(HttpServletRequest request, ApiLogInfo apiLogInfo) {
        if (Objects.nonNull(request)) {
            Object matchingPattern = request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
            if (Objects.nonNull(matchingPattern)) {
                apiLogInfo.setRequestUrl(String.valueOf(matchingPattern));
            } else {
                apiLogInfo.setRequestUrl(request.getRequestURI());
            }
        }
    }

    private void assembleRequestQueryParam(HttpServletRequest request, ApiLogInfo apiLogInfo) {
        apiLogInfo.setQueryParam(request.getQueryString());
    }

    private void buildApiLogAfter(Object o, Boolean status) throws IOException {
        ApiLogInfo apiLogInfo = (ApiLogInfo) ThreadLocalLogContext.get(threadLocal, "apiLogInfo");
        if (!Objects.isNull(apiLogInfo)) {
            apiLogInfo.setSuccess(status);
            JoinPoint joinPoint = (JoinPoint)ThreadLocalLogContext.get(threadLocal, "joinPoint");
            if (!this.checkSkipResult(joinPoint)) {
                if (status) {
                    this.assembleOutputParam(o, apiLogInfo);
                } else {
                    this.assembleExceptionOutputParam((Exception)o, apiLogInfo);
                }

                this.assembleCode(o, apiLogInfo);
            }

            this.assembleElapsedTime(apiLogInfo);
            this.saveApiLogToMDC(apiLogInfo);
        }
    }

    private boolean checkSkipResult(JoinPoint joinPoint) {
        Method method = this.extractMethod(joinPoint);
        ApiLog apiLogMethod = (ApiLog)method.getAnnotation(ApiLog.class);
        if (Objects.nonNull(apiLogMethod) && apiLogMethod.skipResult()) {
            return true;
        } else {
            Class clazz = method.getDeclaringClass();
            ApiLog apiLogClass = (ApiLog)clazz.getAnnotation(ApiLog.class);
            return Objects.nonNull(apiLogClass) && apiLogClass.skipResult();
        }
    }

    private void assembleOutputParam(Object ret, ApiLogInfo apiLogInfo) {
        apiLogInfo.setOutputParam(JSON.toJSONString(ret));
    }
    private void assembleExceptionOutputParam(Exception e, ApiLogInfo apiLogInfo) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        apiLogInfo.setOutputParam(sw.toString());
    }

    private void assembleCode(Object obj, ApiLogInfo apiLogInfo) {
        String code = this.getPropertyValue(obj, "code")==null?"-":this.getPropertyValue(obj, "code").toString();
        apiLogInfo.setCodeVal(code);
    }

    private Object getPropertyValue(Object bean, String name) {
        return (new BeanMap(bean)).get(name);
    }

    private void assembleElapsedTime(ApiLogInfo apiLogInfo) {
        long startTime = (Long)ThreadLocalLogContext.get(threadLocal, "startTime");
        apiLogInfo.setElapsedTime(System.currentTimeMillis() - startTime);
    }
    public void saveApiLogToMDC(ApiLogInfo apiLogInfo) throws IOException {
        Closer closer = Closer.create();

        try {
            MDCUtil.registerMDC(closer, apiLogInfo);
            logger.info("api log:{}", apiLogInfo);
        } catch (Exception var7) {
            logger.error("API-LOG saveApiLogToMDC Error:", var7);
        } finally {
            closer.close();
        }

    }
}
