package com.cargacerta.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.cargacerta.models.TruckType;

@Repository
public class TruckTypeRepository {
	
	@PersistenceContext
	private EntityManager em;

	public List<TruckType> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TruckType> criteria = cb.createQuery(TruckType.class);
        List<TruckType> list = em.createQuery(criteria).getResultList();
        
		return list;		
	}
	
	public TruckType getById(long id) {
		return em.find(TruckType.class, id);
	}
	
	public TruckType save(TruckType truckType) {
		em.persist(truckType);
		return truckType;
	}
	
	public TruckType update(TruckType truckType) {
		em.merge(truckType);
		return truckType;
	}
}
