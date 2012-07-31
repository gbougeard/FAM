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
-- Dumping data for table `fam_place`
--

LOCK TABLES `fam_place` WRITE;
/*!40000 ALTER TABLE `fam_place` DISABLE KEYS */;
INSERT INTO `fam_place` VALUES (1,'Avenue du Général Eisenhower','Toulouse','2012-07-30 19:39:46','2012-07-31 00:01:24',NULL,'FSL Stadium',NULL,2,'31100',27),(2,'Chemin de la Cépière','Toulouse','2012-07-30 19:39:46','2012-07-31 00:03:16',NULL,'Stade Marcel Cerdan n°1',NULL,2,'31100',27),(3,'Chemin de la Cépière','Toulouse','2012-07-30 19:39:46','2012-07-31 00:02:40',NULL,'Stade Marcel Cerdan stabilisé',NULL,2,'31100',25),(4,'Coteaux de Pech David','Toulouse','2012-07-30 19:39:46','2012-07-31 00:03:37',NULL,'Stade Robert Barran n°1',NULL,2,'31000',27),(5,'Chemin de la Ramero','Mauzac','2012-07-30 19:39:46','2012-07-31 00:03:57',NULL,'Stade Municipal',NULL,2,'31410',27),(6,'Rue des Cyclamens','Blagnac','2012-07-30 19:39:46','2012-07-31 00:04:18',NULL,'Stade Barradels',NULL,2,'31700',28),(7,'Rue Ferdinand Lalaunie','Toulouse','2012-07-30 19:39:46','2012-07-31 00:04:07',NULL,'Stade Cossec Mirail',NULL,2,'31000',25),(8,'Chemin du Stade','Roques sur Garonne','2012-07-30 19:39:46','2012-07-31 00:04:27',NULL,'Stade Albert Nauroy',NULL,2,'31120',27),(9,'Avenue Henri Peyrusse','Muret','2012-07-30 19:39:46','2012-07-31 00:02:58',NULL,'Stade Pierre Aragon synthétique - Stade Colette Besson',NULL,2,'31600',28);
/*!40000 ALTER TABLE `fam_place` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-31  3:45:34
