package controller;


import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.RecetteDao;
import DAO.UserDao;
import connexion.Connexion;
import model.Recette;


@ManagedBean
@SessionScoped

public class RecipeSearch implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Recette recette;
	private String recipe_view = "search";
	private Map <Integer, Recette> recettes;

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
		recettes = new HashMap<>();
		try {
			recetteDao = new RecetteDao(Connexion.getInstance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			recette = recetteDao.search(recette);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String comeback () {
		
		recipe_view = "search";
		return "recipe";
		recettes.clear();
	}

}
