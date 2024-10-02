package com.albastore.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "carts")
public class Cart extends PanacheEntity {
    public String userId;
    public double totalPrice;
    @ManyToMany(fetch = FetchType.EAGER)
    public List<Product> products;
}