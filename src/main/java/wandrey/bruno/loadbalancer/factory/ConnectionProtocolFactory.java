/**
 *
 */
package wandrey.bruno.loadbalancer.factory;

import wandrey.bruno.loadbalancer.connection.interfaces.ConnectionProtocol;
import wandrey.bruno.loadbalancer.connection.model.HttpConnection;
import wandrey.bruno.loadbalancer.connection.model.HttpsConnection;
import wandrey.bruno.loadbalancer.exception.ProtocolNotFoundException;

/**
 * @author Bruno Wandrey
 *
 */
public class ConnectionProtocolFactory {

	private ConnectionProtocolFactory() {

	}

	public static ConnectionProtocol getConnectionProtocol(String protocol) throws ProtocolNotFoundException {
		if ("http".equals(protocol)) {
			return new HttpConnection();
		}
		if ("https".equals(protocol)) {
			return new HttpsConnection();
		}

		throw new ProtocolNotFoundException();
	}

}
