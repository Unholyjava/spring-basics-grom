package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import item.Item;


@Controller
public class ItemService {
	
	@Autowired
	private ItemDAO itemDao;

	public ItemService(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}
	
	public Item createItemService(Item item) throws Exception {
		return itemDao.save(item);
	}
	
	public Item updateItemService(Item item) throws Exception {
		return itemDao.update(item);
	}
	
	public Item readItemService(long id) throws Exception {
		return itemDao.findById(id);
	}
	
	public Item deleteItemService(long id) throws Exception {
		return itemDao.delete(id);
	}
	
}
