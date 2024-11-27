package presentation;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditProductDialog extends JDialog {
    private JTextField idField, nameField, descriptionField, priceField, quantityField;
    private JButton saveButton, cancelButton;

    public EditProductDialog(Frame parent) {
        super(parent, "Edit Product", true);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID:"));
        idField = new JTextField();
        idField.setEditable(false);

        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Stock Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveProduct());
        add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);
    }

    public void setProduct(Product product) {
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        descriptionField.setText(product.getDescription());
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getStockQuantity()));
    }

    private void saveProduct() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        ProductDAO dao = new ProductDAO();
        dao.updateProduct(new Product(id, name, description, price, quantity));
        JOptionPane.showMessageDialog(this, "Product updated successfully!");
        setVisible(false);
    }
}
