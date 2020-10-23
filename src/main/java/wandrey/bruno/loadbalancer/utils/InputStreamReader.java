/**
 *
 */
package wandrey.bruno.loadbalancer.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Bruno Wandrey
 *
 */
public class InputStreamReader {

	private InputStreamReader() {

	}

	public static byte[] read(InputStream inputStream) throws IOException {

		byte[] targetArray = new byte[inputStream.available()];
		targetArray = inputStream.readAllBytes();

		return targetArray;

	}
}
