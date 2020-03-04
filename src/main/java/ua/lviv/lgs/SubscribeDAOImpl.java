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

public class SubscribeDAOImpl implements SubscribeDAO {
	private Logger log = Logger.getLogger(SubscribeDAOImpl.class);

	@Override
	public Subscribe insert(Subscribe subscribe)
			throws DAOException {
		log.info("Creating new subscribe in database");
		String sqlQuery = "insert into subscribe(`userId`, `magazineId`, `subscribeStatus`, `subscribeDate`, `subscribePeriod`) values";

		Subscribe newSubscribe = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection");
			connection = DAOOffice.getInstance().getConnection();

			log.trace("Creating prepared statement");
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, subscribe.getUserID());
			statement.setInt(2, subscribe.getMagazineID());
			statement.setBoolean(3, subscribe.getSubscribeStatus());
			statement.setDate(4, Date.valueOf(subscribe.getSubscribeDate()));
			statement.setInt(5, subscribe.getSubscribePeriod());

			log.trace("Executing database update");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) added!", rows));

			log.trace("Getting result set");
			if (rows == 0) {
				log.error("Creating subscribe failed, no rows affected!");
				throw new DAOException("Creating subscribe failed, no rows affected!");
			} else {
				resultSet = statement.getGeneratedKeys();

				if (resultSet.next()) {
					log.trace("Creating Subscribe to return");
					newSubscribe = new Subscribe(resultSet.getInt(1), subscribe.getUserID(), subscribe.getMagazineID(),
							subscribe.getSubscribeStatus(), subscribe.getSubscribeDate(), subscribe.getSubscribePeriod());
				}
			}
		} catch (SQLException e) {
			log.error("Creating subscribe failed!");
			throw new DAOException("Creating subscribe failed!", e);
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

		log.trace("Returning Subscribe");
		log.info(newSubscribe + " is added to database!");
		return newSubscribe;
	}

	@Override
	public List<Subscribe> readAll() throws DAOException {
		log.info("Getting all subscribes from database");
		String sqlQuery = "select * from subscribe";

		List<Subscribe> subscribeList = new ArrayList<>();
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

			log.trace("Creating list of subscribes to return");
			while (resultSet.next()) {
				subscribeList.add(new Subscribe(resultSet.getInt("id"), resultSet.getInt("userId"), resultSet.getInt("magazineId"), resultSet.getBoolean("subscribeStatus"), resultSet.getDate("subscribeDate").toLocalDate(), resultSet.getInt("subscribePeriod")));
			}
		} catch (SQLException e) {
			log.error("Getting list of subscribes failed!");
			throw new DAOException("Getting list of subscribes failed!", e);
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

		log.trace("Returning list of subscribes");
		return subscribeList;
	}

	@Override
	public Subscribe readByID(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateByID(Subscribe t) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
}