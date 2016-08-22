package com.devblogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.devblogs.model.Item;
import com.devblogs.service.ItemService;

@ContextConfiguration("classpath:spring/context.xml")
public class TestItem extends AbstractJUnit4SpringContextTests {
	@Autowired
	private ItemService itemService;
	
	@Test
	public void testDelete() {
		try {
			// Достаём существующий провайдер из базы 
			Item item = itemService.findById(1l);
			
			// Сохраняем его в базе. Вернется объект с просеченым айдишником
			//Item savedItem = itemService.save(item);
			
			// Достаем объект из базы по айдишнику взятым из сохранненого объекта
			//Item itemFromDb = itemService.findById(savedItem.getId());
			
			// Удаляем объект из базы
			itemService.delete(item);
			
			List<Item> items = itemService.findAll();
			
			// Проверяем удаленный объект на отсутствие в базе данных.
			// Должен быть сгенерирован эксцепшен NoResultException
			Item itemAfterDeleting = itemService.findById(item.getId());
			
			assertNull(itemAfterDeleting);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}