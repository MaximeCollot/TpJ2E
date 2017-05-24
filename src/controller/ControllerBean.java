package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControllerBean implements Serializable{
	
	public ControllerBean (){
		
	}
	
	public String goTo(String page) {
		
		switch(page) {
		   case "Shop" :
		      return "http://www.aupanierfermier.com/";
		      break;
		   
		   case "Recipe" :
			   return "Recipe";
		      break;
		     
		   case "Emergency" :
			   return "Emergency";
			   break; 
		   
		   default : 
			   return "Home";
			   break;
		}
	}

}
