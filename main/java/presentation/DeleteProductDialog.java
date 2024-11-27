package presentation;

import dao.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteProductDialog extends JDialog {
    private JTextField idField;
    private JButton okButton, cancelButton;

    public DeleteProductDialog(Frame parent) {
        super(parent, "Delete Product", true);
        setSize(300, 120);
        setLayout(new GridLayout(2, 2, 10, 10));
        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        okButton = new JButton("OK");
        okButton.addActionListener(e -> deleteProductAction(parent));
        add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);
    }

    private void deleteProductAction(Frame parent) {
        try {
            int productId = Integer.parseInt(idField.getText());
            ProductDAO dao = new ProductDAO();
            dao.deleteProduct(productId);
            JOptionPane.showMessageDialog(parent, "Product deleted successfully!");
            if (parent instanceof MainFrame) {
                ((MainFrame)parent).refreshProducts();  // Refresh the product list
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Please enter a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Error deleting product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }

}
