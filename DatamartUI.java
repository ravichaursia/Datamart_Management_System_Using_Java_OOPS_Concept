import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DatamartUI {
    private JFrame frame;
    private JTextField productNameField;
    private JTextField productPriceField;
    private JTextField productQuantityField;
    private JComboBox<String> unitComboBox;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private List<Product> products;
    private List<Product2> productss;
    private final String FILE_NAME = "products.dat"; // File to save products
    private final String USER_FILE = "users.dat"; // File to save users

    public DatamartUI() {
        products = loadProducts(); // Load products from file when the app starts
    }

    // Main method to start the application
    public static void main(String[] args) {
        new DatamartUI().showLoginWindow();  // Show the login window first
    }

    // Show login window

    public void showLoginWindow() {
        // Create the login frame
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 350);  // Increased size to fit the new button
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridBagLayout());  // Using GridBagLayout to better position components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Adding padding between components

        // Set the window's background to a gradient
        loginFrame.getContentPane().setBackground(new Color(255, 204, 0)); // A vibrant yellow background

        // Set a border around the window with rounded corners (using a custom border)
        loginFrame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(255, 128, 0), 5, true));

        // Logo with subscript: "DATAMART" and "Powered by Java"
        JLabel logoLabel = new JLabel("<html><font color='blue' size='20'><b>DATAMART</b></font><br><font color='green' size='12'>Powered by Java</font></html>");
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0; // Position in first column
        gbc.gridy = 0; // Position in first row
        gbc.gridwidth = 2;  // Span across 2 columns
        gbc.anchor = GridBagConstraints.CENTER;  // Center the label
        loginFrame.add(logoLabel, gbc);

        // Welcome Label: Smaller, colorful
        JLabel welcomeLabel = new JLabel("Welcome!!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));  // Smaller font size
        welcomeLabel.setForeground(Color.RED);  // Color set to red
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  // Span across 2 columns
        gbc.anchor = GridBagConstraints.CENTER;  // Center the label
        loginFrame.add(welcomeLabel, gbc);

        // Username and Password Labels
        JLabel usernameLabel = new JLabel("Enter Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Reset to span 1 column
        gbc.anchor = GridBagConstraints.EAST;  // Align to the east (right)
        usernameLabel.setForeground(Color.BLUE);  // Color set to blue for labels
        loginFrame.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);  // Make the text field smaller
        usernameField.setBackground(new Color(255, 255, 204));  // Light yellow background
        usernameField.setForeground(Color.DARK_GRAY);  // Dark text
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));  // Green border for text field
        gbc.gridx = 1; // Position in the second column
        gbc.anchor = GridBagConstraints.WEST;  // Align to the west (left)
        loginFrame.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Enter Password:");
        gbc.gridx = 0;  // Reset to first column
        gbc.gridy = 3;  // Move to next row
        gbc.anchor = GridBagConstraints.EAST;  // Align to the east (right)
        passwordLabel.setForeground(Color.BLUE);  // Color set to blue for labels
        loginFrame.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);  // Make the password field smaller
        passwordField.setBackground(new Color(255, 255, 204));  // Light yellow background
        passwordField.setForeground(Color.DARK_GRAY);  // Dark text
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));  // Green border for password field
        gbc.gridx = 1; // Position in the second column
        gbc.anchor = GridBagConstraints.WEST;  // Align to the west (left)
        loginFrame.add(passwordField, gbc);

        // Login and Register buttons with colorful effects
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 153, 0));  // Green button
        loginButton.setForeground(Color.WHITE);  // White text
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));  // Bold font for button
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));  // White border for button
        gbc.gridx = 0;  // Position in first column
        gbc.gridy = 4;  // Move to the next row
        gbc.gridwidth = 1;  // Span only one column
        gbc.anchor = GridBagConstraints.CENTER;  // Center the button
        loginFrame.add(loginButton, gbc);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(255, 140, 0));  // Orange button
        registerButton.setForeground(Color.WHITE);  // White text
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));  // Bold font for button
        registerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));  // White border for button
        gbc.gridx = 1;  // Position in the second column
        loginFrame.add(registerButton, gbc);

        // New Inventory Details button
        JButton inventoryButton = new JButton("Inventory Details");
        inventoryButton.setBackground(new Color(255, 99, 71));  // Tomato red button
        inventoryButton.setForeground(Color.WHITE);  // White text
        inventoryButton.setFont(new Font("Arial", Font.BOLD, 14));  // Bold font for button
        inventoryButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));  // White border for button
        gbc.gridx = 0;  // Position in first column
        gbc.gridy = 5;  // Move to the next row (below Register button)
        gbc.gridwidth = 2;  // Span across 2 columns
        gbc.anchor = GridBagConstraints.CENTER;  // Center the button
        loginFrame.add(inventoryButton, gbc);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(username, password)) {
                    loginFrame.setVisible(false);
                    showDatamartUI();  // Show the main product management UI
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password.");
                }
            }
        });

        // Action listener for the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (!username.isEmpty() && !password.isEmpty()) {
                    // Register new user
                    User newUser = new User(username, password);
                    addUser(newUser);
                    JOptionPane.showMessageDialog(loginFrame, "Registration successful!");
                    loginFrame.setVisible(false);
                    showDatamartUI();  // Show the main product management UI
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Please enter a username and password.");
                }
            }
        });

        // Action listener for the inventory details button
        // Action listener for the inventory details button
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pass the list of products to the Inventory class
                new Inventory(productss);  // This will open the Inventory management window
            }
        });


        // Show the login frame
        loginFrame.setVisible(true);
    }



    // Method to validate login credentials
    private boolean validateLogin(String username, String password) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;  // Valid login credentials
            }
        }
        return false;  // Invalid login credentials
    }

    // Method to load users from file
    private List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            users = (List<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle errors if the file doesn't exist or is empty
        }
        return users;
    }

    // Method to save users to file
    private void saveUsers(List<User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new user
    private void addUser(User newUser) {
        List<User> users = loadUsers();
        users.add(newUser);
        saveUsers(users);
    }

    // Show DatamartUI window (product management UI)
    private void showDatamartUI() {
        frame = new JFrame("Datamart Management System");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set background color of the frame
        frame.getContentPane().setBackground(new Color(255, 228, 196)); // Light Peach color

        // Top Panel: Add Product
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(new Color(255, 218, 185)); // Light Salmon color

        JLabel productNameLabel = new JLabel("Product Name:");
        productNameField = new JTextField(15);
        JLabel productPriceLabel = new JLabel("Product Price:");
        productPriceField = new JTextField(15);
        JLabel productQuantityLabel = new JLabel("Quantity (kg, pieces, etc.):");
        productQuantityField = new JTextField(10);

        JLabel unitLabel = new JLabel("Unit:");
        unitComboBox = new JComboBox<>(new String[] {"grams", "kilograms", "pieces", "dozens"});

        JButton addProductButton = new JButton("Add Product");
        JButton deleteProductButton = new JButton("Delete Entry");

        // Top panel layout
        topPanel.add(productNameLabel);
        topPanel.add(productNameField);
        topPanel.add(productPriceLabel);
        topPanel.add(productPriceField);
        topPanel.add(productQuantityLabel);
        topPanel.add(productQuantityField);
        topPanel.add(unitLabel);
        topPanel.add(unitComboBox);
        topPanel.add(addProductButton);
        topPanel.add(deleteProductButton);

        // Bottom Panel: Product Table
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(255, 228, 196)); // Light Peach color

        JLabel productListLabel = new JLabel("Product List");
        productListLabel.setFont(new Font("Arial", Font.BOLD, 16));
        productListLabel.setForeground(new Color(0, 51, 102)); // Dark Blue color

        // Table setup
        String[] columnNames = {"Select", "Product Name", "Price", "Quantity", "Unit", "Total"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);

        // Adding checkbox column (for selection)
        productTable.getColumnModel().getColumn(0).setCellEditor(productTable.getDefaultEditor(Boolean.class));
        productTable.getColumnModel().getColumn(0).setCellRenderer(productTable.getDefaultRenderer(Boolean.class));

        // Customizing table appearance
        productTable.setBackground(new Color(255, 250, 240)); // Very light beige
        productTable.setSelectionBackground(new Color(173, 216, 230)); // Light Blue selection color
        productTable.setRowHeight(25);

        // Alternating row colors
        productTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    comp.setBackground(new Color(255, 235, 205)); // Light Orange for even rows
                } else {
                    comp.setBackground(Color.white); // White for odd rows
                }
                return comp;
            }
        });

        // Setting custom column headers
        productTable.getTableHeader().setBackground(new Color(102, 205, 170)); // Light Green color for header

        JScrollPane scrollPane = new JScrollPane(productTable);
        bottomPanel.add(productListLabel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Add Panels to Frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);

        // Action listener for Add Product button
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = productNameField.getText();
                double price = Double.parseDouble(productPriceField.getText());
                double quantity = Double.parseDouble(productQuantityField.getText());
                String unit = (String) unitComboBox.getSelectedItem();

                // Create new product and add to the list
                Product newProduct = new Product(name, price, quantity, unit);
                products.add(newProduct);
                saveProducts();
                refreshProductTable();
                JOptionPane.showMessageDialog(frame, "Product added successfully!");
            }
        });

        // Action listener for Delete Product button
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this product?", "Delete Product", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        products.remove(selectedRow);
                        saveProducts();
                        refreshProductTable();
                    }
                }
            }
        });

        refreshProductTable(); // Refresh product table
    }

    // Method to load products from the file
    private List<Product> loadProducts() {
        List<Product> loadedProducts = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            loadedProducts = (List<Product>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle errors if the file doesn't exist or is empty
        }
        return loadedProducts;
    }

    // Method to save products to file
    private void saveProducts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to refresh the product table
    private void refreshProductTable() {
        tableModel.setRowCount(0); // Clear existing table
        for (Product product : products) {
            Object[] rowData = {
                    false,  // Checkbox column for selection
                    product.getName(),
                    "₹" + product.getPrice(),
                    product.getQuantity(),
                    product.getUnit(),
                    "₹" + (product.getPrice() * product.getQuantity())
            };
            tableModel.addRow(rowData);
        }
    }
}
