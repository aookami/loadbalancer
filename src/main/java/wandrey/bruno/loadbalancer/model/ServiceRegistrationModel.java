/**
 *
 */
package wandrey.bruno.loadbalancer.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Bruno Wandrey
 *
 */

@Table(name = "service_registry")
@Entity
@Data
public class ServiceRegistrationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	String id;

	@Column(name = "ip")
	String ip;
	@Column(name = "port")
	String port;
	@Column(name = "name")
	String name;
	@Column(name = "version")
	String version;

	// the higher the score, the most likely the lb is to send traffic to the
	// service;
	@Column(name = "score")
	Long score;

	@Column(name = "last_heartbeat")
	Timestamp lastHeartbeat;

}
