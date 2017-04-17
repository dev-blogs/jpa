package com.devblogs.service;

import java.util.List;
import com.devblogs.model.Provider;

public interface ServiceProvider {
	public Provider findById(Long id);
	public List<Provider> findAll();
	public Provider save(Provider provider);
	public Provider saveWithException(Provider provider);
}