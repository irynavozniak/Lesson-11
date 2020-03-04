package ua.lviv.lgs;

import java.util.List;
import java.util.Map;

public class MagazineServiceImpl implements MagazineService {

	private static MagazineService magazineService;
	private MagazineDAO magazineDAO;

	MagazineServiceImpl() {
		this.magazineDAO = new MagazineDAOImpl();
	}

	public static MagazineService getMagazineService() {
		if (magazineService == null) {
			magazineService = new MagazineServiceImpl();
		}

		return magazineService;
	}

	@Override
	public Magazine insert(Magazine t) throws DAOException {
		return magazineDAO.insert(t);
	}

	@Override
	public List<Magazine> readAll() throws DAOException {
		return magazineDAO.readAll();
	}

	@Override
	public Magazine readByID(int id) throws DAOException {
		return magazineDAO.readByID(id);
	}

	@Override
	public boolean updateByID(Magazine t) throws DAOException {
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		return false;
	}

	@Override
	public Map<Integer, Magazine> readAllMap() throws DAOException {
		return null;
	}

}