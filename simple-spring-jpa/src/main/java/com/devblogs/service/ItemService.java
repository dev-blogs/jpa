package com.devblogs.service;

import java.sql.SQLException;
import java.util.List;
import com.devblogs.model.Item;

public interface ItemService {
	public List<Item> findAll() throws SQLException, Exception;
	public Item findById(Long id) throws SQLException, Exception;
	public Item save(Item item) throws SQLException, Exception;
	public void delete(Item item) throws SQLException, Exception;
}