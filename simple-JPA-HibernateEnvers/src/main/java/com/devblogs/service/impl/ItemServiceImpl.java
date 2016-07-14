package com.devblogs.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
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
	public List<Item> findAll() {
		return em.createQuery("from Item i", Item.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Item findById(Long id) {
		TypedQuery<Item> typedQuery = em.createQuery("from Item i where i.id = :id", Item.class);
		typedQuery.setParameter("id", id);
		return typedQuery.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Item save(Item item) {
		if (item.getId() == null) {
			log.info("Inserting new item");
			em.persist(item);
		} else {
			em.merge(item);
			log.info("Updating existing item");
		}
		log.info("Item saved with id: " + item.getId());
		return item;
	}

	@Transactional(readOnly = false)
	public void delete(Item item) {
		Item mergedItem = em.merge(item);
		em.remove(mergedItem);
		log.info("Item with id: " + item.getId() + " deleted successfully");
	}

	@Transactional(readOnly = true)
	public Item findAuditByRevision(Long id, int revision) {
		AuditReader auditReader = AuditReaderFactory.get(em);
		return auditReader.find(Item.class, id, revision);
	}
}