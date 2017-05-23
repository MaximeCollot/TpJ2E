-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mar 23 Mai 2017 à 13:38
-- Version du serveur :  5.7.18-0ubuntu0.16.04.1
-- Version de PHP :  7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetJEE`
--
CREATE DATABASE IF NOT EXISTS `projetJEE` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projetJEE`;

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id_comment` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `review` varchar(255) NOT NULL,
  `note` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `lien_meal_comment`
--

DROP TABLE IF EXISTS `lien_meal_comment`;
CREATE TABLE IF NOT EXISTS `lien_meal_comment` (
  `id_meal` int(11) NOT NULL,
  `id_comment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `meal`
--

DROP TABLE IF EXISTS `meal`;
CREATE TABLE IF NOT EXISTS `meal` (
  `id_meal` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `recipe` varchar(255) NOT NULL,
  `number_person` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `preparation_time` int(11) NOT NULL,
  `preparation_time_unit` varchar(20) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id_meal`),
  UNIQUE KEY `id_meal` (`id_meal`),
  KEY `name` (`name`),
  KEY `recipe` (`recipe`),
  KEY `recipe_2` (`recipe`),
  KEY `type` (`type`),
  KEY `level` (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `u$eR`
--

DROP TABLE IF EXISTS `u$eR`;
CREATE TABLE IF NOT EXISTS `u$eR` (
  `id_user` int(11) NOT NULL,
  `isAdmin` int(1) NOT NULL DEFAULT '0',
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `pa$$woRd` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `login_2` (`login`),
  UNIQUE KEY `id_user` (`id_user`),
  KEY `login` (`login`),
  KEY `email` (`email`),
  KEY `lastName` (`lastName`),
  KEY `firstName` (`firstName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `u$eR`
--

INSERT INTO `u$eR` (`id_user`, `isAdmin`, `firstName`, `lastName`, `birthday`, `email`, `login`, `pa$$woRd`) VALUES
(0, 0, 'Jean', 'LeTesteur', '2000-01-01', 'jean@letesteur.con', 'jeanjean', 'test');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
