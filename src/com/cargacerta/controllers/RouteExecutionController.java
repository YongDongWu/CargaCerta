package com.cargacerta.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargacerta.models.RouteExecution;
import com.cargacerta.models.RouteExecutionPeriod;
import com.cargacerta.models.RouteExecutionPoint;
import com.cargacerta.models.Truck;
import com.cargacerta.repositories.RouteExecutionRepository;
import com.cargacerta.repositories.TruckRepository;

@Controller
@RequestMapping("/routeExecution")
public class RouteExecutionController {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private RouteExecutionRepository repository;
	@Autowired
	private TruckRepository truckRepository;

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
	public @ResponseBody RouteExecution create(@RequestBody RouteExecution routeExecution) throws Exception {
		return repository.save(routeExecution);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody RouteExecution update(@PathVariable long id, @RequestBody RouteExecution routeExecution) {
		routeExecution.setId(id);

		return repository.update(routeExecution);
	}
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public @ResponseBody RouteExecution getMy() {
		String token = request.getHeader("authToken");
		Truck truck = truckRepository.getTruckByToken(token);
		
		return repository.getCurrentRouteExecution(truck);
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public @ResponseBody RouteExecutionPeriod start(@RequestBody Date executionDate) {
		String token = request.getHeader("authToken");
		Truck truck = truckRepository.getTruckByToken(token);
		RouteExecution routeExecution = repository.getCurrentRouteExecution(truck);
		
		RouteExecutionPeriod routeExecutionPeriod = new RouteExecutionPeriod();
		routeExecutionPeriod.setStartDate(executionDate);
		
		repository.addPeriodToRouteExecution(routeExecution, routeExecutionPeriod);
		
		return routeExecutionPeriod;
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public @ResponseBody RouteExecutionPeriod stop(@RequestBody Date executionDate) {
		String token = request.getHeader("authToken");
		Truck truck = truckRepository.getTruckByToken(token);
		RouteExecutionPeriod routeExecutionPeriod = repository.getCurrentRouteExecutionPeriod(truck);
		
		routeExecutionPeriod.setEndDate(executionDate);
		
		repository.update(routeExecutionPeriod);
		
		return routeExecutionPeriod;
	}
	
	@RequestMapping(value = "/updatePosition", method = RequestMethod.POST)
	public @ResponseBody RouteExecutionPoint updatePosition(@RequestBody RouteExecutionPoint routeExecutionPoint) {
		String token = request.getHeader("authToken");
		Truck truck = truckRepository.getTruckByToken(token);
		RouteExecutionPeriod routeExecutionPeriod = repository.getCurrentRouteExecutionPeriod(truck);
		
		repository.addPointToRouteExecutionPeriod(routeExecutionPeriod, routeExecutionPoint);
		
		return routeExecutionPoint;
	}
}
