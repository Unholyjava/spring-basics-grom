package com;

import java.util.List;

import entity.File;

public interface DBService {
	List<File> findFilesByStorageID(long idStorage) throws Exception;
}
