package com.cargacerta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargacerta.models.RouteExecution;
import com.cargacerta.repositories.RouteExecutionRepository;

@Controller
@RequestMapping("/routeExecution")
public class RouteExecutionController {
	
	@Autowired
	private RouteExecutionRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<RouteExecution> getAll() {
		List<RouteExecution> list = repository.getAll();
		
		return list;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody RouteExecution getById(@PathVariable long id) {
		return repository.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody RouteExecution create(@RequestBody RouteExecution routeExecution) {
		return repository.save(routeExecution);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody RouteExecution update(@PathVariable long id, @RequestBody RouteExecution routeExecution) {
		routeExecution.setId(id);

		return repository.update(routeExecution);
	}
}
