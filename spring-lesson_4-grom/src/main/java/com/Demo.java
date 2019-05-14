package com;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.File;
import entity.Storage;

@Controller
public class Demo {
	@Autowired
	private com.Controller controller;
	
	@Autowired
	private FileDAO fileDao;
	
	@Autowired
	private StorageDAO storageDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "/start-demo", produces = "text/plain")
	public @ResponseBody void doGet(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		resp.getWriter().println("Begin");
		
		Storage storage1 = new Storage(1, "txt, jpg", "Ukraine", 700);
		Storage storage2 = new Storage(2, "txt, tif", "Ukraine", 700);
		Storage storage4 = new Storage(4, "txt, jpg, tif", "Ukraine", 500);
		Storage storage5 = new Storage(5, "txt, jpg, tif", "Ukraine", 1000);
		
		storageDao.save(storage1);
		storageDao.save(storage2);
		storageDao.save(storage4);
		storageDao.save(storage5);
		File file1 = new File(11, "file1", "txt", 100, null);
		File file2 = new File(12, "file2", "jpg", 500, null);
		File file3 = new File(13, "file3", "jpg", 300, storage5);
		File file4 = new File(14, "file4", "jpg", 300, storage4);
		File file5 = new File(15, "file5", "jpg", 300, storage5);
		File file6 = new File(16, "file123456789", "tif", 300, null);
		File file7 = new File(17, "file7", "jpg", 300, storage4);
		
		fileDao.save(file1);
		fileDao.save(file2);
		fileDao.save(file3);
		fileDao.save(file4);
		fileDao.save(file5);
		fileDao.save(file7);
		
		controller.put(storage1, file1);	//ok put
		try {
			controller.put(storage2, file2);	//error put ("File's format not equal Storage's format")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		try {
			controller.put(storage4, file4);	//error put ("File's ID is used in Storage")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		try {
			controller.put(storage4, file5);	//error put ("File too big, Storage is full")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		controller.delete(storage4, file4); //ok delete
		
		try {
			controller.delete(storage1, file2); //error delete ("not delete File..")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		try {
			controller.delete(storage4, file4); //error delete ("not valid input data")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		try {
			controller.transferAll(storage5, storage2); //error transferALL ("File's format not equal Storage's format")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		try {
			controller.transferAll(storage5, storage4); //error transferALL ("File too big, Storage is full")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		controller.transferAll(storage4, storage5); //ok transferALL
		
		controller.transferFile(storage5, storage1, 13); //ok transferFile
		
		try {
			controller.transferFile(storage5, storage2, 15); //error transferFile ("not equal Storage's format")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		try {
			fileDao.save(file6);	//error save ("too much length of File's name")
		} catch (Exception e){
			resp.getWriter().println(e.getMessage());
		}
		
		System.out.println("Congratulations!");
		resp.getWriter().println("All OK");
		
	}
}
