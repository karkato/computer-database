package com.excilys.cdb.configspring;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("classpath:login.properties")
@ComponentScan(basePackages={"com.excilys.cdb.service","com.excilys.cdb.persistence","com.excilys.cdb.model","com.excilys.cdb.service"})

public class HibernateConfig {

	@Autowired
	private Environment env;

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DriverManagerDataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("com.excilys.cdb.model");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(hibernateProperties());
		return emf;
	}

	Properties hibernateProperties() {
		return new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				setProperty("hibernate.show_sql", "true");
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				setProperty("hibernate.order_updates", "true");
			}
		};
	}
}