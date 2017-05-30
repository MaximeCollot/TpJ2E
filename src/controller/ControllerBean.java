package controller;

import java.io.IOException;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.UserDao;
import connexion.Connexion;
import model.User;

@ManagedBean
@SessionScoped
public class ControllerBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private User user;
	private UserDao userDao;
	
	private String connected;

	
	public ControllerBean (){
		user = new User();
		connected = "ko";
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("connected", connected);
		
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
				   return "recipe?faces-redirect=true";
			case "Emergency" :
				   return "emergency?faces-redirect=true";
			case "Home" :
				   return "home?faces-redirect=true";
			default : 
				   return "home?faces-redirect=true";
		}
	}
	
	public User getUser(){
		return this.user;
	}
	
	public String getConnected(){
		return this.connected;
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
			connected = "ok";
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("connected", connected);
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Problème de couple login/mot de passe", null));
		}
	}

}
