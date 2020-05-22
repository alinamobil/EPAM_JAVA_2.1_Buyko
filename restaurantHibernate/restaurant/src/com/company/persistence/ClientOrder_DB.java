package com.company.persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CO", schema = "dbo", catalog = "restaurant")
public class ClientOrder_DB {
    private int clientId;
    private int orderId;

    public ClientOrder_DB(int clientId, int orderId) {
        this.clientId = clientId;
        this.orderId = orderId;
    }

    public ClientOrder_DB() {}

    @Basic
    @Column(name = "clientId", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Id
    @Column(name = "orderId", nullable = false)
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
        ClientOrder_DB co_db = (ClientOrder_DB) o;
        return clientId == co_db.clientId &&
                orderId == co_db.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, orderId);
    }
}
