/**
 *
 */
package wandrey.bruno.loadbalancer.factory;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import wandrey.bruno.loadbalancer.exception.ProtocolNotFoundException;
import wandrey.bruno.loadbalancer.facade.factory.ConnectionFacadeFactory;
import wandrey.bruno.loadbalancer.interfaces.ConnectionFacade;
import wandrey.bruno.loadbalancer.interfaces.ConnectionHandler;
import wandrey.bruno.loadbalancer.model.ConnectionData;
import wandrey.bruno.loadbalancer.model.ServiceRegistrationModel;

/**
 * @author Bruno Wandrey
 *
 */
public class HttpURLConnectionHandler implements ConnectionHandler {

	@Override
	public <U> ConnectionData processConnection(HttpServletRequest request,
			ServiceRegistrationModel serviceRegistrationModel, String uri, U body)
			throws IOException, ProtocolNotFoundException {

		ConnectionData dataToBeSent = ConnectionDataFactory.encapsulateData(body);

		URL url = URLFactory.getURL(ConnectionProtocolFactory.getConnectionProtocol(serviceRegistrationModel.getProtocol()),
				serviceRegistrationModel.getIp(), serviceRegistrationModel.getPort(), uri);

		URLConnection conn = URLConnectionFactory.getAndSetUpConnection(request, url,
				ConnectionProtocolFactory.getConnectionProtocol(serviceRegistrationModel.getProtocol()));

		ConnectionFacade connectionFacade = ConnectionFacadeFactory.getConnectionFacade(conn);

		connectionFacade.sendData(dataToBeSent);
		return connectionFacade.receiveData();

	}

}
