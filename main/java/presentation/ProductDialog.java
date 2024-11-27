package presentation;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductDialog extends JDialog {
    private JTextField nameField, descriptionField, priceField, quantityField;
    private JButton okButton, cancelButton;

    public ProductDialog(Frame parent) {
        super(parent, "Add Product", true);
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField(20);
        add(descriptionField);

        add(new JLabel("Price:"));
        priceField = new JTextField(20);
        add(priceField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField(20);
        add(quantityField);

        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            okButtonAction(parent);
        });
        add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);
    }

    private void okButtonAction(Frame parent) {
        String name = nameField.getText();
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Product product = new Product(name, description, price, quantity);
        ProductDAO dao = new ProductDAO();
        try {
            dao.addProduct(product);
            JOptionPane.showMessageDialog(parent, "Product added successfully!");
            ((MainFrame) parent).refreshProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Failed to add product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }


}
