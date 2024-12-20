# Gestion des Produits avec Java RMI

## Prérequis
- Java 8 ou supérieur
- MySQL 5.7 ou supérieur
- Maven (optionnel)

## Configuration de la Base de Données
1. Importez le fichier `resources/Schema.sql` et `resources/Data.sql` dans votre base de données MySQL.
2. Modifiez les informations de connexion dans `ServerMain.java` (URL, utilisateur, mot de passe).

## Exécution
1. Lancez le serveur :
   ```bash
   java -cp bin server.ServerMain

2. Lancez l'interface graphique :

```bash
   java -cp bin client.ClientMain
