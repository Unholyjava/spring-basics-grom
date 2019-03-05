package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import entity.Storage;

@Controller
public class StorageService {
	@Autowired
	private CommonDAO<Storage> commonDao;
		
	public StorageService(CommonDAO<Storage> commonDao) {
		this.commonDao = commonDao;
	}
	
	public CommonDAO<Storage> getCommonDao() {
		return commonDao;
	}
}
