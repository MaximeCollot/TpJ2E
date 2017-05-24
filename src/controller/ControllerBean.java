package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ControllerBean implements Serializable{
	
	public ControllerBean (){
		
	}
	
	public String goTo(String page) throws IOException {
		
		switch(page) {
		   case "Shop" :
			   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			    externalContext.redirect("http://www.aupanierfermier.com/");
		      return "Go to http://www.aupanierfermier.com/";
			case "Recipe" :
				   return "recipe";
			case "Emergency" :
				   return "emergency";
			case "Home" :
				   return "home";
			default : 
				   return "home";
		}
	}

}
