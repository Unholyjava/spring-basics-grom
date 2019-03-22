package com;

import entity.File;

public class FileDAO extends CommonDAO<File>{

	public FileDAO(Class<File> classCurrent) {
		super(classCurrent);
	}
	
	public File save(File file) throws Exception {
		if (findById(file.getId()) != null) {
			throw new Exception("File with id = " + file.getId() + " exists, no save File");
		}
		if (file.getName().length() > 10) {
			throw new Exception("Too much lenght of File's name, ID = " + file.getId()+ " , no save File");
		}
		return super.save(file);
	}

	public File delete(long id) throws Exception {
		return super.delete(id);
	}
	
	public File update(File file) throws Exception {
		File fileOld = findById(file.getId());
		if (fileOld == null) {
			throw new Exception("File with id = " + file.getId() + " is upsent, no update File");
		}	
		return super.update(file);
	}
	
	public File findById(long id) throws Exception {
		return super.findById(id);
	}
}
