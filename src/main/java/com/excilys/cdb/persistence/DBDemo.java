package com.excilys.cdb.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBDemo {

	private final static String serverName = "localhost";
	private final static int portNumber = 3306;
	private final static String dbName = "computer-database-db";

	static Logger logger = LoggerFactory.getLogger(DBDemo.class);
	private static Connection connect;
	private final static DBDemo dbdemo = new DBDemo();

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

	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); 
			return true;
		} finally {

			if (stmt != null) { stmt.close(); }
		}
	}

	private DBDemo() {
		super();
	}

	public static Connection connectionDB() throws IOException{
		Properties connectionProps = new Properties();
		Properties prop;
		try {
			prop = DBDemo.load("/home/excilys/eclipse-workspace/Computer-database/src/main/resources/login.properties");
			connectionProps.put("user", prop.getProperty("userName"));
			connectionProps.put("password", prop.getProperty("password"));

		}catch (FileNotFoundException e1) {
			logger.error("le fichier de loggind est introuvable");
		}
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://"
						+ serverName + ":" + portNumber + "/" + dbName +"?useSSL=false&serverTimezone=CET",
						connectionProps);
				System.out.println("Connexion établie ! \n");
			} catch (SQLException e) {
				logger.error("ERROR: Could not connect to the database");
			} catch (ClassNotFoundException e) {
			}		
		return connect;
	}
	public static DBDemo getInstance(){
		return dbdemo;
	}
	public static Connection getConnection() {
		return connect;
	}


}
