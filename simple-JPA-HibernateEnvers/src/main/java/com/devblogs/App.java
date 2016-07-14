package com.devblogs;

import org.springframework.context.support.GenericXmlApplicationContext;
import com.devblogs.model.Provider;
import com.devblogs.service.ProviderService;

public class App {
	public static void main(String[] args) throws Exception {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/spring-data-app-context.xml");
		ctx.refresh();
		
		ProviderService providerService = ctx.getBean("providerService", ProviderService.class);
		
		// Добавить новый провайдер
		System.out.println("Добавить новый провайдер");
		Provider provider = new Provider();
		provider.setName("New Provider");
		providerService.save(provider);
		
		Provider p = providerService.findById(provider.getId());
		System.out.println(p);
		
		Thread.sleep(2000);
		
		// Обновить провайдер
		System.out.println("Обновить провайдер");
		provider.setName("Update provider");
		providerService.save(provider);
		p = providerService.findById(provider.getId());
		System.out.println(p);
		System.out.println();
		
		Thread.sleep(2000);
		
		// Обновить провайдер
		System.out.println("Обновить провайдер еще раз");
		provider.setName("test1");
		providerService.save(provider);
		p = providerService.findById(provider.getId());
		System.out.println(p);
		System.out.println();
		
		Thread.sleep(2000);
		
		// Обновить провайдер
		System.out.println("Обновить провайдер еще раз");
		provider.setName("test2");
		providerService.save(provider);
		p = providerService.findById(provider.getId());
		System.out.println(p);
		System.out.println();
		
		Thread.sleep(2000);
		
		// Найти запись аудита по номеру версии
		System.out.println("Найти запись аудита по номеру версии");
		Provider oldProvider = providerService.findAuditByRevision(6l, 1);
		System.out.println();
		System.out.println("Старый провайдер с id 1 и rev 1: " + oldProvider);
		System.out.println();
		oldProvider = providerService.findAuditByRevision(6l, 2);
		System.out.println();
		System.out.println("Старый провайдер с id 1 и rev 2: " + oldProvider);
		System.out.println();
	}
}