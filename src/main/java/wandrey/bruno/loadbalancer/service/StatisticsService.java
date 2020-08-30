/**
 *
 */
package wandrey.bruno.loadbalancer.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author Bruno Wandrey
 *
 */
@Service
public class StatisticsService {

	static HashMap<String, Long> counterMap = new HashMap<>();

	public Map<String, Long> getCounterMap() {
		return counterMap;
	}

	public void addCountToCounterMap(String serviceId) {
		if (!getCounterMap().containsKey(serviceId)) {
			getCounterMap().put(serviceId, 1L);
		} else {
			getCounterMap().put(serviceId, getCounterMap().get(serviceId) + 1L);
		}

	}
}
