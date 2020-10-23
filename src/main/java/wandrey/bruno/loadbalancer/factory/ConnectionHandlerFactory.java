/**
 *
 */
package wandrey.bruno.loadbalancer.factory;

import wandrey.bruno.loadbalancer.connection.interfaces.ConnectionProtocol;
import wandrey.bruno.loadbalancer.interfaces.ConnectionHandler;

/**
 * @author Bruno Wandrey
 *
 */
public class ConnectionHandlerFactory {

	public static ConnectionHandler getConnectionHandler(ConnectionProtocol connectionType) {
		return connectionType.getConnectionHandler();
	}

}
