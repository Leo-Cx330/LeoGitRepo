package com.leo.app.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: leo-cloud
 * @Package: com.leo.app.filter
 * @ClassName: DecryptFilter
 * @Description: java类作用描述
 * @Author: 李浩
 * @CreateDate: 2018/9/28 上午11:20
 * @UpdateDate: 2018/9/28 上午11:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

public class DecryptFilter extends ZuulFilter {


    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String method = request.getMethod();
        if(method.equalsIgnoreCase("GET")){
            return  null;
        }
        return "OK!";
    }


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


}
