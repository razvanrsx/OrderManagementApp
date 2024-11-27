package presentation;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog for adding new clients in the system.
 * This dialog provides input fields for the user to enter details about a new client
 * and interactively add them to the database through the ClientDAO.
 */
public class AddClientDialog extends JDialog {
    private JTextField nameField, addressField, emailField, phoneField;
    private JButton addButton, cancelButton;

    /**
     * Constructs a new dialog for adding a client.
     *
     * @param parent the parent frame from which this dialog is launched.
     */
    public AddClientDialog(Frame parent) {
        super(parent, "Add Client", true);
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Address:"));
        addressField = new JTextField(20);
        add(addressField);

        add(new JLabel("Email:"));
        emailField = new JTextField(20);
        add(emailField);

        add(new JLabel("Phone:"));
        phoneField = new JTextField(20);
        add(phoneField);

        addButton = new JButton("Add");
        addButton.addActionListener(e -> addClientAction(parent));
        add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);
    }

    /**
     * This method handles the action of adding a client.
     * It retrieves the text from the input fields, creates a new client object, and
     * uses the ClientDAO to persist the client to the database. It shows a success or error message
     * based on the outcome of the attempt to add the client.
     *
     * @param parent the parent frame, used for displaying message dialogs.
     */
    private void addClientAction(Frame parent) {
        String name = nameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        try {
            ClientDAO dao = new ClientDAO();
            dao.addClient(new Client(name, address, email, phone));
            JOptionPane.showMessageDialog(parent, "Client added successfully!");
            setVisible(false);
            ((MainFrame)parent).refreshClients(); // Assuming MainFrame has a method to refresh client list
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding client: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
