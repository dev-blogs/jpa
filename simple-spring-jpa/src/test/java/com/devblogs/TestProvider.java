package com.devblogs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.devblogs.model.Provider;
import com.devblogs.service.ProviderService;

@ContextConfiguration("classpath:spring/context.xml")
public class TestProvider extends AbstractJUnit4SpringContextTests {
	@Autowired
	private ProviderService providerService;
	
	@Test
	public void testFindAll() {
		// Вытаскиваем все объекты из базы
		List<Provider> providers = providerService.findAll();
		// Вытаскиваем первый элемент из результирующего набора
		Provider firstElement = providers.get(0);
		// Проверяем объект на существование
		assertNotNull(firstElement);
	}
	
	@Test
	public void testFindById() {
		// Вытаскиваем провайдер по id 1l
		Provider provider = providerService.findById(1l);
		// Проверяем вытащенный объект на наличие
		assertNotNull(provider);
	}
	
	@Test
	public void testSave() {
		// Создаем новый провайдер
		Provider provider = new Provider();
		provider.setName("testName");
		
		// Сохраняем его в базе. Вернется объект с просеченым айдишником
		Provider savedProvider = providerService.save(provider);
		
		// Вытаскиваем его из базы
		Provider providerFromDb = providerService.findById(savedProvider.getId());
		
		// Сравниваем сохраненный в базе объект с вытащенным из базы объектом
		assertEquals(savedProvider.getName(), providerFromDb.getName());
		providerService.delete(providerFromDb);
	}
	
	@Test(expected = javax.persistence.NoResultException.class)
	public void testDelete() {
		// Создаем новый провайдер
		Provider provider = new Provider();
		provider.setName("testName");
		
		// Сохраняем его в базе. Вернется объект с просеченым айдишником
		Provider savedProvider = providerService.save(provider);
		
		// Достаем объект из базы по айдишнику взятым из сохранненого объекта
		Provider providerFromDb = providerService.findById(savedProvider.getId());
		
		// Удаляем объект из базы
		providerService.delete(providerFromDb);
		
		// Проверяем удаленный объект на отсутствие в базе данных.
		// Должен быть сгенерирован эксцепшен NoResultException
		providerService.findById(providerFromDb.getId());
	}
}