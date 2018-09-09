package mySqlBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingleConnection {
	public static Connection getConnection() throws SQLException, IOException {
		/*
		 * recuperation des informations de connexion dans un fichier *.properties
		 */
		Properties Properties = new Properties();
		FileInputStream fis = new FileInputStream("dl.properties");
		Properties.load(fis);
		fis.close();
		
		String drivers = Properties.getProperty("jdbc.drivers");
		if (drivers != null) {
			System.setProperty("jdbc.drivers", drivers);
		}// Eo if...
		String url = Properties.getProperty("jdbc.url");
		String usr = Properties.getProperty("jdbc.username");
		String pwd = Properties.getProperty("jdbc.password");
		
//		// tentative 2 connexion...
		return DriverManager.getConnection(url, usr, pwd);

	}
}
