package shared.rmi;

import shared.models.Product;
import shared.models.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteInterface extends Remote {
    User authenticate(String username, String password) throws RemoteException;
    
    List<Product> getAllProducts() throws RemoteException;
    void addProduct(Product product) throws RemoteException;
    void updateProduct(Product product) throws RemoteException;
    void deleteProduct(int productId) throws RemoteException;

    List<Product> searchProductsByName(String name) throws RemoteException;
}
