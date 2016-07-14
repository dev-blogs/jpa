package com.devblogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.devblogs.model.Provider;
import com.devblogs.service.ProviderService;
import com.devblogs.service.jpa.ProviderServiceImpl;

public class TestProvider {
	private ProviderService providerService;
	
	@Before
	public void init() {
		providerService = new ProviderServiceImpl();
	}
	
	@Test
	public void testFindAll() {
		List<Provider> providers = providerService.findAll();
		Provider firstElement = providers.get(0);
		assertNotNull(firstElement);
	}
	
	@Test
	public void testFindById() {
		Provider provider = providerService.findById(1l);
		assertNotNull(provider);
	}
	
	@Test
	public void testSave() {
		Provider provider = new Provider();
		provider.setName("testName");
		
		Provider savedProvider = providerService.save(provider);
		
		Provider providerFromDb = providerService.findById(savedProvider.getId());
		
		assertEquals(savedProvider.getName(), providerFromDb.getName());
		providerService.delete(providerFromDb);
	}
	
	@Test(expected = javax.persistence.NoResultException.class)
	public void testDelete() {
		Provider provider = new Provider();
		provider.setName("testName");
		Provider savedProvider = providerService.save(provider);
		
		Provider providerFromDb = providerService.findById(savedProvider.getId());
		providerService.delete(providerFromDb);
		
		providerService.findById(providerFromDb.getId());
	}
}