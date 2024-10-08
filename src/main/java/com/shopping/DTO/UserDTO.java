package com.shopping.DTO;

public class UserDTO {
	
	private String username;
	
	private String password;
	private String fullname;
	private String email;
	private String telephone;
	private Boolean enabled;
	private String address;
	private String role;
	
	


	
	public UserDTO(String username, String password, String fullname, String email, String telephone, Boolean enabled,
			String address, String role) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.telephone = telephone;
		this.enabled = enabled;
		this.address = address;
		this.role = role;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getFullname() {
		return fullname;
	}





	public void setFullname(String fullname) {
		this.fullname = fullname;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getTelephone() {
		return telephone;
	}





	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}





	public Boolean getEnabled() {
		return enabled;
	}





	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getRole() {
		return role;
	}





	public void setRole(String role) {
		this.role = role;
	}


	
}
