package com;

import org.springframework.beans.factory.annotation.Autowired;
import entity.File;
import entity.Storage;

//@org.springframework.stereotype.Controller
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
}
