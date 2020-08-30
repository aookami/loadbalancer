/**
 *
 */
package wandrey.bruno.loadbalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wandrey.bruno.loadbalancer.model.ServiceRegistrationModel;
import wandrey.bruno.loadbalancer.service.ServiceRegistrationService;

/**
 * @author Bruno Wandrey
 *
 */

@RestController
@RequestMapping("/service-registration/")
public class ServiceRegistrationController {

	@Autowired
	ServiceRegistrationService srService;

	@GetMapping
	public Page<ServiceRegistrationModel> getPage(@PageableDefault(size = 15, page = 0) Pageable page) {
		return srService.getPage(page);
	}

	@PostMapping
	public ServiceRegistrationModel postData(@RequestBody ServiceRegistrationModel data) {
		return srService.postData(data);
	}

}
