package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FILES")
public class File {

	private long id;
	private String name;
	private String format;
	private long size;
	private Storage storage;
	
	public File() {
	}
	
	public File(long id, String name, String format, long size, Storage storage) {
		super();
		this.id = id;
		this.name = name;
		this.format = format;
		this.size = size;
		this.storage = storage;
	}
	
	@Id
	@Column (name = "ID")
	public long getId() {
		return id;
	}
	
	@Column (name = "FILESNAME")
	public String getName() {
		return name;
	}
	
	@Column (name = "FILESFORMAT")
	public String getFormat() {
		return format;
	}
	
	@Column (name = "FILESSIZE")
	public long getSize() {
		return size;
	}
	
	@OneToOne (targetEntity = Storage.class, 
			cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_STORAGE")
	public Storage getStorage() {
		return storage;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", name=" + name + ", format=" + format + ", size=" + size + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		File file = (File)obj;
		if (file.id == id && file.name.equals(name)) {
			return true;
		}
		return false;
	}

}
