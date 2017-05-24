package controller;

import java.io.IOException;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import DAO.UserDao;
import connexion.Connexion;
import model.User;

@ManagedBean
@SessionScoped
public class ControllerBean implements Serializable{
	
	private User user;
	private Boolean connected;
	private UserDao userDao;

	
	public ControllerBean (){
		user = new User();
		connected=false;
		try {
			userDao = new UserDao(Connexion.getInstance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String goTo(String page) throws IOException {
		
		switch(page) {
		   case "Shop" :
			   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			    externalContext.redirect("http://www.aupanierfermier.com/");
		      return "Go to http://www.aupanierfermier.com/";
			case "Recipe" :
				   return "recipe";
			case "Emergency" :
				   return "emergency";
			case "Home" :
				   return "home";
			default : 
				   return "home";
		}
	}
	
	public User getUser(){
		return this.user;
	}
	
	public void connect () {
		User tempUser = null;
		try {
			tempUser = userDao.select(user.getLogin());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user.getPassword().equals(tempUser.getPassword())){
			user = tempUser;
			connected = true;
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Problème de couple login/mot de passe", null));
		}
	}

}
