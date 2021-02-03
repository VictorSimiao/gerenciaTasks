package br.com.victorreis.gerenciatasks.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.victorreis.gerenciatasks.domain.user.AppUser;

@Entity
public class Task {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String descricao;
	
	private LocalDate efetuarEm;
	
	private Boolean feita = false;
	
	@ManyToOne
	@JoinColumn(name = "app_user_id")
	private AppUser appUser;

	public Task() {
		
	}

	public Task(String descricao, LocalDate efetuarEm, Boolean feita) {
		super();
		this.descricao = descricao;
		this.efetuarEm = efetuarEm;
		this.feita = feita;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getEfetuarEm() {
		return efetuarEm;
	}

	public void setEfetuarEm(LocalDate efetuarEm) {
		this.efetuarEm = efetuarEm;
	}

	public Boolean getFeita() {
		return feita;
	}

	public void setFeita(Boolean feita) {
		this.feita = feita;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Integer getId() {
		return id;
	}
	
	
}
