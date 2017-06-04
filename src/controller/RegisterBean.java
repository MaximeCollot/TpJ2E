package controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DAO.UserDao;
import connexion.Connexion;
import model.User;

@ManagedBean
@RequestScoped
public class RegisterBean implements Serializable {
	
	private UserDao ud;
	private User user;



	public RegisterBean() {
		super();
		try {
			this.ud = new UserDao(Connexion.getInstance());
			this.user = new User();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save(){
		try {
			System.out.println("USER :"+user.toString());
			ud.insert(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User getUser() {
		return user;
	}

	public void setU(User u) {
		this.user = user;
	}
	
	
	

}
