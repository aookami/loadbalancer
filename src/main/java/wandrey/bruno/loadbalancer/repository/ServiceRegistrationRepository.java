/**
 *
 */
package wandrey.bruno.loadbalancer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wandrey.bruno.loadbalancer.model.ServiceRegistrationModel;

/**
 * @author Bruno Wandrey
 *
 */
@Repository
public interface ServiceRegistrationRepository extends JpaRepository<ServiceRegistrationModel, Long> {

	/**
	 * @return
	 */
	List<ServiceRegistrationModel> findByName(String name);

}
