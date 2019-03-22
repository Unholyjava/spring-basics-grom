package com;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	
	public static void main(String[] args) throws Exception {
		(new Demo()).run();
	}
	
	public void run() throws Exception {
					
		Storage storage1 = new Storage(1, new String[]{"txt", "jpg"}, "Ukraine", 700);
		Storage storage2 = new Storage(2, new String[]{"txt", "tif"}, "Ukraine", 700);
//		Storage storage3 = new Storage(3, null, new String[]{"txt", "jpg", "tif"}, "Ukraine", 300);
		Storage storage4 = new Storage(4, new String[]{"txt", "jpg", "tif"}, "Ukraine", 500);
		Storage storage5 = new Storage(5, new String[]{"txt", "jpg", "tif"}, "Ukraine", 1000);

		storageDao.save(storage1);
		storageDao.save(storage2);
//		storageDao.save(storage3);
		storageDao.save(storage4);
		storageDao.save(storage5);
		File file1 = new File(11, "file1", "txt", 100, null);
		File file2 = new File(12, "file2", "jpg", 500, null);
		File file3 = new File(13, "file3", "jpg", 300, storage5);
		File file4 = new File(14, "file3", "jpg", 300, storage4);
		File file5 = new File(15, "file3", "jpg", 300, storage5);
		File file6 = new File(16, "file123456789", "tif", 300, null);

		fileDao.save(file1);
		fileDao.save(file2);
		fileDao.save(file3);
		fileDao.save(file4);
		fileDao.save(file5);
		
		controller.put(storage1, file1);	//ok put
		controller.put(storage2, file2);	//error put ("File's format not equal Storage's format")
		controller.put(storage4, file4);	//error put ("File's ID is used in Storage")
		controller.put(storage4, file5);	//error put ("File too big, Storage is full")
				
		controller.delete(storage4, file4); //ok delete
		controller.delete(storage1, file2); //error delete ("not delete File..")
		controller.delete(storage4, file4); //error delete ("not valid input data")
		
		controller.transferAll(storage5, storage2); //error transferALL ("File's format not equal Storage's format")
		controller.transferAll(storage5, storage4); //error transferALL ("File too big, Storage is full")
		controller.transferAll(storage4, storage5); //ok transferALL
		
		controller.transferFile(storage5, storage4, 11); //error transferFile ("not valid input data")
		controller.transferFile(storage4, storage5, 11); //error transferFile ("not valid input data")
		controller.transferFile(storage4, storage5, 14); //ok transferFile
		fileDao.save(file6);	//error save ("too much lenght of File's name")
		
		System.out.println("Congratulations!");
	}
	
}
