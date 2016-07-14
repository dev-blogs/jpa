import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.devblogs.dao.ProviderDao;
import com.devblogs.model.Provider;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		ProviderDao providerDao = (ProviderDao) context.getBean(ProviderDao.class);
		
		try {
			List<Provider> providers = providerDao.findAll();
			for (Provider provider: providers) {
				System.out.println(provider.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}