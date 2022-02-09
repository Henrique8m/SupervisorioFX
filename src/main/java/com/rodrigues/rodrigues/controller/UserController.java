package com.rodrigues.rodrigues.controller;


import com.rodrigues.rodrigues.model.dao.UserDao;
import com.rodrigues.rodrigues.model.dto.UserRequest;
import com.rodrigues.rodrigues.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
@Qualifier
@RestController
@RequestMapping(value = "/users")
public class UserController {


	private UserDao dao;
	private final UserRequest dto;
	@Autowired
	private UserService service;

	public UserController(UserRequest dto){
		this.dto = dto;
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Void> createdNewUser( @Valid UserRequest newUser) throws Exception {
		service.saveUser(newUser);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
/*	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponse> findByName(@PathVariable long id) {
		UserResponse userResponse = service.findbyId(id);
		return ResponseEntity.ok().body(userResponse);
	}*/

	//retornar pagina de login
	@GetMapping(value = "/login")
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
}
