package presentation;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientPanel extends JPanel {
    private JTable table;

    public ClientPanel() {
        setLayout(new BorderLayout());
        add(createToolbar(), BorderLayout.NORTH);
        add(new JScrollPane(createTable()), BorderLayout.CENTER);
        loadClients();
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();
        JButton addButton = new JButton("Add Client");
        JButton editButton = new JButton("Edit Client");
        JButton deleteButton = new JButton("Delete Client");

        addButton.addActionListener(e -> addClient());
        editButton.addActionListener(e -> editClient());
        deleteButton.addActionListener(e -> deleteClient());

        toolbar.add(addButton);
        toolbar.add(editButton);
        toolbar.add(deleteButton);

        return toolbar;
    }

    private JTable createTable() {
        table = new JTable(new DefaultTableModel());
        return table;
    }

    void loadClients() {
        ClientDAO clientDao = new ClientDAO();
        List<Client> clients = clientDao.getAllClients();
        JTable newTable = TableUtil.createTableFromList(clients);
        table.setModel(newTable.getModel());  // Actualizează modelul tabelului cu datele din lista de clienți
    }

    private void addClient() {
        AddClientDialog dialog = new AddClientDialog(JOptionPane.getFrameForComponent(this));
        dialog.setVisible(true);
        loadClients();  // Refresh the client list after the dialog is closed
    }

    private void editClient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int clientId = Integer.parseInt(table.getModel().getValueAt(selectedRow, 0).toString());
            Client client = new ClientDAO().getClient(clientId);  // presupunând că ai o metodă getClient()
            EditClientDialog dialog = new EditClientDialog(JOptionPane.getFrameForComponent(this), client);
            dialog.setVisible(true);
            loadClients();  // Refresh the client list after editing
        } else {
            JOptionPane.showMessageDialog(this, "Please select a client to edit.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteClient() {
        DeleteClientDialog dialog = new DeleteClientDialog(JOptionPane.getFrameForComponent(this));
        dialog.setVisible(true);
        loadClients();
    }
}
