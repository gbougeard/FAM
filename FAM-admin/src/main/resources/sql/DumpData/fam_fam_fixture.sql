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
-- Table structure for table `fam_fixture`
--

DROP TABLE IF EXISTS `fam_fixture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_fixture` (
  `id_fixture` bigint(20) NOT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_fixture` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_fixture` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_season_competition` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_fixture`),
  KEY `FK_fam_fixture_id_season_competition` (`id_season_competition`),
  CONSTRAINT `FK_fam_fixture_id_season_competition` FOREIGN KEY (`id_season_competition`) REFERENCES `fam_season_competition` (`id_season_competition`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_fixture`
--

LOCK TABLES `fam_fixture` WRITE;
/*!40000 ALTER TABLE `fam_fixture` DISABLE KEYS */;
INSERT INTO `fam_fixture` VALUES (1,'2012-03-03 21:28:40',NULL,NULL,'J01',1,183),(2,'2012-03-03 21:28:40',NULL,NULL,'J04',1,183),(3,'2012-03-03 21:28:40',NULL,NULL,'J05',1,183),(4,'2012-03-03 21:28:40',NULL,NULL,'J02',1,183),(5,'2012-03-03 21:28:40',NULL,NULL,'J06',1,183),(6,'2012-03-03 21:28:40',NULL,NULL,'J03',1,183);
/*!40000 ALTER TABLE `fam_fixture` ENABLE KEYS */;
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
