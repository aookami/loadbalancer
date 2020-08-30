/**
 *
 */
package wandrey.bruno.loadbalancer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wandrey.bruno.loadbalancer.model.RequestModel;
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

	/**
	 * @param request
	 * @return
	 */
	public <T> T redirect(RequestModel request) {

		List<ServiceRegistrationModel> serviceList = srService.getServiceRegistrationByService(request.getService());

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

		System.out.println(statisticsService.getCounterMap().toString());
		return null;
	}

}
