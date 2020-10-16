/**
 *
 */
package wandrey.bruno.loadbalancer.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	public <T, U> T redirect(String service, U body, HttpServletRequest request) throws IOException {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {

			ServiceRegistrationModel serviceRegistrationModel = getServiceRegistration(service);
			loggingService.logRouting(String.valueOf(body), request.getRemoteAddr(), Instant.now(),
					serviceRegistrationModel.getName());

			URL url = new URL(
					"http://" + serviceRegistrationModel.getIp() + ":" + serviceRegistrationModel.getPort() + "/data/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(request.getMethod());
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			write(conn.getOutputStream(), body);

			String text = null;
			try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
				text = scanner.useDelimiter("\\A").next();
			}

			return (T) text;

		}

		return null;

	}

	/**
	 * @param <U>
	 * @param request
	 */
	private <U> ServiceRegistrationModel getServiceRegistration(U request) {
		List<ServiceRegistrationModel> serviceList = srService.getServiceRegistrationByService(request.toString());

		if (serviceList.isEmpty()) {
			throw new RuntimeException("ServiceRegistration not found");
		}

		// how to choose the service the lb is going to forward the request?
		// lets say we have three services registered
		// 1 - 100 score
		// 2 - 30 score
		// 3 - 30 score
		// 160 total - pick a random number from 1 to 160,
		//

		serviceList.sort((ServiceRegistrationModel sr1, ServiceRegistrationModel sr2) -> {
			return sr1.getScore().compareTo(sr2.getScore());
		});

		// Calculates the sr to be used by retracting scores from a random number
		// between zero and sumOfScores
		// when sumOfScores is less than zero, the current subtracting sr from randomNum
		// is the chosen service

		Long sumOfScores = 0L;
		for (ServiceRegistrationModel sr : serviceList) {
			sumOfScores = sumOfScores + sr.getScore();
		}

		Long randomNum = Double.valueOf(Math.floor(sumOfScores * Math.random())).longValue();

		ServiceRegistrationModel srToBeUsed = null;
		for (ServiceRegistrationModel sr : serviceList) {
			randomNum = randomNum - sr.getScore();
			if (randomNum < 0) {
				srToBeUsed = sr;
				break;
			}
		}

		System.out.println("IVE PICKED" + srToBeUsed.getName() + srToBeUsed.getId());

		statisticsService.addCountToCounterMap(srToBeUsed.getName() + srToBeUsed.getId());

		if (srToBeUsed == null)
			srToBeUsed = serviceList.get(0);

		return srToBeUsed;
	}

	public <T> void write(OutputStream stream, T object) throws IOException {
		if (object instanceof Object) {
			stream.write(object.toString().getBytes());
		} else if (object instanceof LinkedHashMap) {
			stream.write(object.toString().getBytes());
		} else
			stream.write(object.toString().getBytes());

	}

}
