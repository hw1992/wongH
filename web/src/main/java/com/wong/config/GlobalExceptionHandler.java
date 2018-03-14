package com.wong.config;

import com.wong.common.JsonResult;
import com.wong.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wongH 18 1 24
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private JsonResult allExceptionHandler(Exception ex, HttpServletRequest request) {
        logger.error("************************GlobalException开始*******************************");
        logger.error(ex.toString());
        logger.error("msg：" + ex.getMessage());
        logger.error("请求地址：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        logger.error("请求参数");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            logger.error(name + "---" + request.getParameter(name));
        }
        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            logger.error(stackTraceElement.toString());
        }
        logger.error("************************GlobalException结束*******************************");
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("请求地址", request.getRequestURL().toString());
        map.put("请求参数", request.getParameterNames().toString());
        map.put("异常信息", ex.toString());
        return new JsonResult(ResultCode.EXCEPTION, "发生异常",map);
    }
}
