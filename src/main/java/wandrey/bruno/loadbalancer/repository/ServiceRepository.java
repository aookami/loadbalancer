/**
 *
 */
package wandrey.bruno.loadbalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wandrey.bruno.loadbalancer.model.ServiceModel;

/**
 * @author Bruno Wandrey
 *
 */

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {

}
