package com;

import java.util.List;

import entity.File;
import entity.Storage;

public class ValidatorInputData {
	
	public void isFormatsEquals (String[] formats, File file, long storageId) throws Exception {
		for (String format : formats) {
			if (format.equals(file.getFormat())) {
				return;
			}
		}
		throw new Exception("File's format, ID = " + file.getId() 
			+ " not equal Storage's format, ID = " + storageId + "\n");
	}
	
	public void isIdNotInStorage (List<File> files, File file, long storageId) throws Exception {
		for (File currentFile : files) {
			if (currentFile != null && currentFile.getId() == file.getId()) {
				throw new Exception("File, ID = " + file.getId() 
					+ " is used in Storage, ID = " + storageId + "\n");
			}
		}
	}
	
	public void isFileInStorage (List<File> files, File file, long storageId) throws Exception {
		for (File currentFile : files) {
			if (currentFile != null && file.equals(currentFile)) {
				return;
			}
		}
		throw new Exception("File, ID = " + file.getId() 
			+ " is not used in Storage, ID = " + storageId + "\n");
	}
	
	public void isStorageMaxSizeFull (Storage storage, File file, List<File> filesInStorage) throws Exception {
		long maxSizeStorage = 0;
		for (File files : filesInStorage) {
			if (files != null) {
				maxSizeStorage += files.getSize();
			}
		}
		if (maxSizeStorage + file.getSize() > storage.getStorageSize()) {
			throw new Exception("File, ID = " + file.getId() + " too big, Storage, ID = " 
				+ storage.getId() + " will be full\n");
		}
	}
		
	
	public File findFileInStorage(List<File> files, long fileId, long storageId) throws Exception {
		for (File file : files) {
			if (file != null && file.getId() == fileId) {
				return file;
			}
		}
		throw new Exception("File, ID = " + fileId + " not found in Storage, ID = " + storageId + "\n");
	}
	
	public void isStorageAndFileValid (Storage storage, File file, List<File> filesInStorage) throws Exception {
		isFormatsEquals (storage.getArrayFormatsSupported(), file, storage.getId()); 
		isIdNotInStorage (filesInStorage, file, storage.getId());
		isStorageMaxSizeFull (storage, file, filesInStorage);
	}
}
