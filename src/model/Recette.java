package model;

public class Recette {

	private int id =0;
	private String name ="yoyo";
	private String recipe= "on est bien";
	private int preparationTime = 20;
	private int level = 3;
	private int nbPeople =1;
	private String cookType ="SALAD";
	
	
	
	public Recette(int id, String name, String recipe, int preparationTime, int level, int nbPeople,
			String cookType) {
		super();
		this.id = id;
		this.name = name;
		this.recipe = recipe;
		this.preparationTime = preparationTime;
		this.level = level;
		this.nbPeople = nbPeople;
		this.cookType = cookType;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public int getPreparationTime() {
		return preparationTime;
	}
	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNbPeople() {
		return nbPeople;
	}
	public void setNbPeople(int nbPeople) {
		this.nbPeople = nbPeople;
	}
	public String getCookType() {
		return cookType;
	}
	public void setCookType(String cookType) {
		this.cookType = cookType;
	}
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Recette [id=" + id + ", name=" + name + ", recipe=" + recipe + ", preparationTime=" + preparationTime
				+ ", level=" + level + ", nbPeople=" + nbPeople + ", cookType=" + cookType + "]";
	}
	
	
	


	
}