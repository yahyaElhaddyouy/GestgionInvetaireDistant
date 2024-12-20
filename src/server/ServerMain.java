package server;

import server.dao.ProductDAO;
import server.dao.UserDAO;
import server.services.ProductService;
import server.services.UserService;
import shared.rmi.RemoteImplementation;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.DriverManager;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Connexion à la base de données
            String url = "jdbc:mysql://localhost:3306/gestioninventairedb";
            String user = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!");

            // Initialisation des DAO et services
            ProductDAO productDAO = new ProductDAO(connection);
            UserDAO userDAO = new UserDAO(connection);
            ProductService productService = new ProductService(productDAO);
            UserService userService = new UserService(userDAO);

            // Configuration RMI
            RemoteImplementation remoteImplementation = new RemoteImplementation(userService, productService);
            LocateRegistry.createRegistry(1099); // Démarrage du registre RMI
            Naming.rebind("rmi://localhost:1099/ProductService", remoteImplementation);

            System.out.println("Server is running and ready for client connections.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
