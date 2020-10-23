/**
 *
 */
package wandrey.bruno.loadbalancer.connection.model;

import wandrey.bruno.loadbalancer.connection.interfaces.ConnectionProtocol;
import wandrey.bruno.loadbalancer.factory.HttpURLConnectionHandler;
import wandrey.bruno.loadbalancer.interfaces.ConnectionHandler;

/**
 * @author Bruno Wandrey
 *
 */
public class HttpConnection implements ConnectionProtocol {

	@Override
	public String getProtocol() {
		return "http";
	}

	@Override
	public String getPrefix() {
		return "http://";
	}

	@Override
	public ConnectionHandler getConnectionHandler() {
		return new HttpURLConnectionHandler();
	}
}
