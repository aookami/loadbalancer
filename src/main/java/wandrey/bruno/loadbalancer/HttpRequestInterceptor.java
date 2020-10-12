/**
 *
 */
package wandrey.bruno.loadbalancer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import wandrey.bruno.loadbalancer.service.LoadBalancerService;

/**
 * @author Bruno Wandrey
 *
 */

@Component
public class HttpRequestInterceptor implements HandlerInterceptor {

	@Autowired
	LoadBalancerService loadBalancerService;

	@Autowired
	ApplicationContext context;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

	}

}
