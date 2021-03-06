package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class User {

	private String firstName;
	private String lastName;
	private String age;
	private String email;
	private String login;
	private String password;
	private boolean isAdmin;
	private String lastConnection;
	private long connectionDuration;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String ddn, String email,
			String login, String password, boolean isAdmin, String lastConnection, int connectionDuration) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = ddn;
		this.email = email;
		this.login = login;
		this.password = password;
		this.isAdmin=isAdmin;
		this.lastConnection=lastConnection;
		this.connectionDuration=connectionDuration;
	}
	
	public User(String firstName, String lastName, String ddn, String email,
			String login, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = ddn;
		this.email = email;
		this.login = login;
		this.password = password;
		this.isAdmin=false;
	}
	
	

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String ddn) {
		this.age = ddn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(String lastConnection) {
		this.lastConnection = lastConnection;
	}

	public long getConnectionDuration() {
		return connectionDuration;
	}

	public void setConnectionDuration(long connectionDuration) {
		this.connectionDuration = connectionDuration;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", ddn=" + age + ", email=" + email
				+ ", login=" + login + ", password=" + password + "]";
	}

}
