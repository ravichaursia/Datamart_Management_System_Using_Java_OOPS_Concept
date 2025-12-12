import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Inventory {
    private JFrame frame;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private List<Product2> inventory;

    public Inventory(List<Product2> inventory) {
        this.inventory = inventory;
        showInventoryWindow();
    }

    // Display the inventory management window
    public void showInventoryWindow() {
        frame = new JFrame("Inventory Management");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel for Adding Product
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(new Color(255, 228, 196)); // Light Peach color

        JLabel productNameLabel = new JLabel("Product Name:");
        JTextField productNameField = new JTextField(15);
        JLabel productPriceLabel = new JLabel("Price:");
        JTextField productPriceField = new JTextField(10);
        JLabel productQuantityLabel = new JLabel("Quantity:");
        JTextField productQuantityField = new JTextField(10);
        JLabel unitLabel = new JLabel("Unit:");
        JComboBox<String> unitComboBox = new JComboBox<>(new String[]{"grams", "kilograms", "pieces", "dozens"});

        JButton addButton = new JButton("Add Product");
        JButton removeButton = new JButton("Remove Product");

        topPanel.add(productNameLabel);
        topPanel.add(productNameField);
        topPanel.add(productPriceLabel);
        topPanel.add(productPriceField);
        topPanel.add(productQuantityLabel);
        topPanel.add(productQuantityField);
        topPanel.add(unitLabel);
        topPanel.add(unitComboBox);
        topPanel.add(addButton);
        topPanel.add(removeButton);

        // Table Panel for Viewing Products
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        JLabel productListLabel = new JLabel("Inventory List");
        productListLabel.setFont(new Font("Arial", Font.BOLD, 16));

        tableModel = new DefaultTableModel(new String[]{"Product Name", "Price", "Quantity", "Unit"}, 0);
        inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        tablePanel.add(productListLabel, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);

        frame.setVisible(true);

        // Action for Add Product Button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = productNameField.getText();
                double price = Double.parseDouble(productPriceField.getText());
                double quantity = Double.parseDouble(productQuantityField.getText());
                String unit = (String) unitComboBox.getSelectedItem();

                // Create and add new product to inventory
                Product2 newProduct2 = new Product2(name, price, quantity, unit);
                inventory.add(newProduct2);
                refreshTable();
                JOptionPane.showMessageDialog(frame, "Product added to inventory!");
            }
        });

        // Action for Remove Product Button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow != -1) {
                    inventory.remove(selectedRow);
                    refreshTable();
                    JOptionPane.showMessageDialog(frame, "Product removed from inventory!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a product to remove.");
                }
            }
        });

        // Initialize Table with data from inventory
        refreshTable();
    }

    // Refresh the table to display all current products in inventory
    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing rows
        for (Product2 productss : inventory) {
            Object[] rowData = {productss.getName(), "₹" + productss.getPrice(), productss.getQuantity(), productss.getUnit()};
            tableModel.addRow(rowData);
        }
    }
}
