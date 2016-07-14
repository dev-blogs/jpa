package com.devblogs.model;
 
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
 
@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    private Set<Item> items = new HashSet<Item>();
 
    public Warehouse() {
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
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
        return "[id=" + this.id + ", address=" + this.address + "]";
    }
}