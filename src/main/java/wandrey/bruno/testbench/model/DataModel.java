/**
 *
 */
package wandrey.bruno.testbench.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Bruno Wandrey
 *
 */
@Data
@Entity
@Table(name = "data_table")
public class DataModel {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "data")
	private byte[] data;
	@Column(name = "file_type")
	private String fileType;

}
