/**
 *
 */
package wandrey.bruno.loadbalancer.model;

import lombok.Data;

/**
 * @author Bruno Wandrey
 *
 */
@Data
public class RequestModel {

	private String request;
	private String service;

}
