/**
 *
 */
package wandrey.bruno.loadbalancer.model;

/**
 * @author Bruno Wandrey
 *
 */
public class ConnectionData {

	byte[] payload;

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public byte[] getPayload() {
		return payload;
	}

}
