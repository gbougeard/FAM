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
-- Table structure for table `fam_season_competition`
--

DROP TABLE IF EXISTS `fam_season_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_season_competition` (
  `id_season_competition` bigint(20) NOT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_scale` bigint(20) DEFAULT NULL,
  `id_season` bigint(20) DEFAULT NULL,
  `id_typ_competition` bigint(20) DEFAULT NULL,
  `id_category` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_season_competition`),
  KEY `FK_fam_season_competition_id_season` (`id_season`),
  KEY `FK_fam_season_competition_id_scale` (`id_scale`),
  KEY `FK_fam_season_competition_id_typ_competition` (`id_typ_competition`),
  KEY `FK_fam_season_competition_id_category` (`id_category`),
  CONSTRAINT `FK_fam_season_competition_id_category` FOREIGN KEY (`id_category`) REFERENCES `fam_category` (`id_category`),
  CONSTRAINT `FK_fam_season_competition_id_scale` FOREIGN KEY (`id_scale`) REFERENCES `fam_scale` (`id_scale`),
  CONSTRAINT `FK_fam_season_competition_id_season` FOREIGN KEY (`id_season`) REFERENCES `fam_season` (`id_season`),
  CONSTRAINT `FK_fam_season_competition_id_typ_competition` FOREIGN KEY (`id_typ_competition`) REFERENCES `fam_typ_competition` (`id_typ_competition`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_season_competition`
--

LOCK TABLES `fam_season_competition` WRITE;
/*!40000 ALTER TABLE `fam_season_competition` DISABLE KEYS */;
INSERT INTO `fam_season_competition` VALUES (181,'2012-02-25 19:21:56',NULL,1,NULL,31,41,4),(182,'2012-02-25 19:21:56',NULL,1,NULL,31,42,4),(183,'2012-02-25 19:21:56','2012-03-03 21:28:40',2,NULL,32,41,4),(184,'2012-02-25 19:21:56',NULL,1,NULL,35,42,4),(185,'2012-02-25 19:21:56',NULL,1,NULL,35,41,4),(186,'2012-02-25 19:21:56',NULL,1,NULL,35,37,4),(187,'2012-02-25 19:21:56',NULL,1,NULL,35,38,4),(188,'2012-02-25 19:21:56',NULL,1,NULL,33,40,4),(189,'2012-02-25 19:21:56',NULL,1,NULL,33,41,4),(190,'2012-02-25 19:21:56',NULL,1,NULL,33,37,4),(191,'2012-02-25 19:21:56',NULL,1,NULL,34,40,4),(192,'2012-02-25 19:21:56',NULL,1,NULL,35,40,4),(193,'2012-02-25 19:21:56',NULL,1,NULL,32,39,4),(194,'2012-02-25 19:21:56',NULL,1,NULL,34,39,4),(195,'2012-02-25 19:21:56',NULL,1,NULL,33,39,4),(196,'2012-02-25 19:21:56',NULL,1,NULL,34,42,4),(197,'2012-02-25 19:21:56',NULL,1,NULL,33,38,4),(198,'2012-02-25 19:21:56',NULL,1,NULL,33,42,4),(199,'2012-02-25 19:21:56','2012-07-29 01:20:59',2,NULL,32,40,4),(200,'2012-02-25 19:21:56',NULL,1,NULL,34,38,4),(201,'2012-02-25 19:21:56',NULL,1,NULL,34,37,4),(202,'2012-02-25 19:21:56',NULL,1,NULL,31,38,4),(203,'2012-02-25 19:21:56',NULL,1,NULL,35,39,4),(204,'2012-02-25 19:21:56',NULL,1,NULL,32,38,4),(205,'2012-02-25 19:21:56',NULL,1,NULL,31,37,4),(206,'2012-02-25 19:21:56',NULL,1,NULL,32,37,4),(207,'2012-02-25 19:21:56',NULL,1,NULL,31,39,4),(208,'2012-02-25 19:21:56',NULL,1,NULL,31,40,4),(209,'2012-02-25 19:21:56',NULL,1,NULL,34,41,4),(210,'2012-02-25 19:21:56',NULL,1,NULL,32,42,4);
/*!40000 ALTER TABLE `fam_season_competition` ENABLE KEYS */;
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
