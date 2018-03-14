package com.wong.aop;

import com.wong.common.annotation.LoginRequired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 登录认证的拦截器 
 */  
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter{  
	
	/*@Autowired
    public WebTokenService tokenService; 
	
	@Autowired
	private UserService userService;*/
	 /** 
     * Handler执行之前调用这个方法 
     */ 
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String authHeaderVal = httpRequest.getHeader("token");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String name = request.getServletPath().toString();
    	
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        /*if (methodAnnotation != null) {
            if (StringUtils.isNotEmpty(authHeaderVal)) {
            	try {
    				WebToken webToken = tokenService.getToken(authHeaderVal);
					int userId = Integer.valueOf(webToken.getId());
					User user = userService.getUserById(userId);
					user.setPassword("不告诉你");
					httpRequest.setAttribute("userInfo", user);
					//System.out.println("========"+name+"===> LoginInterceptor preHandle 验证成功===>放行"); 
					return true; 
    			} catch (Exception e) {
    				response.setCharacterEncoding("UTF-8");
    				response.getWriter().write((new JsonResult(ResultCode.INVALID_AUTHCODE, "登录已过期，请重新登录！")).toString());
    				//System.out.println("========"+name+"===> LoginInterceptor preHandle 登录已过期，请重新登录！===>拦截"); 
    				return false;
    			}
            } else {
            	response.setCharacterEncoding("UTF-8");
                response.getWriter().write((new JsonResult(ResultCode.NOT_LOGIN,"尚未登录")).toString());
                //System.out.println("========"+name+"===> LoginInterceptor preHandle 尚未登录！===>拦截");
                return false;
            }
        }*/
        //System.out.println("========"+name+"===> LoginInterceptor preHandle 无需验证此接口（没加验证注解）===>放行");
    	return true;   
    } 
  
    /** 
     * Handler执行之后，ModelAndView返回之前调用这个方法 
     */ 
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler, ModelAndView modelAndView) throws Exception { 
        //String name = request.getServletPath().toString();
		System.out.println("===========> LoginInterceptor postHandle");
    }  
    
    /** 
     * Handler执行完成之后调用这个方法 
     */
	@Override
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception exc)  
            throws Exception { 
		//String name = request.getServletPath().toString();
		System.out.println("===========> LoginInterceptor afterCompletion");
    }  
}  
