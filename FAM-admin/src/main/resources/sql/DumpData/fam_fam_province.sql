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
-- Table structure for table `fam_province`
--

DROP TABLE IF EXISTS `fam_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_province` (
  `id_province` bigint(20) NOT NULL,
  `cod_province` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_lower` varchar(255) DEFAULT NULL,
  `lib_province` varchar(255) DEFAULT NULL,
  `lib_upper` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_state` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_province`),
  UNIQUE KEY `UNQ_fam_province_0` (`cod_province`),
  KEY `FK_fam_province_id_state` (`id_state`),
  CONSTRAINT `FK_fam_province_id_state` FOREIGN KEY (`id_state`) REFERENCES `fam_state` (`id_state`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_province`
--

LOCK TABLES `fam_province` WRITE;
/*!40000 ALTER TABLE `fam_province` DISABLE KEYS */;
INSERT INTO `fam_province` VALUES (1,'01','2012-02-25 19:20:30',NULL,'ain','Ain','AIN',1,22),(2,'02','2012-02-25 19:20:30',NULL,'aisne','Aisne','AISNE',1,19),(3,'03','2012-02-25 19:20:30',NULL,'allier','Allier','ALLIER',1,2),(4,'04','2012-02-25 19:20:30',NULL,'alpes de haute-provence','Alpes de Haute-Provence','ALPES DE HAUTE-PROVENCE',1,21),(5,'05','2012-02-25 19:20:30',NULL,'alpes-hautes','Alpes (Hautes)','ALPES (HAUTES)',1,21),(6,'06','2012-02-25 19:20:30',NULL,'alpes-maritimes','Alpes Maritimes','ALPES MARITIMES',1,21),(7,'07','2012-02-25 19:20:30',NULL,'ardeche','Ardéche','ARDÉCHE',1,22),(8,'08','2012-02-25 19:20:30',NULL,'ardennes','Ardennes','ARDENNES',1,6),(9,'09','2012-02-25 19:20:30',NULL,'-ariege',' Ariége',' ARIÉGE',1,14),(10,'10','2012-02-25 19:20:30',NULL,'aube','Aube','AUBE',1,6),(11,'11','2012-02-25 19:20:30',NULL,'aude','Aude','AUDE',1,11),(12,'12','2012-02-25 19:20:30',NULL,'aveyron','Aveyron','AVEYRON',1,14),(13,'13','2012-02-25 19:20:30',NULL,'bouches-du-rhone','Bouches du Rhône','BOUCHES DU RHÔNE',1,21),(14,'14','2012-02-25 19:20:30',NULL,'calvados','Calvados','CALVADOS',1,16),(15,'15','2012-02-25 19:20:30',NULL,'cantal','Cantal','CANTAL',1,2),(16,'16','2012-02-25 19:20:30',NULL,'charente','Charente','CHARENTE',1,20),(17,'17','2012-02-25 19:20:30',NULL,'charente-maritime','Charente Maritime','CHARENTE MARITIME',1,20),(18,'18','2012-02-25 19:20:30',NULL,'cher','Cher','CHER',1,5),(19,'19','2012-02-25 19:20:30',NULL,'correze','Corréze','CORRÉZE',1,12),(21,'23','2012-02-25 19:20:30',NULL,'creuse-','Creuse ','CREUSE ',1,12),(22,'24','2012-02-25 19:20:30',NULL,'dordogne','Dordogne','DORDOGNE',1,1),(23,'25','2012-02-25 19:20:30',NULL,'doubs','Doubs','DOUBS',1,9),(24,'26','2012-02-25 19:20:30',NULL,'drome','Drôme','DRÔME',1,22),(25,'27','2012-02-25 19:20:30',NULL,'eure','Eure','EURE',1,17),(26,'28','2012-02-25 19:20:30',NULL,'eure-et-loir','Eure et Loir','EURE ET LOIR',1,5),(27,'29','2012-02-25 19:20:30',NULL,'finistere','Finistére','FINISTÉRE',1,4),(28,'30','2012-02-25 19:20:30',NULL,'gard','Gard','GARD',1,11),(29,'31','2012-02-25 19:20:30',NULL,'garonne-haute','Garonne (Haute)','GARONNE (HAUTE)',1,14),(30,'32','2012-02-25 19:20:30',NULL,'gers','Gers','GERS',1,14),(31,'33','2012-02-25 19:20:30',NULL,'gironde','Gironde','GIRONDE',1,1),(32,'34','2012-02-25 19:20:30',NULL,'herault','Hérault','HÉRAULT',1,11),(33,'35','2012-02-25 19:20:30',NULL,'ile-et-vilaine','Ile et Vilaine','ILE ET VILAINE',1,4),(34,'36','2012-02-25 19:20:30',NULL,'indre','Indre','INDRE',1,5),(35,'37','2012-02-25 19:20:30',NULL,'indre-et-loire','Indre et Loire','INDRE ET LOIRE',1,5),(36,'38','2012-02-25 19:20:30',NULL,'isere','Isére','ISÉRE',1,22),(37,'39','2012-02-25 19:20:30',NULL,'jura','Jura','JURA',1,9),(38,'40','2012-02-25 19:20:30',NULL,'landes','Landes','LANDES',1,1),(39,'41','2012-02-25 19:20:30',NULL,'loir-et-cher','Loir et Cher','LOIR ET CHER',1,5),(40,'42','2012-02-25 19:20:30',NULL,'loire','Loire','LOIRE',1,22),(41,'43','2012-02-25 19:20:30',NULL,'loire-haute','Loire (Haute)','LOIRE (HAUTE)',1,2),(42,'44','2012-02-25 19:20:30',NULL,'loire-atlantique','Loire Atlantique','LOIRE ATLANTIQUE',1,18),(43,'45','2012-02-25 19:20:30',NULL,'loiret','Loiret','LOIRET',1,5),(44,'46','2012-02-25 19:20:30',NULL,'lot','Lot','LOT',1,14),(45,'47','2012-02-25 19:20:30',NULL,'lot-et-garonne','Lot et Garonne','LOT ET GARONNE',1,1),(46,'48','2012-02-25 19:20:30',NULL,'lozere','Lozére','LOZÉRE',1,11),(47,'49','2012-02-25 19:20:30',NULL,'maine-et-loire','Maine et Loire','MAINE ET LOIRE',1,18),(48,'50','2012-02-25 19:20:30',NULL,'manche','Manche','MANCHE',1,16),(49,'51','2012-02-25 19:20:30',NULL,'marne','Marne','MARNE',1,6),(50,'52','2012-02-25 19:20:30',NULL,'marne-haute','Marne (Haute)','MARNE (HAUTE)',1,6),(51,'53','2012-02-25 19:20:30',NULL,'mayenne','Mayenne','MAYENNE',1,18),(52,'54','2012-02-25 19:20:30',NULL,'meurthe-et-moselle','Meurthe et Moselle','MEURTHE ET MOSELLE',1,13),(53,'55','2012-02-25 19:20:30',NULL,'meuse','Meuse','MEUSE',1,13),(54,'56','2012-02-25 19:20:30',NULL,'morbihan','Morbihan','MORBIHAN',1,4),(55,'57','2012-02-25 19:20:30',NULL,'moselle','Moselle','MOSELLE',1,13),(56,'58','2012-02-25 19:20:30',NULL,'nievre','Niévre','NIÉVRE',1,3),(57,'59','2012-02-25 19:20:30',NULL,'nord','Nord','NORD',1,15),(58,'60','2012-02-25 19:20:30',NULL,'oise','Oise','OISE',1,19),(59,'61','2012-02-25 19:20:30',NULL,'orne','Orne','ORNE',1,16),(60,'62','2012-02-25 19:20:30',NULL,'pas-de-calais','Pas de Calais','PAS DE CALAIS',1,15),(61,'63','2012-02-25 19:20:30',NULL,'puy-de-dome','Puy de Dôme','PUY DE DÔME',1,2),(62,'64','2012-02-25 19:20:30',NULL,'pyrenees-atlantiques','Pyrénées Atlantiques','PYRÉNÉES ATLANTIQUES',1,1),(63,'65','2012-02-25 19:20:30',NULL,'pyrenees-hautes','Pyrénées (Hautes)','PYRÉNÉES (HAUTES)',1,14),(64,'66','2012-02-25 19:20:30',NULL,'pyrenees-orientales','Pyrénées Orientales','PYRÉNÉES ORIENTALES',1,11),(65,'67','2012-02-25 19:20:30',NULL,'rhin-bas','Rhin (Bas)','RHIN (BAS)',1,23),(66,'68','2012-02-25 19:20:30',NULL,'rhin-haut','Rhin (Haut)','RHIN (HAUT)',1,23),(67,'69','2012-02-25 19:20:30',NULL,'rhone','Rhône','RHÔNE',1,22),(68,'70','2012-02-25 19:20:30',NULL,'saone-haute','Saône (Haute)','SAÔNE (HAUTE)',1,9),(69,'71','2012-02-25 19:20:30',NULL,'saone-et-loire','Saône et Loire','SAÔNE ET LOIRE',1,3),(70,'72','2012-02-25 19:20:30',NULL,'sarthe','Sarthe','SARTHE',1,18),(71,'73','2012-02-25 19:20:30',NULL,'savoie','Savoie','SAVOIE',1,22),(72,'74','2012-02-25 19:20:30',NULL,'savoie-haute','Savoie (Haute)','SAVOIE (HAUTE)',1,22),(73,'75','2012-02-25 19:20:30',NULL,'paris','Paris','PARIS',1,10),(74,'76','2012-02-25 19:20:30',NULL,'seine-maritime','Seine Maritime','SEINE MARITIME',1,17),(75,'77','2012-02-25 19:20:30',NULL,'seine-et-marne','Seine et Marne','SEINE ET MARNE',1,10),(76,'78','2012-02-25 19:20:30',NULL,'yvelines','Yvelines','YVELINES',1,10),(77,'79','2012-02-25 19:20:30',NULL,'sevres-deux','Sèvres (Deux)','SÈVRES (DEUX)',1,20),(78,'80','2012-02-25 19:20:30',NULL,'somme','Somme','SOMME',1,19),(79,'81','2012-02-25 19:20:30',NULL,'tarn','Tarn','TARN',1,14),(80,'82','2012-02-25 19:20:30',NULL,'tarn-et-garonne','Tarn et Garonne','TARN ET GARONNE',1,14),(81,'83','2012-02-25 19:20:30',NULL,'var','Var','VAR',1,21),(82,'84','2012-02-25 19:20:30',NULL,'vaucluse','Vaucluse','VAUCLUSE',1,21),(83,'85','2012-02-25 19:20:30',NULL,'vendee','Vendée','VENDÉE',1,18),(84,'86','2012-02-25 19:20:30',NULL,'vienne','Vienne','VIENNE',1,20),(85,'87','2012-02-25 19:20:30',NULL,'vienne-haute','Vienne (Haute)','VIENNE (HAUTE)',1,12),(86,'88','2012-02-25 19:20:30',NULL,'vosges','Vosges','VOSGES',1,13),(87,'89','2012-02-25 19:20:30',NULL,'yonne','Yonne','YONNE',1,3),(88,'90','2012-02-25 19:20:30',NULL,'belfort-territoire-de','Belfort (Territoire de)','BELFORT (TERRITOIRE DE)',1,9),(89,'91','2012-02-25 19:20:30',NULL,'essonne','Essonne','ESSONNE',1,10),(90,'92','2012-02-25 19:20:30',NULL,'hauts-de-seine','Hauts de Seine','HAUTS DE SEINE',1,10),(91,'93','2012-02-25 19:20:30',NULL,'seine-saint-denis','Seine Saint Denis','SEINE SAINT DENIS',1,10),(92,'94','2012-02-25 19:20:30',NULL,'val-de-marne','Val de Marne','VAL DE MARNE',1,10),(93,'976','2012-02-25 19:20:30',NULL,'mayotte','Mayotte','MAYOTTE',1,8),(94,'971','2012-02-25 19:20:30',NULL,'guadeloupe','Guadeloupe','GUADELOUPE',1,8),(95,'973','2012-02-25 19:20:30',NULL,'guyane','Guyane','GUYANE',1,8),(96,'972','2012-02-25 19:20:30',NULL,'martinique','Martinique','MARTINIQUE',1,8),(97,'974','2012-02-25 19:20:30',NULL,'reunion','Réunion','RÉUNION',1,8),(98,'21','2012-02-25 19:20:30',NULL,'cote-dor','Côte d\'or','CÔTE D\'OR',1,3),(100,'22','2012-02-25 19:20:30',NULL,'cotes-darmor','Côtes d\'armor','CÔTES D\'ARMOR',1,4),(102,'2A','2012-02-25 19:20:30',NULL,'corse-du-sud','Corse du sud','CORSE DU SUD',1,7),(103,'2B','2012-02-25 19:20:30',NULL,'haute-corse','Haute corse','HAUTE CORSE',1,7),(104,'95','2012-02-25 19:20:30',NULL,'val-doise','Val d\'oise','VAL D\'OISE',1,10);
/*!40000 ALTER TABLE `fam_province` ENABLE KEYS */;
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
