/**
 *
 */
package wandrey.bruno.loadbalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wandrey.bruno.loadbalancer.model.DataModel;

/**
 * @author Bruno Wandrey
 *
 */

@Repository
public interface DataRepository extends JpaRepository<DataModel, Long> {

}
