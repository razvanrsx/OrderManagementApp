package presentation;

//import com.sun.org.apache.xpath.internal.operations.Or;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private ProductPanel productPanel; // Presupunem că ai un panou pentru produse
    private ClientPanel clientPanel;
    private OrderPanel orderPanel;


    public MainFrame() {
        setTitle("Warehouse Order Management System");
        setSize(800, 600);
        productPanel = new ProductPanel();
        clientPanel = new ClientPanel();
        orderPanel = new OrderPanel();

        add(orderPanel);
        add(productPanel);
        add(clientPanel);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Products", new ProductPanel());
        tabbedPane.addTab("Clients", new ClientPanel());
        tabbedPane.addTab("Orders", new OrderPanel());

        this.add(tabbedPane);
    }


    public void refreshProducts() {
        orderPanel.loadOrders();
        productPanel.loadProducts();
        clientPanel.loadClients();
    }

    public void refreshClients() {
        orderPanel.loadOrders();
        productPanel.loadProducts();
        clientPanel.loadClients();
    }

    public void refreshOrders()
    {
        orderPanel.loadOrders();
        productPanel.loadProducts();
        clientPanel.loadClients();
    }
}
