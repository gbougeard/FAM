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
-- Dumping data for table `fam_team`
--

LOCK TABLES `fam_team` WRITE;
/*!40000 ALTER TABLE `fam_team` DISABLE KEYS */;
INSERT INTO `fam_team` VALUES (1,'FRE1','2012-07-30 20:14:01',NULL,'FREESCALE I',1,4,1,NULL),(2,'FRE2','2012-07-30 20:14:01',NULL,'FREESCALE II',1,4,1,NULL),(3,'FRE3','2012-07-30 20:14:01',NULL,'FREESCALE III',1,4,1,NULL),(4,'ROS1','2012-07-30 20:14:01',NULL,'ROSERAIE I',1,4,2,NULL),(5,'VIG1','2012-07-30 20:14:01',NULL,'VIGNERONS GAILLAC I',1,4,3,NULL),(6,'ELE1','2012-07-30 20:14:01',NULL,'ELECCO F.C. I',1,4,4,NULL),(7,'SOP1','2012-07-30 20:14:01',NULL,'SOPRANE I',1,4,5,NULL),(8,'TOAC1','2012-07-30 20:14:01',NULL,'TOAC I',1,4,6,NULL),(9,'BURO1','2012-07-30 20:14:01',NULL,'BUROPAP',1,4,7,NULL),(10,'SYN1','2012-07-30 20:14:01',NULL,'PRO SYNERGIE I',1,4,8,NULL),(11,'MUN1','2012-07-30 20:14:01',NULL,'MUNICIPAUX I',1,4,9,NULL),(12,'FAB1','2012-07-30 20:14:01',NULL,'FABRE CASTRES I',1,4,10,NULL),(13,'DIAB','2012-07-30 20:14:01',NULL,'PETIT DIABLE',1,4,11,NULL),(14,'MOUL','2012-07-30 20:14:01',NULL,'MOULIN DES ROCHES',1,4,12,NULL),(15,'LAT1','2012-07-30 20:14:01',NULL,'LATECOERE I',1,4,13,NULL),(16,'MUR','2012-07-30 20:14:01',NULL,'CENTRALE MURET',1,4,14,NULL),(17,'BISS','2012-07-30 20:14:01',NULL,'BISSEUIL',1,4,15,NULL),(18,'CAT','2012-07-30 20:14:01',NULL,'CREDIT AGRICOLE',1,4,16,NULL),(19,'FRANC','2012-07-30 20:14:01',NULL,'FRANCAZAL',1,4,17,NULL),(20,'MUN2','2012-07-30 20:14:01',NULL,'MUNICIPAUX II',1,4,9,NULL),(21,'SYN2','2012-07-30 20:14:01',NULL,'PRO SYNERGIE II',1,4,8,NULL),(22,'VIG2','2012-07-30 20:14:01',NULL,'VIGNERONS GAILLAC II',1,4,3,NULL),(23,'TOAC2','2012-07-30 20:14:01',NULL,'TOAC II',1,4,6,NULL),(24,'FAB2','2012-07-30 20:14:01',NULL,'FABRE CASTRES II',1,4,10,NULL),(25,'ROS2','2012-07-30 20:14:01',NULL,'ROSERAIE II',1,4,2,NULL),(26,'ELE2','2012-07-30 20:14:01',NULL,'ELECCO F.C. II',1,4,4,NULL),(27,'LAT2','2012-07-30 20:14:01',NULL,'LATECOERE II',1,4,13,NULL),(28,'APO','2012-07-30 20:14:01',NULL,'APOIL',1,4,18,NULL),(29,'SAM','2012-07-30 20:14:01',NULL,'SAMU',1,4,19,NULL),(30,'PLA','2012-07-30 20:14:01',NULL,'PLATEFORME DU BATIMENT',1,4,20,NULL),(31,'TEL','2012-07-30 20:14:01',NULL,'TELEPERFORMANCE',1,4,21,NULL),(32,'STE','2012-07-30 20:14:01',NULL,'STERELA',1,4,22,NULL),(33,'ANO','2012-07-30 20:14:01',NULL,'ANOTECH',1,4,NULL,NULL),(34,'COS','2012-07-30 20:14:01',NULL,'COSMOS',1,4,NULL,NULL),(35,'TIS','2012-07-30 20:14:01',NULL,'TOULOUSE INTER SPORTS',1,4,NULL,NULL),(36,'RDC','2012-07-30 20:14:01',NULL,'RIVES DICOSTANZO',1,4,NULL,NULL),(37,'SED','2012-07-30 20:14:01',NULL,'SEDUFOOT',1,4,NULL,NULL),(38,'AKE','2012-07-30 20:14:01',NULL,'AKERYS',1,4,23,NULL),(39,'ADB','2012-07-30 20:14:01',NULL,'AMIS DU BOIS',1,4,24,NULL),(40,'AFG','2012-07-30 20:14:01',NULL,'FABRE GAILLAC',1,4,10,NULL),(41,'DDE','2012-07-30 20:14:01',NULL,'EQUIPEMENT',1,4,25,NULL),(42,'TAX','2012-07-30 20:14:01',NULL,'CAPITOLE TAXI',1,4,26,NULL),(43,'SIL','2012-07-30 20:14:01',NULL,'SILOGIC',1,4,27,NULL),(44,'SOG','2012-07-30 20:14:01',NULL,'SOGETI',1,4,28,NULL),(45,'ROC','2012-07-30 20:14:01',NULL,'ROCKWELL',1,4,29,NULL),(46,'IKE','2012-07-30 20:14:01',NULL,'IKEA',1,4,30,NULL),(47,'AZF','2012-07-30 20:14:01',NULL,'A.S. AZF',1,4,31,NULL),(48,'APS','2012-07-30 20:14:01',NULL,'APSTNA',1,4,32,NULL),(49,'GOPT','2012-07-30 20:14:01',NULL,'GRAND OPTICAL',1,4,33,NULL),(50,'ACL','2012-07-30 20:14:01',NULL,'ACLIM 31',1,4,34,NULL),(51,'SYN','2012-07-30 20:14:01',NULL,'SYNGENTA',1,4,35,NULL),(52,'BAB','2012-07-30 20:14:01',NULL,'LES B.A.B.F.C',1,4,36,NULL),(53,'MAT1','2012-07-30 20:14:01',NULL,'MATRA A.S. I',1,4,37,NULL),(54,'MAT2','2012-07-30 20:14:01',NULL,'MATRA A.S. II',1,4,37,NULL),(55,'BURO2','2012-07-30 20:14:01',NULL,'BUROPAP II',1,4,7,NULL),(56,'LME','2012-07-30 20:14:01',NULL,'LEROY MERLIN',1,4,38,NULL),(57,'AHT','2012-07-30 20:14:01',NULL,'ATELIERS HTE',1,4,39,NULL),(58,'BURO3','2012-07-30 20:14:01',NULL,'BUROPAP III',1,4,7,NULL),(59,'CMT','2012-07-30 20:14:01',NULL,'COMAT',1,4,40,NULL),(60,'CC','2012-07-30 20:14:01',NULL,'COPA CABANA',1,4,41,NULL),(61,'CEG','2012-07-30 20:14:01',NULL,'CEGETEL',1,4,42,NULL),(62,'3RMA','2012-07-30 20:14:01',NULL,'3e RMAT',1,4,43,NULL),(63,'TFC','2012-07-30 20:14:01',NULL,'TOLOSA F.C.',1,4,44,NULL),(64,'MONNE','2012-07-30 20:14:01',NULL,'MONNE DECROIX',1,4,46,NULL),(65,'STU','2012-07-30 20:14:01',NULL,'STUDIA BLAGNAC',1,4,47,NULL),(66,'UPS','2012-07-30 20:14:01',NULL,'UPS',1,4,48,NULL),(67,'BNP','2012-07-30 20:14:01',NULL,'BNP',1,4,49,NULL),(68,'MAS','2012-07-30 20:14:01',NULL,'MAS AFC',1,4,50,NULL),(69,'H24','2012-07-30 20:14:01',NULL,'H24 BLAGNAC',1,4,51,NULL),(70,'LIEB','2012-07-30 20:14:01',NULL,'LIEBHERR AEROSPACE',1,4,52,NULL),(71,'ESCTLS','2012-07-30 20:14:01',NULL,'ESC TOULOUSE',1,4,53,NULL),(72,'ERF','2012-07-30 20:14:01',NULL,'EURAFRIFOOT',1,4,54,NULL),(73,'GAR','2012-07-30 20:14:01',NULL,'CSA GARNISON',1,4,55,NULL),(74,'I31','2012-07-30 20:14:01',NULL,'INFORMATIQUE 31',1,4,56,NULL),(75,'ULG','2012-07-30 20:14:01',NULL,'UNILOG',1,4,57,NULL),(76,'SOP','2012-07-30 20:14:01',NULL,'SOPRA',1,4,58,NULL);
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

-- Dump completed on 2012-07-31  3:45:33
