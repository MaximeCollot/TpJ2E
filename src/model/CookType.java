package model;

public enum CookType {
	SALAD("SALAD"), MEAL("MEAL"), FISH("FISH"), DESSERT("DESSERT"), NONE("NONE");
	
	private String type;
	
	CookType(String type){
		this.type = type;
	}
}