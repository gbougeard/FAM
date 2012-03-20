-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Ven 30 Avril 2010 à 17:30
-- Version du serveur: 5.1.36
-- Version de PHP: 5.2.11

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `vilaloka`
--

-- --------------------------------------------------------

--
-- Structure de la table `state`
--

CREATE TABLE IF NOT EXISTS `state` (
  `id_region` int(11) NOT NULL AUTO_INCREMENT,
  `name_state` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name_state_uppercase` varchar(255) DEFAULT NULL,
  `state_slug` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `id_country` int(11) NOT NULL,
  PRIMARY KEY (`id_region`),
  KEY `state_slug` (`state_slug`),
  KEY `id_country` (`id_country`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Contenu de la table `state`
--

INSERT INTO `state` (`id_region`, `name_state`, `name_state_uppercase`, `state_slug`, `id_country`) VALUES
(1, 'Aquitaine', 'AQUITAINE', 'aquitaine', 1),
(2, 'Auvergne', 'AUVERGNE', 'auvergne', 1),
(3, 'Bourgogne', 'BOURGOGNE', 'bourgogne', 1),
(4, 'Bretagne', 'BRETAGNE', 'bretagne', 1),
(5, 'Centre', 'CENTRE', 'centre', 1),
(6, 'Champagne Ardenne', 'CHAMPAGNE ARDENNE', 'champagne-ardenne', 1),
(7, 'Corse', 'CORSE', 'corse', 1),
(8, 'DOM/TOM', 'DOM/TOM', 'dom-tom', 1),
(9, 'Franche Comté', 'FRANCHE COMTÉ', 'franche-comte', 1),
(10, 'Ile de France', 'ILE DE FRANCE', 'ile-de-france', 1),
(11, 'Languedoc Roussillon', 'LANGUEDOC ROUSSILLON', 'languedoc-roussillon', 1),
(12, 'Limousin', 'LIMOUSIN', 'limousin', 1),
(13, 'Lorraine', 'LORRAINE', 'lorraine', 1),
(14, 'Midi Pyrénées', 'MIDI PYRÉNÉES', 'midi-pyrenees', 1),
(15, 'Nord Pas de Calais', 'NORD PAS DE CALAIS', 'nord-pas-de-calais', 1),
(17, 'Haute Normandie ', 'HAUTE NORMANDIE ', 'haute-normandie-', 1),
(18, 'Pays de la Loire', 'PAYS DE LA LOIRE', 'pays-de-la-loire', 1),
(19, 'Picardie', 'PICARDIE', 'picardie', 1),
(20, 'Poitou Charentes', 'POITOU CHARENTES', 'poitou-charentes', 1),
(21, 'Provence Alpes Côte d''azur', 'PROVENCE ALPES CÔTE D''AZUR', 'provence-alpes-cote-dazur', 1),
(22, 'Rhône Alpes', 'RHÔNE ALPES', 'rhone-alpes', 1),
(23, 'Alsace', 'ALSACE', 'alsace', 1),
(24, 'Basse-Normandie', 'BASSE-NORMANDIE', 'basse-normandie', 1);
