-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Ven 30 Avril 2010 à 17:29
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
-- Structure de la table `province`
--

CREATE TABLE IF NOT EXISTS `province` (
  `id_departement` int(11) NOT NULL AUTO_INCREMENT,
  `id_region` int(11) NOT NULL,
  `code` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name_province` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name_province_uppercase` varchar(255) DEFAULT NULL,
  `province_slug` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id_departement`),
  KEY `province_slug` (`province_slug`),
  KEY `id_region` (`id_region`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=105 ;

--
-- Contenu de la table `province`
--

INSERT INTO `province` (`id_departement`, `id_region`, `code`, `name_province`, `name_province_uppercase`, `province_slug`) VALUES
(1, 22, '01', 'Ain', 'AIN', 'ain'),
(2, 19, '02', 'Aisne', 'AISNE', 'aisne'),
(3, 2, '03', 'Allier', 'ALLIER', 'allier'),
(5, 21, '05', 'Alpes (Hautes)', 'ALPES (HAUTES)', 'alpes-hautes'),
(6, 21, '06', 'Alpes Maritimes', 'ALPES MARITIMES', 'alpes-maritimes'),
(7, 22, '07', 'Ardéche', 'ARDÉCHE', 'ardeche'),
(8, 6, '08', 'Ardennes', 'ARDENNES', 'ardennes'),
(9, 14, '09', ' Ariége', ' ARIÉGE', '-ariege'),
(10, 6, '10', 'Aube', 'AUBE', 'aube'),
(11, 11, '11', 'Aude', 'AUDE', 'aude'),
(12, 14, '12', 'Aveyron', 'AVEYRON', 'aveyron'),
(13, 21, '13', 'Bouches du Rhône', 'BOUCHES DU RHÔNE', 'bouches-du-rhone'),
(14, 24, '14', 'Calvados', 'CALVADOS', 'calvados'),
(15, 2, '15', 'Cantal', 'CANTAL', 'cantal'),
(16, 20, '16', 'Charente', 'CHARENTE', 'charente'),
(17, 20, '17', 'Charente Maritime', 'CHARENTE MARITIME', 'charente-maritime'),
(18, 5, '18', 'Cher', 'CHER', 'cher'),
(19, 12, '19', 'Corréze', 'CORRÉZE', 'correze'),
(21, 12, '23', 'Creuse ', 'CREUSE ', 'creuse-'),
(22, 1, '24', 'Dordogne', 'DORDOGNE', 'dordogne'),
(23, 9, '25', 'Doubs', 'DOUBS', 'doubs'),
(24, 22, '26', 'Drôme', 'DRÔME', 'drome'),
(25, 17, '27', 'Eure', 'EURE', 'eure'),
(26, 5, '28', 'Eure et Loir', 'EURE ET LOIR', 'eure-et-loir'),
(27, 4, '29', 'Finistére', 'FINISTÉRE', 'finistere'),
(28, 11, '30', 'Gard', 'GARD', 'gard'),
(29, 14, '31', 'Garonne (Haute)', 'GARONNE (HAUTE)', 'garonne-haute'),
(30, 14, '32', 'Gers', 'GERS', 'gers'),
(31, 1, '33', 'Gironde', 'GIRONDE', 'gironde'),
(32, 11, '34', 'Hérault', 'HÉRAULT', 'herault'),
(33, 4, '35', 'Ile et Vilaine', 'ILE ET VILAINE', 'ile-et-vilaine'),
(34, 5, '36', 'Indre', 'INDRE', 'indre'),
(35, 5, '37', 'Indre et Loire', 'INDRE ET LOIRE', 'indre-et-loire'),
(36, 22, '38', 'Isére', 'ISÉRE', 'isere'),
(37, 9, '39', 'Jura', 'JURA', 'jura'),
(38, 1, '40', 'Landes', 'LANDES', 'landes'),
(39, 5, '41', 'Loir et Cher', 'LOIR ET CHER', 'loir-et-cher'),
(40, 22, '42', 'Loire', 'LOIRE', 'loire'),
(41, 2, '43', 'Loire (Haute)', 'LOIRE (HAUTE)', 'loire-haute'),
(42, 18, '44', 'Loire Atlantique', 'LOIRE ATLANTIQUE', 'loire-atlantique'),
(43, 5, '45', 'Loiret', 'LOIRET', 'loiret'),
(44, 14, '46', 'Lot', 'LOT', 'lot'),
(45, 1, '47', 'Lot et Garonne', 'LOT ET GARONNE', 'lot-et-garonne'),
(46, 11, '48', 'Lozére', 'LOZÉRE', 'lozere'),
(47, 18, '49', 'Maine et Loire', 'MAINE ET LOIRE', 'maine-et-loire'),
(48, 24, '50', 'Manche', 'MANCHE', 'manche'),
(49, 6, '51', 'Marne', 'MARNE', 'marne'),
(50, 6, '52', 'Marne (Haute)', 'MARNE (HAUTE)', 'marne-haute'),
(51, 18, '53', 'Mayenne', 'MAYENNE', 'mayenne'),
(52, 13, '54', 'Meurthe et Moselle', 'MEURTHE ET MOSELLE', 'meurthe-et-moselle'),
(53, 13, '55', 'Meuse', 'MEUSE', 'meuse'),
(54, 4, '56', 'Morbihan', 'MORBIHAN', 'morbihan'),
(55, 13, '57', 'Moselle', 'MOSELLE', 'moselle'),
(56, 3, '58', 'Niévre', 'NIÉVRE', 'nievre'),
(57, 15, '59', 'Nord', 'NORD', 'nord'),
(58, 19, '60', 'Oise', 'OISE', 'oise'),
(59, 24, '61', 'Orne', 'ORNE', 'orne'),
(60, 15, '62', 'Pas de Calais', 'PAS DE CALAIS', 'pas-de-calais'),
(61, 2, '63', 'Puy de Dôme', 'PUY DE DÔME', 'puy-de-dome'),
(62, 1, '64', 'Pyrénées Atlantiques', 'PYRÉNÉES ATLANTIQUES', 'pyrenees-atlantiques'),
(63, 14, '65', 'Pyrénées (Hautes)', 'PYRÉNÉES (HAUTES)', 'pyrenees-hautes'),
(64, 11, '66', 'Pyrénées Orientales', 'PYRÉNÉES ORIENTALES', 'pyrenees-orientales'),
(65, 23, '67', 'Rhin (Bas)', 'RHIN (BAS)', 'rhin-bas'),
(66, 23, '68', 'Rhin (Haut)', 'RHIN (HAUT)', 'rhin-haut'),
(67, 22, '69', 'Rhône', 'RHÔNE', 'rhone'),
(68, 9, '70', 'Saône (Haute)', 'SAÔNE (HAUTE)', 'saone-haute'),
(69, 3, '71', 'Saône et Loire', 'SAÔNE ET LOIRE', 'saone-et-loire'),
(70, 18, '72', 'Sarthe', 'SARTHE', 'sarthe'),
(71, 22, '73', 'Savoie', 'SAVOIE', 'savoie'),
(72, 22, '74', 'Savoie (Haute)', 'SAVOIE (HAUTE)', 'savoie-haute'),
(73, 10, '75', 'Paris', 'PARIS', 'paris'),
(74, 17, '76', 'Seine Maritime', 'SEINE MARITIME', 'seine-maritime'),
(75, 10, '77', 'Seine et Marne', 'SEINE ET MARNE', 'seine-et-marne'),
(76, 10, '78', 'Yvelines', 'YVELINES', 'yvelines'),
(77, 20, '79', 'Sèvres (Deux)', 'SÈVRES (DEUX)', 'sevres-deux'),
(78, 19, '80', 'Somme', 'SOMME', 'somme'),
(79, 14, '81', 'Tarn', 'TARN', 'tarn'),
(80, 14, '82', 'Tarn et Garonne', 'TARN ET GARONNE', 'tarn-et-garonne'),
(81, 21, '83', 'Var', 'VAR', 'var'),
(82, 21, '84', 'Vaucluse', 'VAUCLUSE', 'vaucluse'),
(83, 18, '85', 'Vendée', 'VENDÉE', 'vendee'),
(84, 20, '86', 'Vienne', 'VIENNE', 'vienne'),
(85, 12, '87', 'Vienne (Haute)', 'VIENNE (HAUTE)', 'vienne-haute'),
(86, 13, '88', 'Vosges', 'VOSGES', 'vosges'),
(87, 3, '89', 'Yonne', 'YONNE', 'yonne'),
(88, 9, '90', 'Belfort (Territoire de)', 'BELFORT (TERRITOIRE DE)', 'belfort-territoire-de'),
(89, 10, '91', 'Essonne', 'ESSONNE', 'essonne'),
(90, 10, '92', 'Hauts de Seine', 'HAUTS DE SEINE', 'hauts-de-seine'),
(91, 10, '93', 'Seine Saint Denis', 'SEINE SAINT DENIS', 'seine-saint-denis'),
(92, 10, '94', 'Val de Marne', 'VAL DE MARNE', 'val-de-marne'),
(93, 8, '976', 'Mayotte', 'MAYOTTE', 'mayotte'),
(94, 8, '971', 'Guadeloupe', 'GUADELOUPE', 'guadeloupe'),
(95, 8, '973', 'Guyane', 'GUYANE', 'guyane'),
(96, 8, '972', 'Martinique', 'MARTINIQUE', 'martinique'),
(97, 8, '974', 'Réunion', 'RÉUNION', 'reunion'),
(98, 3, '21', 'Côte d''or', 'CÔTE D''OR', 'cote-dor'),
(100, 4, '22', 'Côtes d''armor', 'CÔTES D''ARMOR', 'cotes-darmor'),
(102, 7, '2A', 'Corse du sud', 'CORSE DU SUD', 'corse-du-sud'),
(103, 7, '2B', 'Haute corse', 'HAUTE CORSE', 'haute-corse'),
(104, 10, '95', 'Val d''oise', 'VAL D''OISE', 'val-doise');
