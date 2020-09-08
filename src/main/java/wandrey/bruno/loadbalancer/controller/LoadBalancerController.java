/**
 *
 */
package wandrey.bruno.loadbalancer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public <T, U> T processRequestGET(@RequestParam("service") String service, @RequestBody U body) throws IOException {
		return lbService.redirectGET(service, body);
	}

	@PostMapping
	public <T, U> T processRequestPOST(@RequestParam("service") String service, @RequestBody U body)
			throws IOException {
		return lbService.redirectPOST(service, body);
	}

}
