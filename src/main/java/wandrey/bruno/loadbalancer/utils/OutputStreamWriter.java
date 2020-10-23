/**
 *
 */
package wandrey.bruno.loadbalancer.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;

/**
 * @author Bruno Wandrey
 *
 */
public class OutputStreamWriter {

	private OutputStreamWriter() {
	}

	public static <T> void write(OutputStream stream, T object) throws IOException {
		if (object instanceof Object) {
			stream.write(object.toString().getBytes());
		} else if (object instanceof LinkedHashMap) {
			stream.write(object.toString().getBytes());
		} else
			stream.write(object.toString().getBytes());
	}

}
