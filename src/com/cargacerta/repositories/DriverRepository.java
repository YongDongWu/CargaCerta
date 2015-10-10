package com.cargacerta.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.cargacerta.models.Driver;

@Repository
public class DriverRepository {
	
	@PersistenceContext
	private EntityManager em;

	public List<Driver> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Driver> criteria = cb.createQuery(Driver.class);
        List<Driver> list = em.createQuery(criteria).getResultList();
        
		return list;		
	}
	
	public Driver getById(long id) {
		return em.find(Driver.class, id);
	}
	
	public Driver save(Driver driver) {
		em.persist(driver);
		return driver;
	}
	
	public Driver update(Driver driver) {
		em.merge(driver);
		return driver;
	}
}
