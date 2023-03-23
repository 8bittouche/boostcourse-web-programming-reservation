package kr.or.connect.reservation.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String controllerName = "";
		String actionName = "";

		if( handler instanceof HandlerMethod ) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			controllerName  = handlerMethod.getBeanType().getSimpleName().replace("Controller", "");
			actionName = handlerMethod.getMethod().getName();
		}
		
		String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
				
		logger.debug("[{}]   [URL]{}    [CONTROLLER]{}    [METHOD]{}   [IP]{}", request.getMethod(), request.getRequestURL().toString(), controllerName, actionName, ip);
		
		return true;
	}
}