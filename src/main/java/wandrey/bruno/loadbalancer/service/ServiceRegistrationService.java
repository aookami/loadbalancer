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

}
