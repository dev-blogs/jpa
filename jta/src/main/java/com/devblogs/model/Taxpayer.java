package com.devblogs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="taxpayers")
public class Taxpayer {
	private Long id;
	private String name;
	private String snils;
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

	@Column(name = "snils")
	public String getSnils() {
		return snils;
	}

	public void setSnils(String snils) {
		this.snils = snils;
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
		Taxpayer otherTaxpayer = (Taxpayer) obj;
		return id.equals(otherTaxpayer.id) &&
				name.equals(otherTaxpayer.name) &&
				snils.equals(otherTaxpayer.snils) &&
				isDebtor.equals(otherTaxpayer.isDebtor);
	}
	
	@Override
	public String toString() {
		return "[id=" + this.id + ", name=" + this.name + ", snils=" + this.snils + ", isDebtor=" + this.isDebtor + "]";
	}
}