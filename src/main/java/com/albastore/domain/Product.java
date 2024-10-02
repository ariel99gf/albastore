package com.albastore.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "products")
public class Product extends PanacheEntity {
    public String name;
    public double price;
    @ManyToMany(mappedBy = "products")
    public List<Cart> carts;
}