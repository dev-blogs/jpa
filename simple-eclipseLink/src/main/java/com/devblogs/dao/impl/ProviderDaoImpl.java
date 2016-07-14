package com.devblogs.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.devblogs.dao.ProviderDao;
import com.devblogs.model.Provider;

@Component
public class ProviderDaoImpl implements ProviderDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public List<Provider> findAll() throws SQLException, Exception {
		return sessionFactory.getCurrentSession().createQuery("from Provider p").list();
	}
}