/**
 *
 */
package wandrey.bruno.loadbalancer.interfaces;

import java.util.List;

import wandrey.bruno.loadbalancer.model.ServiceRegistrationModel;

/**
 * @author Bruno Wandrey
 *
 */
public interface ServiceRegistrationSelectionStrategy {

	ServiceRegistrationModel selectServiceRegistry(List<ServiceRegistrationModel> serviceRegistrationList);

}
