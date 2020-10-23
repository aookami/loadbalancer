/**
 *
 */
package wandrey.bruno.loadbalancer.factory;

import wandrey.bruno.loadbalancer.interfaces.ServiceRegistrationSelectionStrategy;
import wandrey.bruno.loadbalancer.selectionStrategy.WeightedRandomizedStrategy;

/**
 * @author Bruno Wandrey
 *
 */
public class SelectionStrategyFactory {

	private SelectionStrategyFactory() {

	}

	public static ServiceRegistrationSelectionStrategy getStrategy(String strategyName) {

		if ("weightedrandomized".equals(strategyName)) {
			return new WeightedRandomizedStrategy();
		}

		return null;
	}

}
