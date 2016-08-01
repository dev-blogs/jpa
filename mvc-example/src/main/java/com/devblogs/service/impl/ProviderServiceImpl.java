package com.devblogs.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devblogs.model.Provider;
import com.devblogs.service.ProviderService;

@Service("providerService")
@Transactional
public class ProviderServiceImpl implements ProviderService {
	private Log log = LogFactory.getLog(ProviderServiceImpl.class);
	
	@PersistenceContext	
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Provider> findAll() {
		return em.createNamedQuery("Provider.findAll", Provider.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Provider findById(Long id) {
		TypedQuery<Provider> query = em.createNamedQuery("Provider.findById", Provider.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Provider save(Provider provider) {
		if (provider.getId() == null) {
			log.info("Inserting new provider");
			em.persist(provider);
		} else {
			em.merge(provider);
			log.info("Updating existing provider");
		}
		log.info("Provider saved with id: " + provider.getId());
		return provider;
	}

	@Transactional(readOnly = false)
	public void delete(Provider provider) {
		Provider mergedProvider = em.merge(provider);
		em.remove(mergedProvider);
		log.info("Provider with id: " + provider.getId() + " deleted successfully");
	}
}