package presentation;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditClientDialog extends JDialog {
    private JTextField idField, nameField, addressField, emailField, phoneField;
    private JButton saveButton, cancelButton;

    public EditClientDialog(Frame parent, Client client) {
        super(parent, "Edit Client", true);
        setSize(400, 250);
        setLayout(new GridLayout(6, 2));

        idField = new JTextField(String.valueOf(client.getClientId()));
        idField.setEditable(false);
        add(new JLabel("Client ID:"));
        add(idField);

        nameField = new JTextField(client.getName());
        add(new JLabel("Name:"));
        add(nameField);

        addressField = new JTextField(client.getAddress());
        add(new JLabel("Address:"));
        add(addressField);

        emailField = new JTextField(client.getEmail());
        add(new JLabel("Email:"));
        add(emailField);

        phoneField = new JTextField(client.getPhone());
        add(new JLabel("Phone:"));
        add(phoneField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveClient());
        add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);
    }

    private void saveClient() {
        try {
            ClientDAO dao = new ClientDAO();
            dao.updateClient(new Client(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    addressField.getText(),
                    emailField.getText(),
                    phoneField.getText()
            ));
            JOptionPane.showMessageDialog(this, "Client updated successfully!");
            setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating client: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

