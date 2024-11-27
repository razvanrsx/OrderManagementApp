package model;

import java.math.BigDecimal;

public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int productId;
    private int quantity;
    private BigDecimal pricePerUnit;

    public OrderDetail(int orderDetailId, int orderId, int productId, int quantity, BigDecimal pricePerUnit) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }
}

