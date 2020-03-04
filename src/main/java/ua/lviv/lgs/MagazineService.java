package ua.lviv.lgs;

import java.util.Map;

public interface MagazineService extends DAOAbstractCRUD<Magazine>{
	public Map<Integer, Magazine> readAllMap() throws DAOException;
}
