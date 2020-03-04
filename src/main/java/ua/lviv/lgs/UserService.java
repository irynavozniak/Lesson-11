package ua.lviv.lgs;

public interface UserService extends DAOAbstractCRUD<User>{
	
	User readByEmail(String email) throws DAOException;
	boolean updateByEmail(User t) throws DAOException;
	
}