package com.shopping.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "enabled")
	private Boolean enabled;
	@Column(name = "fullname")
	private String fullName;
	@Column(name = "address")
	private String address;
	@Column(name = "email")
	private String email;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "role")
    private String role;
	@Column(name = "city")
    private String city;
	@OneToOne(mappedBy = "user")
    private ShoppingCart shoppingCart;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    private List<Order> orders;
	


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}








	public User(Long id, String username, String password, Boolean enabled, String fullName, String address,
			String email, String telephone, String role, ShoppingCart shoppingCart,String city, List<Order> orders) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.fullName = fullName;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		this.role = role;
		this.city = city;
		this.shoppingCart = shoppingCart;
		this.orders = orders;
	}








	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getCity() {
		return city;
	}








	public void setCity(String city) {
		this.city = city;
	}








	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}





	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}




	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}




	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}








	public List<Order> getOrders() {
		return orders;
	}








	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



	
}
	