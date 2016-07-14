package com.devblogs.dao;

import java.sql.SQLException;
import java.util.List;
import com.devblogs.model.Provider;

public interface ProviderDao {
	public List<Provider> findAll() throws SQLException, Exception;
}