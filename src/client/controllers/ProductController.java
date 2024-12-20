
package client.controllers;

import shared.models.Product;
import shared.rmi.RemoteInterface;

import java.util.List;

public class ProductController {
    private RemoteInterface remote;

    public ProductController(RemoteInterface remote) {
        this.remote = remote;
    }

    public List<Product> getAllProducts() throws Exception {
        return remote.getAllProducts();
    }

    public void addProduct(Product product) throws Exception {
        remote.addProduct(product);
    }

    public void updateProduct(Product product) throws Exception {
        remote.updateProduct(product);
    }

    public void deleteProduct(int productId) throws Exception {
        remote.deleteProduct(productId);
    }

    public List<Product> searchProductsByName(String name) throws Exception {
        return remote.searchProductsByName(name);
    }
}
