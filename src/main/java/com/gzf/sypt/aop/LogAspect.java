package com.gzf.sypt.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guozifan
 * @Description: 实现日志打印切面
 * @date 2018/7/20 19:42
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String requestPath = null;  // 请求地址
    private Long userId = null; // 用户Id
    private String requestParams = null;  // 传入参数
    private String responseParams = null; // 响应参数
    private Long startTime = null;  // 开始时间
    private Long endTime = null;    // 结束时间

    @Pointcut(value = "execution(public * com.gzf.sypt.service.*.*(..))")
    private void allMethod(){}

    @Before(value = "allMethod()")
    private void startRecordTime(){
        startTime = System.currentTimeMillis();
    }

    @Around(value = "allMethod()")
    private Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
//        getUserMessage();
        result = proceedingJoinPoint.proceed();
        endTime = System.currentTimeMillis();
        printLog();
        Map map = new HashMap();
        map.put("result",result);
        responseParams = JSON.toJSONString(map);
        System.out.println(map);
        return result;
    }

    /**
     * 打印日志
     */
    private void printLog(){
        logger.info("IP为" + requestPath + ",id：" + userId + "的用户于" +
                new SimpleDateFormat("yyyy MM-dd HH:mm:ss").format(startTime) + "到" +
                new SimpleDateFormat("yyyy MM-dd HH:mm:ss").format(endTime) + ",用时" +
                (endTime - startTime) + "毫秒" +
                ",传入参数：" + requestParams + ",响应参数：" + responseParams );
    }

    /**
     * 获取用户ID和用户的IP地址等用户信息
     */
    private void getUserMessage() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();    // 获取spring托管的request
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        if (httpServletRequest.getHeader("x-forwarded-for") == null){
            requestPath = httpServletRequest.getHeader("x-forwarded-for");
        }else {
            // 用反向代理时无法跟踪到用户IP
            requestPath = httpServletRequest.getRemoteAddr();
        }
        userId = (Long) httpServletRequest.getSession().getAttribute("userId"); // 从session中取出用户ID
        requestParams = printRequestParams(httpServletRequest);
    }

    /**
     * 将请求参数以字符串形式输出
     * @param httpServletRequest 请求对象
     * @return 以字符串形式输出的数组
     */
    private String printRequestParams(HttpServletRequest httpServletRequest){
        Map paramsMap = httpServletRequest.getParameterMap();
        return JSON.toJSONString(paramsMap);
    }
}
