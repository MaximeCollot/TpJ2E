package model;

public enum CookType {
	SALAD("SALAD"), MEAL("MEAL"), FISH("FISH"), DESSERT("DESSERT");
	
	private String type;
	
	CookType(String type){
		this.type = type;
	}
}