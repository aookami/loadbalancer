/**
 *
 */
package wandrey.bruno.loadbalancer.interfaces;

import java.io.IOException;

import wandrey.bruno.loadbalancer.model.ConnectionData;

/**
 * @author Bruno Wandrey
 *
 */

public interface ConnectionFacade {

	void sendData(ConnectionData data) throws IOException;

	ConnectionData receiveData() throws IOException;

}
