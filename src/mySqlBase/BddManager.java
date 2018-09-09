package mySqlBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import mySqlBase.SingleConnection;

public class BddManager implements IBdd {
	// connection settings
	
	@SuppressWarnings("unused")
	private String host,base,password,settings;
	
	// is connected
	@SuppressWarnings("unused")
	private Boolean connected = false;
	
	
	// handling connection
	public Connection connection;
	//public Statement st; 
	
	/**
	 * CONSTRUCTOR :
	 * pass settings : host, base, password, settings
	 * 
	 * @param host
	 * @param userName
	 * @param password
	 * @param settings
	 */
	public BddManager(String host, String userName, String password, String settings) {
		this.host = host;
		this.base = userName;
		this.password = password;
		this.settings = settings;
	}	
	/**
	 * 
	 */
	public BddManager(String propertiesFile) { }
	
	
	/**
	 *  Connection
	 *  get a connection update connected
	 */
	public void connect() {
		System.out.println("Bo connection...");
		//Connection connection = null;
		try {
			connection = SingleConnection.getConnection();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		connected = true;
	}//end of connection
	
	public void close() {
		try {
			System.out.println("Eo connection");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int bddManagerExecuteUpdate(String query) {
		this.connect();
		int response= -1; // false
	
		try {
			System.out.println(query);
			Statement st  = connection.createStatement();
			
			try {
				response = st.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			this.close();
		}
		return response;
	}
	
	
}
