package com.devblogs.service;

import java.util.List;
import com.devblogs.model.Provider;

public interface ProviderService {
	public List<Provider> findAll();
	public Provider findById(Long id);
	public Provider save(Provider provider);
	public void delete(Provider provider);
}