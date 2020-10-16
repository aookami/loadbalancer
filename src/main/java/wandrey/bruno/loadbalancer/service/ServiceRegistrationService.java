/**
 *
 */
package wandrey.bruno.loadbalancer.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import wandrey.bruno.loadbalancer.exception.ServiceRegistrationNotFoundException;
import wandrey.bruno.loadbalancer.model.ServiceRegistrationModel;
import wandrey.bruno.loadbalancer.repository.ServiceRegistrationRepository;

/**
 * @author Bruno Wandrey
 *
 */

@Service
public class ServiceRegistrationService {

	Logger logger = LoggerFactory.getLogger(ServiceRegistrationService.class);

	@Autowired
	ServiceRegistrationRepository srRepository;

	@Autowired
	StatisticsService statisticsService;

	/**
	 * @param page
	 * @return
	 */
	public Page<ServiceRegistrationModel> getPage(Pageable page) {
		return srRepository.findAll(page);
	}

	/**
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public ServiceRegistrationModel postData(ServiceRegistrationModel data) {

		return srRepository.save(data);
	}

	public List<ServiceRegistrationModel> getServiceRegistrationByService(String service) {
		return srRepository.findByName(service);
	}

	public <U> ServiceRegistrationModel getServiceRegistration(U request) {
		List<ServiceRegistrationModel> serviceList = getServiceRegistrationByService(request.toString());

		if (serviceList.isEmpty()) {
			throw new ServiceRegistrationNotFoundException();
		}

		// how to choose the service the lb is going to forward the request?
		// lets say we have three services registered
		// 1 - 100 score
		// 2 - 30 score
		// 3 - 30 score
		// 160 total - pick a random number from 1 to 160,
		//

		serviceList.sort((ServiceRegistrationModel sr1, ServiceRegistrationModel sr2) -> sr1.getScore()
				.compareTo(sr2.getScore()));

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

		statisticsService.addCountToCounterMap(srToBeUsed.getName() + srToBeUsed.getId());

		return srToBeUsed;
	}

}
