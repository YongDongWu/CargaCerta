package com.cargacerta.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargacerta.models.RouteExecutionPoint;
import com.cargacerta.models.Truck;
import com.cargacerta.repositories.TruckRepository;

@Controller
@RequestMapping("/truck")
public class TruckController {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private TruckRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Truck> getAll() {
		List<Truck> list = repository.getAll();
		
		return list;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Truck getById(@PathVariable long id) {
		return repository.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Truck create(@RequestBody Truck truck) {
		return repository.save(truck);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Truck update(@PathVariable long id, @RequestBody Truck truck) {
		truck.setId(id);

		return repository.update(truck);
	}
	
	@RequestMapping(value = "/updateMyPosition", method = RequestMethod.PUT)
	public @ResponseBody Truck updateCurrentPosition(@RequestBody RouteExecutionPoint routeExecutionPoint) {
		String token = request.getHeader("authToken");
		Truck truck = repository.getTruckByToken(token);
		
		truck.setLatitude(routeExecutionPoint.getLatitude());
		truck.setLongitude(routeExecutionPoint.getLongitude());
		
		repository.update(truck);
		
		return truck;
	}
}
