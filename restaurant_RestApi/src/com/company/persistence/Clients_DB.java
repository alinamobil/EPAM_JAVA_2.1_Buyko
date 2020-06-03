package com.company.persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Clients", schema = "dbo", catalog = "restaurant")
public class Clients_DB {
    private int clientId;
    private String name;
    private String password;

    public Clients_DB(int clientId, String name, String password) {
        this.clientId = clientId;
        this.name = name;
        this.password = password;
    }

    public Clients_DB() {}

    @Id
    @Column(name = "clientId", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients_DB that = (Clients_DB) o;
        return clientId == that.clientId &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, name, password);
    }
}
