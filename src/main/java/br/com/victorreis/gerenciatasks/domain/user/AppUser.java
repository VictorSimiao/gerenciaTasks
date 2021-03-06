package br.com.victorreis.gerenciatasks.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "app_user")
public class AppUser {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@NotBlank(message = "O nome de usu�rio � obrigat�rio")
	private String username;
	
	@NotBlank(message = "A senha � obrigat�ria")
	private String password;
	
	@NotBlank(message = "O nome de exibi��o � obrigat�rio")
	private String displayName;

	public AppUser() {
		
	}

	public AppUser(String username, String password, String displayName) {
		super();
		this.username = username;
		this.password = password;
		this.displayName = displayName;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getId() {
		return id;
	}
	
	
	
}
