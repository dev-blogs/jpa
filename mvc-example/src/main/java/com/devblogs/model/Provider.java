package com.devblogs.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
  
@Entity
@Table(name = "providers")
@NamedQueries({
	@NamedQuery(name = "Provider.findAll", query = "select p from Provider p"),
	@NamedQuery(name = "Provider.findById", query = "select distinct p from Provider p where p.id = :id")
})
public class Provider {
    private Long id;
    private String name;
    private Set<Item> items = new HashSet<Item>();
      
    public Provider() {   
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
  
    @Column(name = "name")
    public String getName() {
        return name;
    }
  
    public void setName(String name) {
        this.name = name;
    }
  
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "items_providers",
            joinColumns={@JoinColumn(name = "provider_id")},
            inverseJoinColumns={@JoinColumn(name = "item_id")})
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
        Provider that = (Provider) obj;
        if (!name.equals(that.name)) return false;
        return true;
    }
     
    @Override
    public String toString() {
        return "[id=" + this.id + ", name=" + this.name + "]";
    }
}