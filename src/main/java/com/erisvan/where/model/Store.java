package com.erisvan.where.model;

import java.util.List;

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
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "calling_store", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "calling_id"))
    private List<Calling> callings;

    public Store() {
    }

    public Store(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Calling> getCallings() {
        return callings;
    }

    public void setCallings(List<Calling> callings) {
        this.callings = callings;
    }

    @Override
    public String toString() {
        return "Store [id=" + id + ", name=" + name + "]";
    }

}
