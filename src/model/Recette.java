package model;

public class Recette {

	private int id =0;
	private String name ="init";
	private String recipe= "init";
	private int preparationTime = 0;
	private int level = -1;
	private int nbPeople =0;
	private String cooktype ="NULL";
	
	public Recette() {
		super();
	}
	
	public Recette(int id, String name, String recipe, int preparationTime, int level, int nbPeople,
			String cooktype) {
		super();
		this.id = id;
		this.name = name;
		this.recipe = recipe;
		this.preparationTime = preparationTime;
		this.level = level;
		this.nbPeople = nbPeople;
		this.cooktype = cooktype;
	}
	

	public int getId() {
		return id;
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

	public String getCooktype() {
		return cooktype;
	}

	public void setCooktype(String cooktype) {
		this.cooktype = cooktype;
	}

	@Override
	public String toString() {
		return "Recette [id=" + id + ", name=" + name + ", recipe=" + recipe + ", preparationTime=" + preparationTime
				+ ", level=" + level + ", nbPeople=" + nbPeople + ", cookType=" + cooktype + "]";
	}
	
	
	


}