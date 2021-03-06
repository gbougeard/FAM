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
-- Dumping data for table `fam_formation_item`
--

LOCK TABLES `fam_formation_item` WRITE;
/*!40000 ALTER TABLE `fam_formation_item` DISABLE KEYS */;
INSERT INTO `fam_formation_item` VALUES (1,11,'2012-07-28 19:04:42','2012-07-28 19:05:07',8,2,1),(2,12,'2012-07-28 19:04:42','2012-07-28 19:05:07',6,2,1),(3,22,'2012-07-28 19:04:42','2012-07-28 19:05:07',4,2,1),(4,15,'2012-07-28 19:04:42','2012-07-28 19:05:07',7,2,1),(5,2,'2012-07-28 19:04:42','2012-07-28 19:05:07',11,2,1),(6,24,'2012-07-28 19:04:42','2012-07-28 19:05:07',5,2,1),(7,28,'2012-07-28 19:04:42','2012-07-28 19:05:07',1,2,1),(8,25,'2012-07-28 19:04:42','2012-07-28 19:05:07',2,2,1),(9,21,'2012-07-28 19:04:42','2012-07-28 19:05:07',3,2,1),(10,4,'2012-07-28 19:04:42','2012-07-28 19:05:07',9,2,1),(11,14,'2012-07-28 19:04:42','2012-07-28 19:05:07',10,2,1),(12,24,'2012-07-28 19:05:21','2012-07-28 19:06:06',5,2,2),(13,28,'2012-07-28 19:05:21','2012-07-28 19:06:06',1,2,2),(14,18,'2012-07-28 19:05:21','2012-07-28 19:06:06',6,2,2),(15,11,'2012-07-28 19:05:21','2012-07-28 19:06:06',8,2,2),(16,3,'2012-07-28 19:05:21','2012-07-28 19:06:06',9,2,2),(17,9,'2012-07-28 19:05:21','2012-07-28 19:06:06',10,2,2),(18,21,'2012-07-28 19:05:21','2012-07-28 19:06:06',3,2,2),(19,7,'2012-07-28 19:05:21','2012-07-28 19:06:06',11,2,2),(20,25,'2012-07-28 19:05:21','2012-07-28 19:06:06',2,2,2),(21,22,'2012-07-28 19:05:21','2012-07-28 19:06:06',4,2,2),(22,15,'2012-07-28 19:05:21','2012-07-28 19:06:06',7,2,2),(23,11,'2012-07-28 19:04:42','2012-07-28 19:05:07',8,2,3),(24,12,'2012-07-28 19:04:42','2012-07-28 19:05:07',6,2,3),(25,18,'2012-07-28 19:04:42','2012-07-31 00:43:28',4,3,3),(26,15,'2012-07-28 19:04:42','2012-07-28 19:05:07',7,2,3),(27,2,'2012-07-28 19:04:42','2012-07-28 19:05:07',11,2,3),(28,23,'2012-07-28 19:04:42','2012-07-31 00:43:28',5,3,3),(29,28,'2012-07-28 19:04:42','2012-07-28 19:05:07',1,2,3),(30,24,'2012-07-28 19:04:42','2012-07-31 00:43:37',2,3,3),(31,22,'2012-07-28 19:04:42','2012-07-31 00:43:37',3,3,3),(32,4,'2012-07-28 19:04:42','2012-07-28 19:05:07',9,2,3),(33,14,'2012-07-28 19:04:42','2012-07-28 19:05:07',10,2,3),(34,24,'2012-07-28 19:05:21','2012-07-28 19:06:06',5,2,4),(35,28,'2012-07-28 19:05:21','2012-07-28 19:06:06',1,2,4),(36,19,'2012-07-28 19:05:21','2012-07-31 00:45:02',6,3,4),(37,17,'2012-07-28 19:05:21','2012-07-31 00:45:02',8,3,4),(38,3,'2012-07-28 19:05:21','2012-07-28 19:06:06',9,2,4),(39,13,'2012-07-28 19:05:21','2012-07-31 00:45:02',10,3,4),(40,21,'2012-07-28 19:05:21','2012-07-28 19:06:06',3,2,4),(41,11,'2012-07-28 19:05:21','2012-07-31 00:45:02',11,3,4),(42,25,'2012-07-28 19:05:21','2012-07-28 19:06:06',2,2,4),(43,22,'2012-07-28 19:05:21','2012-07-28 19:06:06',4,2,4),(44,15,'2012-07-28 19:05:21','2012-07-28 19:06:06',7,2,4);
/*!40000 ALTER TABLE `fam_formation_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-31  3:45:33
