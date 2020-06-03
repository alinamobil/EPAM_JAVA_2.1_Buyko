package com.company.persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DO", schema = "dbo", catalog = "restaurant")
@IdClass(DishOrder_DBPK.class)
public class DishOrder_DB {
    private int dishId;
    private int orderId;
    private int dishAmount;
    private Dishes_DB dishesByDishId;

    public DishOrder_DB(int dishId, int orderId, int dishAmount) {
        this.dishId = dishId;
        this.orderId = orderId;
        this.dishAmount = dishAmount;
    }

    public DishOrder_DB() {}

    @Id
    @Column(name = "dishId", nullable = false)
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Id
    @Column(name = "orderId", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "dishAmount", nullable = false)
    public int getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(int dishAmount) {
        this.dishAmount = dishAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishOrder_DB do_db = (DishOrder_DB) o;
        return dishId == do_db.dishId &&
                orderId == do_db.orderId &&
                dishAmount == do_db.dishAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, orderId, dishAmount);
    }

    @ManyToOne
    @JoinColumn(name = "dishId", referencedColumnName = "dishId", nullable = false)
    public Dishes_DB getDishesByDishId() {
        return dishesByDishId;
    }

    public void setDishesByDishId(Dishes_DB dishesByDishId) {
        this.dishesByDishId = dishesByDishId;
    }
}
