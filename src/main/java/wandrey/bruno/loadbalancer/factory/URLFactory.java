/**
 *
 */
package wandrey.bruno.loadbalancer.factory;

import java.net.MalformedURLException;
import java.net.URL;

import wandrey.bruno.loadbalancer.connection.interfaces.ConnectionProtocol;

/**
 * @author Bruno Wandrey
 *
 */
public class URLFactory {

	private URLFactory() {

	}

	public static URL getURL(ConnectionProtocol protocol, String ip, String port, String uri)
			throws MalformedURLException {
		return new URL(protocol.getPrefix() + ip + ":" + port + uri);
	}

}
