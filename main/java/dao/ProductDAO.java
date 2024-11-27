package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public Product getProduct(int productId) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void addProduct(Product product) {
        String sql = "{CALL AddProduct(?, ?, ?, ?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error executing AddProduct: " + e.getMessage());
            e.printStackTrace();
        }
    }




    public void updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, description = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            stmt.setInt(5, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) throws SQLException {
        String sql = "{CALL DeleteProduct(?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, productId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting product failed, no rows affected.");
            }
        }
    }


    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void updateProductQuantity(int productId, int decrementQuantity) throws SQLException {
        String sql = "{CALL DecrementProductStock(?, ?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, decrementQuantity);
            stmt.execute();
        } catch (SQLException e) {
            if (e.getSQLState().equals("45000")) {
                throw new SQLException("Insufficient stock available for product ID " + productId);
            }
            throw e;
        }
    }

}

