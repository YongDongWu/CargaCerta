package com.cargacerta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargacerta.models.User;
import com.cargacerta.repositories.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<User> getAll() {
		List<User> list = repository.getAll();
		
		return list;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody User getById(@PathVariable long id) {
		return repository.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody User create(@RequestBody User user) {
		return repository.save(user);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody User update(@PathVariable long id, @RequestBody User user) {
		user.setId(id);

		return repository.update(user);
	}
}
