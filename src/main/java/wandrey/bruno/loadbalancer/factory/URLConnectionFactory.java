/**
 *
 */
package wandrey.bruno.loadbalancer.factory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import wandrey.bruno.loadbalancer.connection.interfaces.ConnectionProtocol;
import wandrey.bruno.loadbalancer.connection.model.HttpConnection;

/**
 * @author Bruno Wandrey
 *
 */
public class URLConnectionFactory {

	private URLConnectionFactory() {

	}

	public static URLConnection getAndSetUpConnection(HttpServletRequest request, URL url,
			ConnectionProtocol connectionProtocol) throws IOException {

		if (connectionProtocol instanceof HttpConnection) {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(request.getMethod());
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			return conn;
		}

		return null;
	}

}
