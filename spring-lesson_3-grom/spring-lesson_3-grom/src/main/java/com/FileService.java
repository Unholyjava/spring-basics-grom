package com;

import org.springframework.beans.factory.annotation.Autowired;
import entity.File;
import entity.Storage;

public class FileService {
	@Autowired
	private FileDAO fileDao;
	
	@Autowired
	private StorageDAO storageDao;
	
	private ValidatorInputData validatorInputData = new ValidatorInputData();
	
	public void putFileInStorage(Storage storage, File file) throws Exception {
		try {
			validatorInputData.isStorageAndFileValid(storage, file, storageDao.findFilesByStorageID(storage.getId()));
			file.setStorage(storage);
			fileDao.update(file);
		} catch (Exception e) {
			throw new Exception ("not put File with ID = " + file.getId() 
				+ " in to Storage with ID = " + storage.getId());
		}
	}
	
	public void deleteFile(Storage storage, File file) throws Exception {
		try {
			validatorInputData.isFileInStorage(storageDao.findFilesByStorageID(storage.getId()), file, storage.getId());
			fileDao.delete(file.getId());
		} catch (Exception e) {
			throw new Exception ("not delete File with ID = " + file.getId() 
				+ " from Storage with ID = " + storage.getId());
		} 
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
		try {
			File file = fileDao.findById(id);
			validatorInputData.isFileInStorage(storageDao.findFilesByStorageID(storageFrom.getId()), file, storageFrom.getId()); 
			validatorInputData.isStorageAndFileValid(storageTo, file, storageDao.findFilesByStorageID(storageTo.getId()));
			putFileInStorage(storageTo, file);
		} catch (Exception e) {
			throw new Exception ("not put File with ID = " + id 
				+ " to Storage with ID = " + storageTo.getId()
				+ " from Storage with ID = " + storageFrom.getId());
		}
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
		try {
			for (File file : storageDao.findFilesByStorageID(storageFrom.getId())) {
				validatorInputData.isStorageAndFileValid(storageTo, file, storageDao.findFilesByStorageID(storageTo.getId()));
				putFileInStorage(storageTo, file);
			}
		} catch (Exception e) {
			throw new Exception ("not transfer all files from Storage with ID = " + storageFrom.getId() 
				+ " to Storage with ID = " + storageTo.getId());
		}
	}
	
	public CommonDAO<File> getCommonDao() {
		return fileDao;
	}
}
