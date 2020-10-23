/**
 *
 */
package wandrey.bruno.loadbalancer.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import wandrey.bruno.loadbalancer.model.ServiceModel;
import wandrey.bruno.loadbalancer.repository.ServiceRepository;

/**
 * @author Bruno Wandrey
 *
 */

@Service
public class ServiceService {

	private ServiceRepository serviceRepository;

	public ServiceService(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	public ServiceModel findByName(String name) {

		ServiceModel service = new ServiceModel();
		service.setName(name);
		Example<ServiceModel> serviceExample = Example.of(service);
		return serviceRepository.findOne(serviceExample).orElseThrow();

	}

}
