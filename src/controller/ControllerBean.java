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

import DAO.UserDao;
import connexion.Connexion;
import model.User;

@ManagedBean
@SessionScoped
public class ControllerBean implements Serializable{
	
	private static final long serialVersionUID = -8195560381810150238L;
	private User user;
	private UserDao userDao;
	private Boolean connected;
	private Integer connectionCounter;
	
	public ControllerBean (){
		user = new User();
		connected = false;
		connectionCounter = 0;	
		
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
		ServletContext context = SessionUtils.getServletContext();
		if(context.getAttribute("CONNECTIONS")!=null){
			Integer counterApp=(Integer)context.getAttribute("CONNECTIONS");
			connectionCounter=counterApp;
		}
		
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
			case "Admin" :
					if (user.isAdmin()){
						return "admin?faces-redirect=true";
					}else{
						return null;
					}
			default : 
				   return "home?faces-redirect=true";
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	public Integer getConnectionCounter() {
		return connectionCounter;
	}

	public void setConnectionCounter(Integer connectionCounter) {
		this.connectionCounter = connectionCounter;
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
			connectionCounter = 1;
			ServletContext context = SessionUtils.getServletContext();
			if(context.getAttribute("CONNECTIONS")!=null){
				Integer counterApp=(Integer)context.getAttribute("CONNECTIONS");
				counterApp++;
				connectionCounter=counterApp;
			}
			context.setAttribute("CONNECTIONS",connectionCounter);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous �tes connect�s !", null));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Probl�me de couple login/mot de passe", null));
		}
	}
	
	public void logout () {
		user = new User();
		connected = false;
		ServletContext context = SessionUtils.getServletContext();
		if(context.getAttribute("CONNECTIONS")!=null){
			Integer counterApp=(Integer)context.getAttribute("CONNECTIONS");
			counterApp--;
			connectionCounter=counterApp;
		}
		context.setAttribute("CONNECTIONS",connectionCounter);
	}

}
