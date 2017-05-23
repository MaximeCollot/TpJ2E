package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ControllerBean implements Serializable{
	
	public ControllerBean (){
		
	}
	
	public void goToHome() {
		System.out.println("home");
	}

}
