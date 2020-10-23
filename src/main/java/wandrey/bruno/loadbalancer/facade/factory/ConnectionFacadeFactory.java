/**
 *
 */
package wandrey.bruno.loadbalancer.facade.factory;

import java.net.HttpURLConnection;
import java.net.URLConnection;

import wandrey.bruno.loadbalancer.facade.HttpURLConnectionFacade;
import wandrey.bruno.loadbalancer.interfaces.ConnectionFacade;

/**
 * @author Bruno Wandrey
 *
 */
public class ConnectionFacadeFactory {

	public static ConnectionFacade getConnectionFacade(URLConnection connection) {
		if (connection instanceof HttpURLConnection) {
			return new HttpURLConnectionFacade((HttpURLConnection) connection);
		}
		return null;
	}

}
