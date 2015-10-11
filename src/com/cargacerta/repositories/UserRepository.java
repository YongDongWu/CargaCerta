package com.cargacerta.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cargacerta.models.User;

@Repository
public class UserRepository {
	
	@PersistenceContext
	private EntityManager em;

	public List<User> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        List<User> list = em.createQuery(criteria).getResultList();
        
		return list;		
	}
	
	public User getById(long id) {
		return em.find(User.class, id);
	}
	
	@Transactional
	public User save(User user) {
		em.persist(user);
		return user;
	}
	
	@Transactional
	public User update(User user) {
		em.merge(user);
		return user;
	}
}
