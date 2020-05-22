package com.company.persistence;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DishOrder_DBPK implements Serializable {
    private int dishId;
    private int orderId;

    @Column(name = "dishId", nullable = false, insertable = false, updatable = false)
    @Id
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Column(name = "orderId", nullable = false)
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishOrder_DBPK do_dbpk = (DishOrder_DBPK) o;
        return dishId == do_dbpk.dishId &&
                orderId == do_dbpk.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, orderId);
    }
}
