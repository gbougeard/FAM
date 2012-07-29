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
-- Table structure for table `fam_event`
--

DROP TABLE IF EXISTS `fam_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_event` (
  `id_event` bigint(20) NOT NULL,
  `all_day` tinyint(1) DEFAULT '0',
  `comments` longtext,
  `dt_creat` datetime DEFAULT NULL,
  `dt_event` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `lib_event` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_eventStatus` bigint(20) DEFAULT NULL,
  `id_place` bigint(20) DEFAULT NULL,
  `id_season` bigint(20) DEFAULT NULL,
  `id_typ_event` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_event`),
  KEY `FK_fam_event_id_season` (`id_season`),
  KEY `FK_fam_event_id_eventStatus` (`id_eventStatus`),
  KEY `FK_fam_event_id_typ_event` (`id_typ_event`),
  KEY `FK_fam_event_id_place` (`id_place`),
  CONSTRAINT `FK_fam_event_id_eventStatus` FOREIGN KEY (`id_eventStatus`) REFERENCES `fam_event_status` (`id_event_status`),
  CONSTRAINT `FK_fam_event_id_place` FOREIGN KEY (`id_place`) REFERENCES `fam_place` (`id_place`),
  CONSTRAINT `FK_fam_event_id_season` FOREIGN KEY (`id_season`) REFERENCES `fam_season` (`id_season`),
  CONSTRAINT `FK_fam_event_id_typ_event` FOREIGN KEY (`id_typ_event`) REFERENCES `fam_typ_event` (`id_typ_event`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_event`
--

LOCK TABLES `fam_event` WRITE;
/*!40000 ALTER TABLE `fam_event` DISABLE KEYS */;
INSERT INTO `fam_event` VALUES (7,0,NULL,'2012-02-25 19:21:56','2012-02-25 19:21:56',NULL,120,'E1',1,25,NULL,32,14),(8,0,'2012/2013 Excellence - Equipe_92 - Equipe_70','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_92 - Equipe_70',1,25,NULL,32,13),(9,0,'2012/2013 Excellence - Equipe_70 - Equipe_92','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_70 - Equipe_92',1,25,NULL,32,13),(10,0,'2012/2013 Excellence - Equipe_20 - Equipe_41','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_20 - Equipe_41',1,25,NULL,32,13),(11,0,'2012/2013 Excellence - Equipe_41 - Equipe_20','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_41 - Equipe_20',1,25,NULL,32,13),(12,0,'2012/2013 Excellence - Equipe_70 - Equipe_41','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_70 - Equipe_41',1,25,NULL,32,13),(13,0,'2012/2013 Excellence - Equipe_41 - Equipe_70','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_41 - Equipe_70',1,25,NULL,32,13),(14,0,'2012/2013 Excellence - Equipe_92 - Equipe_20','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_92 - Equipe_20',1,25,NULL,32,13),(15,0,'2012/2013 Excellence - Equipe_20 - Equipe_92','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_20 - Equipe_92',1,25,NULL,32,13),(16,0,'2012/2013 Excellence - Equipe_20 - Equipe_70','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_20 - Equipe_70',1,25,NULL,32,13),(17,0,'2012/2013 Excellence - Equipe_70 - Equipe_20','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_70 - Equipe_20',1,25,NULL,32,13),(18,0,'2012/2013 Excellence - Equipe_41 - Equipe_92','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_41 - Equipe_92',1,25,NULL,32,13),(19,0,'2012/2013 Excellence - Equipe_92 - Equipe_41','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_92 - Equipe_41',1,25,NULL,32,13);
/*!40000 ALTER TABLE `fam_event` ENABLE KEYS */;
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
