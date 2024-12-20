package server.dao;

import shared.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM produits";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            products.add(new Product(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("description"),
                rs.getInt("quantite"),
                rs.getDouble("prix")
            ));
        }
        return products;
    }

    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO produits (nom, description, quantite, prix) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getCategory());
        stmt.setInt(3, product.getQuantity());
        stmt.setDouble(4, product.getPrice());
        stmt.executeUpdate();
    }

    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE produits SET nom = ?, description = ?, quantite = ?, prix = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getCategory());
        stmt.setInt(3, product.getQuantity());
        stmt.setDouble(4, product.getPrice());
        stmt.setInt(5, product.getId());
        stmt.executeUpdate();
    }

    public void deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM produits WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, productId);
        stmt.executeUpdate();
    }

    public List<Product> searchProductsByName(String name) throws SQLException {
        String query = "SELECT * FROM produits WHERE nom LIKE ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, "%" + name + "%");
        ResultSet rs = stmt.executeQuery();

        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getInt("quantite"),
                    rs.getDouble("prix")
            ));
        }
        return products;
    }
}
