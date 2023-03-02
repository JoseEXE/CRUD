-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 23 fév. 2023 à 16:16
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `restaurant`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `id` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `rue` varchar(100) DEFAULT NULL,
  `cod_postal` varchar(45) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `complement` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id`, `id_client`, `rue`, `cod_postal`, `ville`, `complement`) VALUES
(1, 1, '68 Rue Georges et Mai Politzer', '75012', 'Paris', 'coté police'),
(2, 3, 'rue du chemin vert', '75011', 'paris', ''),
(3, 3, 'rue baldor', '78220', 'ivry', ''),
(4, 4, '54 rue de Berry', '75008', 'Paris', 'interphone 0012'),
(5, 2, '112 avenue des Lilas', '74520', 'Bourg-la-reine', ''),
(6, 1, '102 rue du zboub', '94100', 'montreuil', 'csdfghj'),
(8, 2, 'N rue', '45678', 'Ville', 'Comp'),
(9, 1, 'Rue', '23454', 'Neww', 'Ville'),
(10, 5, '102 rue du fiacre', '93100', 'Saint denis', 'interphone0210'),
(11, 6, '55 Avue due paris', '77500', 'Crosnes', 'lot fanfinette'),
(12, 7, '45bis impasse des glands', '45220', 'glassy', ''),
(13, 8, 'rue des collines', '75020', 'paris', ''),
(14, 5, 'Rue', '56789', 'Test', 'Comp'),
(15, 9, 'Rue', '45665', 'Ville', 'Comp');

-- --------------------------------------------------------

--
-- Structure de la table `cat_produit`
--

CREATE TABLE `cat_produit` (
  `id` int(11) NOT NULL,
  `nom` varchar(60) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `cat_produit`
--

INSERT INTO `cat_produit` (`id`, `nom`, `description`) VALUES
(1, 'Plat', 'les sushis, sashimi...'),
(2, 'Boisson', 'Coca, orangina, bieres, cocktails sans alcool'),
(3, 'Menu', 'composition de plats, desserts et boissons'),
(4, 'Dessert', 'lychee, glace...');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `numtel` varchar(10) DEFAULT NULL,
  `statut` varchar(45) DEFAULT 'Actif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `numtel`, `statut`) VALUES
(1, 'Moldoveanu', 'Laura', '0419847707', 'Actif'),
(2, 'Cisse', 'Aly', '0419847707', 'Actif'),
(3, 'Soussama', 'Bafode', '0645326598', 'Actif'),
(4, 'Poupard', 'Simon', '0600125478', 'Actif'),
(5, 'Camarra', 'Moussa', '0645213585', 'Actif'),
(6, 'Dimitri', 'Le Boubennec', '0678956396', 'Actif'),
(7, 'Bouveur', 'Charles', '0680003258', 'Actif'),
(8, 'Boukedamii', 'Nassera', '0412964789', 'Actif'),
(9, 'MARC', 'test', '0987780945', 'Actif');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `commentaire` varchar(150) DEFAULT NULL,
  `totalHt` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `type_paiement` varchar(20) DEFAULT NULL,
  `etat` varchar(20) NOT NULL DEFAULT 'en cours...',
  `date_comm` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `id_client`, `id_user`, `commentaire`, `totalHt`, `total`, `type_paiement`, `etat`, `date_comm`) VALUES
(63, 4, 1, NULL, 0, 23, 'Espèce', 'Expédiée', '2023-02-17 18:55:49'),
(64, 4, 1, NULL, 0, 36, 'Espèce', 'Expédiée', '2023-02-17 19:01:46'),
(69, 1, 1, NULL, 0, 39, 'Carte', 'Expédiée', '2023-02-20 09:10:28'),
(75, 1, 1, NULL, 0, 15, 'Espèce', 'Expédiée', '2023-02-20 09:55:19'),
(76, 2, 1, NULL, 0, 21, 'Carte', 'Expédiée', '2023-02-20 11:20:25'),
(77, 2, 1, NULL, 0, 33, 'Carte', 'Expédiée', '2023-02-20 11:29:46'),
(78, 1, 1, NULL, 0, 31, 'Carte', 'Expédiée', '2023-02-20 11:34:59'),
(79, 2, 1, NULL, 0, 31, 'Carte', 'Expédiée', '2023-02-20 11:37:29'),
(88, 1, 1, NULL, 0, 30, 'Espèce', 'Expédiée', '2023-02-20 13:39:24'),
(91, 2, 1, NULL, 30, 30, 'Carte', 'Expédiée', '2023-02-20 14:21:10'),
(92, 2, 1, NULL, 15.12, 16, 'Espèce', 'Expédiée', '2023-02-20 14:23:24'),
(93, 3, 1, NULL, 25.515, 30, 'Carte', 'Expédiée', '2023-02-20 16:42:51'),
(94, 3, 1, NULL, 34.02, 48, 'Espèce', 'Expédiée', '2023-02-20 16:49:29'),
(95, 3, 1, NULL, 25.515, 27, 'Carte', 'Expédiée', '2023-02-20 16:56:29'),
(96, 2, 1, NULL, 26.46, 32, 'Espèce', 'Expédiée', '2023-02-20 16:58:43'),
(97, 1, 1, NULL, 25.515, 30, 'Espèce', 'Expédiée', '2023-02-20 17:00:45'),
(99, 1, 1, NULL, 29.295, 31, 'Carte', 'Expédiée', '2023-02-20 17:28:14'),
(100, 1, 1, NULL, 25.515, 27, 'Carte', 'Expédiée', '2023-02-21 09:36:30'),
(101, 1, 1, NULL, 29.295, 31, 'Espèce', 'Expédiée', '2023-02-21 09:45:56'),
(102, 1, 1, NULL, 25.515, 27, 'Carte', 'Expédiée', '2023-02-21 09:51:43'),
(103, 2, 1, NULL, 25.515, 27, 'Carte', 'Expédiée', '2023-02-21 09:58:38'),
(104, 2, 1, NULL, 25.515, 27, 'Carte', 'Expédiée', '2023-02-21 10:03:27'),
(105, 2, 1, NULL, 25.515, 27, 'Carte', 'Expédiée', '2023-02-21 10:09:31'),
(106, 2, 1, NULL, 25.515, 27, 'Carte', 'Expédiée', '2023-02-21 10:11:13'),
(107, 1, 1, NULL, 37.8, 40, 'Carte', 'Expédiée', '2023-02-21 10:17:48'),
(108, 1, 1, NULL, 29.295, 31, 'Carte', 'Expédiée', '2023-02-21 10:18:51'),
(109, 5, 1, NULL, 20.6955, 27.3, 'Espèce', 'Expédiée', '2023-02-21 12:24:31'),
(111, 6, 1, NULL, 28.4445, 45, 'Espèce', 'Validée', '2023-02-21 14:11:51'),
(112, 4, 1, NULL, 28.35, 30, 'Espèce', 'Validée', '2023-02-21 14:58:25'),
(116, 2, 3, NULL, 24.57, 62, 'Carte', 'Validée', '2023-02-22 10:49:47'),
(117, 4, 3, NULL, 35.91, 38, 'Espèce', 'Expédiée', '2023-02-22 10:55:58'),
(118, 2, 3, NULL, 13.23, 14, 'Carte', 'Validée', '2023-02-22 17:21:01'),
(119, 2, 1, NULL, 45.36, 48, 'Espèce', 'Validée', '2023-02-23 09:13:29'),
(120, 5, 1, NULL, 35.91, 38, 'Espèce', 'Validée', '2023-02-23 09:56:26'),
(121, 5, 1, NULL, NULL, NULL, NULL, 'en cours...', '2023-02-23 10:51:27'),
(122, 5, 4, NULL, 48.384, 51.2, 'Carte', 'Validée', '2023-02-23 12:12:58'),
(123, 9, 4, NULL, 19.845, 21, 'Espèce', 'Expédiée', '2023-02-23 12:20:00');

-- --------------------------------------------------------

--
-- Structure de la table `detail_commande`
--

CREATE TABLE `detail_commande` (
  `id` int(11) NOT NULL,
  `id_commande` int(11) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT 1,
  `prix_unitaire` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `detail_commande`
--

INSERT INTO `detail_commande` (`id`, `id_commande`, `id_produit`, `quantite`, `prix_unitaire`) VALUES
(89, 63, 4, 1, 3),
(90, 63, 3, 1, 12),
(91, 63, 5, 2, 4),
(92, 64, 2, 1, 12),
(93, 64, 5, 3, 4),
(94, 64, 3, 1, 12),
(105, 69, 2, 1, 12),
(106, 69, 3, 1, 12),
(107, 69, 4, 5, 3),
(111, 75, 2, 1, 12),
(112, 75, 4, 1, 3),
(113, 76, 2, 1, 12),
(114, 76, 4, 3, 3),
(115, 77, 3, 1, 12),
(116, 77, 4, 1, 3),
(117, 77, 4, 2, 3),
(118, 77, 2, 1, 12),
(119, 78, 4, 1, 3),
(120, 78, 2, 1, 12),
(121, 78, 3, 1, 12),
(122, 78, 5, 1, 4),
(123, 79, 3, 1, 12),
(124, 79, 3, 1, 12),
(125, 79, 4, 1, 3),
(126, 79, 5, 1, 4),
(143, 88, 2, 1, 12),
(144, 88, 4, 1, 3),
(145, 88, 3, 1, 12),
(146, 88, 4, 1, 3),
(153, 91, 2, 1, 12),
(154, 91, 3, 1, 12),
(155, 91, 4, 1, 3),
(156, 91, 4, 1, 3),
(157, 92, 3, 1, 12),
(158, 92, 5, 1, 4),
(159, 93, 2, 1, 12),
(160, 93, 4, 2, 3),
(161, 93, 3, 1, 12),
(162, 94, 3, 1, 12),
(163, 94, 2, 1, 12),
(164, 94, 3, 2, 12),
(165, 95, 3, 1, 12),
(166, 95, 4, 1, 3),
(167, 95, 2, 1, 12),
(168, 96, 2, 1, 12),
(169, 96, 3, 1, 12),
(170, 96, 5, 2, 4),
(171, 97, 2, 1, 12),
(172, 97, 3, 1, 12),
(173, 97, 4, 2, 3),
(174, 99, 2, 1, 12),
(175, 99, 4, 1, 3),
(176, 99, 3, 1, 12),
(177, 99, 5, 1, 4),
(178, 100, 2, 1, 12),
(179, 100, 3, 1, 12),
(180, 100, 4, 1, 3),
(181, 101, 4, 1, 3),
(182, 101, 3, 1, 12),
(183, 101, 2, 1, 12),
(184, 101, 5, 1, 4),
(185, 102, 2, 1, 12),
(186, 102, 3, 1, 12),
(187, 102, 4, 1, 3),
(188, 103, 3, 1, 12),
(189, 103, 4, 1, 3),
(190, 103, 2, 1, 12),
(191, 104, 3, 1, 12),
(192, 104, 4, 1, 3),
(193, 104, 2, 1, 12),
(194, 105, 2, 1, 12),
(195, 105, 3, 1, 12),
(196, 105, 4, 1, 3),
(197, 106, 4, 1, 3),
(198, 106, 3, 1, 12),
(199, 106, 2, 1, 12),
(200, 107, 2, 1, 12),
(201, 107, 3, 1, 12),
(202, 107, 5, 1, 4),
(203, 107, 3, 1, 12),
(205, 108, 2, 1, 12),
(206, 108, 3, 1, 12),
(207, 108, 4, 1, 3),
(208, 108, 5, 1, 4),
(209, 109, 6, 2, 10.9),
(211, 109, 10, 1, 5.5),
(212, 111, 8, 1, 15.2),
(213, 111, 7, 2, 14.9),
(214, 112, 3, 1, 12),
(215, 112, 4, 1, 3),
(216, 112, 4, 1, 3),
(217, 112, 2, 1, 12),
(218, 116, 2, 1, 12),
(219, 116, 3, 4, 12),
(220, 116, 13, 1, 2),
(222, 117, 2, 1, 12),
(223, 117, 13, 1, 2),
(224, 117, 3, 1, 12),
(225, 117, 2, 1, 12),
(226, 118, 3, 1, 12),
(227, 118, 13, 1, 2),
(228, 119, 2, 1, 12),
(229, 119, 3, 3, 12),
(230, 120, 2, 2, 12),
(231, 120, 3, 1, 12),
(232, 120, 13, 1, 2),
(233, 122, 2, 2, 12),
(234, 122, 3, 1, 12),
(235, 122, 8, 1, 15.2),
(236, 123, 13, 1, 2),
(237, 123, 3, 1, 12),
(238, 123, 5, 1, 4),
(239, 123, 12, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `etablissement`
--

CREATE TABLE `etablissement` (
  `id` int(11) NOT NULL,
  `nom` varchar(60) DEFAULT NULL,
  `siret` varchar(60) DEFAULT NULL,
  `rue` varchar(100) DEFAULT NULL,
  `cod_postal` varchar(45) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `etablissement`
--

INSERT INTO `etablissement` (`id`, `nom`, `siret`, `rue`, `cod_postal`, `ville`) VALUES
(1, 'ETE EDO - Paris 11E', '123456789087654', '14 RUE DE NICE', '75011', 'Paris');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `id_cat_produit` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `nom` varchar(60) DEFAULT NULL,
  `type_statut` varchar(45) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `prix` double NOT NULL,
  `date_mod` datetime DEFAULT NULL,
  `statut` varchar(45) DEFAULT 'Actif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `id_cat_produit`, `id_user`, `code`, `nom`, `type_statut`, `description`, `prix`, `date_mod`, `statut`) VALUES
(2, 1, 1, 'P01', 'Poke1 Tempura', 'Chaud', 'PokeBowl crevette', 12, '2023-02-11 11:11:08', 'Actif'),
(3, 1, 1, 'P02', 'Poke1 Saumon', 'Chaud', 'Bla', 12, '2023-02-11 11:11:04', 'Actif'),
(4, 2, 3, 'P02', 'Coca', 'Froid', 'Boisson gazeuse', 3.5, '2023-02-22 10:48:36', 'Actif'),
(5, 4, 1, 'D01', 'Coupe Lychee', 'Froid', 'Fruits en sirop', 4, '2023-02-21 15:26:13', 'Actif'),
(6, 3, 1, 'M4', 'Menu Brochette Cali', 'Chaud', '1Soupe, 1 Sallade,1 riz,\n8California\n3 brochette Poulet,boulette,\nboeuf fromage', 10.9, '2023-02-21 11:47:48', 'Actif'),
(7, 3, 1, 'M6', 'Menu Maki', 'Froid', 'soupe,1salade,6maki saumon,3sushi,\n9 sashimi assorti', 14.9, '2023-02-21 11:57:27', 'Actif'),
(8, 3, 1, 'M7', 'Menu Sashimi Saumaon', 'Chaud', '1soupe,1salade,8sashimi saumon,\n6sushi saumon', 15.2, '2023-02-21 12:01:38', 'Actif'),
(9, 3, 1, 'M1', 'Menu Brochette Gyoza', 'Chaud', '1soupe, 1salade, 1riz,\n2 brochettes poulet,\n2 boulettes poulet,\n4 gyoza', 9, '2023-02-21 12:13:58', 'Actif'),
(10, 2, 1, 'B6', 'cocktail 3 couleurs', 'Froid', 'sirop et bonbons colorés avec limonade', 5.5, '2023-02-21 12:17:50', 'Actif'),
(11, 2, 1, 'B5', 'Jus de coco', 'Froid', 'coco fraiche avec morceau', 4.5, '2023-02-21 12:21:24', 'Actif'),
(12, 4, 1, 'D3', 'Perle de coco', 'Chaud', '2 pièces', 3, '2023-02-21 15:27:21', 'Actif'),
(13, 1, 1, 'P10', 'Soupe Miso', 'Chaud', 'soupe avec algues et champignon', 2, '2023-02-21 15:32:01', 'Actif');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `nom` varchar(60) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `statut` varchar(45) DEFAULT 'Actif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `nom`, `description`, `statut`) VALUES
(1, 'Administrateur', 'description administration', 'Actif'),
(2, 'Vendeur', 'equipier polyvalent', 'Actif'),
(3, 'Cuisinier(e)', 'confection des plats chuds et froids', 'Actif'),
(4, 'Livreur', 'Employé qui livre les commandes (non uber eats)', 'Actif');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `id_role` int(11) DEFAULT NULL,
  `nom` varchar(60) DEFAULT NULL,
  `prenom` varchar(60) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `password` varchar(150) NOT NULL,
  `statut` varchar(45) DEFAULT 'Actif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `id_role`, `nom`, `prenom`, `email`, `url`, `password`, `statut`) VALUES
(1, 1, 'Ambroise', 'Moris', 'test@test.com', 'null', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257', 'Actif'),
(2, 1, 'ADMIN', 'Manajer', 'email@email.com', '', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257', 'Actif'),
(3, 2, 'AQUINO M', 'José', 'mail@mail.com', '', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257', 'Actif'),
(4, 2, 'Paul', 'Pierre', 'paulmail@gmail.com', '', '*7297C3E22DEB91303FC493303A8158AD4231F486', 'Actif');

--
-- Déclencheurs `user`
--
DELIMITER $$
CREATE TRIGGER `userEtablisment` AFTER INSERT ON `user` FOR EACH ROW BEGIN
    DECLARE variduser INTEGER;
    INSERT INTO user_etablis(id_etablissment, id_user) VALUES(1 , NEW.id);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `user_etablis`
--

CREATE TABLE `user_etablis` (
  `id` int(11) NOT NULL,
  `id_etablissment` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `user_etablis`
--

INSERT INTO `user_etablis` (`id`, `id_etablissment`, `id_user`) VALUES
(3, 1, 1),
(4, 1, 2),
(5, 1, 3),
(6, 1, 4);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_id_client_adress_idx` (`id_client`);

--
-- Index pour la table `cat_produit`
--
ALTER TABLE `cat_produit`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_id_client_comm_idx` (`id_client`),
  ADD KEY `fk_id_user_comm_idx` (`id_user`);

--
-- Index pour la table `detail_commande`
--
ALTER TABLE `detail_commande`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_id_commande_idx` (`id_commande`),
  ADD KEY `fk_id_produit_idx` (`id_produit`);

--
-- Index pour la table `etablissement`
--
ALTER TABLE `etablissement`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_cat_produit_idx` (`id_cat_produit`),
  ADD KEY `fk_user_idx` (`id_user`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `fk_id_role_idx` (`id_role`);

--
-- Index pour la table `user_etablis`
--
ALTER TABLE `user_etablis`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_id_user_idx` (`id_user`),
  ADD KEY `fk_id_etablissement_idx` (`id_etablissment`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `cat_produit`
--
ALTER TABLE `cat_produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT pour la table `detail_commande`
--
ALTER TABLE `detail_commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=240;

--
-- AUTO_INCREMENT pour la table `etablissement`
--
ALTER TABLE `etablissement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user_etablis`
--
ALTER TABLE `user_etablis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `fk_id_client_adress` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `fk_id_client_comm` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_user_comm` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `detail_commande`
--
ALTER TABLE `detail_commande`
  ADD CONSTRAINT `fk_id_commande` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_produit` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fk_cat_produit` FOREIGN KEY (`id_cat_produit`) REFERENCES `cat_produit` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_id_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `user_etablis`
--
ALTER TABLE `user_etablis`
  ADD CONSTRAINT `fk_id_etablissement` FOREIGN KEY (`id_etablissment`) REFERENCES `etablissement` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
