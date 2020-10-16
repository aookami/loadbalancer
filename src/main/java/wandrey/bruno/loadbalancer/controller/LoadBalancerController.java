/**
 *
 */
package wandrey.bruno.loadbalancer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public <T, U> ResponseEntity<T> processRequestGET(@RequestParam("service") String service,
			@RequestParam("uri") String uri, @RequestBody U body, HttpServletRequest request) throws IOException {
		return lbService.redirect(service, uri, body, request);
	}

	@PostMapping
	public <T, U> ResponseEntity<T> processRequestPOST(@RequestParam("service") String service,
			@RequestParam("uri") String uri, @RequestBody U body, HttpServletRequest request) throws IOException {
		return lbService.redirect(service, uri, body, request);
	}

	@PutMapping
	public <T, U> ResponseEntity<T> processRequestPUT(@RequestParam("service") String service,
			@RequestParam("uri") String uri, @RequestBody U body, HttpServletRequest request) throws IOException {
		return lbService.redirect(service, uri, body, request);
	}

	@DeleteMapping
	public <T, U> ResponseEntity<T> processRequestDELETE(@RequestParam("service") String service,
			@RequestParam("uri") String uri, @RequestBody U body, HttpServletRequest request) throws IOException {
		return lbService.redirect(service, uri, body, request);
	}

}
