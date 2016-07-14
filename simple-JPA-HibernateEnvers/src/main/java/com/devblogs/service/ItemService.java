package com.devblogs.service;

import java.util.List;
import com.devblogs.model.Item;

public interface ItemService {
	public List<Item> findAll();
	public Item findById(Long id);
	public Item save(Item item);
	public void delete(Item item);
	public Item findAuditByRevision(Long id, int revision);
}