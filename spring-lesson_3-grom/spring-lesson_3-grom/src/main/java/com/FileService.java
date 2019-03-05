package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import entity.File;
import entity.Storage;

@Controller
public class FileService {
	@Autowired
	private CommonDAO<File> commonDao;
	

	@Autowired
	private DBServiceOracle dbService;
	
	@Autowired
	private ValidatorInputData validatorInputData;
	
	public FileService(CommonDAO<File> commonDao) {
		this.commonDao = commonDao;
	}

	public void putFileInStorage(Storage storage, File file) throws Exception {
		try {
			validatorInputData.isStorageAndFileValid(storage, file, dbService.findFilesByStorageID(storage.getId()));
			file.setStorage(storage);
			commonDao.update(file);
		} catch (Exception e) {
			throw new Exception ("not put File with ID = " + file.getId() 
				+ " in to Storage with ID = " + storage.getId());
		}
	}
	
	public void deleteFile(Storage storage, File file) throws Exception {
		try {
			validatorInputData.isFileInStorage(dbService.findFilesByStorageID(storage.getId()), file, storage.getId());
			commonDao.delete(file.getId());
		} catch (Exception e) {
			throw new Exception ("not delete File with ID = " + file.getId() 
				+ " from Storage with ID = " + storage.getId());
		} 
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
		try {
			File file = commonDao.findById(id);
			validatorInputData.isFileInStorage(dbService.findFilesByStorageID(storageFrom.getId()), file, storageFrom.getId()); 
			validatorInputData.isStorageAndFileValid(storageTo, file, dbService.findFilesByStorageID(storageTo.getId()));
			putFileInStorage(storageTo, file);
		} catch (Exception e) {
			throw new Exception ("not put File with ID = " + id 
				+ " to Storage with ID = " + storageTo.getId()
				+ " from Storage with ID = " + storageFrom.getId());
		}
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
		try {
			for (File file : dbService.findFilesByStorageID(storageFrom.getId())) {
				validatorInputData.isStorageAndFileValid(storageTo, file, dbService.findFilesByStorageID(storageTo.getId()));
				putFileInStorage(storageTo, file);
			}
		} catch (Exception e) {
			throw new Exception ("not transfer all files from Storage with ID = " + storageFrom.getId() 
				+ " to Storage with ID = " + storageTo.getId());
		}
	}
	
	public CommonDAO<File> getCommonDao() {
		return commonDao;
	}
}
