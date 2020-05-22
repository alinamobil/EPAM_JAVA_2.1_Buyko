package com.company.users;

import java.io.Serializable;

public class Client extends User implements Serializable {
    private int orderId;
    private boolean paid;

    public Client(String name, String password, int id) {
        super(name, password, id);
        this.orderId = 0;
        this.paid = false;
    }

    public Client(User user) {
        super(user.getName(), user.getPassword(), user.getId());
        this.orderId = 0;
        this.paid = false;
    }

    public Client() {
        super("", "",  0);
        this.orderId = 0;
        this.paid = false;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Client{" +
                " id= " + super.getId() +
                ", name= " + super.getName() +
                '}';
    }
}
