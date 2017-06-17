package com.challenge.starter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.challenge.starter.domain.ParamException;
import com.challenge.starter.domain.UserException;

@ComponentScan("com.challenge.starter.controller")
public class ParameterValidatorInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = Logger.getLogger(ParameterValidatorInterceptor.class);

    private final ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    
    /**
     * Validates each request parameter in the URL
     * 
     * @param handlerMethod 
     * @param request
     * @return boolean
     * @throws Exception 
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	System.out.println("Pre handle ..");
    	if(handler instanceof HandlerMethod ){
    		HandlerMethod handlerMethod = (HandlerMethod) handler;
    	MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        for (MethodParameter param : methodParameters) {
        	param.initParameterNameDiscovery(this.parameterNameDiscoverer);
        	 RequestParam requestParam = param.getParameterAnnotation(RequestParam.class);
        	 if(requestParam != null){
        	 String paramName = requestParam.value();
        	 String paramValueInRequest = request.getParameter(paramName);
        	 
        	 if(paramValueInRequest == null || (paramValueInRequest != null && paramValueInRequest.length() == 0)){
        		 logger.warn("parameter " + paramName + " is invalid " + " with value " + paramValueInRequest);
        		 System.out.println("parameter " + paramName + " is invalid " + " with value " + paramValueInRequest);
        		// response.setStatus(HttpStatus.BAD_REQUEST.value());
        		 throw new ParamException("parameter " + paramName + " is invalid " + " with value " + paramValueInRequest);
        		// return false;
        	 }
        	 }
        	 
        }
    	}
    	
    	return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws java.lang.Exception{
    }


}
