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
-- Table structure for table `fam_team`
--

DROP TABLE IF EXISTS `fam_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_team` (
  `id_team` bigint(20) NOT NULL,
  `cod_team` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_team` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_club` bigint(20) DEFAULT NULL,
  `id_place` bigint(20) DEFAULT NULL,
  `id_category` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_team`),
  UNIQUE KEY `UNQ_fam_team_0` (`cod_team`),
  KEY `FK_fam_team_id_club` (`id_club`),
  KEY `FK_fam_team_id_place` (`id_place`),
  KEY `FK_fam_team_id_category` (`id_category`),
  CONSTRAINT `FK_fam_team_id_category` FOREIGN KEY (`id_category`) REFERENCES `fam_category` (`id_category`),
  CONSTRAINT `FK_fam_team_id_club` FOREIGN KEY (`id_club`) REFERENCES `fam_club` (`id_club`),
  CONSTRAINT `FK_fam_team_id_place` FOREIGN KEY (`id_place`) REFERENCES `fam_place` (`id_place`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_team`
--

LOCK TABLES `fam_team` WRITE;
/*!40000 ALTER TABLE `fam_team` DISABLE KEYS */;
INSERT INTO `fam_team` VALUES (181,'EQ92','2012-02-25 19:21:56',NULL,'Equipe_92',1,65,NULL,4),(182,'EQ20','2012-02-25 19:21:56',NULL,'Equipe_20',1,69,NULL,4),(183,'EQ91','2012-02-25 19:21:56',NULL,'Equipe_91',1,65,NULL,4),(184,'EQ90','2012-02-25 19:21:56',NULL,'Equipe_90',1,65,NULL,4),(185,'EQ22','2012-02-25 19:21:56',NULL,'Equipe_22',1,69,NULL,4),(186,'EQ52','2012-02-25 19:21:56',NULL,'Equipe_52',1,66,NULL,4),(187,'EQ41','2012-02-25 19:21:56',NULL,'Equipe_41',1,67,NULL,4),(188,'EQ40','2012-02-25 19:21:56',NULL,'Equipe_40',1,67,NULL,4),(189,'EQ1','2012-02-25 19:21:56',NULL,'Equipe_1',1,61,NULL,4),(190,'EQ70','2012-02-25 19:21:56',NULL,'Equipe_70',1,63,NULL,4),(191,'EQ61','2012-02-25 19:21:56',NULL,'Equipe_61',1,62,NULL,4),(192,'EQ71','2012-02-25 19:21:56',NULL,'Equipe_71',1,63,NULL,4),(193,'EQ62','2012-02-25 19:21:56',NULL,'Equipe_62',1,62,NULL,4),(194,'EQ42','2012-02-25 19:21:56',NULL,'Equipe_42',1,67,NULL,4),(195,'EQ10','2012-02-25 19:21:56',NULL,'Equipe_10',1,68,NULL,4),(196,'EQ11','2012-02-25 19:21:56',NULL,'Equipe_11',1,68,NULL,4),(197,'EQ21','2012-02-25 19:21:56',NULL,'Equipe_21',1,69,NULL,4),(198,'EQ2','2012-02-25 19:21:56',NULL,'Equipe_2',1,61,NULL,4),(199,'EQ81','2012-02-25 19:21:56',NULL,'Equipe_81',1,64,NULL,4),(200,'EQ12','2012-02-25 19:21:56',NULL,'Equipe_12',1,68,NULL,4),(201,'EQ60','2012-02-25 19:21:56',NULL,'Equipe_60',1,62,NULL,4),(202,'EQ50','2012-02-25 19:21:56',NULL,'Equipe_50',1,66,NULL,4),(203,'EQ82','2012-02-25 19:21:56',NULL,'Equipe_82',1,64,NULL,4),(204,'EQ72','2012-02-25 19:21:56',NULL,'Equipe_72',1,63,NULL,4),(205,'EQ32','2012-02-25 19:21:56',NULL,'Equipe_32',1,70,NULL,4),(206,'EQ30','2012-02-25 19:21:56',NULL,'Equipe_30',1,70,NULL,4),(207,'EQ51','2012-02-25 19:21:56',NULL,'Equipe_51',1,66,NULL,4),(208,'EQ31','2012-02-25 19:21:56',NULL,'Equipe_31',1,70,NULL,4),(209,'EQ0','2012-02-25 19:21:56',NULL,'Equipe_0',1,61,NULL,4),(210,'EQ80','2012-02-25 19:21:56',NULL,'Equipe_80',1,64,NULL,4);
/*!40000 ALTER TABLE `fam_team` ENABLE KEYS */;
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
