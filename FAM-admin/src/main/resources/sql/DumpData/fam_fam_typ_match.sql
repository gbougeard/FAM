CREATE DATABASE  IF NOT EXISTS `fam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fam`;
-- MySQL dump 10.13  Distrib 5.5.25, for Linux (x86_64)
--
-- Host: localhost    Database: fam
-- ------------------------------------------------------
-- Server version	5.5.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fam_typ_match`
--

DROP TABLE IF EXISTS `fam_typ_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_match` (
  `id_typ_match` bigint(20) NOT NULL,
  `cod_typ_match` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `extra_time` tinyint(1) DEFAULT '0',
  `extra_time_duration` int(11) DEFAULT NULL,
  `infinite_subs` tinyint(1) DEFAULT '0',
  `lib_typ_match` varchar(255) DEFAULT NULL,
  `nb_penalties` int(11) DEFAULT NULL,
  `nb_player` int(11) DEFAULT NULL,
  `nb_substitute` int(11) DEFAULT NULL,
  `nb_substitution` int(11) DEFAULT NULL,
  `penalties` tinyint(1) DEFAULT '0',
  `period_duration` int(11) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_match`),
  UNIQUE KEY `UNQ_fam_typ_match_0` (`cod_typ_match`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_match`
--

LOCK TABLES `fam_typ_match` WRITE;
/*!40000 ALTER TABLE `fam_typ_match` DISABLE KEYS */;
INSERT INTO `fam_typ_match` VALUES (19,'Cp','2012-02-25 19:21:55',NULL,1,15,0,'Coupe',5,11,5,3,1,45,1),(20,'CORP','2012-02-25 19:21:55',NULL,0,15,1,'Corpo',5,11,5,3,0,45,1),(21,'DEF','2012-02-25 19:21:55',NULL,0,15,0,'Défault',5,11,5,3,0,45,1);
/*!40000 ALTER TABLE `fam_typ_match` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-29  3:44:45
