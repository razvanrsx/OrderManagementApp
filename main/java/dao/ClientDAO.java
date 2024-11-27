package dao;

import connection.ConnectionFactory;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for handling database operations related to clients.
 * This class provides methods to fetch, add, update, and delete client records in the database.
 */
public class ClientDAO {

    /**
     * Retrieves a single client from the database based on the client ID.
     *
     * @param clientId the ID of the client to retrieve.
     * @return the client object if found, otherwise null.
     */
    public Client getClient(int clientId) {
        String query = "SELECT * FROM clients WHERE client_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getInt("client_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of client objects.
     */
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("client_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    /**
     * Adds a new client to the database.
     *
     * @param client the client object to add.
     * @throws SQLException if there is a database error.
     */
    public void addClient(Client client) throws SQLException {
        String sql = "{CALL AddClient(?, ?, ?, ?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getPhone());
            stmt.execute();
        }
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client the client object to update.
     * @throws SQLException if there is a database error.
     */
    public void updateClient(Client client) throws SQLException {
        String sql = "{CALL UpdateClient(?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, client.getClientId());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPhone());
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a client from the database based on the client ID.
     *
     * @param clientId the ID of the client to delete.
     * @throws SQLException if there is a database error.
     */
    public void deleteClient(int clientId) throws SQLException {
        String sql = "{CALL DeleteClient(?)}";
        try (Connection conn = ConnectionFactory.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, clientId);
            stmt.execute();
        }
    }
}
