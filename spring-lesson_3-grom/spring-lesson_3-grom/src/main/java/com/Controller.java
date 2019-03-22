package com;

import org.springframework.beans.factory.annotation.Autowired;
import entity.File;
import entity.Storage;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired
	private FileService fileService;
	
	public void put(Storage storage, File file) throws Exception {
		fileService.putFileInStorage(storage, file);
	}
	
	public void delete(Storage storage, File file) throws Exception {
		fileService.deleteFile(storage, file);
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
		fileService.transferFile(storageFrom, storageTo, id);
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
		fileService.transferAll(storageFrom, storageTo);
	}
	
	
//	@RequestMapping(method = RequestMethod.GET, value = "/read-item", produces = "text/plain")
//	public @ResponseBody void doGet(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		try {
//			long id = Long.valueOf(req.getParameter("itemId"));
//			System.out.println(itemService.readItemService(id));
//			resp.getWriter().println(itemService.readItemService(id));
//		} catch (Exception e) {
//			System.out.println("Error in read-operation");
//			e.printStackTrace();
//			throw e;
//		}
//	}
//	
//	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-item", produces = "text/plain")
//	public @ResponseBody void doDelete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		try {
//			long id = Long.valueOf(req.getParameter("itemId"));
//			itemService.deleteItemService(id);
//			resp.getWriter().println("Delete Item is OK");
//		} catch (Exception e) {
//			System.out.println("Error in delete-operation");
//			e.printStackTrace();
//			throw e;
//		}
//	}
//	
//	@RequestMapping(method = RequestMethod.POST, value = "/save-item", produces = "application/json")
//	public @ResponseBody void doPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        try {
//        	ObjectMapper objMapper = new ObjectMapper();
//        	Item userItem = objMapper.readValue(req.getInputStream(), Item.class);
//        	itemService.createItemService(userItem);
//        	resp.getWriter().println("Save Item is OK");
//        } catch (Exception e) {
//        	System.out.println("Error in post-operation");
//        	e.printStackTrace();
//        	throw e;
//        }
//	}
//	
//	@RequestMapping(method = RequestMethod.PUT, value = "/update-item", produces = "text/plain")
//	public @ResponseBody void doPut(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		try {
//			ObjectMapper objMapper = new ObjectMapper();
//        	Item userItem = objMapper.readValue(req.getInputStream(), Item.class);
//        	itemService.updateItemService(userItem);
//        	resp.getWriter().println("Update Item is OK");
//        } catch (Exception e) {
//        	System.out.println("Error in put-operation");
//        	e.printStackTrace();
//        	throw e;
//        }
//	}
}
