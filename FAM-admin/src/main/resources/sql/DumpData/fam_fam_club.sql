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
-- Table structure for table `fam_club`
--

DROP TABLE IF EXISTS `fam_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_club` (
  `id_club` bigint(20) NOT NULL,
  `code_fff` int(11) DEFAULT NULL,
  `comments` longtext,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_club` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_city` bigint(20) DEFAULT NULL,
  `id_country` bigint(20) DEFAULT NULL,
  `id_province` bigint(20) DEFAULT NULL,
  `id_state` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_club`),
  UNIQUE KEY `UNQ_fam_club_0` (`code_fff`),
  KEY `FK_fam_club_id_country` (`id_country`),
  KEY `FK_fam_club_id_city` (`id_city`),
  KEY `FK_fam_club_id_province` (`id_province`),
  KEY `FK_fam_club_id_state` (`id_state`),
  CONSTRAINT `FK_fam_club_id_state` FOREIGN KEY (`id_state`) REFERENCES `fam_state` (`id_state`),
  CONSTRAINT `FK_fam_club_id_city` FOREIGN KEY (`id_city`) REFERENCES `fam_city` (`id_city`),
  CONSTRAINT `FK_fam_club_id_country` FOREIGN KEY (`id_country`) REFERENCES `fam_country` (`id_country`),
  CONSTRAINT `FK_fam_club_id_province` FOREIGN KEY (`id_province`) REFERENCES `fam_province` (`id_province`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_club`
--

LOCK TABLES `fam_club` WRITE;
/*!40000 ALTER TABLE `fam_club` DISABLE KEYS */;
INSERT INTO `fam_club` VALUES (61,0,NULL,'2012-02-25 19:21:56',NULL,'Club_0',1,NULL,NULL,NULL,NULL),(62,60,NULL,'2012-02-25 19:21:56',NULL,'Club_6',1,NULL,NULL,NULL,NULL),(63,70,NULL,'2012-02-25 19:21:56',NULL,'Club_7',1,NULL,NULL,NULL,NULL),(64,80,NULL,'2012-02-25 19:21:56',NULL,'Club_8',1,NULL,NULL,NULL,NULL),(65,90,NULL,'2012-02-25 19:21:56',NULL,'Club_9',1,NULL,NULL,NULL,NULL),(66,50,NULL,'2012-02-25 19:21:56',NULL,'Club_5',1,NULL,NULL,NULL,NULL),(67,40,NULL,'2012-02-25 19:21:56',NULL,'Club_4',1,NULL,NULL,NULL,NULL),(68,10,NULL,'2012-02-25 19:21:56',NULL,'Club_1',1,NULL,NULL,NULL,NULL),(69,20,NULL,'2012-02-25 19:21:56',NULL,'Club_2',1,NULL,NULL,NULL,NULL),(70,30,NULL,'2012-02-25 19:21:56',NULL,'Club_3',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `fam_club` ENABLE KEYS */;
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
