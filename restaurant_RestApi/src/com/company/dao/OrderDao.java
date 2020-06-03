package com.company.dao;

public interface OrderDao {
    public void createOrder(int clientId, String line);
    public void showOrdersList();
    public void deleteOrder(int id);
    public void updateOrder(String line);
}
