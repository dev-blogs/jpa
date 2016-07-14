package com.devblogs.service;

import java.util.List;
import com.devblogs.model.Warehouse;

public interface WarehouseService {
	public List<Warehouse> findAll();
	public Warehouse findById(Long id);
	public Warehouse save(Warehouse warehouse);
	public void delete(Warehouse warehouse);
	public Warehouse findAuditByRevision(Long id, int revision);
}