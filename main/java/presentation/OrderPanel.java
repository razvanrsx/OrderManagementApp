package presentation;

import dao.OrderDAO;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderPanel extends JPanel {
    private JTable table;

    public OrderPanel() {
        setLayout(new BorderLayout());
        add(createToolbar(), BorderLayout.NORTH);
        table = createTable();
        add(new JScrollPane(table), BorderLayout.CENTER);
        loadOrders();
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();
        JButton addButton = new JButton("Add Order");

        addButton.addActionListener(e -> addOrder());

        toolbar.add(addButton);

        return toolbar;
    }

    private JTable createTable() {
        return TableUtil.createTableFromList(List.of(new Order(0, 0, new java.sql.Date(System.currentTimeMillis()), "x"))); // Creează un tabel gol pe baza clasei Order
    }

    protected void loadOrders() {
        OrderDAO orderDao = new OrderDAO();
        List<Order> orders = orderDao.getAllOrders();
        JTable newTable = TableUtil.createTableFromList(orders);
        table.setModel(newTable.getModel());  // Actualizează modelul tabelului cu datele din lista de comenzi
    }

    private void addOrder() {
        AddOrderDialog dialog = new AddOrderDialog(JOptionPane.getFrameForComponent(this));
        dialog.setVisible(true);
        loadOrders();  // Refresh the order list after the dialog is closed
    }
}
