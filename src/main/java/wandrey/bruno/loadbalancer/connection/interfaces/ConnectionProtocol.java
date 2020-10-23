/**
 *
 */
package wandrey.bruno.loadbalancer.connection.interfaces;

import wandrey.bruno.loadbalancer.interfaces.ConnectionHandler;

/**
 * @author Bruno Wandrey
 *
 */
public interface ConnectionProtocol {

	String getProtocol();

	String getPrefix();

	ConnectionHandler getConnectionHandler();

}
