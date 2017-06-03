package controller;


import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.RecetteDao;

import connexion.Connexion;
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
	private List <Recette> listResult;
	
	private Recette selectedRecette;
	

	public Recette getSelectedRecette() {
		System.out.println("test_get");
		return selectedRecette;
	}

	public void setSelectedRecette(Recette selectedRecette) {
		System.out.println("test_set");
		this.selectedRecette = selectedRecette;
	}

	public List<Recette> getListResult() {
		return listResult;
	}

	public void setListResult(List<Recette> listResult) {
		this.listResult = listResult;
	}

	public RecetteDao getRecetteDao() {
		return recetteDao;
	}

	public void setRecetteDao(RecetteDao recetteDao) {
		this.recetteDao = recetteDao;
	}

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
		listResult = new ArrayList<Recette>();
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
	
	public String search () throws Exception {
		recipe_view = "result";
		listResult = recetteDao.search(recette);
		
		return "recipe";
	}
	
	public String comeback () {
		
		recipe_view = "search";
		listResult.clear();
		recette =new Recette();
		return "recipe";
		
	}

}
