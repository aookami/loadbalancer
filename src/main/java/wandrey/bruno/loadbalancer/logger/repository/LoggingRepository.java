/**
 *
 */
package wandrey.bruno.loadbalancer.logger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wandrey.bruno.loadbalancer.logger.model.LogEntryModel;

/**
 * @author Bruno Wandrey
 *
 */
@Repository
public interface LoggingRepository extends JpaRepository<LogEntryModel, Long> {

}
