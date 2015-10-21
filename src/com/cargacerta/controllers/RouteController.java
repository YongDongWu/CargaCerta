package com.cargacerta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargacerta.models.Route;
import com.cargacerta.repositories.RouteRepository;

@Controller
@RequestMapping("/route")
public class RouteController {
	
	@Autowired
	private RouteRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Route> getAll() {
		List<Route> list = repository.getAll();
		
		return list;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Route getById(@PathVariable long id) {
		return repository.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Route create(@RequestBody Route route) {
		return repository.save(route);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Route update(@PathVariable long id, @RequestBody Route route) {
		route.setId(id);

		return repository.update(route);
	}
}
