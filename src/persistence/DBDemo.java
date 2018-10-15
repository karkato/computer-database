package persistence;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Properties;

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
		
		/**
		 * Connect to MySQL and do some stuff.
		 */
		public void run() {

			try {
				this.getConnection();
				System.out.println("Connected to database");
			} catch (SQLException e) {
				System.out.println("ERROR: Could not connect to the database");
				System.out.println("");
				e.printStackTrace();
				return;
			}

			// Create a table
			/*try {
			    String createString =
				        "CREATE TABLE " + this.tableName + " ( " +
				        "ID INTEGER NOT NULL, " +
				        "NAME varchar(40) NOT NULL, " +
				        "STREET varchar(40) NOT NULL, " +
				        "CITY varchar(20) NOT NULL, " +
				        "STATE char(2) NOT NULL, " +
				        "ZIP char(5), " +
				        "PRIMARY KEY (ID))";
				this.executeUpdate(conn, createString);
				System.out.println("Created a table");
		    } catch (SQLException e) {
				System.out.println("ERROR: Could not create the table");
				e.printStackTrace();
				return;
			}
			
			// Drop the table
			try {
			    String dropString = "DROP TABLE " + this.tableName;
				this.executeUpdate(conn, dropString);
				System.out.println("Dropped the table");
		    } catch (SQLException e) {
				System.out.println("ERROR: Could not drop the table");
				e.printStackTrace();
				return;
			}
			
			// Show the table
			try {
			    String dropString = "SHOW TABLES; "; //+ this.tableName;
				this.executeUpdate(this.getConnection(), dropString);
				System.out.println("This is the table");
		    } catch (SQLException e) {
				System.out.println("ERROR: Could not drop the table");
				e.printStackTrace();
				return;
			}*/
		}
		
		/**
		 * Connect to the DB and do some stuff
		 */
		public static void main(String[] args) {
			DBDemo app = new DBDemo();
			app.run();
		}
	}
