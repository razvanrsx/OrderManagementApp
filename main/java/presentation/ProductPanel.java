package presentation;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductPanel extends JPanel {
    private JTable table;

    public ProductPanel() {
        setLayout(new BorderLayout());
        add(createToolbar(), BorderLayout.NORTH);
        table = createTable();
        add(new JScrollPane(table), BorderLayout.CENTER);
        loadProducts();
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(e -> addProduct());
        deleteButton.addActionListener(e -> deleteProduct());
        editButton.addActionListener(e -> editProduct());

        toolbar.add(addButton);
        toolbar.add(editButton);
        toolbar.add(deleteButton);

        return toolbar;
    }

    private void addProduct() {
        ProductDialog dialog = new ProductDialog(JOptionPane.getFrameForComponent(this));
        dialog.setVisible(true);
        loadProducts();
    }

    private void deleteProduct() {
        Frame parentFrame = JOptionPane.getFrameForComponent(this);
        DeleteProductDialog dialog = new DeleteProductDialog(parentFrame);
        dialog.setVisible(true);
        loadProducts();
    }

    private void editProduct() {
        Frame parentFrame = JOptionPane.getFrameForComponent(this);
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int productId = (Integer) table.getModel().getValueAt(selectedRow, 0);
            ProductDAO productDao = new ProductDAO();
            Product product = productDao.getProduct(productId);
            EditProductDialog dialog = new EditProductDialog(parentFrame);
            dialog.setProduct(product); // Setează produsul curent în dialog pentru a fi editat
            dialog.setVisible(true);
            loadProducts();  // Reîncarcă lista de produse după închiderea dialogului
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to edit.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private JTable createTable() {
        return TableUtil.createTableFromList(List.of(new Product("Example", "Example Description", 0.0, 0))); // Creează un tabel gol pe baza clasei Product
    }

    void loadProducts() {
        ProductDAO productDao = new ProductDAO();
        List<Product> products = productDao.getAllProducts();
        JTable newTable = TableUtil.createTableFromList(products);
        table.setModel(newTable.getModel());  // Actualizează modelul tabelului cu datele din lista de produse
    }

    public void refreshProducts() {
        loadProducts();
    }
}
