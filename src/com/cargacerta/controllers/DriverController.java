package com.cargacerta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargacerta.models.Driver;
import com.cargacerta.repositories.DriverRepository;

@Controller
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	private DriverRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Driver> getAll() {
		List<Driver> list = repository.getAll();
		
		return list;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Driver getById(@PathVariable long id) {
		return repository.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Driver create(@RequestBody Driver driver) {
		return repository.save(driver);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Driver update(@PathVariable long id, @RequestBody Driver driver) {
		driver.setId(id);

		return repository.update(driver);
	}
}
