package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "STORAGE")
public class Storage {

	private long id;
	private String formatsSupported;
	private String storageCountry;
	private long storageSize;
	
	public Storage() {
	}
	
	public Storage(long id, String formatsSupported, String storageCountry, long storageSize) {
		super();
		this.id = id;
		this.formatsSupported = formatsSupported;
		this.storageCountry = storageCountry;
		this.storageSize = storageSize;
	}
	
	@Id
	@Column (name = "ID")
	public long getId() {
		return id;
	}
	
	@Column (name = "FORMATSSUPPORTED")
	public String getFormatsSupported() {
		return formatsSupported;
	}
	
	
	@Column (name = "STORAGECOUNTRY")
	public String getStorageCountry() {
		return storageCountry;
	}
	
	@Column (name = "STORAGESIZE")
	public long getStorageSize() {
		return storageSize;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setFormatsSupported(String formatsSupported) {
		this.formatsSupported = formatsSupported;
	}

	public void setStorageCountry(String storageCountry) {
		this.storageCountry = storageCountry;
	}

	public void setStorageSize(long storageSize) {
		this.storageSize = storageSize;
	}

	@Transient
	public String[] getArrayFormatsSupported() {
		return getFormatsSupported().replaceAll(" ", "").split(",");
	}
	
	@Override
	public String toString() {
		return "Storage [id=" + id + ", formatsSupported=" + formatsSupported 
				+ ", storageCountry=" + storageCountry + ", storageSize=" + storageSize + "]";
	}
}
