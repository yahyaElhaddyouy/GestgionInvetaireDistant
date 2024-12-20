//package client.ui;
//
//import client.controllers.ProductController;
//import shared.models.Product;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class ProductUI {
//    private JFrame frame;
//    private ProductController productController;
//
//    public ProductUI(ProductController productController) {
//        if (productController == null) {
//            throw new IllegalArgumentException("ProductController cannot be null");
//        }
//
//        this.productController = productController;
//
//        frame = new JFrame("Product Management");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 400);
//        frame.setLayout(null);
//
//        JButton listButton = new JButton("List Products");
//        listButton.setBounds(50, 50, 150, 30);
//        frame.add(listButton);
//
//        listButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                handleListProducts();
//            }
//        });
//
//        JButton addButton = new JButton("Add Product");
//        addButton.setBounds(250, 50, 150, 30);
//        frame.add(addButton);
//
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                handleAddProduct();
//            }
//        });
//    }
//
//    private void handleListProducts() {
//        try {
//            List<Product> products = productController.getAllProducts();
//            StringBuilder productList = new StringBuilder("Products:\n");
//            for (Product product : products) {
//                productList.append(product.getName())
//                        .append(" - $").append(product.getPrice()).append("\n");
//            }
//            JOptionPane.showMessageDialog(frame, productList.toString());
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
//        }
//    }
//
//    private void handleAddProduct() {
//        String name = JOptionPane.showInputDialog("Enter product name:");
//        String category = JOptionPane.showInputDialog("Enter product category:");
//        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter product quantity:"));
//        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:"));
//
//        try {
//            Product product = new Product(0, name, category, quantity, price);
//            productController.addProduct(product);
//            JOptionPane.showMessageDialog(frame, "Product added successfully!");
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
//        }
//    }
//
//    public void show() {
//        frame.setVisible(true);
//    }
//}




package client.ui;

import client.controllers.ProductController;
import shared.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductUI {
    private JFrame frame;
    private ProductController productController;
    private JTable productTable;
    private DefaultTableModel tableModel;

    public ProductUI(ProductController productController) {
        if (productController == null) {
            throw new IllegalArgumentException("ProductController cannot be null");
        }

        this.productController = productController;

        frame = new JFrame("Product Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(null);

        // Create table model and JTable
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Quantity", "Price"}, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(50, 100, 500, 200);
        frame.add(scrollPane);

        JButton listButton = new JButton("List Products");
        listButton.setBounds(50, 50, 150, 30);
        frame.add(listButton);

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleListProducts();
            }
        });

        JButton addButton = new JButton("Add Product");
        addButton.setBounds(250, 50, 150, 30);
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddProduct();
            }
        });
        
        JButton updateButton = new JButton("Update Product");
        updateButton.setBounds(450, 50, 150, 30);
        frame.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateProduct();
            }
        });

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(450, 310, 150, 30);
        frame.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteProduct();
            }
        });
        
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(50, 310, 150, 30);
        frame.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearchProduct();
            }
        });

    }
    
    

    private void handleListProducts() {
        try {
            List<Product> products = productController.getAllProducts();
            // Clear existing rows
            tableModel.setRowCount(0);
            // Add products to the table model
            for (Product product : products) {
                tableModel.addRow(new Object[]{
                        product.getId(), // Assuming Product has a getId() method
                        product.getName(),
                        product.getCategory(),
                        product.getQuantity(),
                        product.getPrice()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    private void handleAddProduct() {
        // Create a dialog for adding a product
        JDialog addProductDialog = new JDialog(frame, "Add Product", true);
        addProductDialog.setSize(300, 300);
        addProductDialog.setLayout(null);

        // Input fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        addProductDialog.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 150, 25);
        addProductDialog.add(nameField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(20, 60, 80, 25);
        addProductDialog.add(categoryLabel);

        JTextField categoryField = new JTextField();
        categoryField.setBounds(100, 60, 150, 25);
        addProductDialog.add(categoryField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 100, 80, 25);
        addProductDialog.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(100, 100, 150, 25);
        addProductDialog.add(quantityField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 140, 80, 25);
        addProductDialog.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(100, 140, 150, 25);
        addProductDialog.add(priceField);

        // Add button to submit the product
        JButton submitButton = new JButton("Add Product");
        submitButton.setBounds(100, 200, 120, 30);
        addProductDialog.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String category = categoryField.getText();
                    int quantity = Integer.parseInt(quantityField.getText());
                    double price = Double.parseDouble(priceField.getText());

                    Product product = new Product(0, name, category, quantity, price);
                    productController.addProduct(product);
                    JOptionPane.showMessageDialog(frame, "Product added successfully!");
                    addProductDialog.dispose(); // Close the dialog
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(addProductDialog, "Error: " + ex.getMessage());
                }
            }
        });

        addProductDialog.add(submitButton);
        addProductDialog.setLocationRelativeTo(frame); // Center the dialog
        addProductDialog.setVisible(true); // Show the dialog
    }
    
    
    private void handleUpdateProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a product to update.");
            return;
        }

        // Get the selected product details
        int productId = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String category = (String) tableModel.getValueAt(selectedRow, 2);
        int quantity = (int) tableModel.getValueAt(selectedRow, 3);
        double price = (double) tableModel.getValueAt(selectedRow, 4);

        // Create a dialog for updating the product
        JDialog updateProductDialog = new JDialog(frame, "Update Product", true);
        updateProductDialog.setSize(300, 300);
        updateProductDialog.setLayout(null);

        // Input fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        updateProductDialog.add(nameLabel);

        JTextField nameField = new JTextField(name);
        nameField.setBounds(100, 20, 150, 25);
        updateProductDialog.add(nameField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(20, 60, 80, 25);
        updateProductDialog.add(categoryLabel);

        JTextField categoryField = new JTextField(category);
        categoryField.setBounds(100, 60, 150, 25);
        updateProductDialog.add(categoryField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 100, 80, 25);
        updateProductDialog.add(quantityLabel);

        JTextField quantityField = new JTextField(String.valueOf(quantity));
        quantityField.setBounds(100, 100, 150, 25);
        updateProductDialog.add(quantityField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 140, 80, 25);
        updateProductDialog.add(priceLabel);

        JTextField priceField = new JTextField(String.valueOf(price));
        priceField.setBounds(100, 140, 150, 25);
        updateProductDialog.add(priceField);

        // Add button to submit the updated product
        JButton updateButton = new JButton("Update Product");
        updateButton.setBounds(100, 200, 120, 30);
        updateProductDialog.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String updatedName = nameField.getText();
                    String updatedCategory = categoryField.getText();
                    int updatedQuantity = Integer.parseInt(quantityField.getText());
                    double updatedPrice = Double.parseDouble(priceField.getText());

                    Product updatedProduct = new Product(productId, updatedName, updatedCategory, updatedQuantity, updatedPrice);
                    productController.updateProduct(updatedProduct);
                    JOptionPane.showMessageDialog(frame, "Product updated successfully!");
                    updateProductDialog.dispose(); // Close the dialog
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(updateProductDialog, "Error: " + ex.getMessage());
                }
 }
        });

        updateProductDialog.add(updateButton);
        updateProductDialog.setLocationRelativeTo(frame); // Center the dialog
        updateProductDialog.setVisible(true); // Show the dialog
    }

    private void handleDeleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a product to delete.");
            return;
        }

        int productId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this product?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                productController.deleteProduct(productId);
                JOptionPane.showMessageDialog(frame, "Product deleted successfully!");
                handleListProducts(); // Refresh the product list
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        }
    }
    
 // Method to handle search

    private void handleSearchProduct() {
        String searchTerm = JOptionPane.showInputDialog(frame, "Enter product name to search:");
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            try {
                List<Product> products = productController.searchProductsByName(searchTerm);
                tableModel.setRowCount(0); // Clear existing rows
                for (Product product : products) {
                    tableModel.addRow(new Object[]{
                            product.getId(),
                            product.getName(),
                            product.getCategory(),
                            product.getQuantity(),
                            product.getPrice()
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a valid search term.");
        }
    }



    public void show() {
        frame.setVisible(true);
    }
}