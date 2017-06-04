package controller;

import java.io.IOException;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.RecetteDao;
import DAO.UserDao;
import connexion.Connexion;
import model.Recette;
import model.User;

@SessionScoped
@ManagedBean
public class AdminControllerBean implements Serializable{
	
	private static final long serialVersionUID = 6327343996376470073L;
	private User admin;
	private UserDao userDao;
	private RecetteDao recipeDao;
	private Boolean connected;
	private LocalDateTime connectionDateTime;
	private List<User> userList;
	private User selectedUser;
	private User newUser;
	private List<Recette> recipeList;
	private Recette selectedRecipe;
	private Recette newRecipe;
	
	public AdminControllerBean (){
		admin = new User();
		connected = false;
		userList = new ArrayList<>();
		newUser = new User();
		recipeList = new ArrayList<>();
		newRecipe = new Recette();
		
		try {
			userDao = new UserDao(Connexion.getInstance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			recipeDao = new RecetteDao(Connexion.getInstance());
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String goTo(String page) throws IOException {
		
		switch(page) {
		   case "MngUsers" :
			   updateUserList();
			   return "mngusers?faces-redirect=true";
			case "MngRecipes" :
				updateRecipeList();
				return "mngrecipes?faces-redirect=true";
			case "AdminHome" :
				return "index?faces-redirect=true";
			default :
				return "index?faces-redirect=true";
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

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public List<Recette> getRecipeList() {
		return recipeList;
	}

	public void setRecipeList(List<Recette> recipeList) {
		this.recipeList = recipeList;
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
	
	private void updateUserList(){
		try {
			userList = new ArrayList<>(userDao.selectAll().values());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateRecipeList(){
		try {
			recipeList = new ArrayList<>(recipeDao.selectAll().values());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String saveUser() throws IOException {
			try {
				recipeDao.update(selectedRecipe);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				recipeDao.insert(selectedRecipe);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return goTo("MngUsers");
	}
	
	public Recette getSelectedRecipe() {
		return selectedRecipe;
	}

	public void setSelectedRecipe(Recette selectedRecipe) {
		this.selectedRecipe = selectedRecipe;
	}
	
	public Recette getNewRecipe() {
		return newRecipe;
	}

	public void setNewRecipe(Recette newRecipe) {
		this.newRecipe = newRecipe;
	}

	public String removeUser(User user) throws IOException{
		try {
			userDao.delete(user);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User deleted !", null));
		return goTo("MngUsers");
	}

	public String removeRecipe(Recette recette) throws IOException {
		try {
			recipeDao.delete(recette);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recipe deleted !", null));
		return goTo("MngRecipes");
	}
	
	public String saveRecipe() throws IOException {
		if (selectedRecipe.getId() != -1){
			try {
				recipeDao.update(selectedRecipe);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				recipeDao.insert(selectedRecipe);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return goTo("MngRecipes");
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
