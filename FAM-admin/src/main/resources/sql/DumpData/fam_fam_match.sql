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
-- Table structure for table `fam_match`
--

DROP TABLE IF EXISTS `fam_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_match` (
  `idMatch` bigint(20) NOT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_fixture` bigint(20) DEFAULT NULL,
  `id_season_competition` bigint(20) DEFAULT NULL,
  `id_event` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idMatch`),
  KEY `FK_fam_match_id_fixture` (`id_fixture`),
  KEY `FK_fam_match_id_season_competition` (`id_season_competition`),
  KEY `FK_fam_match_id_event` (`id_event`),
  CONSTRAINT `FK_fam_match_id_event` FOREIGN KEY (`id_event`) REFERENCES `fam_event` (`id_event`),
  CONSTRAINT `FK_fam_match_id_fixture` FOREIGN KEY (`id_fixture`) REFERENCES `fam_fixture` (`id_fixture`),
  CONSTRAINT `FK_fam_match_id_season_competition` FOREIGN KEY (`id_season_competition`) REFERENCES `fam_season_competition` (`id_season_competition`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_match`
--

LOCK TABLES `fam_match` WRITE;
/*!40000 ALTER TABLE `fam_match` DISABLE KEYS */;
INSERT INTO `fam_match` VALUES (1,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,1,183,8),(2,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,5,183,9),(3,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,1,183,10),(4,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,5,183,11),(5,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,4,183,12),(6,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,3,183,13),(7,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,4,183,14),(8,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,3,183,15),(9,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,6,183,16),(10,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,2,183,17),(11,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,6,183,18),(12,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,2,183,19);
/*!40000 ALTER TABLE `fam_match` ENABLE KEYS */;
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
