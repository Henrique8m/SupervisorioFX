package com.rodrigues.rodrigues.model.dto;

import com.rodrigues.rodrigues.model.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Qualifier
public class UserRequest {

	@NotBlank
	private String name;

	private String email;
	@NotBlank
	@Size(min = 6)
	private String senha;

	public UserRequest() {}

	public UserRequest(String name, String email, String senha) {
		this.name = name;
		this.email = email;
		this.senha = senha;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
