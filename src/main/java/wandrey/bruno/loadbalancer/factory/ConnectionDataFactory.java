/**
 *
 */
package wandrey.bruno.loadbalancer.factory;

import wandrey.bruno.loadbalancer.model.ConnectionData;

/**
 * @author Bruno Wandrey
 *
 */
public class ConnectionDataFactory {

	/**
	 * @param outputStream
	 * @param body
	 * @return
	 */
	public static <U> ConnectionData encapsulateData(U body) {
		if (body instanceof byte[]) {
			ConnectionData connData = new ConnectionData();
			connData.setPayload(body.toString().getBytes());
			return connData;
		}
		return new ConnectionData();
	}

}
