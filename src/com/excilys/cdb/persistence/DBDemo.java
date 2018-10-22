package com.excilys.cdb.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class DBDemo {

	/** The name of the computer running MySQL */
	private final static String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final static int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final static String dbName = "computer-database-db";

	private static Connection connect;





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

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) { stmt.close(); }
		}
	}

	public void test(Connection conn) throws SQLException {

		Statement state = conn.createStatement();
		//L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery("SELECT id,name FROM computer LIMIT 10");
		//On récupère les MetaData
		ResultSetMetaData resultMeta = (ResultSetMetaData) result.getMetaData();

		result.close();
		state.close();

	}   


	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() {
		try {
			connect=getInstance();
			System.out.println("Connected to database");
			this.test(connect);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			System.out.println("");
			e.printStackTrace();
			try {
				connect.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Méthode qui va retourner notre instance
	 * et la créer si elle n'existe pas...
	 * @return
	 */
	public static Connection getInstance(){
		Properties connectionProps = new Properties();
		Properties prop;
		try {
			prop = DBDemo.load("login.properties");
			connectionProps.put("user", prop.getProperty("userName"));
			connectionProps.put("password", prop.getProperty("password"));
			if(connect == null){
				try {
					connect = DriverManager.getConnection("jdbc:mysql://"
							+ serverName + ":" + portNumber + "/" + dbName +"?useSSL=false&serverTimezone=CET",
							connectionProps);
					System.out.println("Connexion établie ! \n");
				} catch (SQLException e) {
					System.out.println("ERROR: Could not connect to the database");
					System.out.println("");
					e.printStackTrace();
				}
			}		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return connect;
	}


}
