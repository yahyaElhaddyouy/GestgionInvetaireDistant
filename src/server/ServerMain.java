package server;

import server.dao.ProductDAO;
import server.dao.UserDAO;
import server.services.ProductService;
import server.services.UserService;
import shared.rmi.RemoteImplementation;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;

public class ServerMain {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            // Connexion à la base de données
            String url = "jdbc:mysql://localhost:3306/gestioninventairedb";
            String user = "root";
            String password = "";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!");

            // Initialisation des DAO et services
            ProductDAO productDAO = new ProductDAO(connection);
            UserDAO userDAO = new UserDAO(connection);
            ProductService productService = new ProductService(productDAO);
            UserService userService = new UserService(userDAO);

            // Configuration RMI
            RemoteImplementation remoteImplementation = new RemoteImplementation(userService, productService);
            Registry registry;

            try {
                // Essayer de récupérer le registre RMI
                registry = LocateRegistry.getRegistry(1099);
                // Vérifier si le registre est déjà en cours d'exécution
                registry.list(); // Cela lève une exception si le registre n'est pas trouvé
                System.out.println("RMI Registry is already running.");
            } catch (Exception e) {
                // Si le registre n'est pas trouvé, le créer
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("RMI Registry created.");
            }

            // Lier l'implémentation distante
            try {
                Naming.rebind("rmi://localhost:1099/ProductService", remoteImplementation);
                System.out.println("Remote implementation bound to RMI Registry.");
            } catch (Exception e) {
                System.err.println("Failed to bind remote implementation: " + e.getMessage());
                // Fermer la connexion à la base de données ou effectuer d'autres nettoyages si nécessaire
                closeConnection();
                System.exit(1); // Quitter l'application avec une erreur
            }

            // Ajouter un hook de shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down server...");
                closeConnection();
                System.out.println("Server shut down.");
            }));

            System.out.println("Server is running and ready for client connections.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (Exception e) {
                System.err.println("Failed to close database connection: " + e.getMessage());
            }
        }
    }
}