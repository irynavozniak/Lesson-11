package ua.lviv.lgs;

import java.util.List;

public class SubscribeServiceImpl implements SubscribeService {

	private static SubscribeService subscribeService;
	private SubscribeDAO subscribeDAO;

	SubscribeServiceImpl() {
		this.subscribeDAO = new SubscribeDAOImpl();
	}

	public static SubscribeService getSubscribeService() {
		if (subscribeService == null) {
			subscribeService = new SubscribeServiceImpl();
		}

		return subscribeService;
	}
	
	@Override
	public Subscribe insert(Subscribe t) throws DAOException {
		return subscribeDAO.insert(t);
	}

	@Override
	public List<Subscribe> readAll() throws DAOException {
		return subscribeDAO.readAll();
	}

	@Override
	public Subscribe readByID(int id) throws DAOException {
		return null;
	}

	@Override
	public boolean updateByID(Subscribe t) throws DAOException {
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		return false;
	}

}