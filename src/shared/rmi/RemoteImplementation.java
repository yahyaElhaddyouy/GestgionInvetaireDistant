package shared.rmi;

import server.services.ProductService;
import server.services.UserService;
import shared.models.Product;
import shared.models.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {
    private UserService userService;
    private ProductService productService;

    public RemoteImplementation(UserService userService, ProductService productService) throws RemoteException {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public User authenticate(String username, String password) throws RemoteException {
        try {
            return userService.authenticate(username, password);
        } catch (Exception e) {
            throw new RemoteException("Authentication failed", e);
        }
    }

    @Override
    public List<Product> getAllProducts() throws RemoteException {
        try {
            return productService.getAllProducts();
        } catch (Exception e) {
            throw new RemoteException("Failed to fetch products", e);
        }
    }

    @Override
    public void addProduct(Product product) throws RemoteException {
        try {
            productService.addProduct(product);
        } catch (Exception e) {
            throw new RemoteException("Failed to add product", e);
        }
    }

    @Override
    public void updateProduct(Product product) throws RemoteException {
        try {
            productService.updateProduct(product);
        } catch (Exception e) {
            throw new RemoteException("Failed to update product", e);
        }
    }

    @Override
    public void deleteProduct(int productId) throws RemoteException {
        try {
            productService.deleteProduct(productId);
        } catch (Exception e) {
            throw new RemoteException("Failed to delete product", e);
        }
    }
    
    @Override
    public List<Product> searchProductsByName(String name) throws RemoteException {
        try {
            return productService.searchProductsByName(name);
        } catch (Exception e) {
            throw new RemoteException("Error searching products", e);
        }
    }
}
