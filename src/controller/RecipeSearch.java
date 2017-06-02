package controller;


import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.RecetteDao;
import model.Recette;


@ManagedBean
@SessionScoped

public class RecipeSearch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1020826598289442019L;
	private Recette recette;
	private String recipe_view = "search";

	private RecetteDao recetteDao;
	
	
	public String getRecipe_view() {
		return recipe_view;
	}

	public void setRecipe_view(String recipe_view) {
		this.recipe_view = recipe_view;
	}

	public RecipeSearch() {
		super();
		recette = new Recette();
	}
	public RecipeSearch(Recette recette, RecetteDao recetteDao) {
		super();
		this.recette = recette;
		this.recetteDao = recetteDao;
	}
	
	public Recette getRecette() {
		return recette;
	}
	public void setRecette(Recette recette) {
		this.recette = recette;
	}
	
	public String search () {
		recipe_view = "result";
		return "recipe";
		/*try {
			recette = recetteDao.search(recette);
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Probl�me de couple login/mot de passe", null));
		}*/
	}
	public String comeback () {
		
		recipe_view = "search";
		return "recipe";
	}

}
