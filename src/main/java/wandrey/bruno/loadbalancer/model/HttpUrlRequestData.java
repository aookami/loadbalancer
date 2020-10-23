/**
 *
 */
package wandrey.bruno.loadbalancer.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Bruno Wandrey
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class HttpUrlRequestData extends ConnectionData {

	HttpStatus httpStatus;

}
