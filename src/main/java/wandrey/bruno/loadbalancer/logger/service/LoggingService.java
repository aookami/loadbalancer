/**
 *
 */
package wandrey.bruno.loadbalancer.logger.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wandrey.bruno.loadbalancer.logger.model.LogEntryModel;
import wandrey.bruno.loadbalancer.logger.repository.LoggingRepository;

/**
 * @author Bruno Wandrey
 *
 */

@Service
public class LoggingService {

	@Autowired
	LoggingRepository loggingRepository;

	public void logRouting(String request, String incomingIp, Instant time, String service) {

		LogEntryModel logEntryModel = new LogEntryModel();
		logEntryModel.setIncomingIp(incomingIp);
		logEntryModel.setRequest(request);
		logEntryModel.setRequestTime(Timestamp.from(time));
		logEntryModel.setService(service);
		loggingRepository.save(logEntryModel);
	}

}
