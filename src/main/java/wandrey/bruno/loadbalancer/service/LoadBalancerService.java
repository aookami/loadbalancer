/**
 *
 */
package wandrey.bruno.loadbalancer.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import wandrey.bruno.loadbalancer.exception.ProtocolNotFoundException;
import wandrey.bruno.loadbalancer.factory.ConnectionProtocolFactory;
import wandrey.bruno.loadbalancer.factory.SelectionStrategyFactory;
import wandrey.bruno.loadbalancer.logger.service.LoggingService;
import wandrey.bruno.loadbalancer.model.ConnectionData;
import wandrey.bruno.loadbalancer.model.ServiceModel;
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
	ServiceRegistrationService serviceRegistrationService;

	@Autowired
	ServiceService serviceService;

	@Autowired
	LoggingService loggingService;

	/**
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ProtocolNotFoundException
	 */
	public <U> ResponseEntity<byte[]> redirect(String serviceName, String uri, U body, HttpServletRequest request)
			throws IOException {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {

			ServiceModel service = serviceService.findByName(serviceName);

			ServiceRegistrationModel serviceRegistrationModel = serviceRegistrationService.applyStrategy(
					service.getServiceRegistrationModelList(),
					SelectionStrategyFactory.getStrategy(service.getStrategy()));

			loggingService.logRouting(String.valueOf(body), request.getRemoteAddr(), Instant.now(), service.getName());

			try {

				ConnectionData receivedData = ConnectionProtocolFactory
						.getConnectionProtocol(serviceRegistrationModel.getProtocol()).getConnectionHandler()
						.processConnection(request, serviceRegistrationModel, uri, body);
				return new ResponseEntity<>(receivedData.getPayload(), HttpStatus.OK);
			} catch (MalformedURLException e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (ProtocolNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
