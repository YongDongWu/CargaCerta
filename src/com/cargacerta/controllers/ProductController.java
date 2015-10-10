package com.cargacerta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargacerta.models.Product;
import com.cargacerta.repositories.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Product> getAll() {
		List<Product> list = repository.getAll();
		
		return list;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Product getById(@PathVariable long id) {
		return repository.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Product create(@RequestBody Product product) {
		return repository.save(product);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Product update(@PathVariable long id, @RequestBody Product product) {
		product.setId(id);

		return repository.update(product);
	}
}
