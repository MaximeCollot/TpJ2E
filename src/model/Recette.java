package model;

public class Recette {

	private int id = -1;
	private String name ="INIT";
	private String recipe= "INIT";
	private int preparationTime = -1;
	private int level = -1;
	private int nbPeople = -1;
	private String cooktype = " ";
	private String photo =" ";
	
	

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
		setPhoto("");
		System.out.println(this.photo);
	}
	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		switch (this.cooktype) {
        case "SALAD":  this.photo = "ressource/logo_salad.png";
                 break;
        case "FISH":  this.photo = "ressource/logo_fish.png";
        	break;
        case "DESSERT":  this.photo = "ressource/logo_dessert.png";
        	break;
        case "MEAL":  this.photo = "ressource/logo_meal.png";
        	break;}
		
    
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