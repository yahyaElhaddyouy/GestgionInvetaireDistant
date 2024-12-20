//package client;
//
//import client.controllers.ProductController;
//import shared.models.Product;
//import shared.rmi.RemoteInterface;
//
//import java.rmi.Naming;
//import java.util.List;
//
//public class ClientMain {
//    public static void main(String[] args) {
//        try {
//            // Connexion au service RMI
//            RemoteInterface remote = (RemoteInterface) Naming.lookup("rmi://localhost:1099/ProductService");
//            ProductController productController = new ProductController(remote);
//
//            // Tester les fonctionnalités
//            List<Product> products = productController.getAllProducts();
//            System.out.println("Liste des produits disponibles :");
//            for (Product product : products) {
//                System.out.println(product.getName() + " - " + product.getPrice());
//            }
//
//            // Ajouter un produit
////            Product newProduct = new Product(0, "Produit C", "Catégorie 3", 15, 199.99);
////            productController.addProduct(newProduct);
////            System.out.println("Produit ajouté avec succès.");
//            
////            productController.deleteProduct(12);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


package client;

import client.controllers.UserController;
import client.ui.LoginUI;
import shared.rmi.RemoteInterface;

import java.rmi.Naming;

public class ClientMain {
    public static void main(String[] args) {
        try {
//            RemoteInterface remote = (RemoteInterface) Naming.lookup("rmi://localhost:1099/ProductService");
//            UserController userController = new UserController(remote);
        	
        	 String serverAddress = "rmi://localhost:1099/ProductService";

             // Initialisation du UserController avec l'adresse du serveur
             UserController userController = new UserController(serverAddress);


            LoginUI loginUI = new LoginUI(userController);
            loginUI.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

