package com.cargacerta.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cargacerta.models.Truck;

@Repository
public class TruckRepository {
	
	@PersistenceContext
	private EntityManager em;

	public List<Truck> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Truck> criteria = cb.createQuery(Truck.class);
        List<Truck> list = em.createQuery(criteria).getResultList();
        
		return list;		
	}
	
	public Truck getById(long id) {
		return em.find(Truck.class, id);
	}
	
	@Transactional
	public Truck save(Truck truck) {
		em.persist(truck);
		return truck;
	}
	
	@Transactional
	public Truck update(Truck truck) {
		em.merge(truck);
		return truck;
	}
	
	public Truck getTruckByToken(String token) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Truck> criteria = cb.createQuery(Truck.class);
        Root<Truck> root = criteria.from(Truck.class);
        criteria.select(root).where(cb.equal(root.get("token"), token));
        return em.createQuery(criteria).getSingleResult();
	}
}
