package com;

import org.springframework.beans.factory.annotation.Autowired;

public class StorageService {
	@Autowired
	private StorageDAO storageDao;
		
	public StorageDAO getCommonDao() {
		return storageDao;
	}
}
