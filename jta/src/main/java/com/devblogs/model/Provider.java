package com.devblogs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="providers")
@NamedQueries({
	@NamedQuery(name = "Provider.findAll", query = "select p from Provider p"),
	@NamedQuery(name = "Provider.findById", query = "select distinct p from Provider p where p.id = :id")
})
public class Provider {
	private Long id;
	private String name;
	private Boolean isDebtor;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "isDebtor")
	public Boolean getIsDebtor() {
		return isDebtor;
	}

	public void setIsDebtor(Boolean isDebtor) {
		this.isDebtor = isDebtor;
	}
	
	@Override
	public int hashCode() {
		return 3*id.hashCode() + 5*name.hashCode() + 7*isDebtor.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Provider otherProvider = (Provider) obj;
		return id.equals(otherProvider.id) &&
				name.equals(otherProvider.name) &&
				isDebtor.equals(otherProvider.isDebtor);
	}

	@Override
	public String toString() {
		return "[id=" + this.id + ", name=" + this.name + ", isDebtor=" + this.isDebtor + "]";
	}
}