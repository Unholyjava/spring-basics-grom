package com;


import entity.Storage;

public class StorageDAO extends CommonDAO<Storage>{

//	private static final String UPDATE_STORAGE_BY_ID = "UPDATE STORAGE SET FORMATSSUPPORTED = ?, STORAGECOUNTRY = ?, STORAGESIZE = ? WHERE ID = ?";
//	private static final String DELETE_STORAGE_BY_ID = "DELETE FROM STORAGE WHERE ID = ?";
//	private static final String INSERT_INTO_STORAGE = "INSERT INTO STORAGE VALUES (?, ?, ?, ?)";
//	private static final String SELECT_STORAGE_BY_ID = "SELECT * FROM STORAGE WHERE ID = ?";

	public StorageDAO(Class<Storage> classCurrent) {
		super(classCurrent);
	}
	
	public Storage save(Storage storage) throws Exception {
		if (findById(storage.getId()) != null) {
			throw new Exception("Storage with id = " + storage.getId() + " exists, no save Storage");
		}
		return super.save(storage);
	}

	public Storage delete(long id) throws Exception {
		return super.delete(id);
	}
	
	public Storage update(Storage storage) throws Exception {
		Storage storageOld = findById(storage.getId());
		if (storageOld == null) {
			throw new Exception("Storage with id = " + storage.getId() + " is upsent, no update Storage");
		}	
		return super.update(storage);
	}
	
	public Storage findById(long id) throws Exception {
		return super.findById(id);
	}
	
}
