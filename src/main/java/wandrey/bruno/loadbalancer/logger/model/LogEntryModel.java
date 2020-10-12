/**
 *
 */
package wandrey.bruno.loadbalancer.logger.model;

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
@Entity
@Data
@Table(name = "request_log")
public class LogEntryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "incoming_ip")
	String incomingIp;

	@Column(name = "request")
	String request;

	@Column(name = "request_time")
	Timestamp requestTime;

	@Column(name = "service")
	String service;

}
