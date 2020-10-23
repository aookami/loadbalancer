/**
 *
 */
package wandrey.bruno.loadbalancer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	@Column(name = "protocol")
	String protocol;

	@Column(name = "ip")
	String ip;
	@Column(name = "port")
	String port;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	ServiceModel service;

	@Column(name = "version")
	String version;

	@Column(name = "score")
	Long score;

}
