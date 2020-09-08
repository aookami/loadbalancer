/**
 *
 */
package wandrey.bruno.loadbalancer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import wandrey.bruno.loadbalancer.HttpRequestInterceptor;

/**
 * @author Bruno Wandrey
 *
 */
@Configuration
public class HttpRequestInterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpRequestInterceptor());
	}
}
