package presentation;

import model.Client;
import model.Product;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

public class TableUtil {
    public static <T> JTable createTableFromList(List<T> objects) {
        if (objects == null || objects.isEmpty()) {
            return new JTable(new DefaultTableModel());
        }

        Class<?> clazz = objects.get(0).getClass();

        DefaultTableModel model = new DefaultTableModel();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            model.addColumn(field.getName());
        }

        for (T object : objects) {
            Vector<Object> row = new Vector<>();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    row.add(field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(row);
        }

        return new JTable(model);
    }
}
