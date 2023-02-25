use restaurant;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'Pass123';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.role TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.user TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.adresse TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.cat_produit TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.client TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.commande TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.detail_commande TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.etablissement TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.produit TO 'admin'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.user_etablis TO 'admin'@'localhost';
SHOW GRANTS FOR 'admin'@'localhost';

CREATE USER 'manager'@'localhost' IDENTIFIED BY 'Pass123';
GRANT SELECT, INSERT ON restaurant.role TO 'manager'@'localhost';
GRANT SELECT, INSERT, UPDATE ON restaurant.user TO 'manager'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON restaurant.adresse TO 'manager'@'localhost';
GRANT SELECT, INSERT, UPDATE ON restaurant.cat_produit TO 'manager'@'localhost';
GRANT SELECT, INSERT, UPDATE ON restaurant.client TO 'manager'@'localhost';
GRANT SELECT, INSERT, UPDATE ON restaurant.commande TO 'manager'@'localhost';
GRANT SELECT, INSERT, UPDATE ON restaurant.detail_commande TO 'manager'@'localhost';
GRANT SELECT, INSERT, UPDATE ON restaurant.etablissement TO 'manager'@'localhost';
GRANT SELECT, INSERT, UPDATE ON restaurant.produit TO 'manager'@'localhost';
GRANT SELECT, INSERT, UPDATE ON restaurant.user_etablis TO 'manager'@'localhost';

GRANT DELETE ON restaurant.commande TO 'manager'@'localhost';
GRANT DELETE ON restaurant.detail_commande TO 'manager'@'localhost';
SHOW GRANTS FOR 'manager'@'localhost';
