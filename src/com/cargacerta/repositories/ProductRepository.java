package com.cargacerta.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cargacerta.models.Product;

@Repository
public class ProductRepository {
	
	@PersistenceContext
	private EntityManager em;

	public List<Product> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        List<Product> list = em.createQuery(criteria).getResultList();
        
		return list;		
	}
	
	public Product getById(long id) {
		return em.find(Product.class, id);
	}
	
	@Transactional
	public Product save(Product product) {
		em.persist(product);
		return product;
	}
	
	@Transactional
	public Product update(Product product) {
		em.merge(product);
		return product;
	}
}
