package model;

import java.sql.Date;

public class Order {
    private int orderId;
    private int clientId;
    private Date orderDate;
    private String status;

    public Order(int orderId, int clientId, Date orderDate, String status) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getClientId() {
        return clientId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

