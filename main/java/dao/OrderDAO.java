package dao;

import connection.ConnectionFactory;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    public Order getOrder(int orderId) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("order_id"),
                        rs.getInt("client_id"),
                        rs.getDate("order_date"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("client_id"),
                        rs.getDate("order_date"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void addOrder(int clientId, int productId, int quantity) throws SQLException {
        String sql = "{CALL AddOrder(?, ?, ?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, clientId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Unable to execute order: " + e.getMessage());
        }
    }

    public void updateOrder(Order order) {
        String query = "UPDATE orders SET client_id = ?, order_date = ?, status = ? WHERE order_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, order.getClientId());
            stmt.setDate(2, order.getOrderDate());
            stmt.setString(3, order.getStatus());
            stmt.setInt(4, order.getOrderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
