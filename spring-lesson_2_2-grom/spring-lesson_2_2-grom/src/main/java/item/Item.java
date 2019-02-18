package item;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ITEMS")
public class Item {
	private long id;
	private String name;
	
	@JsonIgnore
	private Date dateCreated;
	
	@JsonIgnore
	private Date lastUpdatedDate;
	private String description;
	
	@Id
//	@SequenceGenerator (name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
//	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
	@Column (name = "ID")
	public long getId() {
		return id;
	}
	
	@Column (name = "NAME")
	public String getName() {
		return name;
	}
	
	@Column (name = "DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}
	
	@Column (name = "DATE_LAST_UPDATE")
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	
	@Column (name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", dateCreated=" + dateCreated + ", lastUpdatedDate="
				+ lastUpdatedDate + ", description=" + description + "]";
	}
}
