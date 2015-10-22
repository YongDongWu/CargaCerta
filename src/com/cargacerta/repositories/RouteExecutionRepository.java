package com.cargacerta.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cargacerta.models.RouteExecution;

@Repository
public class RouteExecutionRepository {
	
	@PersistenceContext
	private EntityManager em;

	public List<RouteExecution> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RouteExecution> criteria = cb.createQuery(RouteExecution.class);
        List<RouteExecution> list = em.createQuery(criteria).getResultList();
        
		return list;		
	}
	
	public RouteExecution getById(long id) {
		return em.find(RouteExecution.class, id);
	}
	
	@Transactional
	public RouteExecution save(RouteExecution routeExecution) {
		em.persist(routeExecution);
		return routeExecution;
	}
	
	@Transactional
	public RouteExecution update(RouteExecution routeExecution) {
		em.merge(routeExecution);
		return routeExecution;
	}
}
