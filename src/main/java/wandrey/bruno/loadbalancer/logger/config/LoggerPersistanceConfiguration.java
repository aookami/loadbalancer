/**
 *
 */
package wandrey.bruno.loadbalancer.logger.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Bruno Wandrey
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "wandrey.bruno.loadbalancer.logger.repository", entityManagerFactoryRef = "loggerEntityManager", transactionManagerRef = "loggerTransactionManager")
public class LoggerPersistanceConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean loggerEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(loggerDataSource());
		em.setPackagesToScan("wandrey.bruno.loadbalancer.logger.model");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.mysql.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public DataSource loggerDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("logger.driverClassName"));
		dataSource.setUrl(env.getProperty("logger.url"));
		dataSource.setUsername(env.getProperty("logger.user"));
		dataSource.setPassword(env.getProperty("logger.pass"));

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager loggerTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(loggerEntityManager().getObject());
		return transactionManager;
	}

}
