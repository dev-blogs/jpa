package com.devblogs.service.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.devblogs.model.Provider;
import com.devblogs.service.ProviderService;

public class ProviderServiceImpl implements ProviderService {
	private Log log = LogFactory.getLog(ProviderServiceImpl.class);
	private EntityManager em = Persistence.createEntityManagerFactory("warehouse").createEntityManager();

	public List<Provider> findAll() {
		em.getTransaction().begin();
		List<Provider> providers = em.createNamedQuery("Provider.findAll", Provider.class).getResultList();
		em.getTransaction().commit();
		return providers;
	}	

	public Provider findById(Long id) {
		em.getTransaction().begin();
		TypedQuery<Provider> query = em.createNamedQuery("Provider.findById", Provider.class);
		query.setParameter("id", id);
		em.getTransaction().commit();
		return query.getSingleResult();
	}

	public Provider save(Provider provider) {
		em.getTransaction().begin();
		if (provider.getId() == null) {
			log.info("Inserting new provider");
			em.persist(provider);
		} else {
			em.merge(provider);
			log.info("Updating existing provider");
		}
		em.getTransaction().commit();
		log.info("Provider saved with id: " + provider.getId());
		return provider;
	}

	public void delete(Provider provider) {
		em.getTransaction().begin();
		Provider mergedProvider = em.merge(provider);
		em.remove(mergedProvider);
		em.getTransaction().commit();
		log.info("Provider with id: " + provider.getId() + " deleted successfully");
	}
}