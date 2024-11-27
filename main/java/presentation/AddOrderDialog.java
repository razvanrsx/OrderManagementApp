package presentation;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class AddOrderDialog extends JDialog {
    private JComboBox<Product> productComboBox;
    private JComboBox<Client> clientComboBox;
    private JTextField quantityField;
    private JButton addButton, cancelButton;
    private ProductDAO productDAO;
    private ClientDAO clientDAO;
    private OrderDAO orderDAO;  // Adăugat instanța pentru OrderDAO

    public AddOrderDialog(Frame parent) {
        super(parent, "Add Order", true);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        productDAO = new ProductDAO();
        clientDAO = new ClientDAO();
        orderDAO = new OrderDAO();  // Inițializare OrderDAO aici

        List<Product> products = productDAO.getAllProducts();
        List<Client> clients = clientDAO.getAllClients();

        productComboBox = new JComboBox<>(new DefaultComboBoxModel(products.toArray(new Product[0])));
        clientComboBox = new JComboBox<>(new DefaultComboBoxModel(clients.toArray(new Client[0])));

        add(new JLabel("Select Product:"));
        add(productComboBox);

        add(new JLabel("Select Client:"));
        add(clientComboBox);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        addButton = new JButton("Add");
        addButton.addActionListener(e -> addOrder());
        add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);

        setLocationRelativeTo(parent);
    }

    private void addOrder() {
        Product selectedProduct = (Product) productComboBox.getSelectedItem();
        Client selectedClient = (Client) clientComboBox.getSelectedItem();
        int quantity = Integer.parseInt(quantityField.getText());

        try {
            orderDAO.addOrder(selectedClient.getClientId(), selectedProduct.getId(), quantity);
            JOptionPane.showMessageDialog(this, "Order added successfully!");
            setVisible(false);
            if (getParent() instanceof MainFrame) {
                ((MainFrame)getParent()).refreshOrders();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding order: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
