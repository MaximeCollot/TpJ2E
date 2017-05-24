package model;

public class User {

	private String firstName;
	private String lastName;
	private String ddn;
	private String email;
	private String login;
	private String password;
	private boolean isAdmin;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String ddn, String email,
			String login, String password, boolean isAdmin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ddn = ddn;
		this.email = email;
		this.login = login;
		this.password = password;
		this.isAdmin=isAdmin;
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

	public String getDdn() {
		return ddn;
	}

	public void setDdn(String ddn) {
		this.ddn = ddn;
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
	

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", ddn=" + ddn + ", email=" + email
				+ ", login=" + login + ", password=" + password + "]";
	}

}