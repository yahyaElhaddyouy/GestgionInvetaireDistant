USE Database gestioninventairedb

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(1, 'yahya', '159357', 'admin'),
(2, 'hanae', '2024', 'employee');




INSERT INTO `produits` (`id`, `nom`, `description`, `prix`, `quantite`) VALUES
(1, 'Laptop HP Pavilion', 'Ordinateur portable 15 pouces avec Intel i5', 750, 15),
(2, 'Souris Logitech MX Master 3', 'Souris sans fil ergonomique', 199.99, 50),
(3, 'Clavier mécanique Razer BlackWidow', 'Clavier gaming avec rétroéclairage RGB', 129.99, 20),
(4, 'Écran Dell UltraSharp', 'Moniteur 27 pouces 4K UHD', 349, 10),
(5, 'Casque Bose QC35 II', 'Casque audio Bluetooth avec réduction de bruit', 299, 8),
(6, 'Smartphone Samsung Galaxy S21', 'Téléphone avec écran AMOLED et 128Go de stockage', 999, 5),
(7, 'Disque Dur Seagate 2To', 'Disque dur externe USB 3.0', 79.99, 25),
(8, 'Imprimante Epson EcoTank', 'Imprimante jet d\'encre avec réservoirs rechargeables', 199.99, 12),
(9, 'Batterie externe Anker 20000mAh', 'Chargeur portable avec port USB-C', 49.99, 30),
(10, 'Tablette Apple iPad 10.2', 'Tablette avec 64Go de stockage et écran Retina', 399, 7),
(18, 'hp', 'laptop', 1654, 55);