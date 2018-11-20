package com.excilys.cdb.configspring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:login.properties")
@ComponentScan({"com.excilys.cdb.service","com.excilys.cdb.mapper","com.excilys.cdb.persistence","com.excilys.cdb.controller","com.excilys.cdb.configspring"})
public class HibernateConfig {

	@Autowired
	private Properties prop;
	@Bean
	public DataSource dataSource() {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("login.properties");
		prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
			
		}

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(prop.getProperty("driverClassName"));
		dataSource.setUrl(prop.getProperty("JdbcUrl"));
		dataSource.setUsername(prop.getProperty("user"));
		dataSource.setPassword(prop.getProperty("password"));
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager =  new DataSourceTransactionManager(dataSource);
		return transactionManager;
	}


/*
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
	        return emf;
	}*/
}