package com;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import item.Item;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.GET, value = "/read-item", produces = "text/plain")
	public @ResponseBody void doGet(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			long id = Long.valueOf(req.getParameter("itemId"));
			System.out.println(itemService.readItemService(id));
			resp.getWriter().println(itemService.readItemService(id));
		} catch (Exception e) {
			System.out.println("Error in read-operation");
			e.printStackTrace();
			throw e;
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-item", produces = "text/plain")
	public @ResponseBody void doDelete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			long id = Long.valueOf(req.getParameter("itemId"));
			itemService.deleteItemService(id);
			resp.getWriter().println("Delete Item is OK");
		} catch (Exception e) {
			System.out.println("Error in delete-operation");
			e.printStackTrace();
			throw e;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save-item", produces = "application/json")
	public @ResponseBody void doPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
        	ObjectMapper objMapper = new ObjectMapper();
        	Item userItem = objMapper.readValue(req.getInputStream(), Item.class);
        	itemService.createItemService(userItem);
        	resp.getWriter().println("Save Item is OK");
        } catch (Exception e) {
        	System.out.println("Error in post-operation");
        	e.printStackTrace();
        	throw e;
        }
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update-item", produces = "text/plain")
	public @ResponseBody void doPut(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			ObjectMapper objMapper = new ObjectMapper();
        	Item userItem = objMapper.readValue(req.getInputStream(), Item.class);
        	itemService.updateItemService(userItem);
        	resp.getWriter().println("Update Item is OK");
        } catch (Exception e) {
        	System.out.println("Error in put-operation");
        	e.printStackTrace();
        	throw e;
        }
	}
}
