package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControllerBean implements Serializable{
	
	public ControllerBean (){
		
	}
	
	public void goTo(String page) {
		
		System.out.println("home");
	}

}
