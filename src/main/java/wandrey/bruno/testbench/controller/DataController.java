/**
 *
 */
package wandrey.bruno.testbench.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wandrey.bruno.testbench.model.DataModel;
import wandrey.bruno.testbench.service.DataService;

/**
 * @author Bruno Wandrey
 *
 */

@RestController
@RequestMapping("/data/")
public class DataController {

	@Autowired
	DataService dataService;

	@GetMapping
	public Page<DataModel> getPage(@PageableDefault(size = 15, page = 0) Pageable page) {
		return dataService.getPage(page);
	}

	@PostMapping
	public DataModel postData(@RequestBody DataModel data) {
		return dataService.postData(data);
	}

}
