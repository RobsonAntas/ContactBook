package br.com.contactBook.model;
//feito por Robson Antas
import java.util.List;

public class User {

	private long id;
	private String name;
	private String password;
	private String email;
	private List<Telephone> telephones;
	private Telephone telephone;

	
	public User() {

	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Telephone> getTelephones() {
		return telephones;
	}


	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}


	public Telephone getTelephone() {
		return telephone;
	}


	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}
	
	

	
	
	
	
}
