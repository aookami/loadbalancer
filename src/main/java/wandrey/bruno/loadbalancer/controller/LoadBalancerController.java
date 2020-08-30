/**
 *
 */
package wandrey.bruno.loadbalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wandrey.bruno.loadbalancer.model.RequestModel;
import wandrey.bruno.loadbalancer.service.LoadBalancerService;

/**
 * @author Bruno Wandrey
 *
 */

@RestController
@RequestMapping("/load-balancer/")
public class LoadBalancerController {

	@Autowired
	LoadBalancerService lbService;

	@GetMapping
	public <T> T processRequest(RequestModel request) {

		return lbService.redirect(request);

	}

}
