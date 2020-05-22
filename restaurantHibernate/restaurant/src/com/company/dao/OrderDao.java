package com.company.dao;

import com.company.users.Client;

public interface OrderDao {
    public void createOrder(Client client);
    public void showOrdersList();
    public void deleteOrder(int id);
    public void updateOrder();
}
