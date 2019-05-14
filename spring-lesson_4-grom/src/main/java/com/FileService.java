package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import entity.File;
import entity.Storage;

public class FileService {
	@Autowired
	private FileDAO fileDao;
	
	@Autowired
	private StorageDAO storageDao;
	
	public void putFileInStorage(Storage storage, File file) throws Exception {
		try {
			ValidatorInputData.isStorageAndFileValid(storage, file, storageDao.findFilesByStorageID(storage.getId()));
			file.setStorage(storage);
			fileDao.update(file);
		} catch (Exception e) {
			throw new Exception (e.getMessage() + "not put File with ID = " + file.getId() 
				+ " in to Storage with ID = " + storage.getId());
		}
	}
	
	public void deleteFile(Storage storage, File file) throws Exception {
		try {
			ValidatorInputData.isFileInStorage(storageDao.findFilesByStorageID(storage.getId()), file, storage.getId());
			//fileDao.delete(file.getId());
			file.setStorage(null);
			fileDao.update(file);
		} catch (Exception e) {
			throw new Exception (e.getMessage() + "not delete File with ID = " + file.getId() 
				+ " from Storage with ID = " + storage.getId());
		} 
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
		try {
			File file = fileDao.findById(id);
			ValidatorInputData.isFileInStorage(storageDao.findFilesByStorageID(storageFrom.getId()), file, storageFrom.getId()); 
			ValidatorInputData.isStorageAndFileValid(storageTo, file, storageDao.findFilesByStorageID(storageTo.getId()));
			putFileInStorage(storageTo, file);
		} catch (Exception e) {
			throw new Exception (e.getMessage() + "not put File with ID = " + id 
				+ " to Storage with ID = " + storageTo.getId()
				+ " from Storage with ID = " + storageFrom.getId());
		}
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
		try {
			List<File> fileList = storageDao.findFilesByStorageID(storageFrom.getId());
			for (File file : fileList) {
				ValidatorInputData.isStorageAndFileValid(storageTo, file, fileList);
				//putFileInStorage(storageTo, file);
			}
			fileDao.updateFileArrayByStorage(storageTo, fileList);
		} catch (Exception e) {
			throw new Exception (e.getMessage() + "not transfer all files from Storage with ID = " + storageFrom.getId() 
				+ " to Storage with ID = " + storageTo.getId());
		}
	}
	
	public CommonDAO<File> getCommonDao() {
		return fileDao;
	}
}
