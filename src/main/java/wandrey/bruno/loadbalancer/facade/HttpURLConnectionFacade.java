/**
 *
 */
package wandrey.bruno.loadbalancer.facade;

import java.io.IOException;
import java.net.HttpURLConnection;

import wandrey.bruno.loadbalancer.interfaces.ConnectionFacade;
import wandrey.bruno.loadbalancer.model.ConnectionData;
import wandrey.bruno.loadbalancer.utils.InputStreamReader;
import wandrey.bruno.loadbalancer.utils.OutputStreamWriter;

/**
 * @author Bruno Wandrey
 *
 */
public class HttpURLConnectionFacade implements ConnectionFacade {

	public HttpURLConnectionFacade(HttpURLConnection connection) {
		this.connection = connection;
	}

	HttpURLConnection connection;

	@Override
	public void sendData(ConnectionData data) throws IOException {

		OutputStreamWriter.write(connection.getOutputStream(), data);

	}

	@Override
	public ConnectionData receiveData() throws IOException {
		ConnectionData connData = new ConnectionData();
		connData.setPayload(InputStreamReader.read(connection.getInputStream()));
		return connData;
	}

}
