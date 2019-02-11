package com;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import item.Item;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.GET, value = "/test", produces = "text/plain")
	public @ResponseBody void doGet(HttpServletRequest req) {
		try {
			long id = Long.valueOf(req.getParameter("itemId"));
			System.out.println(readItem(id));
		} catch (Exception e) {
			System.out.println("Error in read-operation");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/test", produces = "text/plain")
	public @ResponseBody void doDelete(HttpServletRequest req) {
		try {
			long id = Long.valueOf(req.getParameter("itemId"));
			deleteItem(id);
		} catch (Exception e) {
			System.out.println("Error in delete-operation");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/test", produces = "application/json")
	public @ResponseBody void doPost(HttpServletRequest req) {
        try {
        	String stringJson = req.getReader().lines().collect(Collectors.joining());
        	JSONObject userJson = new JSONObject(stringJson);
        	long id = userJson.getLong("id");
        	String name = userJson.getString("name");
        	String description = userJson.getString("description");
        	Item userItem = new Item();
        	userItem.setId(id);
        	userItem.setName(name);
        	userItem.setDescription(description);
        	createItem(userItem);
        } catch (Exception e) {
        	System.out.println("Error in post-operation");
        	e.printStackTrace();
        }
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/test", produces = "text/plain")
	public @ResponseBody void doPut(HttpServletRequest req) {
		try {
			String stringJson = req.getReader().lines().collect(Collectors.joining());
			JSONObject userJson = new JSONObject(stringJson);
        	long id = userJson.getLong("id");
        	String name = userJson.getString("name");
        	String description = userJson.getString("description");
        	Item userItem = new Item();
        	userItem.setId(id);
        	userItem.setName(name);
        	userItem.setDescription(description);
        	updateItem(userItem);
        } catch (Exception e) {
        	System.out.println("Error in put-operation");
        	e.printStackTrace();
        }
	}
	
	public Item createItem(Item item) throws Exception {
		return itemService.createItemService(item);
	}
	
	public Item updateItem(Item item) throws Exception {
		return itemService.updateItemService(item);
	}
	
	public @ResponseBody Item readItem(long id) throws Exception {
		return itemService.readItemService(id);
	}
	
	public Item deleteItem(long id) throws Exception {
		return itemService.deleteItemService(id);
	}
}
