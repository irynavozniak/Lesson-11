package ua.lviv.lgs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Application {
	public static void main(String[] args) throws DAOException {
		Logger log = Logger.getLogger(Application.class);
		PropertyConfigurator.configure("log4j.config.properties");
		log.trace("Starting application");
		
		List<User> userList = new ArrayList<>();
		userList.add(new User("Iryna", "Vozniak", "irenka_v@meta.ua", "0000", "USER"));
		userList.add(new User("Yana", "Ponomarenko", "ponomarenko@gmail.com", "123456", "USER"));

		UserService userService = new UserServiceImpl();
		userList.forEach(user -> {
			try {
				System.out.println(userService.insert(user));
			} catch (DAOException e) {
				log.error("Error occured!", e);
				e.printStackTrace();
			}
		});
		
		System.out.println(userService.readByID(2));
		System.out.println(userService.readByEmail("ponomarenko@gmail.com"));
		userService.updateByID(new User(1, "Oleg", "Makarov", "makarovo@gmail.com", "11111", "АDMIN"));
		userService.updateByEmail(new User("Oleksands", "Koroviy", "sasha@gmail.com", "09876", "USER"));
		userService.delete(2);
		userService.readAll().forEach(System.out::println);

		MagazineService magazineService = new MagazineServiceImpl();
		System.out.println(
				magazineService.insert(new Magazine("Fashion", "A lot of interesting information about fashion trands!", LocalDate.parse("2020-02-25"), 1250)));
		magazineService.readAll().forEach(System.out::println);

		SubscribeService subscribeService = new SubscribeServiceImpl();
		System.out.println(subscribeService.insert(new Subscribe(1, 1, true, LocalDate.parse("2020-02-25"), 21)));
		subscribeService.readAll().forEach(System.out::println);
	}
}
