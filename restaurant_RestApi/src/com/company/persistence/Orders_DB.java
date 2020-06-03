package com.company.persistence;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Orders", schema = "dbo", catalog = "restaurant")
public class Orders_DB {
    private int orderId;
    private BigDecimal total;
    private String paid;

    @Id
    @Column(name = "orderId", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "total", nullable = false)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @Column(name = "paid", nullable = false, length = 10)
    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders_DB orders_db = (Orders_DB) o;
        return orderId == orders_db.orderId &&
                Objects.equals(total, orders_db.total) &&
                Objects.equals(paid, orders_db.paid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, total, paid);
    }
}
