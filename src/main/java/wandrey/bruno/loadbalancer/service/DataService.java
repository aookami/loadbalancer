/**
 *
 */
package wandrey.bruno.loadbalancer.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import wandrey.bruno.loadbalancer.model.DataModel;
import wandrey.bruno.loadbalancer.repository.DataRepository;

/**
 * @author Bruno Wandrey
 *
 */

@Service
public class DataService {

	Logger logger = LoggerFactory.getLogger(DataService.class);

	@Autowired
	DataRepository dataRepository;

	/**
	 * @param page
	 * @return
	 */
	public Page<DataModel> getPage(Pageable page) {
		return dataRepository.findAll(page);
	}

	/**
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public DataModel postData(DataModel data) {

		return dataRepository.save(data);
	}

}
