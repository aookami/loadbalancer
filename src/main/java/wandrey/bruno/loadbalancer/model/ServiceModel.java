/**
 *
 */
package wandrey.bruno.loadbalancer.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Bruno Wandrey
 *
 */

@Data
@Table(name = "services")
@Entity
public class ServiceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	String id;

	@Column(name = "name")
	String name;

	@Column(name = "strategy")
	String strategy;

	@OneToMany(mappedBy = "service")
	List<ServiceRegistrationModel> serviceRegistrationModelList;

	@Override
	public String toString() {
		return (super.toString());
	}

}
