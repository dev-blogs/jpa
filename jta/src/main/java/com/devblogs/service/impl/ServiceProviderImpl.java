package com.devblogs.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.devblogs.model.Provider;
import com.devblogs.model.Taxpayer;
import com.devblogs.service.ServiceProvider;

@Component
@Transactional
public class ServiceProviderImpl implements ServiceProvider {
	@PersistenceContext(unitName = "emfOur")
	private EntityManager emOur;
	@PersistenceContext(unitName = "emfTax")
	private EntityManager emTax;
	
	public void clear() {
		Query ourQuery = emOur.createQuery("DELETE FROM Provider");
		Query taxQuery = emTax.createQuery("DELETE FROM Taxpayer");
		ourQuery.executeUpdate();
		taxQuery.executeUpdate();
	}

	@Transactional(readOnly = true)
	public Provider findById(Long id) {
		TypedQuery<Provider> query = emOur.createNamedQuery("Provider.findById", Provider.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Transactional(readOnly = true)
	public List<Provider> findAll() {
		return emOur.createNamedQuery("Provider.findAll", Provider.class).getResultList();
	}

	public Provider save(Provider provider) {
		Taxpayer taxpayer = new Taxpayer();
		taxpayer.setName(provider.getName());
		taxpayer.setIsDebtor(provider.getIsDebtor());

		if (provider.getId() == null) {
			emOur.persist(provider);
			emTax.persist(taxpayer);
		} else {
			emOur.merge(provider);
			emTax.merge(taxpayer);
		}
		return provider;
	}

	public Provider saveWithException(Provider provider) {
		Taxpayer taxpayer = new Taxpayer();
		taxpayer.setName(provider.getName());
		taxpayer.setIsDebtor(provider.getIsDebtor());

		if (provider.getId() == null) {
			emOur.persist(provider);
			emTax.persist(taxpayer);
			throw new JpaSystemException(new PersistenceException());
		} else {
			emOur.merge(provider);
			emTax.merge(taxpayer);
		}
		return provider;
	}
}