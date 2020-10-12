/**
 *
 */
package wandrey.bruno.loadbalancer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

	@Autowired
	ApplicationContext applicationContext;

	@GetMapping
	public <T, U> T processRequestGET(@RequestParam("service") String service, @RequestBody U body,
			HttpServletRequest request) throws IOException {
		return lbService.redirectGET(service, body, request);
	}

	@PostMapping
	public <T, U> T processRequestPOST(@RequestParam("service") String service, @RequestBody U body)
			throws IOException {
		System.out.println(applicationContext);
		return lbService.redirectPOST(service, body);
	}

}
