package com.devblogs.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.devblogs.model.Provider;
import com.devblogs.service.ServiceProvider;

@ContextConfiguration("classpath:spring/context.xml")
public class JtaTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private ServiceProvider serviceProvider;
	
	@Before
	public void clear() {
		serviceProvider.clear();
	}
	
	@Test
	public void testSave() {
		Provider provider = new Provider();
		provider.setName("provider 1");
		provider.setIsDebtor(false);
		
		Provider savedProviders = serviceProvider.save(provider);
		Provider providerFromDb = serviceProvider.findById(savedProviders.getId());
		
		assertEquals(savedProviders, providerFromDb);
	}
	
	@Test(expected=JpaSystemException.class)
	public void testFaledSave() {
		Provider provider = new Provider();
		provider.setName("provider 1");
		provider.setIsDebtor(false);
		
		Provider savedProviders = serviceProvider.saveWithException(provider);
		Provider providerFromDb = serviceProvider.findById(savedProviders.getId());
		
		assertEquals(savedProviders, providerFromDb);
	}
}