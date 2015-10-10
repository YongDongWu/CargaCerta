package com.cargacerta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargacerta.models.TruckType;
import com.cargacerta.repositories.TruckTypeRepository;

@Controller
@RequestMapping("/truckType")
public class TruckTypeController {
	
	@Autowired
	private TruckTypeRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<TruckType> getAll() {
		List<TruckType> list = repository.getAll();
		
		return list;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody TruckType getById(@PathVariable long id) {
		return repository.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody TruckType create(@RequestBody TruckType truckType) {
		return repository.save(truckType);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody TruckType update(@PathVariable long id, @RequestBody TruckType truckType) {
		truckType.setId(id);

		return repository.update(truckType);
	}
}
