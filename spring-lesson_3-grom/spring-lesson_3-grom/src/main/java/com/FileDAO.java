package com;

import entity.File;

public class FileDAO extends CommonDAO<File>{

//	private static final String UPDATE_FILES_BY_ID = "UPDATE FILES SET FILESNAME = ?, FILESFORMAT = ?, FILESSIZE = ? WHERE ID = ?";
//	private static final String DELETE_FILES_BY_ID = "DELETE FROM FILES WHERE ID = ?";
//	private static final String INSERT_INTO_FILES = "INSERT INTO FILES (ID, FILESNAME, FILESFORMAT, FILESSIZE) VALUES (?, ?, ?, ?)";
//	private static final String SELECT_FILES_BY_ID = "SELECT * FROM FILES WHERE ID = ?";
//	private static final String SELECT_FILES_BY_ID_STORAGE = "SELECT * FROM FILES WHERE ID_STORAGE = ?";

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
	
//	public File[] findByIdStorage(long id_storage) {
//		try (Connection connection = CommonDAO_Old.getConnection();
//				PreparedStatement prepareStatementSelect = connection
//						.prepareStatement(SELECT_FILES_BY_ID_STORAGE)) {
//			prepareStatementSelect.setLong(1, id_storage);
//			ResultSet resultSet = prepareStatementSelect.executeQuery();
//			List<File> files = new ArrayList<>();
//			if (!resultSet.next()) {
//				return null;
//			} else {
//				while (resultSet.next()) {
//					File file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));
//					files.add(file);
//				}
//				return files.toArray(new File[files.size()]);
//			}
//		} catch (SQLException e) {
//			System.out.println(CommonDAO_Old.ERROR);
//			e.printStackTrace();
//		}
//		return null;
//	}
	
}
