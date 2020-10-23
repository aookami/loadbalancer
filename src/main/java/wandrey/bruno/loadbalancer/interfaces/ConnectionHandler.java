/**
 *
 */
package wandrey.bruno.loadbalancer.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import wandrey.bruno.loadbalancer.exception.ProtocolNotFoundException;
import wandrey.bruno.loadbalancer.model.ConnectionData;
import wandrey.bruno.loadbalancer.model.ServiceRegistrationModel;

/**
 * @author Bruno Wandrey
 *
 */
public interface ConnectionHandler {

	/**
	 * @param <U>
	 * @param request
	 * @param serviceRegistrationModel
	 * @param uri
	 * @param body
	 * @return
	 * @throws ProtocolNotFoundException
	 * @throws IOException
	 */
	<U> ConnectionData processConnection(HttpServletRequest request, ServiceRegistrationModel serviceRegistrationModel,
			String uri, U body) throws IOException, ProtocolNotFoundException;

}
