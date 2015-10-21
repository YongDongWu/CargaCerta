package com.cargacerta.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cargacerta.models.Route;
import com.cargacerta.models.RoutePoint;

@Repository
public class RouteRepository {
	
	@PersistenceContext
	private EntityManager em;

	public List<Route> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Route> criteria = cb.createQuery(Route.class);
        List<Route> list = em.createQuery(criteria).getResultList();
        
		return list;		
	}
	
	public Route getById(long id) {
		return em.find(Route.class, id);
	}
	
	@Transactional
	public Route save(Route route) {
		for (RoutePoint point : route.getRoutePoints()) {
			point.setRoute(route);
		}
		
		em.persist(route);
		
		return route;
	}
	
	@Transactional
	public Route update(Route route) {
		em.merge(route);
		return route;
	}
}
