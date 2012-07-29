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
-- Table structure for table `fam_position`
--

DROP TABLE IF EXISTS `fam_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_position` (
  `id_position` bigint(20) NOT NULL,
  `cod_position` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_position` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_position`),
  UNIQUE KEY `UNQ_fam_position_0` (`cod_position`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_position`
--

LOCK TABLES `fam_position` WRITE;
/*!40000 ALTER TABLE `fam_position` DISABLE KEYS */;
INSERT INTO `fam_position` VALUES (91,'DC','2012-02-25 19:21:56',NULL,'Défenseur central',1),(92,'MG','2012-02-25 19:21:56',NULL,'Milieu gauche',1),(93,'MDF','2012-02-25 19:21:56',NULL,'Milieu défensif',1),(94,'MJ','2012-02-25 19:21:56',NULL,'Meneur de jeu',1),(95,'LIB','2012-02-25 19:21:56',NULL,'Libéro',1),(96,'MD','2012-02-25 19:21:56',NULL,'Milieu droit',1),(97,'G','2012-02-25 19:21:56',NULL,'Gardien de but',1),(98,'AC','2012-02-25 19:21:56',NULL,'Avant centre',1),(99,'LD','2012-02-25 19:21:56',NULL,'Latéral droit',1),(100,'MO','2012-02-25 19:21:56',NULL,'Milieu offensif',1),(101,'AD','2012-02-25 19:21:56',NULL,'Ailier droit',1),(102,'ST','2012-02-25 19:21:56',NULL,'Stoppeur',1),(103,'SA','2012-02-25 19:21:56',NULL,'Second attaquant',1),(104,'LG','2012-02-25 19:21:56',NULL,'Latéral gauche',1),(105,'AG','2012-02-25 19:21:56',NULL,'Ailier gauche',1);
/*!40000 ALTER TABLE `fam_position` ENABLE KEYS */;
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
