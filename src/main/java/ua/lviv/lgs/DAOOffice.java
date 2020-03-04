package ua.lviv.lgs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class DAOOffice {
	private static DAOOffice instance;
	private Connection connection;

	private String url = "jdbc:mysql://localhost/magazine_shop?serverTimezone=" + TimeZone.getDefault().getID();
	private String user = "root";
	private String password = "0000";

	private static Logger log = Logger.getLogger(DAOOffice.class);

	private DAOOffice() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			log.error("Database driver class can't be found!", e);
		} catch (SQLException e) {
			log.error("Database connection creation failed!", e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DAOOffice getInstance() {
		if (instance == null) {
			log.trace("Opening connection");
			instance = new DAOOffice();
		} else
			try {
				if (instance.getConnection().isClosed()) {
					log.trace("Reopening connection");
					instance = new DAOOffice();
				}
			} catch (SQLException e) {
				log.error("Database access error!", e);
			}

		return instance;
	}
}
