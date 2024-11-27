package presentation;

import dao.ClientDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteClientDialog extends JDialog {
    private JTextField clientIdField;
    private JButton deleteButton, cancelButton;

    public DeleteClientDialog(Frame parent) {
        super(parent, "Delete Client", true);
        setSize(300, 100);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Client ID:"));
        clientIdField = new JTextField();
        add(clientIdField);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteClient(parent));
        add(deleteButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);
    }

    private void deleteClient(Frame parent) {
        int clientId = Integer.parseInt(clientIdField.getText());
        try {
            ClientDAO dao = new ClientDAO();
            dao.deleteClient(clientId);
            JOptionPane.showMessageDialog(parent, "Client deleted successfully!");
            ((MainFrame)parent).refreshClients();  // Assuming MainFrame has a method to refresh client list
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting client: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }
}
