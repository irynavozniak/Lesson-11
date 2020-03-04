package ua.lviv.lgs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class MagazineDAOImpl implements MagazineDAO {
	private Logger log = Logger.getLogger(MagazineDAOImpl.class);

	@Override
	public Magazine insert(Magazine magazine)
			throws DAOException {
		log.info("Creating new magazine in database");
		String sqlQuery = "insert into magazine(`title`, `description`, `publishingDate`, `Price`) values";

		Magazine newMagazine = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection");
			connection = DAOOffice.getInstance().getConnection();

			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, magazine.getTitle());
			statement.setString(2, magazine.getDescription());
			statement.setDate(3, Date.valueOf(magazine.getPublishingDate()));
			statement.setInt(4, magazine.getPrice());

			log.trace("Executing database update");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) added!", rows));

			log.trace("Getting result set");
			if (rows == 0) {
				log.error("Creating magazine failed, no rows affected!");
				throw new DAOException("Creating magaziner failed, no rows affected!");
			} else {
				resultSet = statement.getGeneratedKeys();

				if (resultSet.next()) {
					log.trace("Creating Magazine to return");
					newMagazine = new Magazine(resultSet.getInt(1), magazine.getTitle(), magazine.getDescription(), magazine.getPublishingDate(), magazine.getPrice());
				}
			}
		} catch (SQLException e) {
			log.error("Creating magazine failed!");
			throw new DAOException("Creating magazine failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning Magazine");
		log.info(newMagazine + " is added to database!");
		return newMagazine;
	}

	@Override
	public List<Magazine> readAll() throws DAOException {
		log.info("Getting all magazines from database");
		String sqlQuery = "select * from magazine";

		List<Magazine> magazineList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection");
			connection = DAOOffice.getInstance().getConnection();

			log.trace("Creating prepared statement");
			statement = connection.prepareStatement(sqlQuery);

			log.trace("Getting result set");
			resultSet = statement.executeQuery();

			log.trace("Creating list of magazines to return");
			while (resultSet.next()) {
				magazineList.add(new Magazine(resultSet.getInt("id"), resultSet.getString("title"),
						resultSet.getString("description"), resultSet.getDate("publishingDate").toLocalDate(),
						resultSet.getInt("Price")));
			}
		} catch (SQLException e) {
			log.error("Getting list of magazines failed!");
			throw new DAOException("Getting list of magazines failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning list of magazines");
		return magazineList;
	}

	@Override
	public Magazine readByID(int id) throws DAOException {
		log.info("Getting magazine by id from database");
		String sqlQuery = "select * from magazine where id = ?";

		Magazine magazine = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection");
			connection = DAOOffice.getInstance().getConnection();

			log.trace("Creating prepared statement");
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, id);

			log.trace("Getting result set");
			resultSet = statement.executeQuery();

			log.trace("Creating Magazine to return");
			while (resultSet.next()) {
				magazine = new Magazine(resultSet.getInt("id"), resultSet.getString("title"),
						resultSet.getString("description"), resultSet.getDate("publishingDate").toLocalDate(),
						resultSet.getInt("Price"));
			}
		} catch (SQLException e) {
			log.error("Getting magazine by id failed!");
			throw new DAOException("Getting magazine by id failed!", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed!");
			} catch (SQLException e) {
				log.error("Result set can't be closed!", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed!");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed!", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed!");
			} catch (SQLException e) {
				log.error("Connection can't be closed!", e);
			}
		}

		log.trace("Returning Magazine");
		log.info(magazine + " is getted from database!");
		return magazine;
	}

	@Override
	public boolean updateByID(Magazine t) throws DAOException {
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		return false;
	}
}