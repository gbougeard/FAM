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
-- Table structure for table `fam_state`
--

DROP TABLE IF EXISTS `fam_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_state` (
  `id_state` bigint(20) NOT NULL,
  `cod_state` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_lower` varchar(255) DEFAULT NULL,
  `lib_state` varchar(255) DEFAULT NULL,
  `lib_upper` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_country` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_state`),
  UNIQUE KEY `UNQ_fam_state_0` (`cod_state`),
  KEY `FK_fam_state_id_country` (`id_country`),
  CONSTRAINT `FK_fam_state_id_country` FOREIGN KEY (`id_country`) REFERENCES `fam_country` (`id_country`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_state`
--

LOCK TABLES `fam_state` WRITE;
/*!40000 ALTER TABLE `fam_state` DISABLE KEYS */;
INSERT INTO `fam_state` VALUES (1,'1','2012-02-25 19:20:29',NULL,'aquitaine','Aquitaine','AQUITAINE',1,1),(2,'2','2012-02-25 19:20:29',NULL,'auvergne','Auvergne','AUVERGNE',1,1),(3,'3','2012-02-25 19:20:29',NULL,'bourgogne','Bourgogne','BOURGOGNE',1,1),(4,'4','2012-02-25 19:20:29',NULL,'bretagne','Bretagne','BRETAGNE',1,1),(5,'5','2012-02-25 19:20:29',NULL,'centre','Centre','CENTRE',1,1),(6,'6','2012-02-25 19:20:30',NULL,'champagne-ardenne','Champagne Ardenne','CHAMPAGNE ARDENNE',1,1),(7,'7','2012-02-25 19:20:30',NULL,'corse','Corse','CORSE',1,1),(8,'8','2012-02-25 19:20:30',NULL,'dom-tom','DOM/TOM','DOM/TOM',1,1),(9,'9','2012-02-25 19:20:30',NULL,'franche-comte','Franche Comté','FRANCHE COMTÉ',1,1),(10,'10','2012-02-25 19:20:30',NULL,'ile-de-france','Ile de France','ILE DE FRANCE',1,1),(11,'11','2012-02-25 19:20:30',NULL,'languedoc-roussillon','Languedoc Roussillon','LANGUEDOC ROUSSILLON',1,1),(12,'12','2012-02-25 19:20:30',NULL,'limousin','Limousin','LIMOUSIN',1,1),(13,'13','2012-02-25 19:20:30',NULL,'lorraine','Lorraine','LORRAINE',1,1),(14,'14','2012-02-25 19:20:30',NULL,'midi-pyrenees','Midi Pyrénées','MIDI PYRÉNÉES',1,1),(15,'15','2012-02-25 19:20:30',NULL,'nord-pas-de-calais','Nord Pas de Calais','NORD PAS DE CALAIS',1,1),(16,'16','2012-02-25 19:20:30',NULL,'basse-normandie','Basse-Normandie','BASSE-NORMANDIE',1,1),(17,'17','2012-02-25 19:20:30',NULL,'haute-normandie-','Haute Normandie ','HAUTE NORMANDIE ',1,1),(18,'18','2012-02-25 19:20:30',NULL,'pays-de-la-loire','Pays de la Loire','PAYS DE LA LOIRE',1,1),(19,'19','2012-02-25 19:20:30',NULL,'picardie','Picardie','PICARDIE',1,1),(20,'20','2012-02-25 19:20:30',NULL,'poitou-charentes','Poitou Charentes','POITOU CHARENTES',1,1),(21,'21','2012-02-25 19:20:30',NULL,'provence-alpes-cote-dazur','Provence Alpes Côte d\'azur','PROVENCE ALPES CÔTE D\'AZUR',1,1),(22,'22','2012-02-25 19:20:30',NULL,'rhone-alpes','Rhône Alpes','RHÔNE ALPES',1,1),(23,'23','2012-02-25 19:20:30',NULL,'alsace','Alsace','ALSACE',1,1);
/*!40000 ALTER TABLE `fam_state` ENABLE KEYS */;
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
