package com.devblogs.model;
 
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
 
@Entity
@Audited
@Table(name = "warehouses")
public class Warehouse implements Auditable<String, Long>, Serializable {
    private Long id;
    private String address;
    private String createdBy;
    private DateTime createdDate;
    private String lastModifiedBy;
    private DateTime lastModifiedDate;
    private Set<Item> items = new HashSet<Item>();
 
    public Warehouse() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "address")
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name = "created_by")
    public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_date")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "last_modified_by")
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Column(name = "last_modified_date")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Transient
    public boolean isNew() {
		if (id == null) {
			return true;
		} else {
			return false;
		}
	}

	@NotAudited
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    public Set<Item> getItems() {
        return items;
    }
 
    public void setItems(Set<Item> items) {
        this.items = items;
    }
     
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Warehouse that = (Warehouse) obj;
        if (!address.equals(that.address)) return false;
        return true;
    }
     
    @Override
    public String toString() {
        return "\n[id: " + this.id + ",\naddress: " + this.address + ",\nCreated by: " +
        		this.createdBy + ",\ncreateDate: " + this.createdDate + ",\nlastModifiedBy: " +
        		this.lastModifiedBy + ",\nlastModifiedDate: " + this.lastModifiedDate + "]";
    }
}