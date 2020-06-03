package com.company.persistence;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Dishes", schema = "dbo", catalog = "restaurant")
public class Dishes_DB {
    private int dishId;
    private String name;
    private double price;
    private Collection<DishOrder_DB> dosByDishId;

    @Id
    @Column(name = "dishId", nullable = false)
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dishes_DB dishes_db = (Dishes_DB) o;
        return dishId == dishes_db.dishId &&
                Objects.equals(name, dishes_db.name) &&
                Objects.equals(price, dishes_db.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, name, price);
    }

    @OneToMany(mappedBy = "dishesByDishId")
    public Collection<DishOrder_DB> getDosByDishId() {
        return dosByDishId;
    }

    public void setDosByDishId(Collection<DishOrder_DB> dosByDishId) {
        this.dosByDishId = dosByDishId;
    }
}
