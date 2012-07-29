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
-- Table structure for table `fam_competition_team`
--

DROP TABLE IF EXISTS `fam_competition_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_competition_team` (
  `id_competition_team` bigint(20) NOT NULL,
  `id_season_competition` bigint(20) DEFAULT NULL,
  `id_team` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_competition_team`),
  KEY `FK_fam_competition_team_id_team` (`id_team`),
  KEY `FK_fam_competition_team_id_season_competition` (`id_season_competition`),
  CONSTRAINT `FK_fam_competition_team_id_season_competition` FOREIGN KEY (`id_season_competition`) REFERENCES `fam_season_competition` (`id_season_competition`),
  CONSTRAINT `FK_fam_competition_team_id_team` FOREIGN KEY (`id_team`) REFERENCES `fam_team` (`id_team`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_competition_team`
--

LOCK TABLES `fam_competition_team` WRITE;
/*!40000 ALTER TABLE `fam_competition_team` DISABLE KEYS */;
INSERT INTO `fam_competition_team` VALUES (1,183,187),(2,183,182),(3,183,181),(4,183,190),(5,199,184),(6,199,185),(7,199,186),(8,199,183),(9,199,181),(10,199,182);
/*!40000 ALTER TABLE `fam_competition_team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-29  3:44:46
