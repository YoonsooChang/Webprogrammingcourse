package kr.or.connect.reservation.interceptor;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		logger.debug("REQUEST {} FROM {} AT {}", request.getRequestURL(), request.getRemoteAddr(), LocalDateTime.now());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		if (modelAndView == null) {
			logger.debug("CALLED REST-API {}", handler.toString());
		} else {
			logger.debug("CALLED {}, VIEW : {}, MODEL : {}", handler.toString(), modelAndView.getViewName(),
				modelAndView.getModel().toString());
		}
	}

}
