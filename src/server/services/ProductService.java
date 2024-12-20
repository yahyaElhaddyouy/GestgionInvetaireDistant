package server.services;

import server.dao.ProductDAO;
import shared.models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        productDAO.deleteProduct(productId);
    }

    public List<Product> searchProductsByName(String name) throws Exception {
        return productDAO.searchProductsByName(name);
    }

}
