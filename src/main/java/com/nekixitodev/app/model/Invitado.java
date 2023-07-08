package com.nekixitodev.app.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class Invitado implements Serializable {

	private static final long serialVersionUID = 4760112063170030068L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Debe ingresar el nombre")
	private String name;

	@NotBlank(message = "Debe ingresar una referencia")
	private String reference;

	/*
	@NotEmpty(message = "Debe ingresar su email")
	@Email
	private String email;

	@NotBlank(message = "Debe ingresar su celular")
	private String celphone;

	@DateTimeFormat(iso = ISO.DATE)
	@Past
	@NotNull(message = "Debe ingresar su fecha de nacimiento")
	private LocalDate birthDate;
	*/
	private LocalDate registerDate;

	@PrePersist
	public void setRegisterDate() {
		this.registerDate = LocalDate.now();
	}

	/*
	public Invitado(Integer id, String name, String reference, String email, String celphone, LocalDate birthDate,
			LocalDate registerDate) {
		this.id = id;
		this.name = name;
		this.reference = reference;
		this.email = email;
		this.celphone = celphone;
		this.birthDate = birthDate;
	}
	*/
	
	public Invitado(Integer id, String name, String reference, LocalDate registerDate) {
		this.id = id;
		this.name = name;
		this.reference = reference;
	}

	public Invitado() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	/*
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelphone() {
		return celphone;
	}

	public void setCelphone(String celphone) {
		this.celphone = celphone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	*/
	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

}
