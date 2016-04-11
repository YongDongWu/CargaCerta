package com.cargacerta.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cargacerta.models.RouteExecution;
import com.cargacerta.models.RouteExecutionPeriod;
import com.cargacerta.models.RouteExecutionPoint;
import com.cargacerta.models.Truck;

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
	public RouteExecution save(RouteExecution routeExecution) throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		 
		CriteriaQuery<RouteExecution> q = cb.createQuery(RouteExecution.class);
		Root<RouteExecution> c = q.from(RouteExecution.class);
		q.select(c).where(
			cb.and(
				cb.or(
					cb.equal(c.get("driver"), routeExecution.getDriver()),
					cb.equal(c.get("truck"), routeExecution.getTruck())
				),
				cb.or(
					cb.and(
						cb.lessThanOrEqualTo(c.<Date>get("startDate"), routeExecution.getStartDate()),
						cb.greaterThan(c.<Date>get("deliveryDate"), routeExecution.getStartDate())
					),
					cb.and(
						cb.lessThanOrEqualTo(c.<Date>get("startDate"), routeExecution.getDeliveryDate()),
						cb.greaterThan(c.<Date>get("deliveryDate"), routeExecution.getDeliveryDate())
					)
				)
			)
		);
		TypedQuery<RouteExecution> query = em.createQuery(q);
		List<RouteExecution> results = query.getResultList();
		
		if (!results.isEmpty()) {
			throw new Exception("Conflict");
		}
		
		em.persist(routeExecution);
		return routeExecution;
	}
	
	@Transactional
	public RouteExecution update(RouteExecution routeExecution) {
		em.merge(routeExecution);
		return routeExecution;
	}
	
	@Transactional
	public RouteExecutionPeriod update(RouteExecutionPeriod routeExecutionPeriod) {
		em.merge(routeExecutionPeriod);
		return routeExecutionPeriod;
	}
	
	public RouteExecution getRouteExecutionByDate(Truck truck, Date date) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RouteExecution> criteria = cb.createQuery(RouteExecution.class);
        Root<RouteExecution> root = criteria.from(RouteExecution.class);
        criteria.select(root).where(
    		cb.and(
    			cb.equal(root.get("truck"), truck),
    			cb.and(cb.lessThanOrEqualTo(root.<Date>get("startDate"), date), cb.greaterThan(root.<Date>get("deliveryDate"), date))
    		)
    	);
        return em.createQuery(criteria).getSingleResult();
	}
	
	public RouteExecution getCurrentRouteExecution(Truck truck) {
		Date currentDate = new Date();
		
		return getRouteExecutionByDate(truck, currentDate);
	}
	
	public RouteExecutionPeriod getCurrentRouteExecutionPeriod(Truck truck) {
		RouteExecution routeExecution = getCurrentRouteExecution(truck);
		
		List<RouteExecutionPeriod> routeExecutionPeriods = routeExecution.getRouteExecutionPeriods();
		
		return routeExecutionPeriods.get(routeExecutionPeriods.size() - 1);
	}
	
	public RouteExecutionPeriod addPeriodToRouteExecution(RouteExecution routeExecution, RouteExecutionPeriod routeExecutionPeriod) {
		routeExecution.getRouteExecutionPeriods().add(routeExecutionPeriod);
		routeExecutionPeriod.setRouteExecution(routeExecution);
		
		em.persist(routeExecutionPeriod);
		em.merge(routeExecution);
		
		return routeExecutionPeriod;
	}
	
	public RouteExecutionPoint addPointToRouteExecutionPeriod(RouteExecutionPeriod routeExecutionPeriod, RouteExecutionPoint routeExecutionPoint) {
		routeExecutionPeriod.getRouteExecutionPoints().add(routeExecutionPoint);
		routeExecutionPoint.setRouteExecutionPeriod(routeExecutionPeriod);
		
		em.persist(routeExecutionPoint);
		em.merge(routeExecutionPeriod);
		
		return routeExecutionPoint;
	}
}
