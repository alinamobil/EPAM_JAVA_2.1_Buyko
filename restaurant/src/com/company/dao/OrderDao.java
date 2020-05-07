package com.company.dao;

import com.company.users.Client;

import java.sql.SQLException;

public interface OrderDao {
    public void createOrder(Client client) throws SQLException;
    public void showOrdersList() throws SQLException;
    public void deleteOrder(int id) throws SQLException;
    public void updateOrder() throws SQLException;
}
