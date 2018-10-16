package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class DBDemo {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "admincdb";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "Qwerty1234";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "computer-database-db";

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		//Class.forName("");

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName+"?useSSL=false&serverTimezone=CET",
				connectionProps);

		return conn;
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
		ResultSet result = state.executeQuery("SELECT * FROM company");
		//On récupère les MetaData
		ResultSetMetaData resultMeta = (ResultSetMetaData) result.getMetaData();

		System.out.println("\n**********************************");
		//On affiche le nom des colonnes
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

		System.out.println("\n**********************************");

		while(result.next()){         
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + result.getObject(i).toString() + "\t |");

			System.out.println("\n---------------------------------");

		}

		result.close();
		state.close();

	}   


	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() {

		try {
			this.getConnection();
			System.out.println("Connected to database");
			//this.test(this.getConnection());
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			System.out.println("");
			e.printStackTrace();
			//return;
		}
	}

	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		DBDemo app = new DBDemo();
		app.run();
	}
}
