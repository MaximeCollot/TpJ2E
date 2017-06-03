package controller;

import java.io.IOException;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.core.ApplicationContext;

import com.sun.net.httpserver.HttpContext;
import com.sun.org.apache.bcel.internal.generic.CPInstruction;

import DAO.UserDao;
import connexion.Connexion;
import model.User;

@ManagedBean
public class AdminControllerBean implements Serializable{
	
	private static final long serialVersionUID = 6327343996376470073L;
	private User admin;
	private UserDao userDao;
	private Boolean connected;
	private LocalDateTime connectionDateTime;
	
	public AdminControllerBean (){
		admin = new User();
		connected = false;
		
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
		   case "MngUsers" :
		      return "mngUsers?faces-redirect=true";
			case "MngRecipes" :
				   return "mngRecipes?faces-redirect=true";
			case "AdminHome" :
					return "admin?faces-redirect=true";
			default : 
				   return "admin?faces-redirect=true";
		}
	}
	
	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	public void connect () {
		User tempAdmin = null;
		try {
			tempAdmin = userDao.select(admin.getLogin());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (admin.getPassword().equals(tempAdmin.getPassword()) && tempAdmin.isAdmin()){
			admin = tempAdmin;
			connected = true;
			connectionDateTime = LocalDateTime.now();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vous êtes connectés !", null));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Problème de couple login/mot de passe", null));
		}
	}
	
	@PreDestroy
	public void updateAdmin(){
		admin.setLastConnection(LocalDateTime.now().toString());
		admin.setConnectionDuration(connectionDateTime.until(LocalDateTime.now(), ChronoUnit.MINUTES));
		try {
			userDao.update(admin);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
