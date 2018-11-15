package com.excilys.cdb.configspring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("classpath:login.properties")
@ComponentScan({"com.excilys.cdb.service","com.excilys.cdb.mapper","com.excilys.cdb.persistence","com.excilys.cdb.controller","com.excilys.cdb.configspring"})
public class DBDemo {

	static Logger logger = LoggerFactory.getLogger(DBDemo.class);

	@Bean
	public DataSource dataSource() {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("login.properties");
		Properties prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			logger.error(e.getMessage());
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

	

	/* @Autowired
	    private Properties prop;

	    @Bean
	    public DriverManagerDataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();

	        dataSource.setDriverClassName(prop.getProperty("driverClassName"));
	        dataSource.setUrl(prop.getProperty("JdbcUrl"));
	        dataSource.setUsername(prop.getProperty("user"));
	        dataSource.setPassword(prop.getProperty("password"));

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
	        return emf;
	}*/

}
