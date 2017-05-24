import java.io.IOException;
import java.sql.SQLException;

import DAO.RecetteDao;
import DAO.UserDao;
import connexion.Connexion;
import model.Recette;
import model.User;

public class MainTest {

	public static void main(String[] args) {
		try {
			UserDao ud = new UserDao(Connexion.getInstance());
			User u = ud.select("jeanjean");
			System.out.println("User " + u);
			u.setEmail("louis.jura@juraski.com");
			ud.update(u);
			u = ud.select("jeanjean");
			System.out.println("User " + u);
			
			RecetteDao rd = new RecetteDao(Connexion.getInstance());
			Recette r = rd.select(1);
			System.out.println("Recette" + r);
			r.setName("JURACOOK");
			rd.insert(r);
			 r = rd.select(3);
			System.out.println("Recette" + r);

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
