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
-- Table structure for table `fam_answer`
--

DROP TABLE IF EXISTS `fam_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_answer` (
  `id_answer` bigint(20) NOT NULL,
  `comments` longtext,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_event` bigint(20) DEFAULT NULL,
  `id_player` bigint(20) DEFAULT NULL,
  `id_typ_answer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_answer`),
  KEY `FK_fam_answer_id_player` (`id_player`),
  KEY `FK_fam_answer_id_event` (`id_event`),
  KEY `FK_fam_answer_id_typ_answer` (`id_typ_answer`),
  CONSTRAINT `FK_fam_answer_id_typ_answer` FOREIGN KEY (`id_typ_answer`) REFERENCES `fam_typ_answer` (`id_typ_answer`),
  CONSTRAINT `FK_fam_answer_id_event` FOREIGN KEY (`id_event`) REFERENCES `fam_event` (`id_event`),
  CONSTRAINT `FK_fam_answer_id_player` FOREIGN KEY (`id_player`) REFERENCES `fam_player` (`id_player`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_answer`
--

LOCK TABLES `fam_answer` WRITE;
/*!40000 ALTER TABLE `fam_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_answer` ENABLE KEYS */;
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
