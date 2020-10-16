/**
 *
 */
package wandrey.bruno.loadbalancer.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import wandrey.bruno.loadbalancer.logger.service.LoggingService;
import wandrey.bruno.loadbalancer.model.ServiceRegistrationModel;

/**
 * @author Bruno Wandrey
 *
 */
@Service
public class LoadBalancerService {

	@Autowired
	StatisticsService statisticsService;

	@Autowired
	ServiceRegistrationService srService;

	@Autowired
	LoggingService loggingService;

	/**
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public <T, U> ResponseEntity<T> redirect(String service, String uri, U body, HttpServletRequest request)
			throws IOException {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {

			ServiceRegistrationModel serviceRegistrationModel = srService.getServiceRegistration(service);

			loggingService.logRouting(String.valueOf(body), request.getRemoteAddr(), Instant.now(),
					serviceRegistrationModel.getName());

			URL url = new URL(
					"http://" + serviceRegistrationModel.getIp() + ":" + serviceRegistrationModel.getPort() + uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(request.getMethod());
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			write(conn.getOutputStream(), body);

			byte[] targetArray = new byte[conn.getInputStream().available()];
			targetArray = conn.getInputStream().readAllBytes();

			return new ResponseEntity<>((T) targetArray, HttpStatus.valueOf(conn.getResponseCode()));

		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 * @param <U>
	 * @param request
	 */

	public <T> void write(OutputStream stream, T object) throws IOException {
		if (object instanceof Object) {
			stream.write(object.toString().getBytes());
		} else if (object instanceof LinkedHashMap) {
			stream.write(object.toString().getBytes());
		} else
			stream.write(object.toString().getBytes());

	}

}
