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
import com.devblogs.model.Warehouse;
import com.devblogs.service.WarehouseService;

@Service("warehouseService")
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
	private Log log = LogFactory.getLog(WarehouseServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Warehouse> findAll() {
		return em.createQuery("from Warehouse w", Warehouse.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Warehouse findById(Long id) {
		TypedQuery<Warehouse> typedQuery = em.createQuery("from Warehouse w where w.id = :id", Warehouse.class);
		typedQuery.setParameter("id", id);
		return typedQuery.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Warehouse save(Warehouse warehouse) {
		if (warehouse.getId() == null) {
			log.info("Inserting new warehouse");
			em.persist(warehouse);
		} else {
			em.merge(warehouse);
			log.info("Updating existing warehouse");
		}
		log.info("Warehouse saved with id: " + warehouse.getId());
		return warehouse;
	}

	@Transactional(readOnly = false)
	public void delete(Warehouse warehouse) {
		Warehouse mergedWarehouse = em.merge(warehouse);
		em.remove(mergedWarehouse);
		log.info("Warehouse with id: " + warehouse.getId() + " deleted successfully");
	}

	@Transactional(readOnly = true)
	public Warehouse findAuditByRevision(Long id, int revision) {
		AuditReader auditReader = AuditReaderFactory.get(em);
		return auditReader.find(Warehouse.class, id, revision);
	}
}