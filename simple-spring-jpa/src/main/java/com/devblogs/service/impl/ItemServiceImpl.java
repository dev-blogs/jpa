package com.devblogs.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devblogs.model.Item;
import com.devblogs.service.ItemService;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {
	private Log log = LogFactory.getLog(ItemServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Item> findAll() throws SQLException, Exception {
		return em.createQuery("from Item", Item.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Item findById(Long id) throws SQLException, Exception {
		TypedQuery<Item> query = em.createQuery("from Item i where i.id = :id", Item.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	@Transactional(readOnly = false)
	public Item save(Item item) throws SQLException, Exception {
		if (item.getId() == null) {
			log.info("Item new provider");
			em.persist(item);
		} else {
			em.merge(item);
			log.info("Updating existing item");
		}
		log.info("Provider saved with id: " + item.getId());
		return item;
	}

	@Transactional(readOnly = false)
	public void delete(Item item) throws SQLException, Exception {
		em.remove(em.contains(item) ? item : em.merge(item));
	}
}