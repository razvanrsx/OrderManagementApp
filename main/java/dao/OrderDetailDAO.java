package dao;
import connection.ConnectionFactory;
import model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM orderdetails WHERE order_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderDetails.add(new OrderDetail(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("price_per_unit")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        String query = "INSERT INTO orderdetails (order_id, product_id, quantity, price_per_unit) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderDetail.getOrderId());
            stmt.setInt(2, orderDetail.getProductId());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setBigDecimal(4, orderDetail.getPricePerUnit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

