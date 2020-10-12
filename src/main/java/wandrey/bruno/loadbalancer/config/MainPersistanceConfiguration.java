/**
 *
 */
package wandrey.bruno.loadbalancer.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = "wandrey.bruno.loadbalancer.repository", entityManagerFactoryRef = "mainEntityManager", transactionManagerRef = "mainTransactionManager")
public class MainPersistanceConfiguration {

	@Autowired
	private Environment env;

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean mainEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mainDataSource());
		em.setPackagesToScan("wandrey.bruno.loadbalancer.model");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.main.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	@Primary
	public DataSource mainDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("main.driverClassName"));
		dataSource.setUrl(env.getProperty("main.url"));
		dataSource.setUsername(env.getProperty("main.user"));
		dataSource.setPassword(env.getProperty("main.pass"));

		return dataSource;
	}

	@Bean
	@Primary
	public PlatformTransactionManager mainTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(mainEntityManager().getObject());
		return transactionManager;
	}

}
