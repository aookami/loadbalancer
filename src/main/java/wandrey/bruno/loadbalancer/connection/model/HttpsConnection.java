/**
 *
 */
package wandrey.bruno.loadbalancer.connection.model;

import wandrey.bruno.loadbalancer.connection.interfaces.ConnectionProtocol;
import wandrey.bruno.loadbalancer.interfaces.ConnectionHandler;

/**
 * @author Bruno Wandrey
 *
 */
public class HttpsConnection implements ConnectionProtocol {

	@Override
	public String getProtocol() {
		return "https";
	}

	@Override
	public String getPrefix() {
		return "https://";
	}

	@Override
	public ConnectionHandler getConnectionHandler() {
		return null;
	}

}
