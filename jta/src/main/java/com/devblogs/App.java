package com.devblogs;

import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.devblogs.model.Provider;
import com.devblogs.service.ServiceProvider;

public class App {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:spring/context.xml");
		context.refresh();
		
		ServiceProvider serviceProvider = context.getBean(ServiceProvider.class);
		
		Provider provider = new Provider();
		provider.setName("petya");
		provider.setIsDebtor(false);
		serviceProvider.save(provider);
		
		List<Provider> providers = serviceProvider.findAll();
		
		for (Provider p : providers) {
			System.out.println(p);
		}
	}
}