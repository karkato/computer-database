package com.excilys.cdb.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBDemo {

	static Logger logger = LoggerFactory.getLogger(DBDemo.class);
	private static Connection connection;
	
	private final static DBDemo dbdemo = new DBDemo();
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;
	
	private DBDemo() {
		super();
		config = new HikariConfig("/home/excilys/eclipse-workspace/Computer-database/src/main/resources/login.properties");
		ds = new HikariDataSource(config);
	}
	public static Properties load(String filename) throws IOException, FileNotFoundException{
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream(filename); 

		try{
			properties.load(input);
			return properties;
		}
		finally{
			input.close();
		}
	}

	public static   Connection connectionDB() throws IOException, SQLException{

		
		try {
			Connection connect = ds.getConnection();
			return connect;
		} catch (SQLException e) {
			logger.error("Echec de la connexion.");
			ds.close();
			return null;
		}

	}
	public static DBDemo getInstance(){
		return dbdemo;
	}
	public static Connection getConnection() {
		return connection;
	}


}
