-- MySQL dump 10.13  Distrib 5.5.16, for Linux (x86_64)
--
-- Host: localhost    Database: fam
-- ------------------------------------------------------
-- Server version	5.5.16-log

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
-- Table structure for table `fam_absence`
--

DROP TABLE IF EXISTS `fam_absence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_absence` (
  `id_absence` bigint(20) NOT NULL AUTO_INCREMENT,
  `comments` longtext,
  `dt_beg_absence` datetime DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_end_absence` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_typ_absence` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_absence`),
  KEY `FK_fam_absence_id_typ_absence` (`id_typ_absence`),
  CONSTRAINT `FK_fam_absence_id_typ_absence` FOREIGN KEY (`id_typ_absence`) REFERENCES `fam_typ_absence` (`id_typ_absence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_absence`
--

LOCK TABLES `fam_absence` WRITE;
/*!40000 ALTER TABLE `fam_absence` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_absence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_answer`
--

DROP TABLE IF EXISTS `fam_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_answer` (
  `id_answer` bigint(20) NOT NULL AUTO_INCREMENT,
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
  CONSTRAINT `FK_fam_answer_id_event` FOREIGN KEY (`id_event`) REFERENCES `fam_event` (`id_event`),
  CONSTRAINT `FK_fam_answer_id_player` FOREIGN KEY (`id_player`) REFERENCES `fam_player` (`id_player`),
  CONSTRAINT `FK_fam_answer_id_typ_answer` FOREIGN KEY (`id_typ_answer`) REFERENCES `fam_typ_answer` (`id_typ_answer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_answer`
--

LOCK TABLES `fam_answer` WRITE;
/*!40000 ALTER TABLE `fam_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_card`
--

DROP TABLE IF EXISTS `fam_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_card` (
  `id_card` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_time` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_player` bigint(20) DEFAULT NULL,
  `id_match` bigint(20) DEFAULT NULL,
  `id_team` bigint(20) DEFAULT NULL,
  `id_typ_card` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_card`),
  KEY `FK_fam_card_id_match` (`id_match`,`id_team`,`id_player`),
  KEY `FK_fam_card_id_typ_card` (`id_typ_card`),
  CONSTRAINT `FK_fam_card_id_match` FOREIGN KEY (`id_match`, `id_team`, `id_player`) REFERENCES `fam_match_player` (`id_match`, `id_team`, `id_player`),
  CONSTRAINT `FK_fam_card_id_typ_card` FOREIGN KEY (`id_typ_card`) REFERENCES `fam_typ_card` (`id_typ_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_card`
--

LOCK TABLES `fam_card` WRITE;
/*!40000 ALTER TABLE `fam_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_city`
--

DROP TABLE IF EXISTS `fam_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_city` (
  `id_city` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_city` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_city` varchar(255) DEFAULT NULL,
  `lib_lower` varchar(255) DEFAULT NULL,
  `lib_upper` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_province` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_city`),
  KEY `FK_fam_city_id_province` (`id_province`),
  CONSTRAINT `FK_fam_city_id_province` FOREIGN KEY (`id_province`) REFERENCES `fam_province` (`id_province`)
) ENGINE=InnoDB AUTO_INCREMENT=256747 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_city`
--



--
-- Table structure for table `fam_club`
--

DROP TABLE IF EXISTS `fam_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_club` (
  `id_club` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_fff` int(11) DEFAULT NULL,
  `comments` longtext,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_club` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_city` bigint(20) DEFAULT NULL,
  `id_country` bigint(20) DEFAULT NULL,
  `id_province` bigint(20) DEFAULT NULL,
  `id_state` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_club`),
  UNIQUE KEY `UNQ_fam_club_0` (`code_fff`),
  KEY `FK_fam_club_id_country` (`id_country`),
  KEY `FK_fam_club_id_city` (`id_city`),
  KEY `FK_fam_club_id_province` (`id_province`),
  KEY `FK_fam_club_id_state` (`id_state`),
  CONSTRAINT `FK_fam_club_id_city` FOREIGN KEY (`id_city`) REFERENCES `fam_city` (`id_city`),
  CONSTRAINT `FK_fam_club_id_country` FOREIGN KEY (`id_country`) REFERENCES `fam_country` (`id_country`),
  CONSTRAINT `FK_fam_club_id_province` FOREIGN KEY (`id_province`) REFERENCES `fam_province` (`id_province`),
  CONSTRAINT `FK_fam_club_id_state` FOREIGN KEY (`id_state`) REFERENCES `fam_state` (`id_state`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_club`
--

LOCK TABLES `fam_club` WRITE;
/*!40000 ALTER TABLE `fam_club` DISABLE KEYS */;
INSERT INTO `fam_club` VALUES (61,0,NULL,'2012-02-25 19:21:56',NULL,'Club_0',1,NULL,NULL,NULL,NULL),(62,60,NULL,'2012-02-25 19:21:56',NULL,'Club_6',1,NULL,NULL,NULL,NULL),(63,70,NULL,'2012-02-25 19:21:56',NULL,'Club_7',1,NULL,NULL,NULL,NULL),(64,80,NULL,'2012-02-25 19:21:56',NULL,'Club_8',1,NULL,NULL,NULL,NULL),(65,90,NULL,'2012-02-25 19:21:56',NULL,'Club_9',1,NULL,NULL,NULL,NULL),(66,50,NULL,'2012-02-25 19:21:56',NULL,'Club_5',1,NULL,NULL,NULL,NULL),(67,40,NULL,'2012-02-25 19:21:56',NULL,'Club_4',1,NULL,NULL,NULL,NULL),(68,10,NULL,'2012-02-25 19:21:56',NULL,'Club_1',1,NULL,NULL,NULL,NULL),(69,20,NULL,'2012-02-25 19:21:56',NULL,'Club_2',1,NULL,NULL,NULL,NULL),(70,30,NULL,'2012-02-25 19:21:56',NULL,'Club_3',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `fam_club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_comment`
--

DROP TABLE IF EXISTS `fam_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_comment` (
  `id_comment` bigint(20) NOT NULL AUTO_INCREMENT,
  `comments` longtext,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `FK_fam_comment_id_user` (`id_user`),
  CONSTRAINT `FK_fam_comment_id_user` FOREIGN KEY (`id_user`) REFERENCES `fam_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_comment`
--

LOCK TABLES `fam_comment` WRITE;
/*!40000 ALTER TABLE `fam_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_competition_team`
--

DROP TABLE IF EXISTS `fam_competition_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_competition_team` (
  `id_competition_team` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_season_competition` bigint(20) DEFAULT NULL,
  `id_team` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_competition_team`),
  KEY `FK_fam_competition_team_id_team` (`id_team`),
  KEY `FK_fam_competition_team_id_season_competition` (`id_season_competition`),
  CONSTRAINT `FK_fam_competition_team_id_season_competition` FOREIGN KEY (`id_season_competition`) REFERENCES `fam_season_competition` (`id_season_competition`),
  CONSTRAINT `FK_fam_competition_team_id_team` FOREIGN KEY (`id_team`) REFERENCES `fam_team` (`id_team`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_competition_team`
--

LOCK TABLES `fam_competition_team` WRITE;
/*!40000 ALTER TABLE `fam_competition_team` DISABLE KEYS */;
INSERT INTO `fam_competition_team` VALUES (1,183,187),(2,183,182),(3,183,181),(4,183,190);
/*!40000 ALTER TABLE `fam_competition_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_country`
--

DROP TABLE IF EXISTS `fam_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_country` (
  `id_country` bigint(20) NOT NULL,
  `cod_country` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_country` varchar(255) DEFAULT NULL,
  `lib_lower` varchar(255) DEFAULT NULL,
  `lib_upper` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_country`),
  UNIQUE KEY `UNQ_fam_country_0` (`cod_country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_country`
--

LOCK TABLES `fam_country` WRITE;
/*!40000 ALTER TABLE `fam_country` DISABLE KEYS */;
INSERT INTO `fam_country` VALUES (1,'fr','2012-02-25 19:20:28',NULL,'France','france','FRANCE',1),(2,'af','2012-02-25 19:20:28',NULL,'Afghanistan','afghanistan','AFGHANISTAN',1),(3,'za','2012-02-25 19:20:28',NULL,'Afrique du sud','afrique-du-sud','AFRIQUE DU SUD',1),(4,'al','2012-02-25 19:20:28',NULL,'Albanie','albanie','ALBANIE',1),(5,'dz','2012-02-25 19:20:28',NULL,'Algérie','algerie','ALGÉRIE',1),(6,'de','2012-02-25 19:20:28',NULL,'Allemagne','allemagne','ALLEMAGNE',1),(7,'sa','2012-02-25 19:20:28',NULL,'Arabie saoudite','arabie-saoudite','ARABIE SAOUDITE',1),(8,'ar','2012-02-25 19:20:28',NULL,'Argentine','argentine','ARGENTINE',1),(9,'au','2012-02-25 19:20:28',NULL,'Australie','australie','AUSTRALIE',1),(10,'at','2012-02-25 19:20:29',NULL,'Autriche','autriche','AUTRICHE',1),(11,'be','2012-02-25 19:20:29',NULL,'Belgique','belgique','BELGIQUE',1),(12,'br','2012-02-25 19:20:29',NULL,'Brésil','bresil','BRÉSIL',1),(13,'bg','2012-02-25 19:20:29',NULL,'Bulgarie','bulgarie','BULGARIE',1),(14,'ca','2012-02-25 19:20:29',NULL,'Canada','canada','CANADA',1),(15,'cl','2012-02-25 19:20:29',NULL,'Chili','chili','CHILI',1),(16,'cn','2012-02-25 19:20:29',NULL,'Chine (Rép. pop.)','chine-rep-pop','CHINE (RÉP. POP.)',1),(17,'co','2012-02-25 19:20:29',NULL,'Colombie','colombie','COLOMBIE',1),(18,'kr','2012-02-25 19:20:29',NULL,'Corée, Sud','coree-sud','CORÉE, SUD',1),(19,'cr','2012-02-25 19:20:29',NULL,'Costa Rica','costa-rica','COSTA RICA',1),(20,'hr','2012-02-25 19:20:29',NULL,'Croatie','croatie','CROATIE',1),(21,'dk','2012-02-25 19:20:29',NULL,'Danemark','danemark','DANEMARK',1),(22,'eg','2012-02-25 19:20:29',NULL,'Égypte','egypte','ÉGYPTE',1),(23,'ae','2012-02-25 19:20:29',NULL,'Émirats arabes unis','emirats-arabes-unis','ÉMIRATS ARABES UNIS',1),(24,'ec','2012-02-25 19:20:29',NULL,'Équateur','equateur','ÉQUATEUR',1),(25,'us','2012-02-25 19:20:29',NULL,'États-Unis','etats-unis','ÉTATS-UNIS',1),(26,'sv','2012-02-25 19:20:29',NULL,'El Salvador','el-salvador','EL SALVADOR',1),(27,'es','2012-02-25 19:20:29',NULL,'Espagne','espagne','ESPAGNE',1),(28,'fi','2012-02-25 19:20:29',NULL,'Finlande','finlande','FINLANDE',1),(29,'gr','2012-02-25 19:20:29',NULL,'Grèce','grece','GRÈCE',1),(30,'hk','2012-02-25 19:20:29',NULL,'Hong Kong','hong-kong','HONG KONG',1),(31,'hu','2012-02-25 19:20:29',NULL,'Hongrie','hongrie','HONGRIE',1),(32,'in','2012-02-25 19:20:29',NULL,'Inde','inde','INDE',1),(33,'id','2012-02-25 19:20:29',NULL,'Indonésie','indonesie','INDONÉSIE',1),(34,'ie','2012-02-25 19:20:29',NULL,'Irlande','irlande','IRLANDE',1),(35,'il','2012-02-25 19:20:29',NULL,'Israël','israel','ISRAËL',1),(36,'it','2012-02-25 19:20:29',NULL,'Italie','italie','ITALIE',1),(37,'jp','2012-02-25 19:20:29',NULL,'Japon','japon','JAPON',1),(38,'jo','2012-02-25 19:20:29',NULL,'Jordanie','jordanie','JORDANIE',1),(39,'lb','2012-02-25 19:20:29',NULL,'Liban','liban','LIBAN',1),(40,'my','2012-02-25 19:20:29',NULL,'Malaisie','malaisie','MALAISIE',1),(41,'ma','2012-02-25 19:20:29',NULL,'Maroc','maroc','MAROC',1),(42,'mx','2012-02-25 19:20:29',NULL,'Mexique','mexique','MEXIQUE',1),(43,'no','2012-02-25 19:20:29',NULL,'Norvège','norvege','NORVÈGE',1),(44,'nz','2012-02-25 19:20:29',NULL,'Nouvelle-Zélande','nouvelle-zelande','NOUVELLE-ZÉLANDE',1),(45,'pe','2012-02-25 19:20:29',NULL,'Pérou','perou','PÉROU',1),(46,'pk','2012-02-25 19:20:29',NULL,'Pakistan','pakistan','PAKISTAN',1),(47,'nl','2012-02-25 19:20:29',NULL,'Pays-Bas','pays-bas','PAYS-BAS',1),(48,'ph','2012-02-25 19:20:29',NULL,'Philippines','philippines','PHILIPPINES',1),(49,'pl','2012-02-25 19:20:29',NULL,'Pologne','pologne','POLOGNE',1),(50,'pr','2012-02-25 19:20:29',NULL,'Porto Rico','porto-rico','PORTO RICO',1),(51,'pt','2012-02-25 19:20:29',NULL,'Portugal','portugal','PORTUGAL',1),(52,'cz','2012-02-25 19:20:29',NULL,'République tchèque','republique-tcheque','RÉPUBLIQUE TCHÈQUE',1),(53,'ro','2012-02-25 19:20:29',NULL,'Roumanie','roumanie','ROUMANIE',1),(54,'uk','2012-02-25 19:20:29',NULL,'Royaume-Uni','royaume-uni','ROYAUME-UNI',1),(55,'ru','2012-02-25 19:20:29',NULL,'Russie','russie','RUSSIE',1),(56,'sg','2012-02-25 19:20:29',NULL,'Singapour','singapour','SINGAPOUR',1),(57,'se','2012-02-25 19:20:29',NULL,'Suède','suede','SUÈDE',1),(58,'ch','2012-02-25 19:20:29',NULL,'Suisse','suisse','SUISSE',1),(59,'tw','2012-02-25 19:20:29',NULL,'Taiwan','taiwan','TAIWAN',1),(60,'th','2012-02-25 19:20:29',NULL,'Thailande','thailande','THAILANDE',1),(61,'tr','2012-02-25 19:20:29',NULL,'Turquie','turquie','TURQUIE',1),(62,'ua','2012-02-25 19:20:29',NULL,'Ukraine','ukraine','UKRAINE',1),(63,'ve','2012-02-25 19:20:29',NULL,'Venezuela','venezuela','VENEZUELA',1),(64,'yu','2012-02-25 19:20:29',NULL,'Yougoslavie','yougoslavie','YOUGOSLAVIE',1),(65,'as','2012-02-25 19:20:29',NULL,'Samoa','samoa','SAMOA',1),(66,'ad','2012-02-25 19:20:29',NULL,'Andorre','andorre','ANDORRE',1),(67,'ao','2012-02-25 19:20:29',NULL,'Angola','angola','ANGOLA',1),(68,'ai','2012-02-25 19:20:29',NULL,'Anguilla','anguilla','ANGUILLA',1),(69,'aq','2012-02-25 19:20:29',NULL,'Antarctique','antarctique','ANTARCTIQUE',1),(70,'ag','2012-02-25 19:20:29',NULL,'Antigua et Barbuda','antigua-et-barbuda','ANTIGUA ET BARBUDA',1),(71,'am','2012-02-25 19:20:29',NULL,'Arménie','armenie','ARMÉNIE',1),(72,'aw','2012-02-25 19:20:29',NULL,'Aruba','aruba','ARUBA',1),(73,'az','2012-02-25 19:20:29',NULL,'Azerbaïdjan','azerbaidjan','AZERBAÏDJAN',1),(74,'bs','2012-02-25 19:20:29',NULL,'Bahamas','bahamas','BAHAMAS',1),(75,'bh','2012-02-25 19:20:29',NULL,'Bahrain','bahrain','BAHRAIN',1),(76,'bd','2012-02-25 19:20:29',NULL,'Bangladesh','bangladesh','BANGLADESH',1),(77,'by','2012-02-25 19:20:29',NULL,'Biélorussie','bielorussie','BIÉLORUSSIE',1),(78,'bz','2012-02-25 19:20:29',NULL,'Belize','belize','BELIZE',1),(79,'bj','2012-02-25 19:20:29',NULL,'Benin','benin','BENIN',1),(80,'bm','2012-02-25 19:20:29',NULL,'Bermudes (Les)','bermudes-les','BERMUDES (LES)',1),(81,'bt','2012-02-25 19:20:29',NULL,'Bhoutan','bhoutan','BHOUTAN',1),(82,'bo','2012-02-25 19:20:29',NULL,'Bolivie','bolivie','BOLIVIE',1),(83,'ba','2012-02-25 19:20:29',NULL,'Bosnie-Herzégovine','bosnie-herzegovine','BOSNIE-HERZÉGOVINE',1),(84,'bw','2012-02-25 19:20:29',NULL,'Botswana','botswana','BOTSWANA',1),(85,'bv','2012-02-25 19:20:29',NULL,'Bouvet (Îles)','bouvet-iles','BOUVET (ÎLES)',1),(86,'io','2012-02-25 19:20:29',NULL,'Territoire britannique de l\'océan Indien','territoire-britannique-de-locean-indien','TERRITOIRE BRITANNIQUE DE L\'OCÉAN INDIEN',1),(87,'vg','2012-02-25 19:20:29',NULL,'Vierges britanniques (Îles)','vierges-britanniques-iles','VIERGES BRITANNIQUES (ÎLES)',1),(88,'bn','2012-02-25 19:20:29',NULL,'Brunei','brunei','BRUNEI',1),(89,'bf','2012-02-25 19:20:29',NULL,'Burkina Faso','burkina-faso','BURKINA FASO',1),(90,'bi','2012-02-25 19:20:29',NULL,'Burundi','burundi','BURUNDI',1),(91,'kh','2012-02-25 19:20:29',NULL,'Cambodge','cambodge','CAMBODGE',1),(92,'cm','2012-02-25 19:20:29',NULL,'Cameroun','cameroun','CAMEROUN',1),(93,'cv','2012-02-25 19:20:29',NULL,'Cap Vert','cap-vert','CAP VERT',1),(94,'ky','2012-02-25 19:20:29',NULL,'Cayman (Îles)','cayman-iles','CAYMAN (ÎLES)',1),(95,'cf','2012-02-25 19:20:29',NULL,'République centrafricaine','republique-centrafricaine','RÉPUBLIQUE CENTRAFRICAINE',1),(96,'td','2012-02-25 19:20:29',NULL,'Tchad','tchad','TCHAD',1),(97,'cx','2012-02-25 19:20:29',NULL,'Christmas (Île)','christmas-ile','CHRISTMAS (ÎLE)',1),(98,'cc','2012-02-25 19:20:29',NULL,'Cocos (Îles)','cocos-iles','COCOS (ÎLES)',1),(99,'km','2012-02-25 19:20:29',NULL,'Comores','comores','COMORES',1),(100,'cg','2012-02-25 19:20:29',NULL,'Rép. Dém. du Congo','rep-dem-du-congo','RÉP. DÉM. DU CONGO',1),(101,'ck','2012-02-25 19:20:29',NULL,'Cook (Îles)','cook-iles','COOK (ÎLES)',1),(102,'cu','2012-02-25 19:20:29',NULL,'Cuba','cuba','CUBA',1),(103,'cy','2012-02-25 19:20:29',NULL,'Chypre','chypre','CHYPRE',1),(104,'dj','2012-02-25 19:20:29',NULL,'Djibouti','djibouti','DJIBOUTI',1),(105,'dm','2012-02-25 19:20:29',NULL,'Dominique','dominique','DOMINIQUE',1),(106,'do','2012-02-25 19:20:29',NULL,'République Dominicaine','republique-dominicaine','RÉPUBLIQUE DOMINICAINE',1),(107,'tp','2012-02-25 19:20:29',NULL,'Timor','timor','TIMOR',1),(108,'gq','2012-02-25 19:20:29',NULL,'Guinée Equatoriale','guinee-equatoriale','GUINÉE EQUATORIALE',1),(109,'er','2012-02-25 19:20:29',NULL,'Érythrée','erythree','ÉRYTHRÉE',1),(110,'ee','2012-02-25 19:20:29',NULL,'Estonie','estonie','ESTONIE',1),(111,'et','2012-02-25 19:20:29',NULL,'Ethiopie','ethiopie','ETHIOPIE',1),(112,'fk','2012-02-25 19:20:29',NULL,'Falkland (Île)','falkland-ile','FALKLAND (ÎLE)',1),(113,'fo','2012-02-25 19:20:29',NULL,'Féroé (Îles)','feroe-iles','FÉROÉ (ÎLES)',1),(114,'fj','2012-02-25 19:20:29',NULL,'Fidji (République des)','fidji-republique-des','FIDJI (RÉPUBLIQUE DES)',1),(115,'gf','2012-02-25 19:20:29',NULL,'Guyane française','guyane-francaise','GUYANE FRANÇAISE',1),(116,'pf','2012-02-25 19:20:29',NULL,'Polynésie française','polynesie-francaise','POLYNÉSIE FRANÇAISE',1),(117,'tf','2012-02-25 19:20:29',NULL,'Territoires français du sud','territoires-francais-du-sud','TERRITOIRES FRANÇAIS DU SUD',1),(118,'ga','2012-02-25 19:20:29',NULL,'Gabon','gabon','GABON',1),(119,'gm','2012-02-25 19:20:29',NULL,'Gambie','gambie','GAMBIE',1),(120,'ge','2012-02-25 19:20:29',NULL,'Géorgie','georgie','GÉORGIE',1),(121,'gh','2012-02-25 19:20:29',NULL,'Ghana','ghana','GHANA',1),(122,'gi','2012-02-25 19:20:29',NULL,'Gibraltar','gibraltar','GIBRALTAR',1),(123,'gl','2012-02-25 19:20:29',NULL,'Groenland','groenland','GROENLAND',1),(124,'gd','2012-02-25 19:20:29',NULL,'Grenade','grenade','GRENADE',1),(125,'gp','2012-02-25 19:20:29',NULL,'Guadeloupe','guadeloupe','GUADELOUPE',1),(126,'gu','2012-02-25 19:20:29',NULL,'Guam','guam','GUAM',1),(127,'gt','2012-02-25 19:20:29',NULL,'Guatemala','guatemala','GUATEMALA',1),(128,'gn','2012-02-25 19:20:29',NULL,'Guinée','guinee','GUINÉE',1),(129,'gw','2012-02-25 19:20:29',NULL,'Guinée-Bissau','guinee-bissau','GUINÉE-BISSAU',1),(130,'gy','2012-02-25 19:20:29',NULL,'Guyane','guyane','GUYANE',1),(131,'ht','2012-02-25 19:20:29',NULL,'Haïti','haiti','HAÏTI',1),(132,'hm','2012-02-25 19:20:29',NULL,'Heard et McDonald (Îles)','heard-et-mcdonald-iles','HEARD ET MCDONALD (ÎLES)',1),(133,'hn','2012-02-25 19:20:29',NULL,'Honduras','honduras','HONDURAS',1),(134,'is','2012-02-25 19:20:29',NULL,'Islande','islande','ISLANDE',1),(135,'ir','2012-02-25 19:20:29',NULL,'Iran','iran','IRAN',1),(136,'iq','2012-02-25 19:20:29',NULL,'Irak','irak','IRAK',1),(137,'ci','2012-02-25 19:20:29',NULL,'Côte d\'Ivoire','cote-divoire','CÔTE D\'IVOIRE',1),(138,'jm','2012-02-25 19:20:29',NULL,'Jamaïque','jamaique','JAMAÏQUE',1),(139,'kz','2012-02-25 19:20:29',NULL,'Kazakhstan','kazakhstan','KAZAKHSTAN',1),(140,'ke','2012-02-25 19:20:29',NULL,'Kenya','kenya','KENYA',1),(141,'ki','2012-02-25 19:20:29',NULL,'Kiribati','kiribati','KIRIBATI',1),(142,'kp','2012-02-25 19:20:29',NULL,'Corée du Nord','coree-du-nord','CORÉE DU NORD',1),(143,'kw','2012-02-25 19:20:29',NULL,'Koweit','koweit','KOWEIT',1),(144,'kg','2012-02-25 19:20:29',NULL,'Kirghizistan','kirghizistan','KIRGHIZISTAN',1),(145,'la','2012-02-25 19:20:29',NULL,'Laos','laos','LAOS',1),(146,'lv','2012-02-25 19:20:29',NULL,'Lettonie','lettonie','LETTONIE',1),(147,'ls','2012-02-25 19:20:29',NULL,'Lesotho','lesotho','LESOTHO',1),(148,'lr','2012-02-25 19:20:29',NULL,'Libéria','liberia','LIBÉRIA',1),(149,'ly','2012-02-25 19:20:29',NULL,'Libye','libye','LIBYE',1),(150,'li','2012-02-25 19:20:29',NULL,'Liechtenstein','liechtenstein','LIECHTENSTEIN',1),(151,'lt','2012-02-25 19:20:29',NULL,'Lithuanie','lithuanie','LITHUANIE',1),(152,'lu','2012-02-25 19:20:29',NULL,'Luxembourg','luxembourg','LUXEMBOURG',1),(153,'mo','2012-02-25 19:20:29',NULL,'Macau','macau','MACAU',1),(154,'mk','2012-02-25 19:20:29',NULL,'Macédoine','macedoine','MACÉDOINE',1),(155,'mg','2012-02-25 19:20:29',NULL,'Madagascar','madagascar','MADAGASCAR',1),(156,'mw','2012-02-25 19:20:29',NULL,'Malawi','malawi','MALAWI',1),(157,'mv','2012-02-25 19:20:29',NULL,'Maldives (Îles)','maldives-iles','MALDIVES (ÎLES)',1),(158,'ml','2012-02-25 19:20:29',NULL,'Mali','mali','MALI',1),(159,'mt','2012-02-25 19:20:29',NULL,'Malte','malte','MALTE',1),(160,'mh','2012-02-25 19:20:29',NULL,'Marshall (Îles)','marshall-iles','MARSHALL (ÎLES)',1),(161,'mq','2012-02-25 19:20:29',NULL,'Martinique','martinique','MARTINIQUE',1),(162,'mr','2012-02-25 19:20:29',NULL,'Mauritanie','mauritanie','MAURITANIE',1),(163,'mu','2012-02-25 19:20:29',NULL,'Maurice','maurice','MAURICE',1),(164,'yt','2012-02-25 19:20:29',NULL,'Mayotte','mayotte','MAYOTTE',1),(165,'fm','2012-02-25 19:20:29',NULL,'Micronésie (États fédérés de)','micronesie-etats-federes-de','MICRONÉSIE (ÉTATS FÉDÉRÉS DE)',1),(166,'md','2012-02-25 19:20:29',NULL,'Moldavie','moldavie','MOLDAVIE',1),(167,'mc','2012-02-25 19:20:29',NULL,'Monaco','monaco','MONACO',1),(168,'mn','2012-02-25 19:20:29',NULL,'Mongolie','mongolie','MONGOLIE',1),(169,'ms','2012-02-25 19:20:29',NULL,'Montserrat','montserrat','MONTSERRAT',1),(170,'mz','2012-02-25 19:20:29',NULL,'Mozambique','mozambique','MOZAMBIQUE',1),(171,'mm','2012-02-25 19:20:29',NULL,'Myanmar','myanmar','MYANMAR',1),(172,'na','2012-02-25 19:20:29',NULL,'Namibie','namibie','NAMIBIE',1),(173,'nr','2012-02-25 19:20:29',NULL,'Nauru','nauru','NAURU',1),(174,'np','2012-02-25 19:20:29',NULL,'Nepal','nepal','NEPAL',1),(175,'an','2012-02-25 19:20:29',NULL,'Antilles néerlandaises','antilles-neerlandaises','ANTILLES NÉERLANDAISES',1),(176,'nc','2012-02-25 19:20:29',NULL,'Nouvelle Calédonie','nouvelle-caledonie','NOUVELLE CALÉDONIE',1),(177,'ni','2012-02-25 19:20:29',NULL,'Nicaragua','nicaragua','NICARAGUA',1),(178,'ne','2012-02-25 19:20:29',NULL,'Niger','niger','NIGER',1),(179,'ng','2012-02-25 19:20:29',NULL,'Nigeria','nigeria','NIGERIA',1),(180,'nu','2012-02-25 19:20:29',NULL,'Niue','niue','NIUE',1),(181,'nf','2012-02-25 19:20:29',NULL,'Norfolk (Îles)','norfolk-iles','NORFOLK (ÎLES)',1),(182,'mp','2012-02-25 19:20:29',NULL,'Mariannes du Nord (Îles)','mariannes-du-nord-iles','MARIANNES DU NORD (ÎLES)',1),(183,'om','2012-02-25 19:20:29',NULL,'Oman','oman','OMAN',1),(184,'pw','2012-02-25 19:20:29',NULL,'Palau','palau','PALAU',1),(185,'pa','2012-02-25 19:20:29',NULL,'Panama','panama','PANAMA',1),(186,'pg','2012-02-25 19:20:29',NULL,'Papouasie-Nouvelle-Guinée','papouasie-nouvelle-guinee','PAPOUASIE-NOUVELLE-GUINÉE',1),(187,'py','2012-02-25 19:20:29',NULL,'Paraguay','paraguay','PARAGUAY',1),(188,'pn','2012-02-25 19:20:29',NULL,'Pitcairn (Îles)','pitcairn-iles','PITCAIRN (ÎLES)',1),(189,'qa','2012-02-25 19:20:29',NULL,'Qatar','qatar','QATAR',1),(190,'re','2012-02-25 19:20:29',NULL,'Réunion (La)','reunion-la','RÉUNION (LA)',1),(191,'rw','2012-02-25 19:20:29',NULL,'Rwanda','rwanda','RWANDA',1),(192,'gs','2012-02-25 19:20:29',NULL,'Géorgie du Sud et Sandwich du Sud (Îles)','georgie-du-sud-et-sandwich-du-sud-iles','GÉORGIE DU SUD ET SANDWICH DU SUD (ÎLES)',1),(193,'kn','2012-02-25 19:20:29',NULL,'Saint-Kitts et Nevis','saint-kitts-et-nevis','SAINT-KITTS ET NEVIS',1),(194,'lc','2012-02-25 19:20:29',NULL,'Sainte Lucie','sainte-lucie','SAINTE LUCIE',1),(195,'vc','2012-02-25 19:20:29',NULL,'Saint Vincent et les Grenadines','saint-vincent-et-les-grenadines','SAINT VINCENT ET LES GRENADINES',1),(196,'ws','2012-02-25 19:20:29',NULL,'Samoa','samoa','SAMOA',1),(197,'sm','2012-02-25 19:20:29',NULL,'Saint-Marin (Rép. de)','saint-marin-rep-de','SAINT-MARIN (RÉP. DE)',1),(198,'st','2012-02-25 19:20:29',NULL,'São Tomé et Príncipe (Rép.)','sao-tome-et-principe-rep','SÃO TOMÉ ET PRÍNCIPE (RÉP.)',1),(199,'sn','2012-02-25 19:20:29',NULL,'Sénégal','senegal','SÉNÉGAL',1),(200,'sc','2012-02-25 19:20:29',NULL,'Seychelles','seychelles','SEYCHELLES',1),(201,'sl','2012-02-25 19:20:29',NULL,'Sierra Leone','sierra-leone','SIERRA LEONE',1),(202,'sk','2012-02-25 19:20:29',NULL,'Slovaquie','slovaquie','SLOVAQUIE',1),(203,'si','2012-02-25 19:20:29',NULL,'Slovénie','slovenie','SLOVÉNIE',1),(204,'so','2012-02-25 19:20:29',NULL,'Somalie','somalie','SOMALIE',1),(205,'lk','2012-02-25 19:20:29',NULL,'Sri Lanka','sri-lanka','SRI LANKA',1),(206,'sh','2012-02-25 19:20:29',NULL,'Sainte Hélène','sainte-helene','SAINTE HÉLÈNE',1),(207,'pm','2012-02-25 19:20:29',NULL,'Saint Pierre et Miquelon','saint-pierre-et-miquelon','SAINT PIERRE ET MIQUELON',1),(208,'sd','2012-02-25 19:20:29',NULL,'Soudan','soudan','SOUDAN',1),(209,'sr','2012-02-25 19:20:29',NULL,'Suriname','suriname','SURINAME',1),(210,'sj','2012-02-25 19:20:29',NULL,'Svalbard et Jan Mayen (Îles)','svalbard-et-jan-mayen-iles','SVALBARD ET JAN MAYEN (ÎLES)',1),(211,'sz','2012-02-25 19:20:29',NULL,'Swaziland','swaziland','SWAZILAND',1),(212,'sy','2012-02-25 19:20:29',NULL,'Syrie','syrie','SYRIE',1),(213,'tj','2012-02-25 19:20:29',NULL,'Tadjikistan','tadjikistan','TADJIKISTAN',1),(214,'tz','2012-02-25 19:20:29',NULL,'Tanzanie','tanzanie','TANZANIE',1),(215,'tg','2012-02-25 19:20:29',NULL,'Togo','togo','TOGO',1),(216,'tk','2012-02-25 19:20:29',NULL,'Tokelau','tokelau','TOKELAU',1),(217,'to','2012-02-25 19:20:29',NULL,'Tonga','tonga','TONGA',1),(218,'tt','2012-02-25 19:20:29',NULL,'Trinité et Tobago','trinite-et-tobago','TRINITÉ ET TOBAGO',1),(219,'tn','2012-02-25 19:20:29',NULL,'Tunisie','tunisie','TUNISIE',1),(220,'tm','2012-02-25 19:20:29',NULL,'Turkménistan','turkmenistan','TURKMÉNISTAN',1),(221,'tc','2012-02-25 19:20:29',NULL,'Turks et Caïques (Îles)','turks-et-caiques-iles','TURKS ET CAÏQUES (ÎLES)',1),(222,'tv','2012-02-25 19:20:29',NULL,'Tuvalu','tuvalu','TUVALU',1),(223,'um','2012-02-25 19:20:29',NULL,'Îles Mineures Éloignées des États-Unis','iles-mineures-eloignees-des-etats-unis','ÎLES MINEURES ÉLOIGNÉES DES ÉTATS-UNIS',1),(224,'ug','2012-02-25 19:20:29',NULL,'Ouganda','ouganda','OUGANDA',1),(225,'uy','2012-02-25 19:20:29',NULL,'Uruguay','uruguay','URUGUAY',1),(226,'uz','2012-02-25 19:20:29',NULL,'Ouzbékistan','ouzbekistan','OUZBÉKISTAN',1),(227,'vu','2012-02-25 19:20:29',NULL,'Vanuatu','vanuatu','VANUATU',1),(228,'va','2012-02-25 19:20:29',NULL,'Vatican (Etat du)','vatican-etat-du','VATICAN (ETAT DU)',1),(229,'vn','2012-02-25 19:20:29',NULL,'Vietnam','vietnam','VIETNAM',1),(230,'vi','2012-02-25 19:20:29',NULL,'Vierges (Îles)','vierges-iles','VIERGES (ÎLES)',1),(231,'wf','2012-02-25 19:20:29',NULL,'Wallis et Futuna (Îles)','wallis-et-futuna-iles','WALLIS ET FUTUNA (ÎLES)',1),(232,'eh','2012-02-25 19:20:29',NULL,'Sahara Occidental','sahara-occidental','SAHARA OCCIDENTAL',1),(233,'ye','2012-02-25 19:20:29',NULL,'Yemen','yemen','YEMEN',1),(234,'zr','2012-02-25 19:20:29',NULL,'Zaïre','zaire','ZAÏRE',1),(235,'zm','2012-02-25 19:20:29',NULL,'Zambie','zambie','ZAMBIE',1),(236,'zw','2012-02-25 19:20:29',NULL,'Zimbabwe','zimbabwe','ZIMBABWE',1),(237,'bb','2012-02-25 19:20:29',NULL,'La Barbad','la-barbad','LA BARBAD',1);
/*!40000 ALTER TABLE `fam_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_event`
--

DROP TABLE IF EXISTS `fam_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_event` (
  `id_event` bigint(20) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_event`
--

LOCK TABLES `fam_event` WRITE;
/*!40000 ALTER TABLE `fam_event` DISABLE KEYS */;
INSERT INTO `fam_event` VALUES (7,0,NULL,'2012-02-25 19:21:56','2012-02-25 19:21:56',NULL,120,'E1',1,25,NULL,32,14),(8,0,'2012/2013 Excellence - Equipe_92 - Equipe_70','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_92 - Equipe_70',1,25,NULL,32,13),(9,0,'2012/2013 Excellence - Equipe_70 - Equipe_92','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_70 - Equipe_92',1,25,NULL,32,13),(10,0,'2012/2013 Excellence - Equipe_20 - Equipe_41','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_20 - Equipe_41',1,25,NULL,32,13),(11,0,'2012/2013 Excellence - Equipe_41 - Equipe_20','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_41 - Equipe_20',1,25,NULL,32,13),(12,0,'2012/2013 Excellence - Equipe_70 - Equipe_41','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_70 - Equipe_41',1,25,NULL,32,13),(13,0,'2012/2013 Excellence - Equipe_41 - Equipe_70','2012-03-03 21:28:40','2012-03-03 21:28:40',NULL,90,'Equipe_41 - Equipe_70',1,25,NULL,32,13),(14,0,'2012/2013 Excellence - Equipe_92 - Equipe_20','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_92 - Equipe_20',1,25,NULL,32,13),(15,0,'2012/2013 Excellence - Equipe_20 - Equipe_92','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_20 - Equipe_92',1,25,NULL,32,13),(16,0,'2012/2013 Excellence - Equipe_20 - Equipe_70','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_20 - Equipe_70',1,25,NULL,32,13),(17,0,'2012/2013 Excellence - Equipe_70 - Equipe_20','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_70 - Equipe_20',1,25,NULL,32,13),(18,0,'2012/2013 Excellence - Equipe_41 - Equipe_92','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_41 - Equipe_92',1,25,NULL,32,13),(19,0,'2012/2013 Excellence - Equipe_92 - Equipe_41','2012-03-03 21:28:41','2012-03-03 21:28:41',NULL,90,'Equipe_92 - Equipe_41',1,25,NULL,32,13);
/*!40000 ALTER TABLE `fam_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_event_status`
--

DROP TABLE IF EXISTS `fam_event_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_event_status` (
  `id_event_status` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_event_status` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_event_status` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_event_status`),
  UNIQUE KEY `UNQ_fam_event_status_0` (`cod_event_status`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_event_status`
--

LOCK TABLES `fam_event_status` WRITE;
/*!40000 ALTER TABLE `fam_event_status` DISABLE KEYS */;
INSERT INTO `fam_event_status` VALUES (25,'P','2012-02-25 19:21:56',NULL,'Planifie',1),(26,'R','2012-02-25 19:21:56',NULL,'Reporte',1),(27,'F','2012-02-25 19:21:56',NULL,'Fini',1),(28,'A','2012-02-25 19:21:56',NULL,'Annule',1);
/*!40000 ALTER TABLE `fam_event_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_event_team`
--

DROP TABLE IF EXISTS `fam_event_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_event_team` (
  `id_team` bigint(20) NOT NULL,
  `id_event` bigint(20) NOT NULL,
  PRIMARY KEY (`id_team`,`id_event`),
  KEY `FK_fam_event_team_id_event` (`id_event`),
  CONSTRAINT `FK_fam_event_team_id_event` FOREIGN KEY (`id_event`) REFERENCES `fam_event` (`id_event`),
  CONSTRAINT `FK_fam_event_team_id_team` FOREIGN KEY (`id_team`) REFERENCES `fam_team` (`id_team`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_event_team`
--

LOCK TABLES `fam_event_team` WRITE;
/*!40000 ALTER TABLE `fam_event_team` DISABLE KEYS */;
INSERT INTO `fam_event_team` VALUES (181,8),(190,8),(181,9),(190,9),(182,10),(187,10),(182,11),(187,11),(187,12),(190,12),(187,13),(190,13),(181,14),(182,14),(181,15),(182,15),(182,16),(190,16),(182,17),(190,17),(181,18),(187,18),(181,19),(187,19);
/*!40000 ALTER TABLE `fam_event_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_fixture`
--

DROP TABLE IF EXISTS `fam_fixture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_fixture` (
  `id_fixture` bigint(20) NOT NULL AUTO_INCREMENT,
  `dt_creat` datetime DEFAULT NULL,
  `dt_fixture` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_fixture` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_season_competition` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_fixture`),
  KEY `FK_fam_fixture_id_season_competition` (`id_season_competition`),
  CONSTRAINT `FK_fam_fixture_id_season_competition` FOREIGN KEY (`id_season_competition`) REFERENCES `fam_season_competition` (`id_season_competition`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_fixture`
--

LOCK TABLES `fam_fixture` WRITE;
/*!40000 ALTER TABLE `fam_fixture` DISABLE KEYS */;
INSERT INTO `fam_fixture` VALUES (1,'2012-03-03 21:28:40',NULL,NULL,'J01',1,183),(2,'2012-03-03 21:28:40',NULL,NULL,'J04',1,183),(3,'2012-03-03 21:28:40',NULL,NULL,'J05',1,183),(4,'2012-03-03 21:28:40',NULL,NULL,'J02',1,183),(5,'2012-03-03 21:28:40',NULL,NULL,'J06',1,183),(6,'2012-03-03 21:28:40',NULL,NULL,'J03',1,183);
/*!40000 ALTER TABLE `fam_fixture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_formation`
--

DROP TABLE IF EXISTS `fam_formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_formation` (
  `id_formation` bigint(20) NOT NULL AUTO_INCREMENT,
  `byDefault` tinyint(1) DEFAULT '0',
  `cod_formation` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_formation` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_typ_match` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_formation`),
  UNIQUE KEY `UNQ_fam_formation_0` (`cod_formation`),
  KEY `FK_fam_formation_id_typ_match` (`id_typ_match`),
  CONSTRAINT `FK_fam_formation_id_typ_match` FOREIGN KEY (`id_typ_match`) REFERENCES `fam_typ_match` (`id_typ_match`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_formation`
--

LOCK TABLES `fam_formation` WRITE;
/*!40000 ALTER TABLE `fam_formation` DISABLE KEYS */;
insert into fam_formation (1, TRUE, '442', now(), '4-4-2', 21 );
/*!40000 ALTER TABLE `fam_formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_formation_item`
--

DROP TABLE IF EXISTS `fam_formation_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_formation_item` (
  `id_formation_item` bigint(20) NOT NULL AUTO_INCREMENT,
  `coord` int(11) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `num_item` int(11) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_formation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_formation_item`),
  KEY `FK_fam_formation_item_id_formation` (`id_formation`),
  CONSTRAINT `FK_fam_formation_item_id_formation` FOREIGN KEY (`id_formation`) REFERENCES `fam_formation` (`id_formation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_formation_item`
--

LOCK TABLES `fam_formation_item` WRITE;
/*!40000 ALTER TABLE `fam_formation_item` DISABLE KEYS */;
insert into fam_formation_item (id_formation, coord, num_item, dt_creat)
values
(1,1,1,now()),
(1,2,2,now()),
(1,3,3,now()),
(1,4,4,now()),
(1,5,5,now()),
(1,6,6,now()),
(1,7,7,now()),
(1,8,8,now()),
(1,9,9,now()),
(1,10,10,now()),
(1,11,11,now()),
/*!40000 ALTER TABLE `fam_formation_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_goal`
--

DROP TABLE IF EXISTS `fam_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_goal` (
  `id_goal` bigint(20) NOT NULL AUTO_INCREMENT,
  `csc` tinyint(1) DEFAULT '0',
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `goal_time` varchar(255) DEFAULT NULL,
  `penalty` tinyint(1) DEFAULT '0',
  `VERSION` bigint(20) DEFAULT NULL,
  `id_assist` bigint(20) DEFAULT NULL,
  `id_match` bigint(20) DEFAULT NULL,
  `id_striker` bigint(20) DEFAULT NULL,
  `id_team` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_goal`),
  KEY `FK_fam_goal_id_match` (`id_match`,`id_team`,`id_striker`),
  CONSTRAINT `FK_fam_goal_id_match` FOREIGN KEY (`id_match`, `id_team`, `id_striker`) REFERENCES `fam_match_player` (`id_match`, `id_team`, `id_player`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_goal`
--

LOCK TABLES `fam_goal` WRITE;
/*!40000 ALTER TABLE `fam_goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_group`
--

DROP TABLE IF EXISTS `fam_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_group` (
  `id_group` bigint(20) NOT NULL AUTO_INCREMENT,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_group`),
  UNIQUE KEY `UNQ_fam_group_0` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_group`
--

LOCK TABLES `fam_group` WRITE;
/*!40000 ALTER TABLE `fam_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_match`
--

DROP TABLE IF EXISTS `fam_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_match` (
  `idMatch` bigint(20) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_match`
--

LOCK TABLES `fam_match` WRITE;
/*!40000 ALTER TABLE `fam_match` DISABLE KEYS */;
INSERT INTO `fam_match` VALUES (1,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,1,183,8),(2,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,5,183,9),(3,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,1,183,10),(4,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,5,183,11),(5,'2012-03-03 21:28:40','2012-03-03 21:28:40',2,4,183,12),(6,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,3,183,13),(7,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,4,183,14),(8,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,3,183,15),(9,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,6,183,16),(10,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,2,183,17),(11,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,6,183,18),(12,'2012-03-03 21:28:41','2012-03-03 21:28:41',2,2,183,19);
/*!40000 ALTER TABLE `fam_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_match_player`
--

DROP TABLE IF EXISTS `fam_match_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_match_player` (
  `captain` tinyint(1) DEFAULT '0',
  `comments` longtext,
  `note` decimal(38,0) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `time_played` int(11) DEFAULT NULL,
  `id_match` bigint(20) NOT NULL,
  `id_team` bigint(20) NOT NULL,
  `id_player` bigint(20) NOT NULL,
  PRIMARY KEY (`id_match`,`id_team`,`id_player`),
  KEY `FK_fam_match_player_id_player` (`id_player`),
  CONSTRAINT `FK_fam_match_player_id_match` FOREIGN KEY (`id_match`, `id_team`) REFERENCES `fam_match_team` (`id_match`, `id_team`),
  CONSTRAINT `FK_fam_match_player_id_player` FOREIGN KEY (`id_player`) REFERENCES `fam_player` (`id_player`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_match_player`
--

LOCK TABLES `fam_match_player` WRITE;
/*!40000 ALTER TABLE `fam_match_player` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_match_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_match_team`
--

DROP TABLE IF EXISTS `fam_match_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_match_team` (
  `defeat` int(11) DEFAULT NULL,
  `draft` tinyint(1) DEFAULT '0',
  `draw` int(11) DEFAULT NULL,
  `goal_scored` int(11) DEFAULT NULL,
  `goal_shipped` int(11) DEFAULT NULL,
  `home` tinyint(1) DEFAULT '0',
  `points` int(11) DEFAULT NULL,
  `victory` int(11) DEFAULT NULL,
  `id_formation` bigint(20) DEFAULT NULL,
  `id_match` bigint(20) NOT NULL,
  `id_team` bigint(20) NOT NULL,
  PRIMARY KEY (`id_match`,`id_team`),
  KEY `FK_fam_match_team_id_team` (`id_team`),
  KEY `FK_fam_match_team_id_formation` (`id_formation`),
  CONSTRAINT `FK_fam_match_team_id_formation` FOREIGN KEY (`id_formation`) REFERENCES `fam_formation` (`id_formation`),
  CONSTRAINT `FK_fam_match_team_id_match` FOREIGN KEY (`id_match`) REFERENCES `fam_match` (`idMatch`),
  CONSTRAINT `FK_fam_match_team_id_team` FOREIGN KEY (`id_team`) REFERENCES `fam_team` (`id_team`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_match_team`
--

LOCK TABLES `fam_match_team` WRITE;
/*!40000 ALTER TABLE `fam_match_team` DISABLE KEYS */;
INSERT INTO `fam_match_team` VALUES (NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,1,181),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,1,190),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,2,181),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,2,190),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,3,182),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,3,187),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,4,182),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,4,187),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,5,187),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,5,190),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,6,187),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,6,190),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,7,181),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,7,182),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,8,181),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,8,182),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,9,182),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,9,190),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,10,182),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,10,190),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,11,181),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,11,187),(NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,12,181),(NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,12,187);
/*!40000 ALTER TABLE `fam_match_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_organization`
--

DROP TABLE IF EXISTS `fam_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_organization` (
  `id_organization` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_organization` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_organization` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_organization`),
  UNIQUE KEY `UNQ_fam_organization_0` (`cod_organization`),
  KEY `FK_fam_organization_id_parent` (`id_parent`),
  CONSTRAINT `FK_fam_organization_id_parent` FOREIGN KEY (`id_parent`) REFERENCES `fam_organization` (`id_organization`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_organization`
--

LOCK TABLES `fam_organization` WRITE;
/*!40000 ALTER TABLE `fam_organization` DISABLE KEYS */;
INSERT INTO `fam_organization` VALUES (25,'FFF','2012-02-25 19:21:55',NULL,'Fédération Française de Football',1,NULL),(26,'LMP','2012-02-25 19:21:55',NULL,'Ligue Midi Pyrénées',1,25),(27,'UFO','2012-02-25 19:21:55',NULL,'UFOLEP',1,NULL),(28,'DMT','2012-02-25 19:21:55',NULL,'District Midi Toulousain',1,26);
/*!40000 ALTER TABLE `fam_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_place`
--

DROP TABLE IF EXISTS `fam_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_place` (
  `id_place` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `latitude` decimal(10,8) DEFAULT NULL,
  `lib_place` varchar(255) DEFAULT NULL,
  `longitude` decimal(10,8) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `id_typ_place` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_place`),
  KEY `FK_fam_place_id_typ_place` (`id_typ_place`),
  CONSTRAINT `FK_fam_place_id_typ_place` FOREIGN KEY (`id_typ_place`) REFERENCES `fam_typ_place` (`id_typ_place`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_place`
--

LOCK TABLES `fam_place` WRITE;
/*!40000 ALTER TABLE `fam_place` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_player`
--

DROP TABLE IF EXISTS `fam_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_player` (
  `id_player` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `dt_arrival` date DEFAULT NULL,
  `dt_birth` date DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `ice_comment` longtext,
  `ice_contact` varchar(255) DEFAULT NULL,
  `ice_tel` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `num_license` bigint(20) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  `id_club` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_player`),
  KEY `FK_fam_player_id_user` (`id_user`),
  CONSTRAINT `FK_fam_player_id_user` FOREIGN KEY (`id_user`) REFERENCES `fam_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_player`
--

LOCK TABLES `fam_player` WRITE;
/*!40000 ALTER TABLE `fam_player` DISABLE KEYS */;
INSERT INTO `fam_player` VALUES (1,'4, rue Roland Garros','TOULOUSE','2008-01-24','1968-01-22','2012-02-25 19:21:57',NULL,'gbougeard@gmail.com','Gregory',NULL,NULL,NULL,'Bougeard',1,NULL,1,'31200',NULL,NULL),(2,NULL,NULL,'2008-06-10','1960-12-21','2012-02-25 19:21:57',NULL,'toto@gmail.com','Toto',NULL,NULL,NULL,'Test',2,NULL,1,NULL,1367,65),(3,'1635 Catalpa Ridge','Leesburg','2009-04-07','1972-04-29','2012-02-25 19:21:57',NULL,'camecame@ma1l2u.biz','Dillon',NULL,'Erika Shelton','2526823524','Hudson',86218,'2289026244',1,'09629',NULL,NULL),(4,'606 Fishers Ave','Bemiss','2005-02-14','1961-04-04','2012-02-25 19:21:57',NULL,'constantlyon@somema1l.org','Robert',NULL,'Courtney Wolfe','5253127818','Perkins',33526,'2621040469',1,'32240',NULL,NULL),(5,'1399 Robinwood Terrace','Duluth','2010-05-02','1958-11-04','2012-02-25 19:21:58',NULL,'pwilson@somema1l.net','Jenny',NULL,'Megan Peters','9382128337','Charles',94887,'6938930493',1,'01403',NULL,NULL),(6,'1803 Leonardville Place','Locust Grove','1997-11-06','1975-05-19','2012-02-25 19:21:58',NULL,'ofwrite@somema1l.org','Charlie',NULL,'Teresa Young','8928693099','Pollard',18823,'3603289869',1,'15429',NULL,NULL),(7,'1286 Brick Path','Chickamauga','2006-09-11','1979-03-30','2012-02-25 19:21:58',NULL,'buildon9@yah00.biz','Lynn',NULL,'Gage McClure','3131055073','Yang',33899,'7605060861',1,'55860',NULL,NULL),(8,'1473 Peters Road','Camilla','2009-05-11','1983-07-05','2012-02-25 19:21:58',NULL,'isassert@somema1l.us','Kelsey',NULL,'Dusty Marquez','3198521812','Sherman',20501,'4941435943',1,'06796',NULL,NULL),(9,'1708 Cliffwood Park','Folkston','2007-09-25','1964-03-25','2012-02-25 19:21:58',NULL,'pgraves@ma1lbox.us','Doug',NULL,'Brady Chang','6255650289','Stein',56496,'3134324318',1,'20061',NULL,NULL),(10,'1332 Findley Ave','Withers','2003-06-23','1955-03-09','2012-02-25 19:21:58',NULL,'uosborne@ma1lbox.net','Unborn',NULL,'Susan Chang','5457300919','Berry',46156,'7340305981',1,'80015',NULL,NULL),(11,'619 Blocksom Place','Kirkland','2006-03-09','1961-05-07','2012-02-25 19:21:58',NULL,'magneticon39@gma1l.co.uk','Andy',NULL,'Christopher Gonzalez','1313769712','English',1621,'3725571692',1,'68968',NULL,NULL),(12,'746 Chesapeake Heights','Baconton','1998-06-15','1974-08-06','2012-02-25 19:21:58',NULL,'kjuarez@everyma1l.us','Shelly',NULL,'Krista Cochran','0794056511','Reyes',90507,'7221208807',1,'57892',NULL,NULL),(13,'1282 Western Drive','Saint George','2011-04-28','1984-10-17','2012-02-25 19:21:58',NULL,'smendez4@gma1l.us','Joyce',NULL,'Keith Burnett','7786365133','Carlson',72224,'9293666228',1,'46280',NULL,NULL),(14,'1569 Forest Rd','Pineview','2002-08-11','1963-05-26','2012-02-25 19:21:58',NULL,'tbartlett@ma1l2u.co.uk','Ken',NULL,'Dillon Steele','6834838598','Casey',2785,'4070980574',1,'48045',NULL,NULL),(15,'412 Inwood Place','Douglasville','2008-05-21','1967-01-17','2012-02-25 19:21:58',NULL,'dsaunders@hotma1l.org','Jessie',NULL,'Shaun Cantu','0296850168','Woodard',56816,'8838933877',1,'33012',NULL,NULL),(16,'797 Eddy Trail','Walthourville','2011-11-10','1955-09-12','2012-02-25 19:21:58',NULL,'mboyer@hotma1l.biz','Brandon',NULL,'Nicholas Reese','5516458940','Sandoval',39184,'1036205171',1,'06605',NULL,NULL),(17,'724 Mcconnellsville Run','Weber','1998-02-07','1979-01-29','2012-02-25 19:21:58',NULL,'wewords@everyma1l.org','Angel',NULL,'Marlene Merrill','6365138250','Allison',38907,'0044505280',1,'41918',NULL,NULL),(18,'1776 Kimes Ridge','Lafayette','2005-04-22','1977-12-19','2012-02-25 19:21:58',NULL,'shipthrow@gma1l.net','Susan',NULL,'Melody Barrett','0319357105','Lowe',39142,'5917090212',1,'44404',NULL,NULL),(19,'597 Benjamin Ridge','Cartersville','2009-04-29','1964-04-07','2012-02-25 19:21:58',NULL,'areor@everyma1l.net','Amber',NULL,'Ron Stanton','4014422809','Cobb',41790,'8199912386',1,'59349',NULL,NULL),(20,'811 Vinsel Place','Hopeulikit','1999-12-08','1959-02-18','2012-02-25 19:21:58',NULL,'fprince@ma1l2u.us','Katelyn',NULL,'Annette Everett','8828303446','Dixon',40581,'5360664023',1,'81435',NULL,NULL),(21,'737 Brandywine Avenue','Hoboken','1997-11-29','1983-04-09','2012-02-25 19:21:58',NULL,'jprince@hotma1l.us','Gloria',NULL,'Dillon Knight','0802901500','Mullins',63671,'6366218179',1,'31142',NULL,NULL),(22,'1646 Clarice Road','Stockbridge','2005-12-21','1966-06-11','2012-02-25 19:21:58',NULL,'dowindow@ma1lbox.net','Dana',NULL,'Mitchell Valentine','8518425851','Kirk',15583,'6101501299',1,'68552',NULL,NULL),(23,'1265 Moore Place','Macon','1999-10-16','1958-04-07','2012-02-25 19:21:58',NULL,'csilva@everyma1l.co.uk','Destiny',NULL,'Jeremiah Vaughan','6261743327','Riddle',94743,'7426880701',1,'55739',NULL,NULL),(24,'941 Easter Circle','Abbeville','2002-01-11','1961-02-09','2012-02-25 19:21:58',NULL,'ssanchez@yah00.net','Thomas',NULL,'Brooklyn Vang','3681076882','Rivas',13988,'6253579989',1,'31435',NULL,NULL),(25,'815 Temple Parkway','Council','2002-02-17','1978-06-05','2012-02-25 19:21:58',NULL,'automaticallygenerated77@yah00.us','Abby',NULL,'Shanna Pitts','2575345768','Duncan',62,'7396261905',1,'51066',NULL,NULL),(26,'1511 Cleve Ave','Greenville','1999-01-11','1973-12-28','2012-02-25 19:21:58',NULL,'kleach@ma1lbox.biz','Lisa',NULL,'Dorothy Madden','0131914924','Bowman',53105,'4002978721',1,'55802',NULL,NULL),(27,'550 Northcrest Place','Albany','1998-05-08','1974-04-04','2012-02-25 19:21:58',NULL,'nlester@ma1l2u.co.uk','Joanne',NULL,'Casey Pratt','5962583721','Vance',47442,'8837533303',1,'99724',NULL,NULL),(28,'1460 Roseville Crescent','Temple','2001-12-25','1957-12-02','2012-02-25 19:21:58',NULL,'lbaker@somema1l.org','Tommy',NULL,'Susan Clayton','1120020433','Bowman',55540,'6242396365',1,'11814',NULL,NULL),(29,'1006 Kirk Square','Wray','2007-08-06','1958-01-09','2012-02-25 19:21:58',NULL,'cgamble@gma1l.co.uk','Marilyn',NULL,'Stanley McLean','0197160590','Hodge',72638,'1822303390',1,'40536',NULL,NULL),(30,'447 Genessee Blvd','Monticello','2000-07-01','1975-04-26','2012-02-25 19:21:58',NULL,'elsewant44@ma1lbox.org','Leah',NULL,'Jonathan Browning','8117464268','Rosa',72751,'0255278611',1,'37580',NULL,NULL),(31,'508 Broadvue Path','Meldrim','1999-06-05','1968-12-31','2012-02-25 19:21:59',NULL,'neverett55@b1zmail.com','Debbie',NULL,'Lindsey Hodges','9090791346','Schwartz',72037,'9645151466',1,'59955',NULL,NULL),(32,'1197 Stalling Ave','Americus','2007-12-05','1976-04-21','2012-02-25 19:21:59',NULL,'csoto@ma1lbox.net','Noah',NULL,'Pam Boyle','7294951642','Lawrence',58958,'3635464485',1,'97886',NULL,NULL),(33,'1151 Lithopolis St','Valdosta','2006-08-17','1957-10-22','2012-02-25 19:21:59',NULL,'hatchor@yah00.us','Brooklyn',NULL,'Kylie Burnett','7552462271','Torres',10164,'6784028767',1,'62732',NULL,NULL),(34,'758 Robinwood Heights','Monroe','2010-07-13','1979-12-03','2012-02-25 19:21:59',NULL,'constantlywhite@everyma1l.net','Wanda',NULL,'Shawna Robinson','3871280496','McFadden',52311,'7983716334',1,'29798',NULL,NULL),(35,'447 Summit Terrace','Abba','2005-07-30','1970-03-20','2012-02-25 19:21:59',NULL,'sitscode@b1zmail.biz','Gene',NULL,'Carolyn Michael','1384288662','Stevens',26513,'5962445064',1,'57708',NULL,NULL),(36,'1466 Price Park','Garden City','2009-06-18','1959-09-23','2012-02-25 19:21:59',NULL,'ithave@ma1lbox.biz','Natasha',NULL,'Cody Ramsey','6205627500','Mendez',53482,'6005619246',1,'90441',NULL,NULL),(37,'1166 Snyder Heights','Sunbury','2000-12-27','1983-09-05','2012-02-25 19:21:59',NULL,'sidekickit@yah00.net','Paul',NULL,'Kendra Carter','6310979130','O\'neill',81014,'9590945166',1,'40516',NULL,NULL),(38,'1331 Woolper Path','Covington','2004-07-21','1969-03-30','2012-02-25 19:21:59',NULL,'jpatrick71@hotma1l.com','Tabitha',NULL,'Christina Benton','0152312617','Diaz',9751,'5006373079',1,'90069',NULL,NULL),(39,'630 Exchange Avenue','Statenville','2001-04-24','1969-05-06','2012-02-25 19:21:59',NULL,'drice@yah00.us','Joey',NULL,'Devin Adams','5229329205','Wall',81466,'1032253991',1,'98170',NULL,NULL),(40,'728 Cattle Blvd','Rockmart','2007-08-24','1964-06-13','2012-02-25 19:21:59',NULL,'itwill@b1zmail.org','Skyler',NULL,'Kelsey Monroe','0402408477','Farrell',93792,'6224279703',1,'11967',NULL,NULL),(41,'420 Melick Road','Lake Park','2010-06-22','1974-03-06','2012-02-25 19:21:59',NULL,'rescueso@somema1l.us','Joel',NULL,'Matthew McCarthy','4867000779','Serrano',79292,'1732975892',1,'14558',NULL,NULL),(42,'609 Rucker Lane','Riverside','2003-09-30','1979-10-14','2012-02-25 19:21:59',NULL,'tmoses@ma1lbox.biz','Derek',NULL,'Lloyd McIntyre','4323231984','Hart',5280,'6083379912',1,'89471',NULL,NULL),(43,'456 Quick Ln','Irwinton','2010-01-09','1960-02-28','2012-02-25 19:21:59',NULL,'rabbitmy@everyma1l.org','Sam',NULL,'Robin Avila','7723701405','Fox',58255,'2780004814',1,'35008',NULL,NULL),(44,'1281 Layton Circle','Jeffersonville','1997-04-11','1957-04-07','2012-02-25 19:21:59',NULL,'jwells@everyma1l.co.uk','Thelma',NULL,'Rob Peck','4345811446','Boyle',34663,'9942840132',1,'70689',NULL,NULL),(45,'1438 Discovery Crescent','Social Circle','2008-08-23','1962-10-23','2012-02-25 19:21:59',NULL,'theyno@somema1l.biz','Roy',NULL,'Cindy Wooten','6774444842','Howell',51846,'6539756910',1,'66971',NULL,NULL),(46,'1601 Madison Crescent','Thomson','2003-05-24','1981-06-21','2012-02-25 19:21:59',NULL,'mvaldez@everyma1l.biz','Theresa',NULL,'Trevor Leblanc','6889408638','Gilmore',28611,'8918430742',1,'38407',NULL,NULL),(47,'1664 Winter Road','Culloden','2008-07-23','1981-07-28','2012-02-25 19:21:59',NULL,'efrye8@yah00.net','Jordan',NULL,'Derek Cochran','0792341066','Mercer',34792,'2594109888',1,'22289',NULL,NULL),(48,'1256 Garst Blvd','Country Club Estate','1999-02-19','1959-11-09','2012-02-25 19:21:59',NULL,'cmontoya90@gma1l.org','Lindsey',NULL,'Ellen McCormick','8165564775','Salinas',20275,'9414263880',1,'79528',NULL,NULL),(49,'762 Elmville Lane','Augusta','1997-07-09','1960-04-06','2012-02-25 19:21:59',NULL,'tocomputer@somema1l.biz','Jacob',NULL,'Fred Sweeney','7508264510','Barr',35820,'3718829955',1,'65620',NULL,NULL),(50,'1037 Swartz Blvd','Country Club Estate','2002-04-14','1962-02-18','2012-02-25 19:21:59',NULL,'cameuntil35@everyma1l.net','Marion',NULL,'Kyle Pennington','8863592941','Moody',26148,'9523063129',1,'61198',NULL,NULL),(51,'1305 Rosewood Trail','Rome','2003-04-01','1982-05-18','2012-02-25 19:21:59',NULL,'camedemanded@gma1l.com','Regina',NULL,'Steve Higgins','4396895652','Bass',9914,'0311056823',1,'72631',NULL,NULL),(52,'1233 Ridgeland Avenue','Lexington','2006-09-01','1982-06-08','2012-02-25 19:21:59',NULL,'rabbithot@somema1l.net','Shane',NULL,'Ruth Haynes','0262471601','Sosa',89065,'5666896490',1,'83702',NULL,NULL),(53,'556 Bonsels Circle','Appling','2001-11-21','1982-10-23','2012-02-25 19:21:59',NULL,'rbuck65@ma1lbox.biz','Susan',NULL,'Margaret Eaton','1785209384','Haynes',82904,'8871219628',1,'68717',NULL,NULL),(54,'1216 Watkins Court','Dupont','2009-11-05','1956-07-22','2012-02-25 19:22:00',NULL,'ovazquez@everyma1l.com','Vanessa',NULL,'Louis Boyd','1591014621','Fitzgerald',55123,'8434085745',1,'79205',NULL,NULL),(55,'1103 Sealover Heights','Fort Gaines','1999-02-10','1974-10-01','2012-02-25 19:22:00',NULL,'wornlots@yah00.net','Candy',NULL,'Troy Singleton','5254488398','Gaines',78796,'5657397905',1,'76640',NULL,NULL),(56,'1503 Manor Place','Fitzgerald','1998-02-23','1965-01-15','2012-02-25 19:22:00',NULL,'cduke@somema1l.org','Gage',NULL,'Austin Hines','8857148783','Dickerson',2002,'4607850782',1,'93222',NULL,NULL),(57,'1258 Mill Blvd','Lawrenceville','2010-06-19','1974-12-02','2012-02-25 19:22:00',NULL,'bmelton@gma1l.org','Katherine',NULL,'Austin Molina','3232979724','McClure',66913,'3596753231',1,'68969',NULL,NULL),(58,'1019 Webb Square','Lulaton','2008-01-27','1982-05-22','2012-02-25 19:22:00',NULL,'jburch35@gma1l.us','Rocky',NULL,'Veronica Velazquez','6060670068','Hewitt',62202,'0012323643',1,'94031',NULL,NULL),(59,'925 Eaton Blvd','Clyo','1999-02-14','1956-05-28','2012-02-25 19:22:00',NULL,'thedays@yah00.biz','Jake',NULL,'Dennis Justice','1726655090','Barton',61256,'6307673577',1,'33886',NULL,NULL),(60,'1270 Dixon Court','Brinson','2003-02-27','1959-10-24','2012-02-25 19:22:00',NULL,'dreamswith@ma1l2u.net','Lacey',NULL,'Breanna Ray','9749631323','Spence',53083,'3039385509',1,'58509',NULL,NULL),(61,'511 Chewelah Ave','Shawnee','2003-09-23','1983-04-15','2012-02-25 19:22:00',NULL,'gconner@everyma1l.org','Barb',NULL,'Joe Lindsey','4446336896','Dunn',1565,'4239381545',1,'11829',NULL,NULL),(62,'1269 Weedon Run','Ridgeville','2003-05-07','1957-09-10','2012-02-25 19:22:00',NULL,'mrocha92@gma1l.org','Nicole',NULL,'Dylan Glass','9012265710','Morrison',54945,'8071245550',1,'25363',NULL,NULL),(63,'940 Bolton Rd','Kingsland','2002-12-12','1959-12-25','2012-02-25 19:22:00',NULL,'kabbott@hotma1l.biz','Mandy',NULL,'Corey Sharp','6617052982','Winters',89092,'2734679394',1,'63780',NULL,NULL),(64,'664 Melrose Road','Hamilton','2008-02-16','1983-12-02','2012-02-25 19:22:00',NULL,'grios@everyma1l.us','Tamara',NULL,'Unborn Moran','2379598963','Reyes',2081,'1443695844',1,'24647',NULL,NULL),(65,'1111 Willow Heights','Eulonia','2008-10-08','1968-03-09','2012-02-25 19:22:00',NULL,'cmaddox@gma1l.biz','Jasmine',NULL,'Pam Bennett','9141929863','Petersen',22652,'6675296365',1,'03894',NULL,NULL),(66,'632 Grove Square','Dawsonville','2008-07-11','1977-12-27','2012-02-25 19:22:00',NULL,'mgriffin@somema1l.co.uk','Ethan',NULL,'Destiny Butler','8940196241','Dickinson',39655,'0219541468',1,'63610',NULL,NULL),(67,'1318 Central Run','Poulan','2010-01-25','1980-07-21','2012-02-25 19:22:00',NULL,'mgilbert@b1zmail.us','Ann',NULL,'Gina Sims','4629229276','Alford',60510,'7823224866',1,'10599',NULL,NULL),(68,'1325 Douglas Ridge','Fort Oglethorpe','2009-04-15','1965-06-03','2012-02-25 19:22:00',NULL,'inwant@yah00.co.uk','Don',NULL,'Tom Wilkinson','6734258981','Solis',13700,'8894613849',1,'29476',NULL,NULL),(69,'1733 Devlin Heights','Edith','2008-06-07','1968-06-10','2012-02-25 19:22:00',NULL,'nolock@b1zmail.co.uk','Misty',NULL,'Bradley McGee','9202894090','Stuart',91906,'6395743554',1,'14810',NULL,NULL),(70,'1356 Raintree Run','Sparks','2000-12-17','1955-10-07','2012-02-25 19:22:00',NULL,'sacrificesacrifice7@everyma1l.org','Sandy',NULL,'Carrie Rush','7420462994','Jones',93790,'6313603572',1,'42728',NULL,NULL),(71,'1516 Bottom Boulevard','Statenville','2005-01-09','1974-06-26','2012-02-25 19:22:00',NULL,'pullout@gma1l.biz','Trisha',NULL,'Francis Harding','6777262682','Bass',51642,'9060957295',1,'04952',NULL,NULL),(72,'1799 Lark Drive','Macon','2007-07-11','1974-02-04','2012-02-25 19:22:00',NULL,'ccochran34@ma1l2u.us','Michelle',NULL,'Chester Franks','2374135519','Farrell',8985,'0845854961',1,'87358',NULL,NULL),(73,'654 Cambridge Ave','South Newport','2002-02-02','1968-08-18','2012-02-25 19:22:00',NULL,'constantlywaited@everyma1l.co.uk','Tia',NULL,'Juanita Lynn','0153787379','Petersen',54492,'3619999314',1,'44283',NULL,NULL),(74,'671 Pioneer Ave','Dallas','1997-07-23','1966-08-18','2012-02-25 19:22:00',NULL,'othersrabbit@ma1lbox.co.uk','Wanda',NULL,'Glenn Wilder','9498645752','Mayo',21444,'1152210487',1,'20156',NULL,NULL),(75,'1387 Miner Square','Rincon','2007-01-24','1984-01-08','2012-02-25 19:22:00',NULL,'findout@ma1lbox.us','Katrina',NULL,'Victor Delacruz','6125663888','Davis',82237,'7159106347',1,'44649',NULL,NULL),(76,'1020 Sunray Way','Buena Vista','1997-07-31','1963-12-07','2012-02-25 19:22:00',NULL,'tooktook97@everyma1l.us','Vivian',NULL,'Destiny Green','5194678032','Padilla',63130,'5974644964',1,'53039',NULL,NULL),(77,'1134 Whipple Boulevard','Bremen','2011-04-27','1957-09-27','2012-02-25 19:22:00',NULL,'discoveredtook@ma1l2u.co.uk','Pamela',NULL,'Bobby Hudson','6139992728','McCullough',95504,'5751009811',1,'54525',NULL,NULL),(78,'1220 Hunterdon Terrace','Lax','2005-06-13','1961-02-15','2012-02-25 19:22:00',NULL,'headphonesare@ma1lbox.net','Travis',NULL,'Bob Foley','6892753681','Floyd',97828,'5899185313',1,'98827',NULL,NULL),(79,'1326 Kinzel Heights','Fort Gaines','2005-07-23','1969-11-10','2012-02-25 19:22:00',NULL,'othersin@somema1l.us','Tashia',NULL,'Pam Chase','3216936785','Gibbs',75239,'1509528655',1,'20291',NULL,NULL),(80,'1767 Lakeside Rd','Hagan','2003-05-29','1979-02-13','2012-02-25 19:22:01',NULL,'wmcdonald@ma1lbox.us','Clifford',NULL,'Thomas Parrish','1492747789','Cotton',66910,'7229002256',1,'78279',NULL,NULL),(81,'1693 Pineview Way','Thomasville','2003-10-19','1972-07-06','2012-02-25 19:22:01',NULL,'mfloyd@gma1l.biz','Jodi',NULL,'Ruth Giles','7662869094','Travis',45506,'5205657623',1,'51926',NULL,NULL),(82,'1380 Southern Ln','Mora','2010-01-08','1965-04-25','2012-02-25 19:22:01',NULL,'whomagnetic@yah00.org','Sandy',NULL,'Billy Ballard','0486332923','Wallace',6237,'8511317354',1,'75406',NULL,NULL),(83,'1718 Kopchak Lane','Hartwell','2006-12-05','1984-06-08','2012-02-25 19:22:01',NULL,'it\'send@everyma1l.com','Tia',NULL,'Roxanne Payne','9694946519','Spears',10099,'2844388231',1,'78288',NULL,NULL),(84,'1757 Galighner Avenue','Axson','2007-06-29','1981-06-08','2012-02-25 19:22:01',NULL,'tochoa@somema1l.biz','Matthew',NULL,'Terry Poole','8773394788','Macias',84811,'0274501690',1,'14212',NULL,NULL),(85,'1487 Clearey Trail','Worth','1999-08-31','1978-03-29','2012-02-25 19:22:01',NULL,'nfischer84@ma1l2u.us','Daniel',NULL,'Carolyn Hicks','9795364633','Velez',54998,'5773950981',1,'92540',NULL,NULL),(86,'1247 Plainfield Park','Oglethorpe','2006-11-26','1970-08-31','2012-02-25 19:22:01',NULL,'islandred98@everyma1l.us','Abigail',NULL,'Ronald Blackwell','2065551527','Irwin',20636,'7400311070',1,'99308',NULL,NULL),(87,'1409 Princeton Crescent','Potter','2010-08-12','1968-11-11','2012-02-25 19:22:01',NULL,'endstook@ma1l2u.net','Austin',NULL,'Russell Meadows','2108449377','Pratt',68768,'6824257708',1,'39006',NULL,NULL),(88,'457 Miller Blvd','Fort Valley','2003-07-29','1978-06-23','2012-02-25 19:22:01',NULL,'illsofa@gma1l.com','Nancy',NULL,'Kevin Holmes','3060156122','Dixon',70127,'5005929542',1,'18899',NULL,NULL),(89,'1014 Hanover Avenue','Bogart','2009-10-12','1966-07-27','2012-02-25 19:22:01',NULL,'tsnider@hotma1l.net','Chelsea',NULL,'Justin Blevins','3762085455','Moreno',62966,'5260644927',1,'09358',NULL,NULL),(90,'447 Bradington Crescent','Newton','2010-06-19','1970-03-22','2012-02-25 19:22:01',NULL,'textinterest90@everyma1l.co.uk','Danielle',NULL,'Savannah Weaver','7858822630','Hunt',55802,'6180643569',1,'21636',NULL,NULL),(91,'708 Kenny Run','Albany','2008-10-10','1983-02-28','2012-02-25 19:22:01',NULL,'tdelacruz89@b1zmail.co.uk','Ethan',NULL,'Marlene Tillman','6516836296','Huff',69650,'1290085432',1,'63603',NULL,NULL),(92,'509 Carlysle Ave','Chauncey','2006-03-18','1976-01-14','2012-02-25 19:22:01',NULL,'islandis@somema1l.net','Marvin',NULL,'Zachary Browning','3132470736','Terrell',32719,'7109623021',1,'07733',NULL,NULL),(93,'1120 Shandon Way','Berlin','1998-05-17','1958-10-12','2012-02-25 19:22:01',NULL,'sburton@b1zmail.co.uk','Juanita',NULL,'Vicky Wooten','1481724025','Hickman',66745,'9359149788',1,'96682',NULL,NULL),(94,'933 Layton Ave','Johns Creek','2008-05-06','1955-03-15','2012-02-25 19:22:01',NULL,'aperez51@somema1l.biz','Tia',NULL,'Pat Bentley','4775687970','Conley',3759,'8308812599',1,'57616',NULL,NULL),(95,'838 Goslen Trail','Coolidge','2010-07-06','1984-10-07','2012-02-25 19:22:01',NULL,'interestso@somema1l.co.uk','Audrey',NULL,'Russell Gay','8983580863','Warren',92417,'4353135819',1,'57810',NULL,NULL),(96,'1219 Scarborough Trail','Coolidge','2008-01-09','1974-03-02','2012-02-25 19:22:01',NULL,'isman@ma1lbox.us','Sierra',NULL,'Valerie Smith','5859201036','Wilcox',44721,'0874276680',1,'30233',NULL,NULL),(97,'1442 Park Ridge','Dixie','1998-05-05','1967-12-28','2012-02-25 19:22:01',NULL,'soare@gma1l.biz','Betty',NULL,'Donna Pace','0385089488','Powers',11198,'0478265722',1,'56348',NULL,NULL),(98,'460 Bellflower Park','Elberton','2004-04-03','1968-05-01','2012-02-25 19:22:01',NULL,'aorr@yah00.biz','Rick',NULL,'Wanda Taylor','0861897165','Acosta',64699,'5269804432',1,'98543',NULL,NULL),(99,'666 Fowlers Court','Manassas','2011-03-11','1964-12-13','2012-02-25 19:22:01',NULL,'trichmond@everyma1l.com','Rocky',NULL,'Shelly Conway','3101437018','Knowles',77108,'3560729139',1,'77913',NULL,NULL),(100,'780 Oakland Avenue','Amboy','2000-09-12','1961-06-04','2012-02-25 19:22:01',NULL,'thecame@ma1lbox.co.uk','Brady',NULL,'Jimmy Kirkland','5633343957','Vaughn',81896,'5916343131',1,'40720',NULL,NULL),(101,'1063 Buttermilk Run','New Rock Hill','2005-01-17','1966-04-16','2012-02-25 19:22:01',NULL,'tmcmahon@gma1l.org','Jackie',NULL,'Misty Wheeler','1296009023','Cantrell',27734,'9091742038',1,'82008',NULL,NULL),(102,'1171 Tridelphia Street','Lagrange','1997-03-24','1972-01-25','2012-02-25 19:22:01',NULL,'ho\'neil@hotma1l.org','Gail',NULL,'Anita Potter','8118604412','Sims',12554,'5717120970',1,'79272',NULL,NULL),(103,'1213 Chandlersville Station','Allenhurst','2000-03-20','1958-07-30','2012-02-25 19:22:01',NULL,'itdays@hotma1l.us','Sharon',NULL,'Jason Mejia','4240608384','Horton',14857,'4364516012',1,'25243',NULL,NULL),(104,'1241 Britton Street','Bainbridge','1997-05-13','1979-12-16','2012-02-25 19:22:02',NULL,'ggarrett@hotma1l.net','Cheyenne',NULL,'Alan Coffey','9568837695','Rowland',57235,'3911523027',1,'43301',NULL,NULL),(105,'1445 South Station','Sycamore','2005-10-06','1971-09-20','2012-02-25 19:22:02',NULL,'jcollier@b1zmail.co.uk','Diana',NULL,'Sally Thompson','8821914299','Jimenez',62768,'5585043769',1,'01767',NULL,NULL),(106,'1639 Lewis Heights','Cuthbert','1997-12-05','1964-02-04','2012-02-25 19:22:02',NULL,'nmunoz@b1zmail.biz','Gene',NULL,'Steven Castaneda','7062410072','Wooten',25659,'4562554681',1,'43746',NULL,NULL),(107,'996 Harmon Drive','Ambrose','2006-03-22','1973-02-18','2012-02-25 19:22:02',NULL,'thewitt@b1zmail.net','Leslie',NULL,'Adam Poole','9738000705','Hampton',29128,'7083515798',1,'26919',NULL,NULL),(108,'1358 Leslie Road','Braselton','2002-01-21','1981-10-09','2012-02-25 19:22:02',NULL,'nlambert@yah00.org','Melody',NULL,'Shane Buchanan','9281817985','Irwin',90070,'8075353191',1,'36947',NULL,NULL),(109,'1423 Eddy Road','Warwick','2003-11-26','1984-08-03','2012-02-25 19:22:02',NULL,'planedad@ma1lbox.us','Linda',NULL,'Tasha Randolph','4382640031','Underwood',25185,'8248397123',1,'87030',NULL,NULL),(110,'884 Palamino St','Cave Spring','2005-09-03','1973-03-20','2012-02-25 19:22:02',NULL,'myates@gma1l.com','Charlotte',NULL,'Dakota Elliott','0463719940','Elliott',81067,'3214615654',1,'28818',NULL,NULL),(111,'1272 Piper Run','Dupont','2002-06-21','1980-09-05','2012-02-25 19:22:02',NULL,'interestwrite@hotma1l.co.uk','Ginger',NULL,'Max Decker','2669205453','Franco',40861,'9967947972',1,'24321',NULL,NULL),(112,'1137 Locust Court','Elberton','2002-02-25','1968-09-17','2012-02-25 19:22:02',NULL,'erandolph74@somema1l.net','Taylor',NULL,'Howard Leonard','8243692217','Mays',92036,'7444288344',1,'77691',NULL,NULL),(113,'551 Granger Street','Fort Gaines','2004-10-03','1983-09-20','2012-02-25 19:22:02',NULL,'codeor_maybe@somema1l.biz','Greg',NULL,'Shirley Burt','4271906328','Bush',86478,'1971695855',1,'47708',NULL,NULL),(114,'875 Whitman Drive','Thomasville','2002-07-03','1971-10-20','2012-02-25 19:22:02',NULL,'or_maybelost@somema1l.co.uk','Debra',NULL,'Jonathon Sparks','0659502853','Sullivan',16651,'2991141632',1,'14815',NULL,NULL),(115,'875 Detroit Way','Rockingham','2008-04-08','1984-02-26','2012-02-25 19:22:02',NULL,'skelly@b1zmail.biz','Sydney',NULL,'Jenny Diaz','2148620498','Lawrence',15451,'5673627286',1,'33663',NULL,NULL),(116,'981 Auburn Lane','Twin City','2011-06-27','1960-03-16','2012-02-25 19:22:02',NULL,'endingcaves@b1zmail.us','Chastity',NULL,'Brooklyn Sims','0015167722','Jenkins',12455,'6346540109',1,'99751',NULL,NULL),(117,'676 Flint Parkway','Dalton','2007-12-25','1958-01-11','2012-02-25 19:22:02',NULL,'aevans95@b1zmail.org','Jade',NULL,'Donnie Beasley','1508640416','Sharpe',95286,'3335142157',1,'39377',NULL,NULL),(118,'898 Julian Ave','Fayetteville','2006-07-23','1983-07-12','2012-02-25 19:22:02',NULL,'dguzman@b1zmail.us','Mike',NULL,'Paige Garrison','2399924120','Bradshaw',67504,'0557842489',1,'66016',NULL,NULL),(119,'1347 Pike Drive','Tarboro','2006-01-13','1960-11-29','2012-02-25 19:22:02',NULL,'tsykes@somema1l.com','Johnathan',NULL,'Cathy Dejesus','0216441105','Grant',65314,'9181050738',1,'39271',NULL,NULL),(120,'1592 Thornberry Street','Ludowici','2007-03-07','1973-07-21','2012-02-25 19:22:02',NULL,'sjimenez@everyma1l.com','Sean',NULL,'Jonathon Ayers','6815518112','Booth',70346,'6869218998',1,'90791',NULL,NULL),(121,'1021 Marne Avenue','Norman Park','2003-04-29','1981-07-19','2012-02-25 19:22:02',NULL,'cthornton@ma1l2u.us','Charles',NULL,'Everett Whitley','6657082036','Mayo',88470,'2037685660',1,'13119',NULL,NULL),(122,'1049 Clarendon Way','Metter','2011-02-28','1961-08-20','2012-02-25 19:22:02',NULL,'havedo@everyma1l.com','Kaylee',NULL,'Kevin Odonnell','6677278788','Richard',25165,'3486549894',1,'55155',NULL,NULL),(123,'621 Wheatland Parkway','Dahlonega','2000-09-06','1975-05-17','2012-02-25 19:22:02',NULL,'ckline74@yah00.biz','Rachel',NULL,'Pamela Klein','5180330425','Morrow',24207,'3300195336',1,'15977',NULL,NULL),(124,'1546 Peachblow Drv','Register','2004-08-28','1978-12-28','2012-02-25 19:22:02',NULL,'bpierce@somema1l.net','Kaylee',NULL,'Beth Hurst','1884860988','Sawyer',71170,'6022844275',1,'53188',NULL,NULL),(125,'1460 Tupedo Terrace','Dawson','2008-04-09','1979-07-08','2012-02-25 19:22:02',NULL,'jstevens@ma1l2u.us','Tabatha',NULL,'Brittany Petersen','7417962761','Herring',81313,'1908903039',1,'66642',NULL,NULL),(126,'867 Lark Avenue','Jeffersonville','2004-08-03','1962-05-03','2012-02-25 19:22:02',NULL,'tfranklin@gma1l.us','Sierra',NULL,'Pam Foster','5608059152','Porter',5607,'9559790506',1,'24895',NULL,NULL),(127,'816 George Station','Funston','1998-03-09','1977-04-03','2012-02-25 19:22:02',NULL,'ison@gma1l.org','Jerry',NULL,'Sherri Cline','1236860873','Hood',37083,'8265379809',1,'51246',NULL,NULL),(128,'640 Warwick Crescent','Dasher','2009-06-22','1966-03-08','2012-02-25 19:22:02',NULL,'obaxter@gma1l.biz','Tashia',NULL,'Derek Aguilar','2247298137','Alston',3412,'7026902369',1,'58295',NULL,NULL),(129,'1458 Rucker Trail','Fayetteville','2009-02-07','1961-12-20','2012-02-25 19:22:02',NULL,'speedassert@ma1l2u.com','Cathy',NULL,'Kristin Marsh','0937915862','Kline',470,'0179658352',1,'35020',NULL,NULL),(130,'1287 Buttermilk Run','Amboy','1998-07-31','1973-09-21','2012-02-25 19:22:03',NULL,'sblack@gma1l.com','Wanda',NULL,'Diane Jarvis','2168366239','Gaines',38177,'6258069346',1,'12027',NULL,NULL),(131,'1411 Moccasin Avenue','Weber','2011-09-26','1978-10-18','2012-02-25 19:22:03',NULL,'llyons@yah00.biz','Chad',NULL,'Wendy Caldwell','5342829967','Schmidt',75627,'7806031564',1,'15248',NULL,NULL),(132,'1482 Cattle Ave','Warwick','2000-10-06','1980-04-01','2012-02-25 19:22:03',NULL,'cjarvis71@everyma1l.biz','Judy',NULL,'Krista Hardin','7373121067','Sexton',71576,'5813722144',1,'93957',NULL,NULL),(133,'1657 Alexis Drv','Thomson','2004-02-18','1971-02-21','2012-02-25 19:22:03',NULL,'cpratt@ma1l2u.biz','Gage',NULL,'Sam Hahn','4152809923','Macdonald',49768,'7173442304',1,'92709',NULL,NULL),(134,'883 Marchmont Ave','Howell','2010-02-22','1958-01-03','2012-02-25 19:22:03',NULL,'ofopen@yah00.biz','Janice',NULL,'Lindsey Padilla','9771967019','Douglas',42827,'4303146376',1,'65688',NULL,NULL),(135,'905 Lefter Crescent','Lagrange','1998-11-22','1977-06-18','2012-02-25 19:22:03',NULL,'mmcneil@yah00.us','Sherri',NULL,'Norman Moses','6480818664','Clarke',55697,'5490166525',1,'59298',NULL,NULL),(136,'475 Edison Path','Union City','2004-03-19','1962-08-17','2012-02-25 19:22:03',NULL,'djordan@hotma1l.com','Becky',NULL,'Tia Baxter','7418037480','Humphrey',41634,'8867937398',1,'21803',NULL,NULL),(137,'953 Belden Road','Barretts','2011-09-27','1978-08-17','2012-02-25 19:22:03',NULL,'kwaller74@ma1l2u.us','Jay',NULL,'Logan Ellison','6061859108','Bradford',95307,'1984233353',1,'71914',NULL,NULL),(138,'1174 Bolen Trail','Iron City','2011-10-23','1958-07-22','2012-02-25 19:22:03',NULL,'cjimenez@gma1l.com','Ruth',NULL,'Chelsea Burnett','2554687537','Stein',47634,'1597587718',1,'23034',NULL,NULL),(139,'1707 Thornhill Run','Strongsville','2005-05-12','1962-09-17','2012-02-25 19:22:03',NULL,'shipleader@yah00.com','Jeannie',NULL,'Kaitlyn Weeks','5992657231','Burton',47427,'6209748035',1,'05949',NULL,NULL),(140,'1160 Rosewood Station','Ellaville','2008-05-21','1977-03-27','2012-02-25 19:22:03',NULL,'camefire@yah00.biz','Elizabeth',NULL,'Ed Landry','4602655238','Rivera',7506,'0800886904',1,'20025',NULL,NULL),(141,'1144 Pennisula Way','Euharlee','2001-11-28','1980-10-01','2012-02-25 19:22:03',NULL,'cphillips@gma1l.org','Glen',NULL,'Judy Carr','4434687375','McLean',99415,'5732796013',1,'56111',NULL,NULL),(142,'1605 Tedrick Drv','Valdosta','2001-12-03','1972-11-10','2012-02-25 19:22:03',NULL,'bsnow@hotma1l.us','Jake',NULL,'Richard Kaufman','1183937505','Stafford',57030,'0487465321',1,'48577',NULL,NULL),(143,'432 Clay Court','Parrott','2010-03-29','1984-02-26','2012-02-25 19:22:03',NULL,'twoodard44@somema1l.com','Deborah',NULL,'Donnie Decker','6936704694','Klein',29493,'9270675689',1,'08060',NULL,NULL),(144,'1243 Chesapeake Path','Boston','2009-08-09','1974-08-19','2012-02-25 19:22:03',NULL,'jmerritt@everyma1l.us','Kim',NULL,'Rob Burch','8231354968','Valencia',94586,'3127519503',1,'97965',NULL,NULL),(145,'1077 Pinkerton Place','Tarboro','2011-07-15','1959-11-24','2012-02-25 19:22:03',NULL,'endinghat@somema1l.co.uk','Elizabeth',NULL,'Elizabeth Schmidt','7322430889','Trevino',84982,'0038868241',1,'27301',NULL,NULL),(146,'429 Ransbottom Boulevard','Brooks','1999-01-30','1968-05-23','2012-02-25 19:22:03',NULL,'thefind35@yah00.com','Maria',NULL,'Sean Castillo','1607563109','Walsh',91887,'6666608647',1,'70439',NULL,NULL),(147,'553 Wargo Blvd','Sycamore','1998-11-10','1970-09-14','2012-02-25 19:22:03',NULL,'gbennett@hotma1l.net','Jerry',NULL,'Carla Barton','5313694058','Sykes',10696,'4394205342',1,'00487',NULL,NULL),(148,'1611 Beechcreek Way','Bristol','2002-07-09','1975-09-02','2012-02-25 19:22:03',NULL,'dkeller@b1zmail.com','Stacey',NULL,'Tristan Todd','0583933098','Baker',9795,'7781270899',1,'86836',NULL,NULL),(149,'1273 Edward Crescent','Port Wentworth','2002-06-04','1961-07-09','2012-02-25 19:22:03',NULL,'thisof@hotma1l.us','Gerald',NULL,'Clarence Collier','8637994843','Reeves',81572,'4973931887',1,'48566',NULL,NULL),(150,'430 Bell Station','Toomsboro','2008-05-14','1970-02-03','2012-02-25 19:22:03',NULL,'lbradshaw66@ma1l2u.biz','Lee',NULL,'Christine Delacruz','6241682671','Roth',21867,'5622357473',1,'69540',NULL,NULL),(151,'1393 Morningstar Rd','College Park','2003-02-28','1960-05-22','2012-02-25 19:22:03',NULL,'mroth@b1zmail.biz','Jake',NULL,'Vicky Hill','8876307255','Edwards',64317,'1806845634',1,'81178',NULL,NULL),(152,'1122 Montgomery Ridge','Portal','2005-07-14','1968-03-10','2012-02-25 19:22:03',NULL,'rescueare@hotma1l.com','Rochelle',NULL,'Anthony Ford','5534555508','Matthews',24351,'8152260973',1,'17314',NULL,NULL),(153,'1351 Eddy Boulevard','Locust Grove','2004-07-15','1979-06-06','2012-02-25 19:22:03',NULL,'jsharpe@yah00.org','Margaret',NULL,'Raymond Conner','3423911234','McCullough',32233,'9011147496',1,'71832',NULL,NULL),(154,'1694 Okey Park','Ebenezer','2003-12-24','1969-08-20','2012-02-25 19:22:03',NULL,'cpratt@b1zmail.us','Grace',NULL,'Michelle Cervantes','3552888409','Woodard',18562,'0896246109',1,'70106',NULL,NULL),(155,'1338 Morse Rd','Cordele','2005-02-11','1970-12-07','2012-02-25 19:22:03',NULL,'wantgenerated@b1zmail.org','Elaine',NULL,'Danielle Allison','9896828987','Yang',23135,'8252564657',1,'40531',NULL,NULL),(156,'694 Cochran Way','Chickamauga','2004-02-11','1963-05-07','2012-02-25 19:22:03',NULL,'areassert@yah00.com','Morgan',NULL,'Nick Knowles','3782553793','McMahon',72472,'7471607149',1,'37595',NULL,NULL),(157,'1126 Holbein Terrace','Winokur','2000-05-25','1963-10-02','2012-02-25 19:22:03',NULL,'thardy@b1zmail.biz','Tim',NULL,'Destiny Cooper','8378508242','Ochoa',56784,'6499886228',1,'80899',NULL,NULL),(158,'1360 Benjamin Court','Fort Stewart','2007-02-22','1963-08-22','2012-02-25 19:22:04',NULL,'holdhad75@everyma1l.biz','Jon',NULL,'Krista Preston','7211856211','Stewart',58940,'5549342141',1,'53765',NULL,NULL),(159,'508 Coronado Terrace','Lyons','1999-09-13','1984-10-04','2012-02-25 19:22:04',NULL,'incomputer@yah00.org','Leonard',NULL,'Glenn Crawford','5352111911','McDowell',25995,'1792304251',1,'39839',NULL,NULL),(160,'606 Circleville St','Thomaston','2007-10-06','1955-05-19','2012-02-25 19:22:04',NULL,'wordspull@ma1l2u.biz','Kelly',NULL,'Christopher McPherson','1320511987','Valentine',30898,'1330036667',1,'01694',NULL,NULL),(161,'1438 Shady Lane','Lumber City','2004-03-06','1965-05-11','2012-02-25 19:22:04',NULL,'mbell@b1zmail.com','Brandy',NULL,'Madison Snider','0220305171','Vang',40226,'9856953108',1,'18998',NULL,NULL),(162,'1365 Dewey Lane','Rebecca','2009-01-14','1971-06-04','2012-02-25 19:22:04',NULL,'tholland@gma1l.us','Harley',NULL,'Tyrone Warner','4267830583','Galloway',96109,'5525945843',1,'82314',NULL,NULL),(163,'1109 Dogwood Park','Retreat','2001-08-28','1955-05-26','2012-02-25 19:22:04',NULL,'findwhile@b1zmail.com','Patty',NULL,'Louis Dyer','5185034447','Dickerson',94893,'8725897843',1,'37358',NULL,NULL),(164,'417 Hannah Heights','Clayton','2007-02-18','1974-08-07','2012-02-25 19:22:04',NULL,'anicholson@hotma1l.org','Alan',NULL,'Whitney Henderson','9029052629','Greer',22571,'2117674005',1,'82358',NULL,NULL),(165,'494 Findley Ln','Buena Vista','2009-03-25','1978-11-22','2012-02-25 19:22:04',NULL,'smokelost@gma1l.org','Jody',NULL,'Dakota Anderson','5351107067','Ochoa',46890,'5191382243',1,'45715',NULL,NULL),(166,'1331 Kimes Avenue','Coverdale','1997-09-28','1969-12-06','2012-02-25 19:22:04',NULL,'cavessuspense@b1zmail.biz','Katherine',NULL,'Carla Cantrell','6710217275','Barrera',17219,'4688248057',1,'28017',NULL,NULL),(167,'485 Basil Avenue','Westwood','2005-08-10','1955-11-16','2012-02-25 19:22:04',NULL,'cespinoza@somema1l.net','Vernon',NULL,'Brooke Barker','3209677203','Chandler',17083,'1128967647',1,'42397',NULL,NULL),(168,'542 Busch Lane','Glory','2005-05-04','1973-07-22','2012-02-25 19:22:04',NULL,'headphoneswrong@b1zmail.org','Jill',NULL,'Dana Moore','8108861217','Kramer',63366,'4229969710',1,'06155',NULL,NULL),(169,'1207 Sharonwood Way','Milledgeville','2001-12-11','1963-11-17','2012-02-25 19:22:04',NULL,'endsmuch@hotma1l.org','Kristina',NULL,'Crystal Miranda','2260283177','David',71058,'7711074057',1,'42533',NULL,NULL),(170,'1171 Valley Crescent','Dallas','2011-10-15','1958-12-07','2012-02-25 19:22:04',NULL,'firedad@hotma1l.net','Trevor',NULL,'Sally Strickland','0545579665','Dickinson',21827,'4700538143',1,'12209',NULL,NULL),(171,'1101 Meriwether Terrace','Isle Of Hope-Dutch Island','2010-02-23','1967-04-09','2012-02-25 19:22:04',NULL,'redin@somema1l.us','Dixie',NULL,'Melinda Pennington','7378361792','Estrada',21917,'5119474915',1,'84265',NULL,NULL),(172,'906 Vista Heights','Sasser','1999-12-06','1982-02-02','2012-02-25 19:22:04',NULL,'ecasey@somema1l.us','Vickie',NULL,'Allison Burton','0588472710','Wiley',37869,'5400527141',1,'82759',NULL,NULL),(173,'1800 Scarborough Park','Harding','2011-08-26','1960-12-08','2012-02-25 19:22:04',NULL,'isthey@yah00.org','Karen',NULL,'Margaret Bentley','7056432524','Cortez',33896,'7686672926',1,'67215',NULL,NULL),(174,'1004 Indiana Way','Funston','2004-12-10','1962-05-09','2012-02-25 19:22:04',NULL,'goodpeople@yah00.us','Melissa',NULL,'Andrew Tran','2105527931','Walters',77336,'6467939044',1,'85857',NULL,NULL),(175,'1351 Runyan Heights','Leefield','2007-01-18','1956-04-29','2012-02-25 19:22:04',NULL,'throwpull@hotma1l.org','Brandy',NULL,'Sandra Albert','7716487984','Phelps',34525,'9948589070',1,'42061',NULL,NULL),(176,'1107 Stoutsville Path','Nashville','2004-08-08','1961-03-28','2012-02-25 19:22:04',NULL,'listare@ma1lbox.net','Summer',NULL,'Wesley Weaver','0269172836','Lopez',58582,'2880326484',1,'21108',NULL,NULL),(177,'691 Monroe Ridge','Cox','2004-10-01','1970-02-16','2012-02-25 19:22:05',NULL,'wewith@ma1lbox.net','Samuel',NULL,'Becky Valencia','4280494596','Mooney',81386,'5990363674',1,'02055',NULL,NULL),(178,'512 Dickson Street','Swainsboro','2008-07-07','1961-03-15','2012-02-25 19:22:05',NULL,'dosidekick30@ma1l2u.biz','Pamela',NULL,'Crystal McBride','1071446969','Fleming',8323,'0623673638',1,'49599',NULL,NULL),(179,'1430 Pembroke Boulevard','Tifton','2009-02-08','1966-11-24','2012-02-25 19:22:05',NULL,'inwhere@somema1l.biz','Peggy',NULL,'Brian Johnson','5515294121','Stevenson',34158,'7814773913',1,'25919',NULL,NULL),(180,'919 Cherry Ridge','Twin City','1997-08-08','1967-05-25','2012-02-25 19:22:05',NULL,'chewitt@ma1lbox.net','Dalton',NULL,'Floyd Adams','3701485026','Odonnell',7925,'3826791348',1,'11170',NULL,NULL),(181,'700 Charles Avenue','Riverdale','2003-12-02','1958-08-17','2012-02-25 19:22:05',NULL,'cschultz@hotma1l.co.uk','Mother',NULL,'Cory York','8746969062','Hinton',73033,'6111176916',1,'31716',NULL,NULL),(182,'1634 Leedom St','Temple','2000-03-19','1974-07-28','2012-02-25 19:22:05',NULL,'rpalmer@ma1lbox.co.uk','Charlie',NULL,'Peggy Dixon','4112064536','Tanner',18884,'6841624791',1,'59549',NULL,NULL),(183,'1396 Imlay St','Union City','2004-05-27','1955-03-29','2012-02-25 19:22:05',NULL,'peopleshould@everyma1l.co.uk','Leonard',NULL,'Marvin Boyle','8111375657','Chambers',99561,'1839898098',1,'05194',NULL,NULL),(184,'794 Howell Heights','Lafayette','2005-10-10','1965-08-22','2012-02-25 19:22:05',NULL,'clynch@ma1lbox.biz','Audrey',NULL,'Vickie Ryan','1553137364','Cochran',15227,'6435549730',1,'31821',NULL,NULL),(185,'1715 Ryan Park','Trudie','2007-09-21','1969-05-23','2012-02-25 19:22:05',NULL,'jmccarty@everyma1l.biz','Gary',NULL,'Tara Clarke','0453656887','Pacheco',60357,'5594863064',1,'22448',NULL,NULL),(186,'1340 Laurel Station','Lakeland','2009-01-14','1966-07-10','2012-02-25 19:22:05',NULL,'kkline@yah00.net','Shaun',NULL,'Alice Velasquez','4585046906','Bowers',13794,'7599513816',1,'34113',NULL,NULL),(187,'1425 Pointe Terrace','Leefield','2002-01-26','1983-06-22','2012-02-25 19:22:05',NULL,'whathatch@somema1l.org','Eric',NULL,'Christina Harris','9842808066','Hardin',59496,'0195063035',1,'55288',NULL,NULL),(188,'868 Cowden Ave','Tybee Island','2008-02-09','1957-06-05','2012-02-25 19:22:05',NULL,'raguirre@gma1l.co.uk','Kristina',NULL,'Glen Terrell','7234179614','Austin',57357,'9349259230',1,'74048',NULL,NULL),(189,'1158 Higgins Street','Canton','2003-12-02','1969-09-18','2012-02-25 19:22:05',NULL,'or_maybeisland@ma1l2u.net','Kylie',NULL,'Rex Phillips','3953411762','Lambert',87419,'0176093473',1,'96905',NULL,NULL),(190,'978 Lesslar Park','Bushnell','2009-07-09','1970-09-25','2012-02-25 19:22:05',NULL,'aaguilar7@yah00.biz','Martha',NULL,'Jessica Cantu','1500235596','Carrillo',13537,'7096878659',1,'29171',NULL,NULL),(191,'1415 Ritenour Terrace','Lakeland','2002-06-23','1968-09-22','2012-02-25 19:22:05',NULL,'elsethey@b1zmail.org','Melinda',NULL,'Alissa Bray','3925368530','Singleton',98195,'4310699140',1,'34959',NULL,NULL),(192,'432 Wheelabout Park','Clayton','2002-01-23','1956-01-31','2012-02-25 19:22:05',NULL,'ddillard68@gma1l.org','Harley',NULL,'Allen Harmon','6765189371','Ortiz',80787,'2342962364',1,'43311',NULL,NULL),(193,'608 Price Court','Poulan','2001-08-24','1971-03-19','2012-02-25 19:22:05',NULL,'islanddied@ma1l2u.com','Austin',NULL,'George Potter','7713258446','Pate',96193,'6057437808',1,'69633',NULL,NULL),(194,'1313 Brookside Ave','Eulonia','2000-04-14','1977-11-22','2012-02-25 19:22:05',NULL,'listcode0@ma1lbox.biz','Robin',NULL,'Rebecca Hensley','4782081063','Holmes',31647,'1225297123',1,'17123',NULL,NULL),(195,'1558 Palmwood Park','Pelham','2003-12-12','1961-07-22','2012-02-25 19:22:05',NULL,'constantlyto@gma1l.org','Marilyn',NULL,'Cheryl Avery','2205459394','Noel',69,'4100443480',1,'03972',NULL,NULL),(196,'538 Evelyn Crescent','Fairburn','1999-01-21','1957-12-14','2012-02-25 19:22:05',NULL,'cfarrell@yah00.net','Candy',NULL,'Eric Steele','0930801159','Calhoun',46676,'8829769113',1,'54700',NULL,NULL),(197,'1560 Bowtown Road','Parrott','1998-05-16','1970-10-29','2012-02-25 19:22:05',NULL,'jbuck@everyma1l.us','Jennifer',NULL,'Martin Fisher','3018834371','Le',28791,'8084617255',1,'88513',NULL,NULL),(198,'1749 Stephens Street','Rockmart','2003-04-10','1972-07-07','2012-02-25 19:22:05',NULL,'btravis@hotma1l.com','Wesley',NULL,'Samantha Fernandez','8057823555','Todd',49942,'7668773197',1,'16755',NULL,NULL),(199,'638 Long Ridge','Rochelle','2005-10-09','1979-05-21','2012-02-25 19:22:05',NULL,'vmontgomery25@b1zmail.co.uk','Jade',NULL,'Eva William','6674139127','Ellis',38486,'1922898166',1,'41245',NULL,NULL),(200,'686 Sherborne Drive','Donalsonville','2004-02-08','1955-06-06','2012-02-25 19:22:05',NULL,'nhill@somema1l.org','Jacob',NULL,'Kristy Wiley','2483721804','Johnson',20756,'5826972768',1,'58919',NULL,NULL),(201,'1076 Kenny St','Centerville','2000-04-16','1977-06-18','2012-02-25 19:22:05',NULL,'aboutno@gma1l.org','Dallas',NULL,'Larry Ashley','8755939132','McCall',83321,'8513698944',1,'65424',NULL,NULL),(202,'1251 Sunset Drv','Colesburg','2005-12-18','1980-09-05','2012-02-25 19:22:05',NULL,'pjames23@somema1l.net','Randy',NULL,'Stephen Glover','5758790735','George',89863,'7112789117',1,'69634',NULL,NULL);
/*!40000 ALTER TABLE `fam_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_player_position`
--

DROP TABLE IF EXISTS `fam_player_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_player_position` (
  `num_order` int(11) NOT NULL,
  `id_player` bigint(20) NOT NULL,
  `id_position` bigint(20) NOT NULL,
  PRIMARY KEY (`num_order`,`id_player`,`id_position`),
  KEY `FK_fam_player_position_id_position` (`id_position`),
  KEY `FK_fam_player_position_id_player` (`id_player`),
  CONSTRAINT `FK_fam_player_position_id_player` FOREIGN KEY (`id_player`) REFERENCES `fam_player` (`id_player`),
  CONSTRAINT `FK_fam_player_position_id_position` FOREIGN KEY (`id_position`) REFERENCES `fam_position` (`id_position`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_player_position`
--

LOCK TABLES `fam_player_position` WRITE;
/*!40000 ALTER TABLE `fam_player_position` DISABLE KEYS */;
INSERT INTO `fam_player_position` VALUES (0,5,91),(0,23,91),(0,55,91),(0,65,91),(0,98,91),(0,102,91),(0,121,91),(0,143,91),(0,144,91),(0,161,91),(0,182,91),(0,189,91),(0,194,91),(1,14,91),(1,42,91),(1,44,91),(1,81,91),(1,82,91),(2,24,91),(2,33,91),(2,39,91),(2,138,91),(2,196,91),(3,31,91),(3,44,91),(3,119,91),(3,163,91),(0,10,92),(0,25,92),(0,30,92),(0,48,92),(0,58,92),(0,61,92),(0,74,92),(0,96,92),(0,108,92),(0,112,92),(0,152,92),(1,51,92),(1,101,92),(1,112,92),(1,113,92),(1,134,92),(1,138,92),(1,144,92),(1,163,92),(2,71,92),(2,111,92),(0,1,93),(0,8,93),(0,38,93),(0,49,93),(0,50,93),(0,51,93),(0,66,93),(0,73,93),(0,77,93),(0,87,93),(0,116,93),(0,150,93),(0,153,93),(0,163,93),(0,173,93),(0,174,93),(0,181,93),(0,201,93),(1,7,93),(1,84,93),(1,100,93),(1,155,93),(1,164,93),(1,173,93),(1,178,93),(1,187,93),(2,27,93),(2,123,93),(2,143,93),(3,1,93),(3,15,93),(3,17,93),(3,101,93),(0,27,94),(0,29,94),(0,32,94),(0,79,94),(0,99,94),(0,120,94),(0,126,94),(0,130,94),(0,136,94),(0,195,94),(1,13,94),(1,29,94),(1,34,94),(1,37,94),(1,65,94),(1,74,94),(1,122,94),(1,168,94),(1,190,94),(1,196,94),(2,13,94),(2,62,94),(2,82,94),(2,88,94),(2,101,94),(2,110,94),(2,133,94),(2,141,94),(3,84,94),(4,62,94),(4,198,94),(0,71,95),(0,82,95),(0,168,95),(0,186,95),(1,1,95),(1,32,95),(1,46,95),(1,87,95),(1,120,95),(2,163,95),(2,188,95),(3,62,95),(3,123,95),(0,4,96),(0,45,96),(0,64,96),(0,72,96),(0,84,96),(0,124,96),(0,132,96),(0,133,96),(0,134,96),(0,162,96),(0,166,96),(1,17,96),(1,21,96),(1,24,96),(1,58,96),(1,70,96),(1,111,96),(1,158,96),(2,34,96),(2,103,96),(2,192,96),(3,196,96),(3,198,96),(0,15,97),(0,68,97),(0,95,97),(0,105,97),(0,110,97),(0,138,97),(0,147,97),(0,188,97),(1,36,97),(1,62,97),(1,104,97),(1,182,97),(1,186,97),(1,189,97),(1,197,97),(1,200,97),(2,4,97),(2,65,97),(3,65,97),(0,18,98),(0,52,98),(0,75,98),(0,94,98),(0,135,98),(0,137,98),(0,155,98),(0,160,98),(0,165,98),(0,177,98),(0,187,98),(0,197,98),(1,56,98),(1,119,98),(1,131,98),(1,141,98),(1,148,98),(2,7,98),(2,37,98),(2,46,98),(2,108,98),(2,122,98),(2,164,98),(0,9,99),(0,16,99),(0,17,99),(0,24,99),(0,70,99),(0,78,99),(0,91,99),(0,179,99),(0,191,99),(1,30,99),(1,38,99),(1,49,99),(1,66,99),(1,71,99),(1,96,99),(1,103,99),(1,156,99),(1,160,99),(1,198,99),(2,144,99),(3,71,99),(0,13,100),(0,44,100),(0,47,100),(0,57,100),(0,62,100),(0,69,100),(0,81,100),(0,122,100),(0,123,100),(0,125,100),(0,156,100),(0,159,100),(0,169,100),(0,190,100),(0,192,100),(1,33,100),(1,63,100),(1,116,100),(1,143,100),(1,192,100),(1,199,100),(2,1,100),(2,17,100),(2,25,100),(2,50,100),(2,70,100),(2,84,100),(2,107,100),(2,153,100),(2,202,100),(3,34,100),(3,188,100),(0,7,101),(0,12,101),(0,14,101),(0,31,101),(0,35,101),(0,88,101),(0,92,101),(0,113,101),(0,127,101),(0,131,101),(0,178,101),(0,200,101),(1,9,101),(1,19,101),(1,39,101),(1,126,101),(1,127,101),(1,159,101),(1,169,101),(1,195,101),(2,131,101),(0,2,102),(0,3,102),(0,20,102),(0,33,102),(0,36,102),(0,42,102),(0,83,102),(0,100,102),(0,117,102),(0,148,102),(0,196,102),(0,199,102),(1,4,102),(1,5,102),(1,25,102),(1,27,102),(1,50,102),(1,64,102),(1,107,102),(1,121,102),(1,123,102),(1,124,102),(1,130,102),(1,133,102),(1,174,102),(1,202,102),(2,21,102),(2,36,102),(2,121,102),(2,198,102),(3,88,102),(0,39,103),(0,53,103),(0,85,103),(0,101,103),(0,119,103),(0,141,103),(0,154,103),(0,176,103),(1,69,103),(1,73,103),(1,88,103),(1,102,103),(1,105,103),(1,108,103),(1,139,103),(1,166,103),(2,19,103),(2,31,103),(2,92,103),(2,148,103),(3,133,103),(3,192,103),(0,6,104),(0,19,104),(0,21,104),(0,34,104),(0,37,104),(0,41,104),(0,46,104),(0,63,104),(0,111,104),(0,128,104),(0,139,104),(0,149,104),(0,158,104),(0,164,104),(0,202,104),(1,8,104),(1,15,104),(1,20,104),(1,31,104),(1,110,104),(1,177,104),(1,188,104),(2,5,104),(2,119,104),(2,134,104),(2,169,104),(4,70,104),(0,56,105),(0,90,105),(0,103,105),(0,104,105),(0,107,105),(0,118,105),(0,184,105),(0,198,105),(1,92,105),(1,153,105),(1,165,105),(2,15,105),(2,44,105),(2,64,105),(2,197,105),(3,19,105),(3,24,105),(3,70,105);
/*!40000 ALTER TABLE `fam_player_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_player_season`
--

DROP TABLE IF EXISTS `fam_player_season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_player_season` (
  `att` int(11) DEFAULT NULL,
  `def` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `phy` int(11) DEFAULT NULL,
  `profile_chart_url` varchar(1048) DEFAULT NULL,
  `pui` int(11) DEFAULT NULL,
  `tec` int(11) DEFAULT NULL,
  `vit` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `avg_assist_per_match` decimal(38,0) DEFAULT NULL,
  `avg_goal_per_match` decimal(38,0) DEFAULT NULL,
  `avg_note` decimal(38,0) DEFAULT NULL,
  `dt_modif_stat` datetime DEFAULT NULL,
  `nb_assist` int(11) DEFAULT NULL,
  `nb_goal` int(11) DEFAULT NULL,
  `nb_match` int(11) DEFAULT NULL,
  `nb_substitute` int(11) DEFAULT NULL,
  `nb_workout` int(11) DEFAULT NULL,
  `time_played` int(11) DEFAULT NULL,
  `id_club` bigint(20) DEFAULT NULL,
  `id_player` bigint(20) NOT NULL,
  `id_season` bigint(20) NOT NULL,
  `id_team` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_player`,`id_season`),
  KEY `FK_fam_player_season_id_team` (`id_team`),
  KEY `FK_fam_player_season_id_club` (`id_club`),
  KEY `FK_fam_player_season_id_season` (`id_season`),
  CONSTRAINT `FK_fam_player_season_id_club` FOREIGN KEY (`id_club`) REFERENCES `fam_club` (`id_club`),
  CONSTRAINT `FK_fam_player_season_id_player` FOREIGN KEY (`id_player`) REFERENCES `fam_player` (`id_player`),
  CONSTRAINT `FK_fam_player_season_id_season` FOREIGN KEY (`id_season`) REFERENCES `fam_season` (`id_season`),
  CONSTRAINT `FK_fam_player_season_id_team` FOREIGN KEY (`id_team`) REFERENCES `fam_team` (`id_team`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_player_season`
--

LOCK TABLES `fam_player_season` WRITE;
/*!40000 ALTER TABLE `fam_player_season` DISABLE KEYS */;
INSERT INTO `fam_player_season` VALUES (43,48,186,68,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bhyjrheuXCqPbh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',36,79,66,92,0,0,0,NULL,0,0,0,0,0,0,64,1,32,NULL),(73,44,156,28,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:utQpR7cKszdcut&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',70,26,46,110,0,0,0,NULL,0,0,0,0,0,0,65,2,32,181),(75,51,160,27,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:v.QARSgotbjMv.&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',71,25,55,95,0,0,0,NULL,0,0,0,0,0,0,64,3,32,NULL),(42,37,199,51,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:a4rhgoXrwoj1a4&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',76,68,56,72,0,0,0,NULL,0,0,0,0,0,0,64,4,32,NULL),(46,44,165,27,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:dceuRScKyjRSdc&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',79,48,27,76,0,0,0,NULL,0,0,0,0,0,0,65,5,32,NULL),(59,44,174,35,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lwlHWZcKfXUelw&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',49,58,32,114,0,0,0,NULL,0,0,0,0,0,0,68,6,32,NULL),(38,31,184,71,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:YUUetbT1VHhRYU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',33,32,52,91,0,0,0,NULL,0,0,0,0,0,0,67,7,32,NULL),(53,72,176,34,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:h6q4VwuEkeczh6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',57,67,45,76,0,0,0,NULL,0,0,0,0,0,0,68,8,32,NULL),(27,61,163,28,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:RSnCR7nCXCY9RS&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',36,61,39,109,0,0,0,NULL,0,0,0,0,0,0,61,9,32,NULL),(36,73,161,67,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:XCQAq4utcKwoXC&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',44,25,76,108,0,0,0,NULL,0,0,0,0,0,0,68,10,32,NULL),(58,35,207,54,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lHeuijWZWZYUlH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',35,48,38,68,0,0,0,NULL,0,0,0,0,0,0,70,11,32,NULL),(58,74,158,65,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lHUepmvWWZVwlH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',35,32,34,74,0,0,0,NULL,0,0,0,0,0,0,63,12,32,NULL),(50,71,185,60,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:gAcKmZtbrhhRgA&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',68,44,52,86,0,0,0,NULL,0,0,0,0,0,0,62,13,32,NULL),(42,66,205,72,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:a4euuEqPjMVwa4&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',55,48,34,88,0,0,0,NULL,0,0,0,0,0,0,63,14,32,NULL),(56,59,172,60,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:j1aPmZlwh6h6j1&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',53,41,53,79,0,0,0,NULL,0,0,0,0,0,0,63,15,32,NULL),(29,74,176,44,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:SkxRcKvWszq4Sk&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',70,77,67,82,0,0,0,NULL,0,0,0,0,0,0,66,16,32,NULL),(63,50,165,75,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:oUSkv.gAfXY9oU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',49,29,39,101,0,0,0,NULL,0,0,0,0,0,0,70,17,32,NULL),(35,27,184,79,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:WZyjyjRSYUq4WZ&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',38,79,67,82,0,0,0,NULL,0,0,0,0,0,0,66,18,32,NULL),(29,57,169,34,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:SkqPVwkedcx6Sk&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',46,66,78,105,0,0,0,NULL,0,0,0,0,0,0,69,19,32,NULL),(30,70,182,32,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:TNnCUeszx6fXTN&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',78,61,49,77,0,0,0,NULL,0,0,0,0,0,0,64,20,32,NULL),(53,56,157,47,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:h6Y9eFj1keczh6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',57,39,45,90,0,0,0,NULL,0,0,0,0,0,0,64,21,32,NULL),(46,75,194,73,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:dcZmutv.q4xRdc&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',67,40,77,69,0,0,0,NULL,0,0,0,0,0,0,70,22,32,NULL),(52,27,179,60,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:hRSkmZRSZmczhR&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',40,29,45,107,0,0,0,NULL,0,0,0,0,0,0,68,23,32,NULL),(47,44,193,78,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:eFcKx6cKszfXeF&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',70,44,49,86,0,0,0,NULL,0,0,0,0,0,0,64,24,32,NULL),(55,29,205,67,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:jMbhq4SkZmXrjM&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',40,43,37,77,0,0,0,NULL,0,0,0,0,0,0,62,25,32,NULL),(78,29,190,57,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:x6q4keSkxRv.x6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',77,67,75,114,0,0,0,NULL,0,0,0,0,0,0,62,26,32,NULL),(43,41,169,78,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bhlwx6aPwoVHbh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',76,59,33,66,0,0,0,NULL,0,0,0,0,0,0,61,27,32,NULL),(32,41,153,36,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Uea4XCaPeFgoUe&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',47,42,51,84,0,0,0,NULL,0,0,0,0,0,0,64,28,32,NULL),(54,59,183,39,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:ija4Y9lwRSUeij&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',27,42,32,77,0,0,0,NULL,0,0,0,0,0,0,62,29,32,NULL),(51,75,155,73,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:goWZutv.oUoUgo&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',63,35,63,81,0,0,0,NULL,0,0,0,0,0,0,62,30,32,NULL),(30,50,193,28,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:TNlHR7gAeuSkTN&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',48,58,29,92,0,0,0,NULL,0,0,0,0,0,0,67,31,32,NULL),(66,59,168,37,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:qPszXrlwhRlwqP&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',52,70,59,111,0,0,0,NULL,0,0,0,0,0,0,70,32,32,NULL),(78,41,174,35,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:x6dcWZaPZmq4x6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',40,46,67,114,0,0,0,NULL,0,0,0,0,0,0,61,33,32,NULL),(74,25,185,35,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:vWnrWZQAhRkevW&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',52,62,57,119,0,0,0,NULL,0,0,0,0,0,0,65,34,32,NULL),(45,74,156,40,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:czlHZmvWVHj1cz&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',33,58,56,81,0,0,0,NULL,0,0,0,0,0,0,61,35,32,NULL),(43,67,179,59,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bheulwq4woeubh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',76,48,48,109,0,0,0,NULL,0,0,0,0,0,0,69,36,32,NULL),(70,39,178,36,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:sznrXCY9R7szsz&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',28,62,70,119,0,0,0,NULL,0,0,0,0,0,0,63,37,32,NULL),(46,71,172,37,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:dclwXrtbXrqPdc&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',37,59,66,70,0,0,0,NULL,0,0,0,0,0,0,67,38,32,NULL),(30,43,164,28,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:TNxRR7bhjMrhTN&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',55,77,68,69,0,0,0,NULL,0,0,0,0,0,0,65,39,32,NULL),(32,26,206,44,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:UeXCcKQpZmijUe&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',40,36,54,81,0,0,0,NULL,0,0,0,0,0,0,66,40,32,NULL),(38,46,158,37,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:YUmZXrdcnCsKYU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',61,60,69,119,0,0,0,NULL,0,0,0,0,0,0,63,41,32,NULL),(78,42,179,78,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:x6WZx6a4QAnCx6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',25,35,61,101,0,0,0,NULL,0,0,0,0,0,0,69,42,32,NULL),(31,77,205,49,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:T1aPfXxRVwj1T1&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,41,56,66,0,0,0,NULL,0,0,0,0,0,0,67,43,32,NULL),(71,58,197,36,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:tbfXXClHlwrhtb&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',59,49,68,106,0,0,0,NULL,0,0,0,0,0,0,63,44,32,NULL),(65,68,177,31,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:pmdcT1rhkeeupm&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',57,46,48,86,0,0,0,NULL,0,0,0,0,0,0,69,45,32,NULL),(40,64,178,25,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:ZmpmQAo9QAaPZm&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',25,65,41,110,0,0,0,NULL,0,0,0,0,0,0,69,46,32,NULL),(28,53,200,63,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:R7vWoUh6mZj1R7&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',60,74,56,103,0,0,0,NULL,0,0,0,0,0,0,63,47,32,NULL),(78,69,187,36,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:x6XCXCsKeuczx6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',48,36,45,70,0,0,0,NULL,0,0,0,0,0,0,61,48,32,NULL),(50,65,200,47,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:gAijeFpmdcrhgA&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',46,54,68,117,0,0,0,NULL,0,0,0,0,0,0,65,49,32,NULL),(39,54,184,61,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Y9QAnCijtbQAY9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',71,25,25,66,0,0,0,NULL,0,0,0,0,0,0,62,50,32,NULL),(37,75,190,39,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:XrYUY9v.pmyjXr&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',65,38,79,100,0,0,0,NULL,0,0,0,0,0,0,70,51,32,NULL),(70,53,181,72,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:szaPuEh6goeusz&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',51,41,48,87,0,0,0,NULL,0,0,0,0,0,0,68,52,32,NULL),(75,36,189,42,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:v.oUa4XCmZXrv.&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',60,63,37,93,0,0,0,NULL,0,0,0,0,0,0,70,53,32,NULL),(48,56,161,58,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:euaPlHj1Uex6eu&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',32,41,78,79,0,0,0,NULL,0,0,0,0,0,0,62,54,32,NULL),(61,48,191,77,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:nCRSxReuSkwonC&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',29,27,76,95,0,0,0,NULL,0,0,0,0,0,0,67,55,32,NULL),(77,28,163,33,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:xRmZVHR7a4kexR&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',42,60,57,82,0,0,0,NULL,0,0,0,0,0,0,69,56,32,NULL),(57,49,151,68,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:keRSrhfXWZQpke&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',35,27,26,81,0,0,0,NULL,0,0,0,0,0,0,64,57,32,NULL),(58,50,193,64,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lHRSo9gAVwutlH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,27,73,70,0,0,0,NULL,0,0,0,0,0,0,64,58,32,NULL),(64,78,187,67,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:o9euq4x6eFZmo9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',47,48,40,76,0,0,0,NULL,0,0,0,0,0,0,64,59,32,NULL),(73,32,202,60,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:utsKmZUehRdcut&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',52,69,46,111,0,0,0,NULL,0,0,0,0,0,0,61,60,32,NULL),(46,42,192,74,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:dclHvWa4XrSkdc&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',37,58,29,117,0,0,0,NULL,0,0,0,0,0,0,69,61,32,NULL),(41,47,193,61,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:aPo9nCeFWZijaP&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',35,64,54,79,0,0,0,NULL,0,0,0,0,0,0,70,62,32,NULL),(51,39,186,35,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:goZmWZY9R7rhgo&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',28,40,68,91,0,0,0,NULL,0,0,0,0,0,0,62,63,32,NULL),(45,27,177,39,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:czutY9RSRSqPcz&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',27,73,66,115,0,0,0,NULL,0,0,0,0,0,0,66,64,32,NULL),(41,25,183,76,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:aPdcwoQAsKTNaP&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',69,46,30,93,0,0,0,NULL,0,0,0,0,0,0,61,65,32,NULL),(78,72,174,53,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:x6jMh6uExRlHx6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',77,55,58,77,0,0,0,NULL,0,0,0,0,0,0,63,66,32,NULL),(73,68,162,29,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:utWZSkrhqPdcut&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',66,35,46,68,0,0,0,NULL,0,0,0,0,0,0,66,67,32,NULL),(39,56,171,26,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Y9QAQpj1czTNY9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',45,25,30,81,0,0,0,NULL,0,0,0,0,0,0,63,68,32,NULL),(35,38,197,35,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:WZQAWZYUj1WZWZ&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',56,25,35,67,0,0,0,NULL,0,0,0,0,0,0,67,69,32,NULL),(74,41,184,39,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:vWutY9aPoUY9vW&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',63,73,39,70,0,0,0,NULL,0,0,0,0,0,0,63,70,32,NULL),(75,78,205,28,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:v.VHR7x6dceuv.&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',46,33,48,84,0,0,0,NULL,0,0,0,0,0,0,62,71,32,NULL),(66,63,173,48,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:qPszeuoUxRyjqP&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',77,70,79,119,0,0,0,NULL,0,0,0,0,0,0,62,72,32,NULL),(35,50,151,61,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:WZsKnCgAnreFWZ&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',62,69,47,98,0,0,0,NULL,0,0,0,0,0,0,61,73,32,NULL),(56,77,201,41,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:j1eFaPxRgobhj1&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',51,47,43,95,0,0,0,NULL,0,0,0,0,0,0,66,74,32,NULL),(36,77,204,53,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:XCgoh6xRnCSkXC&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',61,51,29,98,0,0,0,NULL,0,0,0,0,0,0,70,75,32,NULL),(26,57,154,36,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Qph6XCkeczwoQp&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',45,53,76,92,0,0,0,NULL,0,0,0,0,0,0,61,76,32,NULL),(76,57,190,58,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:woTNlHkeo9a4wo&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',64,30,42,72,0,0,0,NULL,0,0,0,0,0,0,68,77,32,NULL),(61,33,202,45,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:nCutczVHyjrhnC&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',79,73,68,107,0,0,0,NULL,0,0,0,0,0,0,62,78,32,NULL),(54,79,150,62,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:ijmZnryjx6sKij&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',78,60,69,93,0,0,0,NULL,0,0,0,0,0,0,66,79,32,NULL),(37,37,152,69,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Xro9sKXrUeaPXr&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',32,64,41,87,0,0,0,NULL,0,0,0,0,0,0,68,80,32,NULL),(43,59,180,72,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bhY9uElweFx6bh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',47,39,78,86,0,0,0,NULL,0,0,0,0,0,0,69,81,32,NULL),(70,47,165,50,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:szSkgAeFv.lwsz&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',75,29,59,101,0,0,0,NULL,0,0,0,0,0,0,70,82,32,NULL),(35,44,166,61,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:WZxRnCcKbhR7WZ&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',43,77,28,113,0,0,0,NULL,0,0,0,0,0,0,68,83,32,NULL),(78,76,173,46,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:x6pmdcwolwqPx6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',59,65,66,70,0,0,0,NULL,0,0,0,0,0,0,70,84,32,NULL),(38,63,204,25,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:YUqPQAoUmZWZYU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',60,66,35,110,0,0,0,NULL,0,0,0,0,0,0,67,85,32,NULL),(44,32,197,68,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:cKa4rhUekeuEcK&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',57,42,72,106,0,0,0,NULL,0,0,0,0,0,0,65,86,32,NULL),(54,39,202,41,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:ijR7aPY9qPXCij&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',66,28,36,86,0,0,0,NULL,0,0,0,0,0,0,63,87,32,NULL),(25,66,160,74,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:QAwovWqPVHsKQA&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',33,76,69,108,0,0,0,NULL,0,0,0,0,0,0,70,88,32,NULL),(70,55,186,47,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:szv.eFjMQAmZsz&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',25,75,60,96,0,0,0,NULL,0,0,0,0,0,0,65,89,32,NULL),(49,25,157,51,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:fXxRgoQAmZSkfX&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',60,77,29,67,0,0,0,NULL,0,0,0,0,0,0,67,90,32,NULL),(63,67,183,61,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:oUutnCq4qPutoU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',66,73,73,94,0,0,0,NULL,0,0,0,0,0,0,66,91,32,NULL),(34,35,179,34,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Vwx6VwWZVwh6Vw&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,78,53,86,0,0,0,NULL,0,0,0,0,0,0,67,92,32,NULL),(42,53,195,33,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:a4bhVHh6UeWZa4&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',32,43,35,98,0,0,0,NULL,0,0,0,0,0,0,69,93,32,NULL),(44,73,196,34,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:cKkeVwutZmrhcK&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',40,57,68,95,0,0,0,NULL,0,0,0,0,0,0,64,94,32,NULL),(46,25,194,41,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:dcRSaPQASko9dc&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',29,27,64,93,0,0,0,NULL,0,0,0,0,0,0,68,95,32,NULL),(41,35,205,41,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:aPsKaPWZkeyjaP&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',57,69,79,83,0,0,0,NULL,0,0,0,0,0,0,68,96,32,NULL),(75,49,189,55,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:v.gAjMfXnCfXv.&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',61,50,49,92,0,0,0,NULL,0,0,0,0,0,0,65,97,32,NULL),(26,35,189,52,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:QpyjhRWZR7qPQp&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',28,79,66,93,0,0,0,NULL,0,0,0,0,0,0,69,98,32,NULL),(35,37,153,32,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:WZUeUeXrgATNWZ&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',50,32,30,111,0,0,0,NULL,0,0,0,0,0,0,66,99,32,NULL),(59,27,155,79,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lwgoyjRSjMyjlw&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',55,51,79,112,0,0,0,NULL,0,0,0,0,0,0,63,100,32,NULL),(33,76,200,64,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:VHqPo9woY9eFVH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',39,66,47,74,0,0,0,NULL,0,0,0,0,0,0,68,101,32,NULL),(67,53,151,68,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:q4VHrhh6uEjMq4&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',72,33,55,93,0,0,0,NULL,0,0,0,0,0,0,61,102,32,NULL),(49,26,188,64,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:fXh6o9QpxRwofX&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',77,53,76,83,0,0,0,NULL,0,0,0,0,0,0,62,103,32,NULL),(61,38,207,49,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:nCT1fXYUbhh6nC&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',43,31,53,119,0,0,0,NULL,0,0,0,0,0,0,67,104,32,NULL),(54,64,162,66,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:ijUeqPo9gAhRij&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',50,32,52,86,0,0,0,NULL,0,0,0,0,0,0,66,105,32,NULL),(44,70,160,46,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:cKh6dcszVwgAcK&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,53,50,119,0,0,0,NULL,0,0,0,0,0,0,62,106,32,NULL),(39,28,177,67,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Y9hRq4R7ZmqPY9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',40,52,66,103,0,0,0,NULL,0,0,0,0,0,0,61,107,32,NULL),(65,66,188,52,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:pmqPhRqPq4QApm&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',67,66,25,87,0,0,0,NULL,0,0,0,0,0,0,67,108,32,NULL),(52,37,159,55,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:hRwojMXrdcuEhR&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',46,76,72,68,0,0,0,NULL,0,0,0,0,0,0,67,109,32,NULL),(30,73,208,32,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:TNpmUeuth6pmTN&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',53,65,65,111,0,0,0,NULL,0,0,0,0,0,0,65,110,32,NULL),(33,45,184,54,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:VHlwijczSkdcVH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',29,59,46,65,0,0,0,NULL,0,0,0,0,0,0,64,111,32,NULL),(63,48,186,35,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:oUjMWZeuVwx6oU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,55,78,105,0,0,0,NULL,0,0,0,0,0,0,62,112,32,NULL),(58,47,174,46,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lHwodceFaPh6lH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',41,76,53,90,0,0,0,NULL,0,0,0,0,0,0,66,113,32,NULL),(50,72,199,43,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:gAvWbhuEwoXCgA&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',76,74,36,71,0,0,0,NULL,0,0,0,0,0,0,64,114,32,NULL),(60,68,179,56,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:mZuEj1rhutlHmZ&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',73,72,58,115,0,0,0,NULL,0,0,0,0,0,0,62,115,32,NULL),(50,72,160,46,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:gAmZdcuEuEWZgA&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',72,60,35,79,0,0,0,NULL,0,0,0,0,0,0,69,116,32,NULL),(63,60,159,32,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:oUczUemZTNvWoU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',30,45,74,98,0,0,0,NULL,0,0,0,0,0,0,61,117,32,NULL),(42,28,200,30,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:a4dcTNR7nrhRa4&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',62,46,52,106,0,0,0,NULL,0,0,0,0,0,0,68,118,32,NULL),(40,25,206,78,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:ZmR7x6QAY9nCZm&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',39,28,61,106,0,0,0,NULL,0,0,0,0,0,0,66,119,32,NULL),(60,48,189,58,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:mZuElHeuszczmZ&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',70,72,45,82,0,0,0,NULL,0,0,0,0,0,0,65,120,32,NULL),(63,27,176,28,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:oUyjR7RSpmaPoU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',65,79,41,84,0,0,0,NULL,0,0,0,0,0,0,62,121,32,NULL),(76,50,195,63,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:woczoUgAqPo9wo&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',66,45,64,111,0,0,0,NULL,0,0,0,0,0,0,63,122,32,NULL),(78,37,157,64,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:x6Zmo9Xrx6bhx6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',78,40,43,106,0,0,0,NULL,0,0,0,0,0,0,64,123,32,NULL),(38,51,163,41,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:YUnraPgoVwR7YU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,62,28,88,0,0,0,NULL,0,0,0,0,0,0,62,124,32,NULL),(37,74,198,66,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:XrczqPvWRSnrXr&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',27,45,62,84,0,0,0,NULL,0,0,0,0,0,0,66,125,32,NULL),(63,32,167,74,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:oUoUvWUejMo9oU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',55,63,64,108,0,0,0,NULL,0,0,0,0,0,0,65,126,32,NULL),(58,76,153,48,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lHlHeuwoQAXClH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',25,58,36,69,0,0,0,NULL,0,0,0,0,0,0,64,127,32,NULL),(26,76,176,47,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:QpxReFwoqPgAQp&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',66,77,50,105,0,0,0,NULL,0,0,0,0,0,0,66,128,32,NULL),(51,28,180,26,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:goY9QpR7QAkego&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',25,39,57,97,0,0,0,NULL,0,0,0,0,0,0,67,129,32,NULL),(64,71,159,45,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:o9RScztbVwcKo9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,27,44,73,0,0,0,NULL,0,0,0,0,0,0,61,130,32,NULL),(43,41,181,66,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bhlHqPaPnCdcbh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',61,58,46,112,0,0,0,NULL,0,0,0,0,0,0,67,131,32,NULL),(26,64,200,79,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Qpdcyjo9T1WZQp&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',31,46,35,100,0,0,0,NULL,0,0,0,0,0,0,70,132,32,NULL),(64,61,195,50,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:o9wogAnCXraPo9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',37,76,41,84,0,0,0,NULL,0,0,0,0,0,0,63,133,32,NULL),(32,64,155,46,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Ueyjdco9YUkeUe&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',38,79,57,114,0,0,0,NULL,0,0,0,0,0,0,68,134,32,NULL),(34,73,197,49,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:VwnrfXutfXfXVw&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',49,62,49,65,0,0,0,NULL,0,0,0,0,0,0,69,135,32,NULL),(53,48,204,51,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:h6XCgoeunCaPh6&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',61,36,41,114,0,0,0,NULL,0,0,0,0,0,0,65,136,32,NULL),(70,63,153,59,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:szeFlwoUXrhRsz&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',37,47,52,117,0,0,0,NULL,0,0,0,0,0,0,62,137,32,NULL),(55,54,175,30,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:jMx6TNijutqPjM&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',73,78,66,93,0,0,0,NULL,0,0,0,0,0,0,69,138,32,NULL),(52,69,157,61,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:hRcznCsKxRSkhR&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',77,45,29,67,0,0,0,NULL,0,0,0,0,0,0,67,139,32,NULL),(32,79,151,52,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:UeVHhRyjT1mZUe&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',31,33,60,71,0,0,0,NULL,0,0,0,0,0,0,64,140,32,NULL),(39,39,177,27,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:Y9gARSY9VHY9Y9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',33,50,39,68,0,0,0,NULL,0,0,0,0,0,0,69,141,32,NULL),(71,42,198,25,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:tbXCQAa4hRY9tb&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',52,36,39,86,0,0,0,NULL,0,0,0,0,0,0,66,142,32,NULL),(64,66,185,46,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:o9fXdcqPfXVHo9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',49,49,33,72,0,0,0,NULL,0,0,0,0,0,0,68,143,32,NULL),(49,27,154,54,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:fXXrijRSeFkefX&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',47,37,57,79,0,0,0,NULL,0,0,0,0,0,0,67,144,32,NULL),(55,50,171,31,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:jMrhT1gAutUejM&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',73,68,32,90,0,0,0,NULL,0,0,0,0,0,0,69,145,32,NULL),(62,25,177,57,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:nra4keQAyjcznr&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',79,42,45,110,0,0,0,NULL,0,0,0,0,0,0,64,146,32,NULL),(33,67,201,45,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:VHq4czq4vWT1VH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',74,67,31,113,0,0,0,NULL,0,0,0,0,0,0,62,147,32,NULL),(74,48,192,39,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:vWa4Y9eux6XrvW&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',78,42,37,76,0,0,0,NULL,0,0,0,0,0,0,69,148,32,NULL),(46,45,205,58,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:dcmZlHczvWmZdc&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',74,60,60,71,0,0,0,NULL,0,0,0,0,0,0,70,149,32,NULL),(59,70,166,49,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lwSkfXszh6RSlw&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',53,29,27,80,0,0,0,NULL,0,0,0,0,0,0,61,150,32,NULL),(63,39,197,49,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:oUszfXY9RSvWoU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',27,70,74,101,0,0,0,NULL,0,0,0,0,0,0,67,151,32,NULL),(43,53,162,36,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bhijXCh6czRSbh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',45,54,27,102,0,0,0,NULL,0,0,0,0,0,0,61,152,32,NULL),(33,77,152,56,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:VHlHj1xRkeRSVH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',57,58,27,108,0,0,0,NULL,0,0,0,0,0,0,66,153,32,NULL),(72,54,172,45,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:uEcKczijyjhRuE&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',79,44,52,81,0,0,0,NULL,0,0,0,0,0,0,61,154,32,NULL),(38,36,201,55,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:YUmZjMXCxRpmYU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',77,60,65,104,0,0,0,NULL,0,0,0,0,0,0,61,155,32,NULL),(46,31,190,55,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:dcyjjMT1wouEdc&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',76,79,72,87,0,0,0,NULL,0,0,0,0,0,0,69,156,32,NULL),(49,37,186,56,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:fXeuj1Xrx6XCfX&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',78,48,36,65,0,0,0,NULL,0,0,0,0,0,0,65,157,32,NULL),(50,35,160,30,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:gAeuTNWZhRijgA&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',52,48,54,77,0,0,0,NULL,0,0,0,0,0,0,62,158,32,NULL),(36,30,150,54,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:XCQAijTNdcxRXC&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',46,25,77,103,0,0,0,NULL,0,0,0,0,0,0,62,159,32,NULL),(63,61,152,52,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:oUijhRnCkeRSoU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',57,54,27,85,0,0,0,NULL,0,0,0,0,0,0,62,160,32,NULL),(46,38,172,33,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:dcQpVHYUqPtbdc&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',66,26,71,116,0,0,0,NULL,0,0,0,0,0,0,67,161,32,NULL),(51,69,206,48,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:gofXeusKmZdcgo&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',60,49,46,113,0,0,0,NULL,0,0,0,0,0,0,62,162,32,NULL),(38,36,183,78,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:YUjMx6XCTNUeYU&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',30,55,32,88,0,0,0,NULL,0,0,0,0,0,0,67,163,32,NULL),(44,61,177,57,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:cKWZkenCVHx6cK&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',33,35,78,102,0,0,0,NULL,0,0,0,0,0,0,66,164,32,NULL),(31,64,204,55,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:T1eFjMo9mZZmT1&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',60,47,40,83,0,0,0,NULL,0,0,0,0,0,0,62,165,32,NULL),(71,79,158,41,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:tbSkaPyjwoj1tb&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',76,29,56,77,0,0,0,NULL,0,0,0,0,0,0,67,166,32,NULL),(51,55,195,34,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:goR7VwjMXrVHgo&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',37,28,33,82,0,0,0,NULL,0,0,0,0,0,0,62,167,32,NULL),(58,39,193,29,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lHo9SkY9fXbhlH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',49,64,43,110,0,0,0,NULL,0,0,0,0,0,0,63,168,32,NULL),(31,68,159,27,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:T1uERSrhXrnrT1&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',37,72,62,74,0,0,0,NULL,0,0,0,0,0,0,63,169,32,NULL),(33,62,171,51,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:VHutgonrcKVHVH&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',44,73,33,113,0,0,0,NULL,0,0,0,0,0,0,68,170,32,NULL),(35,67,162,26,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:WZVwQpq4a4xRWZ&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',42,34,77,67,0,0,0,NULL,0,0,0,0,0,0,65,171,32,NULL),(56,31,203,28,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:j1j1R7T1XCmZj1&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',36,56,60,79,0,0,0,NULL,0,0,0,0,0,0,63,172,32,NULL),(34,59,183,51,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:VwaPgolwQpRSVw&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',26,41,27,74,0,0,0,NULL,0,0,0,0,0,0,70,173,32,NULL),(31,56,204,66,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:T1XCqPj1cKsKT1&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',44,36,69,65,0,0,0,NULL,0,0,0,0,0,0,61,174,32,NULL),(62,56,156,39,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:nroUY9j1rhnCnr&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',68,63,61,91,0,0,0,NULL,0,0,0,0,0,0,64,175,32,NULL),(75,25,178,69,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:v.TNsKQAvWQpv.&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',74,30,26,100,0,0,0,NULL,0,0,0,0,0,0,63,176,32,NULL),(64,34,209,42,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:o9xRa4VwZmWZo9&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',40,77,35,99,0,0,0,NULL,0,0,0,0,0,0,69,177,32,NULL),(36,65,160,27,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:XCSkRSpmj1ZmXC&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',56,29,40,82,0,0,0,NULL,0,0,0,0,0,0,63,178,32,NULL),(27,53,180,49,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:RSv.fXh6hRmZRS&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',52,75,60,119,0,0,0,NULL,0,0,0,0,0,0,67,179,32,NULL),(52,52,197,73,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:hRcKuthRkeVwhR&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',57,44,34,73,0,0,0,NULL,0,0,0,0,0,0,62,180,32,NULL),(75,71,162,75,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:v.Y9v.tbyjdcv.&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',79,39,46,78,0,0,0,NULL,0,0,0,0,0,0,66,181,32,NULL),(59,62,153,52,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lwZmhRnrlHgolw&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',58,40,51,84,0,0,0,NULL,0,0,0,0,0,0,66,182,32,NULL),(43,60,168,71,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bhijtbmZVwnrbh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,54,62,68,0,0,0,NULL,0,0,0,0,0,0,69,183,32,NULL),(44,43,171,35,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:cKY9WZbhVHsKcK&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',33,39,69,112,0,0,0,NULL,0,0,0,0,0,0,63,184,32,NULL),(71,42,202,73,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:tbT1uta4pmxRtb&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',65,31,77,82,0,0,0,NULL,0,0,0,0,0,0,68,185,32,NULL),(26,62,166,73,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:QpVHutnrgAyjQp&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',50,33,79,105,0,0,0,NULL,0,0,0,0,0,0,62,186,32,NULL),(59,37,209,77,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:lwh6xRXrx6oUlw&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',78,53,63,105,0,0,0,NULL,0,0,0,0,0,0,65,187,32,NULL),(45,34,205,32,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:czoUUeVwnra4cz&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',62,63,42,110,0,0,0,NULL,0,0,0,0,0,0,62,188,32,NULL),(62,40,178,31,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:nrsKT1ZmWZUenr&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',35,69,32,95,0,0,0,NULL,0,0,0,0,0,0,70,189,32,NULL),(43,50,156,54,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bhpmijgAdcpmbh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',46,65,65,79,0,0,0,NULL,0,0,0,0,0,0,62,190,32,NULL),(40,49,161,63,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:ZmijoUfXxRjMZm&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',77,54,55,85,0,0,0,NULL,0,0,0,0,0,0,63,191,32,NULL),(47,40,173,73,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:eFYUutZmTNtbeF&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',30,38,71,89,0,0,0,NULL,0,0,0,0,0,0,67,192,32,NULL),(51,42,187,62,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:goXrnra4lHlHgo&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',58,37,58,75,0,0,0,NULL,0,0,0,0,0,0,65,193,32,NULL),(25,60,175,30,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:QAUeTNmZVwVHQA&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',34,32,33,74,0,0,0,NULL,0,0,0,0,0,0,63,194,32,NULL),(37,37,160,72,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:XrXCuEXrczVwXr&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',45,36,34,91,0,0,0,NULL,0,0,0,0,0,0,68,195,32,NULL),(62,42,200,46,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:nrjMdca4yjsznr&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',79,55,70,115,0,0,0,NULL,0,0,0,0,0,0,69,196,32,NULL),(43,61,170,64,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:bhlwo9nCczoUbh&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',45,59,63,69,0,0,0,NULL,0,0,0,0,0,0,70,197,32,NULL),(57,57,155,48,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:keUeeukedcyjke&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',46,32,79,77,0,0,0,NULL,0,0,0,0,0,0,66,198,32,NULL),(77,61,182,35,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:xRoUWZnCo9x6xR&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',64,63,78,75,0,0,0,NULL,0,0,0,0,0,0,65,199,32,NULL),(32,79,206,25,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:UeT1QAyjo9XrUe&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',64,31,37,119,0,0,0,NULL,0,0,0,0,0,0,66,200,32,NULL),(69,71,166,28,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:sKjMR7tbgAijsK&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',50,55,54,69,0,0,0,NULL,0,0,0,0,0,0,70,201,32,NULL),(36,44,202,34,'http://chart.apis.google.com/chart?cht=r&chxt=y,x&chls=4,1,0&chco=CC3366&chs=400x400&chtt=Profil&chts=000000,20&chxr=0,0.0,100.0&chm=B,B0C4DE,0,0,0&chd=e:XCeFVwcKY9ijXC&chxp=0,0,20,40,60,80,100&chxl=1:|ATT|TEC|PHY|DEF|PUI|VIT&chxs=0,000000,12,1|1,000000,12,0',39,47,54,71,0,0,0,NULL,0,0,0,0,0,0,63,202,32,NULL);
/*!40000 ALTER TABLE `fam_player_season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_position`
--

DROP TABLE IF EXISTS `fam_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_position` (
  `id_position` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_position` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_position` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_position`),
  UNIQUE KEY `UNQ_fam_position_0` (`cod_position`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_position`
--

LOCK TABLES `fam_position` WRITE;
/*!40000 ALTER TABLE `fam_position` DISABLE KEYS */;
INSERT INTO `fam_position` VALUES (91,'DC','2012-02-25 19:21:56',NULL,'Défenseur central',1),(92,'MG','2012-02-25 19:21:56',NULL,'Milieu gauche',1),(93,'MDF','2012-02-25 19:21:56',NULL,'Milieu défensif',1),(94,'MJ','2012-02-25 19:21:56',NULL,'Meneur de jeu',1),(95,'LIB','2012-02-25 19:21:56',NULL,'Libéro',1),(96,'MD','2012-02-25 19:21:56',NULL,'Milieu droit',1),(97,'G','2012-02-25 19:21:56',NULL,'Gardien de but',1),(98,'AC','2012-02-25 19:21:56',NULL,'Avant centre',1),(99,'LD','2012-02-25 19:21:56',NULL,'Latéral droit',1),(100,'MO','2012-02-25 19:21:56',NULL,'Milieu offensif',1),(101,'AD','2012-02-25 19:21:56',NULL,'Ailier droit',1),(102,'ST','2012-02-25 19:21:56',NULL,'Stoppeur',1),(103,'SA','2012-02-25 19:21:56',NULL,'Second attaquant',1),(104,'LG','2012-02-25 19:21:56',NULL,'Latéral gauche',1),(105,'AG','2012-02-25 19:21:56',NULL,'Ailier gauche',1);
/*!40000 ALTER TABLE `fam_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_profile`
--

DROP TABLE IF EXISTS `fam_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_profile` (
  `id_profile` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_profile` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_profile` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_profile`),
  UNIQUE KEY `UNQ_fam_profile_0` (`cod_profile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_profile`
--

LOCK TABLES `fam_profile` WRITE;
/*!40000 ALTER TABLE `fam_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_profile` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_province`
--

LOCK TABLES `fam_province` WRITE;
/*!40000 ALTER TABLE `fam_province` DISABLE KEYS */;
INSERT INTO `fam_province` VALUES (1,'01','2012-02-25 19:20:30',NULL,'ain','Ain','AIN',1,22),(2,'02','2012-02-25 19:20:30',NULL,'aisne','Aisne','AISNE',1,19),(3,'03','2012-02-25 19:20:30',NULL,'allier','Allier','ALLIER',1,2),(4,'04','2012-02-25 19:20:30',NULL,'alpes de haute-provence','Alpes de Haute-Provence','ALPES DE HAUTE-PROVENCE',1,21),(5,'05','2012-02-25 19:20:30',NULL,'alpes-hautes','Alpes (Hautes)','ALPES (HAUTES)',1,21),(6,'06','2012-02-25 19:20:30',NULL,'alpes-maritimes','Alpes Maritimes','ALPES MARITIMES',1,21),(7,'07','2012-02-25 19:20:30',NULL,'ardeche','Ardéche','ARDÉCHE',1,22),(8,'08','2012-02-25 19:20:30',NULL,'ardennes','Ardennes','ARDENNES',1,6),(9,'09','2012-02-25 19:20:30',NULL,'-ariege',' Ariége',' ARIÉGE',1,14),(10,'10','2012-02-25 19:20:30',NULL,'aube','Aube','AUBE',1,6),(11,'11','2012-02-25 19:20:30',NULL,'aude','Aude','AUDE',1,11),(12,'12','2012-02-25 19:20:30',NULL,'aveyron','Aveyron','AVEYRON',1,14),(13,'13','2012-02-25 19:20:30',NULL,'bouches-du-rhone','Bouches du Rhône','BOUCHES DU RHÔNE',1,21),(14,'14','2012-02-25 19:20:30',NULL,'calvados','Calvados','CALVADOS',1,16),(15,'15','2012-02-25 19:20:30',NULL,'cantal','Cantal','CANTAL',1,2),(16,'16','2012-02-25 19:20:30',NULL,'charente','Charente','CHARENTE',1,20),(17,'17','2012-02-25 19:20:30',NULL,'charente-maritime','Charente Maritime','CHARENTE MARITIME',1,20),(18,'18','2012-02-25 19:20:30',NULL,'cher','Cher','CHER',1,5),(19,'19','2012-02-25 19:20:30',NULL,'correze','Corréze','CORRÉZE',1,12),(21,'23','2012-02-25 19:20:30',NULL,'creuse-','Creuse ','CREUSE ',1,12),(22,'24','2012-02-25 19:20:30',NULL,'dordogne','Dordogne','DORDOGNE',1,1),(23,'25','2012-02-25 19:20:30',NULL,'doubs','Doubs','DOUBS',1,9),(24,'26','2012-02-25 19:20:30',NULL,'drome','Drôme','DRÔME',1,22),(25,'27','2012-02-25 19:20:30',NULL,'eure','Eure','EURE',1,17),(26,'28','2012-02-25 19:20:30',NULL,'eure-et-loir','Eure et Loir','EURE ET LOIR',1,5),(27,'29','2012-02-25 19:20:30',NULL,'finistere','Finistére','FINISTÉRE',1,4),(28,'30','2012-02-25 19:20:30',NULL,'gard','Gard','GARD',1,11),(29,'31','2012-02-25 19:20:30',NULL,'garonne-haute','Garonne (Haute)','GARONNE (HAUTE)',1,14),(30,'32','2012-02-25 19:20:30',NULL,'gers','Gers','GERS',1,14),(31,'33','2012-02-25 19:20:30',NULL,'gironde','Gironde','GIRONDE',1,1),(32,'34','2012-02-25 19:20:30',NULL,'herault','Hérault','HÉRAULT',1,11),(33,'35','2012-02-25 19:20:30',NULL,'ile-et-vilaine','Ile et Vilaine','ILE ET VILAINE',1,4),(34,'36','2012-02-25 19:20:30',NULL,'indre','Indre','INDRE',1,5),(35,'37','2012-02-25 19:20:30',NULL,'indre-et-loire','Indre et Loire','INDRE ET LOIRE',1,5),(36,'38','2012-02-25 19:20:30',NULL,'isere','Isére','ISÉRE',1,22),(37,'39','2012-02-25 19:20:30',NULL,'jura','Jura','JURA',1,9),(38,'40','2012-02-25 19:20:30',NULL,'landes','Landes','LANDES',1,1),(39,'41','2012-02-25 19:20:30',NULL,'loir-et-cher','Loir et Cher','LOIR ET CHER',1,5),(40,'42','2012-02-25 19:20:30',NULL,'loire','Loire','LOIRE',1,22),(41,'43','2012-02-25 19:20:30',NULL,'loire-haute','Loire (Haute)','LOIRE (HAUTE)',1,2),(42,'44','2012-02-25 19:20:30',NULL,'loire-atlantique','Loire Atlantique','LOIRE ATLANTIQUE',1,18),(43,'45','2012-02-25 19:20:30',NULL,'loiret','Loiret','LOIRET',1,5),(44,'46','2012-02-25 19:20:30',NULL,'lot','Lot','LOT',1,14),(45,'47','2012-02-25 19:20:30',NULL,'lot-et-garonne','Lot et Garonne','LOT ET GARONNE',1,1),(46,'48','2012-02-25 19:20:30',NULL,'lozere','Lozére','LOZÉRE',1,11),(47,'49','2012-02-25 19:20:30',NULL,'maine-et-loire','Maine et Loire','MAINE ET LOIRE',1,18),(48,'50','2012-02-25 19:20:30',NULL,'manche','Manche','MANCHE',1,16),(49,'51','2012-02-25 19:20:30',NULL,'marne','Marne','MARNE',1,6),(50,'52','2012-02-25 19:20:30',NULL,'marne-haute','Marne (Haute)','MARNE (HAUTE)',1,6),(51,'53','2012-02-25 19:20:30',NULL,'mayenne','Mayenne','MAYENNE',1,18),(52,'54','2012-02-25 19:20:30',NULL,'meurthe-et-moselle','Meurthe et Moselle','MEURTHE ET MOSELLE',1,13),(53,'55','2012-02-25 19:20:30',NULL,'meuse','Meuse','MEUSE',1,13),(54,'56','2012-02-25 19:20:30',NULL,'morbihan','Morbihan','MORBIHAN',1,4),(55,'57','2012-02-25 19:20:30',NULL,'moselle','Moselle','MOSELLE',1,13),(56,'58','2012-02-25 19:20:30',NULL,'nievre','Niévre','NIÉVRE',1,3),(57,'59','2012-02-25 19:20:30',NULL,'nord','Nord','NORD',1,15),(58,'60','2012-02-25 19:20:30',NULL,'oise','Oise','OISE',1,19),(59,'61','2012-02-25 19:20:30',NULL,'orne','Orne','ORNE',1,16),(60,'62','2012-02-25 19:20:30',NULL,'pas-de-calais','Pas de Calais','PAS DE CALAIS',1,15),(61,'63','2012-02-25 19:20:30',NULL,'puy-de-dome','Puy de Dôme','PUY DE DÔME',1,2),(62,'64','2012-02-25 19:20:30',NULL,'pyrenees-atlantiques','Pyrénées Atlantiques','PYRÉNÉES ATLANTIQUES',1,1),(63,'65','2012-02-25 19:20:30',NULL,'pyrenees-hautes','Pyrénées (Hautes)','PYRÉNÉES (HAUTES)',1,14),(64,'66','2012-02-25 19:20:30',NULL,'pyrenees-orientales','Pyrénées Orientales','PYRÉNÉES ORIENTALES',1,11),(65,'67','2012-02-25 19:20:30',NULL,'rhin-bas','Rhin (Bas)','RHIN (BAS)',1,23),(66,'68','2012-02-25 19:20:30',NULL,'rhin-haut','Rhin (Haut)','RHIN (HAUT)',1,23),(67,'69','2012-02-25 19:20:30',NULL,'rhone','Rhône','RHÔNE',1,22),(68,'70','2012-02-25 19:20:30',NULL,'saone-haute','Saône (Haute)','SAÔNE (HAUTE)',1,9),(69,'71','2012-02-25 19:20:30',NULL,'saone-et-loire','Saône et Loire','SAÔNE ET LOIRE',1,3),(70,'72','2012-02-25 19:20:30',NULL,'sarthe','Sarthe','SARTHE',1,18),(71,'73','2012-02-25 19:20:30',NULL,'savoie','Savoie','SAVOIE',1,22),(72,'74','2012-02-25 19:20:30',NULL,'savoie-haute','Savoie (Haute)','SAVOIE (HAUTE)',1,22),(73,'75','2012-02-25 19:20:30',NULL,'paris','Paris','PARIS',1,10),(74,'76','2012-02-25 19:20:30',NULL,'seine-maritime','Seine Maritime','SEINE MARITIME',1,17),(75,'77','2012-02-25 19:20:30',NULL,'seine-et-marne','Seine et Marne','SEINE ET MARNE',1,10),(76,'78','2012-02-25 19:20:30',NULL,'yvelines','Yvelines','YVELINES',1,10),(77,'79','2012-02-25 19:20:30',NULL,'sevres-deux','Sèvres (Deux)','SÈVRES (DEUX)',1,20),(78,'80','2012-02-25 19:20:30',NULL,'somme','Somme','SOMME',1,19),(79,'81','2012-02-25 19:20:30',NULL,'tarn','Tarn','TARN',1,14),(80,'82','2012-02-25 19:20:30',NULL,'tarn-et-garonne','Tarn et Garonne','TARN ET GARONNE',1,14),(81,'83','2012-02-25 19:20:30',NULL,'var','Var','VAR',1,21),(82,'84','2012-02-25 19:20:30',NULL,'vaucluse','Vaucluse','VAUCLUSE',1,21),(83,'85','2012-02-25 19:20:30',NULL,'vendee','Vendée','VENDÉE',1,18),(84,'86','2012-02-25 19:20:30',NULL,'vienne','Vienne','VIENNE',1,20),(85,'87','2012-02-25 19:20:30',NULL,'vienne-haute','Vienne (Haute)','VIENNE (HAUTE)',1,12),(86,'88','2012-02-25 19:20:30',NULL,'vosges','Vosges','VOSGES',1,13),(87,'89','2012-02-25 19:20:30',NULL,'yonne','Yonne','YONNE',1,3),(88,'90','2012-02-25 19:20:30',NULL,'belfort-territoire-de','Belfort (Territoire de)','BELFORT (TERRITOIRE DE)',1,9),(89,'91','2012-02-25 19:20:30',NULL,'essonne','Essonne','ESSONNE',1,10),(90,'92','2012-02-25 19:20:30',NULL,'hauts-de-seine','Hauts de Seine','HAUTS DE SEINE',1,10),(91,'93','2012-02-25 19:20:30',NULL,'seine-saint-denis','Seine Saint Denis','SEINE SAINT DENIS',1,10),(92,'94','2012-02-25 19:20:30',NULL,'val-de-marne','Val de Marne','VAL DE MARNE',1,10),(93,'976','2012-02-25 19:20:30',NULL,'mayotte','Mayotte','MAYOTTE',1,8),(94,'971','2012-02-25 19:20:30',NULL,'guadeloupe','Guadeloupe','GUADELOUPE',1,8),(95,'973','2012-02-25 19:20:30',NULL,'guyane','Guyane','GUYANE',1,8),(96,'972','2012-02-25 19:20:30',NULL,'martinique','Martinique','MARTINIQUE',1,8),(97,'974','2012-02-25 19:20:30',NULL,'reunion','Réunion','RÉUNION',1,8),(98,'21','2012-02-25 19:20:30',NULL,'cote-dor','Côte d\'or','CÔTE D\'OR',1,3),(100,'22','2012-02-25 19:20:30',NULL,'cotes-darmor','Côtes d\'armor','CÔTES D\'ARMOR',1,4),(102,'2A','2012-02-25 19:20:30',NULL,'corse-du-sud','Corse du sud','CORSE DU SUD',1,7),(103,'2B','2012-02-25 19:20:30',NULL,'haute-corse','Haute corse','HAUTE CORSE',1,7),(104,'95','2012-02-25 19:20:30',NULL,'val-doise','Val d\'oise','VAL D\'OISE',1,10);
/*!40000 ALTER TABLE `fam_province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_scale`
--

DROP TABLE IF EXISTS `fam_scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_scale` (
  `id_scale` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_scale` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_scale` varchar(255) DEFAULT NULL,
  `pts_defeat` int(11) DEFAULT NULL,
  `pts_draw` int(11) DEFAULT NULL,
  `pts_victory` int(11) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_scale`),
  UNIQUE KEY `UNQ_fam_scale_0` (`cod_scale`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_scale`
--

LOCK TABLES `fam_scale` WRITE;
/*!40000 ALTER TABLE `fam_scale` DISABLE KEYS */;
INSERT INTO `fam_scale` VALUES (13,'3PTS','2012-02-25 19:21:56',NULL,'Victoire a 3 points',0,1,3,1),(14,'4PTS','2012-02-25 19:21:56',NULL,'Victoire a 4 points',1,2,4,1);
/*!40000 ALTER TABLE `fam_scale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_season`
--

DROP TABLE IF EXISTS `fam_season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_season` (
  `id_season` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_season` tinyint(1) DEFAULT '0',
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_season` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_season`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_season`
--

LOCK TABLES `fam_season` WRITE;
/*!40000 ALTER TABLE `fam_season` DISABLE KEYS */;
INSERT INTO `fam_season` VALUES (31,0,'2012-02-25 19:21:55',NULL,'2008/2009',1),(32,1,'2012-02-25 19:21:55',NULL,'2012/2013',1),(33,0,'2012-02-25 19:21:55',NULL,'2010/2011',1),(34,0,'2012-02-25 19:21:55',NULL,'2009/2010',1),(35,0,'2012-02-25 19:21:55',NULL,'2011/2012',1);
/*!40000 ALTER TABLE `fam_season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_season_competition`
--

DROP TABLE IF EXISTS `fam_season_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_season_competition` (
  `id_season_competition` bigint(20) NOT NULL AUTO_INCREMENT,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_scale` bigint(20) DEFAULT NULL,
  `id_season` bigint(20) DEFAULT NULL,
  `id_typ_competition` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_season_competition`),
  KEY `FK_fam_season_competition_id_season` (`id_season`),
  KEY `FK_fam_season_competition_id_scale` (`id_scale`),
  KEY `FK_fam_season_competition_id_typ_competition` (`id_typ_competition`),
  CONSTRAINT `FK_fam_season_competition_id_scale` FOREIGN KEY (`id_scale`) REFERENCES `fam_scale` (`id_scale`),
  CONSTRAINT `FK_fam_season_competition_id_season` FOREIGN KEY (`id_season`) REFERENCES `fam_season` (`id_season`),
  CONSTRAINT `FK_fam_season_competition_id_typ_competition` FOREIGN KEY (`id_typ_competition`) REFERENCES `fam_typ_competition` (`id_typ_competition`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_season_competition`
--

LOCK TABLES `fam_season_competition` WRITE;
/*!40000 ALTER TABLE `fam_season_competition` DISABLE KEYS */;
INSERT INTO `fam_season_competition` VALUES (181,'2012-02-25 19:21:56',NULL,1,NULL,31,41),(182,'2012-02-25 19:21:56',NULL,1,NULL,31,42),(183,'2012-02-25 19:21:56','2012-03-03 21:28:40',2,NULL,32,41),(184,'2012-02-25 19:21:56',NULL,1,NULL,35,42),(185,'2012-02-25 19:21:56',NULL,1,NULL,35,41),(186,'2012-02-25 19:21:56',NULL,1,NULL,35,37),(187,'2012-02-25 19:21:56',NULL,1,NULL,35,38),(188,'2012-02-25 19:21:56',NULL,1,NULL,33,40),(189,'2012-02-25 19:21:56',NULL,1,NULL,33,41),(190,'2012-02-25 19:21:56',NULL,1,NULL,33,37),(191,'2012-02-25 19:21:56',NULL,1,NULL,34,40),(192,'2012-02-25 19:21:56',NULL,1,NULL,35,40),(193,'2012-02-25 19:21:56',NULL,1,NULL,32,39),(194,'2012-02-25 19:21:56',NULL,1,NULL,34,39),(195,'2012-02-25 19:21:56',NULL,1,NULL,33,39),(196,'2012-02-25 19:21:56',NULL,1,NULL,34,42),(197,'2012-02-25 19:21:56',NULL,1,NULL,33,38),(198,'2012-02-25 19:21:56',NULL,1,NULL,33,42),(199,'2012-02-25 19:21:56',NULL,1,NULL,32,40),(200,'2012-02-25 19:21:56',NULL,1,NULL,34,38),(201,'2012-02-25 19:21:56',NULL,1,NULL,34,37),(202,'2012-02-25 19:21:56',NULL,1,NULL,31,38),(203,'2012-02-25 19:21:56',NULL,1,NULL,35,39),(204,'2012-02-25 19:21:56',NULL,1,NULL,32,38),(205,'2012-02-25 19:21:56',NULL,1,NULL,31,37),(206,'2012-02-25 19:21:56',NULL,1,NULL,32,37),(207,'2012-02-25 19:21:56',NULL,1,NULL,31,39),(208,'2012-02-25 19:21:56',NULL,1,NULL,31,40),(209,'2012-02-25 19:21:56',NULL,1,NULL,34,41),(210,'2012-02-25 19:21:56',NULL,1,NULL,32,42);
/*!40000 ALTER TABLE `fam_season_competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_staff`
--

DROP TABLE IF EXISTS `fam_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_staff` (
  `id_user` bigint(20) NOT NULL,
  `id_season` bigint(20) NOT NULL,
  `id_staff_function` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`,`id_season`,`id_staff_function`),
  KEY `FK_fam_staff_id_season` (`id_season`,`id_user`),
  KEY `FK_fam_staff_id_staff_function` (`id_staff_function`),
  CONSTRAINT `FK_fam_staff_id_season` FOREIGN KEY (`id_season`, `id_user`) REFERENCES `fam_user_season` (`id_season`, `id_user`),
  CONSTRAINT `FK_fam_staff_id_staff_function` FOREIGN KEY (`id_staff_function`) REFERENCES `fam_staff_function` (`id_staff_function`),
  CONSTRAINT `FK_fam_staff_id_user` FOREIGN KEY (`id_user`, `id_season`) REFERENCES `fam_user_season` (`id_user`, `id_season`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_staff`
--

LOCK TABLES `fam_staff` WRITE;
/*!40000 ALTER TABLE `fam_staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_staff_function`
--

DROP TABLE IF EXISTS `fam_staff_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_staff_function` (
  `id_staff_function` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_staff_function` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_staff_function` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_club` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_staff_function`),
  UNIQUE KEY `UNQ_fam_staff_function_0` (`cod_staff_function`),
  KEY `FK_fam_staff_function_id_club` (`id_club`),
  CONSTRAINT `FK_fam_staff_function_id_club` FOREIGN KEY (`id_club`) REFERENCES `fam_club` (`id_club`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_staff_function`
--

LOCK TABLES `fam_staff_function` WRITE;
/*!40000 ALTER TABLE `fam_staff_function` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_staff_function` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_state`
--

LOCK TABLES `fam_state` WRITE;
/*!40000 ALTER TABLE `fam_state` DISABLE KEYS */;
INSERT INTO `fam_state` VALUES (1,'1','2012-02-25 19:20:29',NULL,'aquitaine','Aquitaine','AQUITAINE',1,1),(2,'2','2012-02-25 19:20:29',NULL,'auvergne','Auvergne','AUVERGNE',1,1),(3,'3','2012-02-25 19:20:29',NULL,'bourgogne','Bourgogne','BOURGOGNE',1,1),(4,'4','2012-02-25 19:20:29',NULL,'bretagne','Bretagne','BRETAGNE',1,1),(5,'5','2012-02-25 19:20:29',NULL,'centre','Centre','CENTRE',1,1),(6,'6','2012-02-25 19:20:30',NULL,'champagne-ardenne','Champagne Ardenne','CHAMPAGNE ARDENNE',1,1),(7,'7','2012-02-25 19:20:30',NULL,'corse','Corse','CORSE',1,1),(8,'8','2012-02-25 19:20:30',NULL,'dom-tom','DOM/TOM','DOM/TOM',1,1),(9,'9','2012-02-25 19:20:30',NULL,'franche-comte','Franche Comté','FRANCHE COMTÉ',1,1),(10,'10','2012-02-25 19:20:30',NULL,'ile-de-france','Ile de France','ILE DE FRANCE',1,1),(11,'11','2012-02-25 19:20:30',NULL,'languedoc-roussillon','Languedoc Roussillon','LANGUEDOC ROUSSILLON',1,1),(12,'12','2012-02-25 19:20:30',NULL,'limousin','Limousin','LIMOUSIN',1,1),(13,'13','2012-02-25 19:20:30',NULL,'lorraine','Lorraine','LORRAINE',1,1),(14,'14','2012-02-25 19:20:30',NULL,'midi-pyrenees','Midi Pyrénées','MIDI PYRÉNÉES',1,1),(15,'15','2012-02-25 19:20:30',NULL,'nord-pas-de-calais','Nord Pas de Calais','NORD PAS DE CALAIS',1,1),(16,'16','2012-02-25 19:20:30',NULL,'basse-normandie','Basse-Normandie','BASSE-NORMANDIE',1,1),(17,'17','2012-02-25 19:20:30',NULL,'haute-normandie-','Haute Normandie ','HAUTE NORMANDIE ',1,1),(18,'18','2012-02-25 19:20:30',NULL,'pays-de-la-loire','Pays de la Loire','PAYS DE LA LOIRE',1,1),(19,'19','2012-02-25 19:20:30',NULL,'picardie','Picardie','PICARDIE',1,1),(20,'20','2012-02-25 19:20:30',NULL,'poitou-charentes','Poitou Charentes','POITOU CHARENTES',1,1),(21,'21','2012-02-25 19:20:30',NULL,'provence-alpes-cote-dazur','Provence Alpes Côte d\'azur','PROVENCE ALPES CÔTE D\'AZUR',1,1),(22,'22','2012-02-25 19:20:30',NULL,'rhone-alpes','Rhône Alpes','RHÔNE ALPES',1,1),(23,'23','2012-02-25 19:20:30',NULL,'alsace','Alsace','ALSACE',1,1);
/*!40000 ALTER TABLE `fam_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_substitution`
--

DROP TABLE IF EXISTS `fam_substitution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_substitution` (
  `id_substitution` bigint(20) NOT NULL AUTO_INCREMENT,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `substitution_time` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_player_in` bigint(20) DEFAULT NULL,
  `id_match` bigint(20) DEFAULT NULL,
  `id_team` bigint(20) DEFAULT NULL,
  `id_player_out` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_substitution`),
  KEY `FK_fam_substitution_id_match` (`id_match`,`id_team`,`id_player_in`),
  CONSTRAINT `FK_fam_substitution_id_match` FOREIGN KEY (`id_match`, `id_team`, `id_player_in`) REFERENCES `fam_match_player` (`id_match`, `id_team`, `id_player`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_substitution`
--

LOCK TABLES `fam_substitution` WRITE;
/*!40000 ALTER TABLE `fam_substitution` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_substitution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_team`
--

DROP TABLE IF EXISTS `fam_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_team` (
  `id_team` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_team` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_team` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_club` bigint(20) DEFAULT NULL,
  `id_place` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_team`),
  UNIQUE KEY `UNQ_fam_team_0` (`cod_team`),
  KEY `FK_fam_team_id_club` (`id_club`),
  KEY `FK_fam_team_id_place` (`id_place`),
  CONSTRAINT `FK_fam_team_id_club` FOREIGN KEY (`id_club`) REFERENCES `fam_club` (`id_club`),
  CONSTRAINT `FK_fam_team_id_place` FOREIGN KEY (`id_place`) REFERENCES `fam_place` (`id_place`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_team`
--

LOCK TABLES `fam_team` WRITE;
/*!40000 ALTER TABLE `fam_team` DISABLE KEYS */;
INSERT INTO `fam_team` VALUES (181,'EQ92','2012-02-25 19:21:56',NULL,'Equipe_92',1,65,NULL),(182,'EQ20','2012-02-25 19:21:56',NULL,'Equipe_20',1,69,NULL),(183,'EQ91','2012-02-25 19:21:56',NULL,'Equipe_91',1,65,NULL),(184,'EQ90','2012-02-25 19:21:56',NULL,'Equipe_90',1,65,NULL),(185,'EQ22','2012-02-25 19:21:56',NULL,'Equipe_22',1,69,NULL),(186,'EQ52','2012-02-25 19:21:56',NULL,'Equipe_52',1,66,NULL),(187,'EQ41','2012-02-25 19:21:56',NULL,'Equipe_41',1,67,NULL),(188,'EQ40','2012-02-25 19:21:56',NULL,'Equipe_40',1,67,NULL),(189,'EQ1','2012-02-25 19:21:56',NULL,'Equipe_1',1,61,NULL),(190,'EQ70','2012-02-25 19:21:56',NULL,'Equipe_70',1,63,NULL),(191,'EQ61','2012-02-25 19:21:56',NULL,'Equipe_61',1,62,NULL),(192,'EQ71','2012-02-25 19:21:56',NULL,'Equipe_71',1,63,NULL),(193,'EQ62','2012-02-25 19:21:56',NULL,'Equipe_62',1,62,NULL),(194,'EQ42','2012-02-25 19:21:56',NULL,'Equipe_42',1,67,NULL),(195,'EQ10','2012-02-25 19:21:56',NULL,'Equipe_10',1,68,NULL),(196,'EQ11','2012-02-25 19:21:56',NULL,'Equipe_11',1,68,NULL),(197,'EQ21','2012-02-25 19:21:56',NULL,'Equipe_21',1,69,NULL),(198,'EQ2','2012-02-25 19:21:56',NULL,'Equipe_2',1,61,NULL),(199,'EQ81','2012-02-25 19:21:56',NULL,'Equipe_81',1,64,NULL),(200,'EQ12','2012-02-25 19:21:56',NULL,'Equipe_12',1,68,NULL),(201,'EQ60','2012-02-25 19:21:56',NULL,'Equipe_60',1,62,NULL),(202,'EQ50','2012-02-25 19:21:56',NULL,'Equipe_50',1,66,NULL),(203,'EQ82','2012-02-25 19:21:56',NULL,'Equipe_82',1,64,NULL),(204,'EQ72','2012-02-25 19:21:56',NULL,'Equipe_72',1,63,NULL),(205,'EQ32','2012-02-25 19:21:56',NULL,'Equipe_32',1,70,NULL),(206,'EQ30','2012-02-25 19:21:56',NULL,'Equipe_30',1,70,NULL),(207,'EQ51','2012-02-25 19:21:56',NULL,'Equipe_51',1,66,NULL),(208,'EQ31','2012-02-25 19:21:56',NULL,'Equipe_31',1,70,NULL),(209,'EQ0','2012-02-25 19:21:56',NULL,'Equipe_0',1,61,NULL),(210,'EQ80','2012-02-25 19:21:56',NULL,'Equipe_80',1,64,NULL);
/*!40000 ALTER TABLE `fam_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_typ_absence`
--

DROP TABLE IF EXISTS `fam_typ_absence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_absence` (
  `id_typ_absence` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_typ_absence` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_typ_absence` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_absence`),
  UNIQUE KEY `UNQ_fam_typ_absence_0` (`cod_typ_absence`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_absence`
--

LOCK TABLES `fam_typ_absence` WRITE;
/*!40000 ALTER TABLE `fam_typ_absence` DISABLE KEYS */;
INSERT INTO `fam_typ_absence` VALUES (13,'V','2012-02-25 19:21:56',NULL,'Vacances',1),(14,'B','2012-02-25 19:21:56',NULL,'Blessure',1);
/*!40000 ALTER TABLE `fam_typ_absence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_typ_answer`
--

DROP TABLE IF EXISTS `fam_typ_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_answer` (
  `id_typ_answer` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_typ_answer` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `grp_typ_answer` varchar(255) DEFAULT NULL,
  `lib_typ_answer` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_answer`),
  UNIQUE KEY `UNQ_fam_typ_answer_0` (`cod_typ_answer`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_answer`
--

LOCK TABLES `fam_typ_answer` WRITE;
/*!40000 ALTER TABLE `fam_typ_answer` DISABLE KEYS */;
INSERT INTO `fam_typ_answer` VALUES (19,'A','2012-02-25 19:21:56',NULL,'NO','Absent',1),(20,'N','2012-02-25 19:21:56',NULL,'MAYBE','Ne sait pas',1),(21,'P','2012-02-25 19:21:56',NULL,'YES','Présent',1);
/*!40000 ALTER TABLE `fam_typ_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_typ_card`
--

DROP TABLE IF EXISTS `fam_typ_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_card` (
  `id_typ_card` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_typ_card` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_typ_card` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_card`),
  UNIQUE KEY `UNQ_fam_typ_card_0` (`cod_typ_card`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_card`
--

LOCK TABLES `fam_typ_card` WRITE;
/*!40000 ALTER TABLE `fam_typ_card` DISABLE KEYS */;
INSERT INTO `fam_typ_card` VALUES (19,'CJ','2012-02-25 19:21:55',NULL,'Carton jaune',1),(20,'CR','2012-02-25 19:21:55',NULL,'Carton rouge',1),(21,'CB','2012-02-25 19:21:55',NULL,'Carton blanc',1);
/*!40000 ALTER TABLE `fam_typ_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_typ_card_finescale`
--

DROP TABLE IF EXISTS `fam_typ_card_finescale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_card_finescale` (
  `id_typ_card_finescale` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_typ_card_finescale` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_typ_card_finescale` varchar(255) DEFAULT NULL,
  `price` decimal(38,0) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_typ_card` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_card_finescale`),
  KEY `FK_fam_typ_card_finescale_id_typ_card` (`id_typ_card`),
  CONSTRAINT `FK_fam_typ_card_finescale_id_typ_card` FOREIGN KEY (`id_typ_card`) REFERENCES `fam_typ_card` (`id_typ_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_card_finescale`
--

LOCK TABLES `fam_typ_card_finescale` WRITE;
/*!40000 ALTER TABLE `fam_typ_card_finescale` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_typ_card_finescale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_typ_competition`
--

DROP TABLE IF EXISTS `fam_typ_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_competition` (
  `id_typ_competition` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_typ_competition` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `championship` tinyint(1) DEFAULT '0',
  `lib_typ_competition` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_typ_match` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_competition`),
  KEY `FK_fam_typ_competition_id_typ_match` (`id_typ_match`),
  CONSTRAINT `FK_fam_typ_competition_id_typ_match` FOREIGN KEY (`id_typ_match`) REFERENCES `fam_typ_match` (`id_typ_match`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_competition`
--

LOCK TABLES `fam_typ_competition` WRITE;
/*!40000 ALTER TABLE `fam_typ_competition` DISABLE KEYS */;
INSERT INTO `fam_typ_competition` VALUES (37,'PH','2012-02-25 19:21:56',NULL,1,'Promotion Honneur',1,21),(38,'CF','2012-02-25 19:21:56',NULL,0,'Coupe de France',1,19),(39,'DH','2012-02-25 19:21:56',NULL,1,'Division Honneur',1,21),(40,'A','2012-02-25 19:21:56',NULL,0,'Match amical',1,21),(41,'EXL','2012-02-25 19:21:56',NULL,1,'Excellence',1,20),(42,'1DIV','2012-02-25 19:21:56',NULL,1,'1ere Division',1,20);
/*!40000 ALTER TABLE `fam_typ_competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_typ_event`
--

DROP TABLE IF EXISTS `fam_typ_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_event` (
  `id_typ_event` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_typ_event` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_typ_event` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_event`),
  UNIQUE KEY `UNQ_fam_typ_event_0` (`cod_typ_event`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_event`
--

LOCK TABLES `fam_typ_event` WRITE;
/*!40000 ALTER TABLE `fam_typ_event` DISABLE KEYS */;
INSERT INTO `fam_typ_event` VALUES (13,'M','2012-02-25 19:21:55',NULL,'Match',1),(14,'E','2012-02-25 19:21:55',NULL,'Entraînement',1);
/*!40000 ALTER TABLE `fam_typ_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_typ_match`
--

DROP TABLE IF EXISTS `fam_typ_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_match` (
  `id_typ_match` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_typ_match` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `extra_time` tinyint(1) DEFAULT '0',
  `extra_time_duration` int(11) DEFAULT NULL,
  `infinite_subs` tinyint(1) DEFAULT '0',
  `lib_typ_match` varchar(255) DEFAULT NULL,
  `nb_penalties` int(11) DEFAULT NULL,
  `nb_player` int(11) DEFAULT NULL,
  `nb_substitute` int(11) DEFAULT NULL,
  `nb_substitution` int(11) DEFAULT NULL,
  `penalties` tinyint(1) DEFAULT '0',
  `period_duration` int(11) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_match`),
  UNIQUE KEY `UNQ_fam_typ_match_0` (`cod_typ_match`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_match`
--

LOCK TABLES `fam_typ_match` WRITE;
/*!40000 ALTER TABLE `fam_typ_match` DISABLE KEYS */;
INSERT INTO `fam_typ_match` VALUES (19,'Cp','2012-02-25 19:21:55',NULL,1,15,0,'Coupe',5,11,5,3,1,45,1),(20,'CORP','2012-02-25 19:21:55',NULL,0,15,1,'Corpo',5,11,5,3,0,45,1),(21,'DEF','2012-02-25 19:21:55',NULL,0,15,0,'Défault',5,11,5,3,0,45,1);
/*!40000 ALTER TABLE `fam_typ_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_typ_place`
--

DROP TABLE IF EXISTS `fam_typ_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_typ_place` (
  `id_typ_place` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod_typ_place` varchar(255) DEFAULT NULL,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `lib_typ_pPlace` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_typ_place`),
  UNIQUE KEY `UNQ_fam_typ_place_0` (`cod_typ_place`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_typ_place`
--

LOCK TABLES `fam_typ_place` WRITE;
/*!40000 ALTER TABLE `fam_typ_place` DISABLE KEYS */;
INSERT INTO `fam_typ_place` VALUES (25,'Stabi','2012-02-25 19:21:56',NULL,'Stabilisé',1),(26,'Autre','2012-02-25 19:21:56',NULL,'Autre',1),(27,'Herbe','2012-02-25 19:21:56',NULL,'Herbe',1),(28,'Synth','2012-02-25 19:21:56',NULL,'Synthétique',1);
/*!40000 ALTER TABLE `fam_typ_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_user`
--

DROP TABLE IF EXISTS `fam_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_user` (
  `id_user` bigint(20) NOT NULL AUTO_INCREMENT,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `openid` tinyint(1) DEFAULT '0',
  `password` varchar(255) DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_current_club` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UNQ_fam_user_0` (`email`),
  KEY `FK_fam_user_id_current_club` (`id_current_club`),
  CONSTRAINT `FK_fam_user_id_current_club` FOREIGN KEY (`id_current_club`) REFERENCES `fam_club` (`id_club`)
) ENGINE=InnoDB AUTO_INCREMENT=1415 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_user`
--

LOCK TABLES `fam_user` WRITE;
/*!40000 ALTER TABLE `fam_user` DISABLE KEYS */;
INSERT INTO `fam_user` VALUES (1213,'2012-02-25 19:21:57',NULL,'lhutchinson@yah00.co.uk','Carol','Miller',0,'pwd',1,NULL),(1214,'2012-02-25 19:21:57',NULL,'endingasked29@hotma1l.org','Carla','Beasley',0,'pwd',1,NULL),(1215,'2012-02-25 19:21:57',NULL,'visionsshepherd@ma1l2u.co.uk','Deborah','Keith',0,'pwd',1,NULL),(1216,'2012-02-25 19:21:57',NULL,'cballard@ma1l2u.biz','Melody','Keith',0,'pwd',1,NULL),(1217,'2012-02-25 19:21:56',NULL,'endslost@everyma1l.org','Jim','Olsen',0,'pwd',1,NULL),(1218,'2012-02-25 19:21:57',NULL,'dsilva39@somema1l.org','Lawrence','Austin',0,'pwd',1,NULL),(1219,'2012-02-25 19:21:57',NULL,'demandedtook@b1zmail.net','Clara','Atkinson',0,'pwd',1,NULL),(1220,'2012-02-25 19:21:57',NULL,'firepull@b1zmail.com','Charlene','Gallegos',0,'pwd',1,NULL),(1221,'2012-02-25 19:21:57',NULL,'onhave@ma1lbox.biz','Jessica','Romero',0,'pwd',1,NULL),(1222,'2012-02-25 19:21:57',NULL,'monstermuch@hotma1l.net','Jill','Salas',0,'pwd',1,NULL),(1223,'2012-02-25 19:21:57',NULL,'firehad@gma1l.us','Summer','O\'neal',0,'pwd',1,NULL),(1224,'2012-02-25 19:21:57',NULL,'hknight@yah00.org','Vivian','Barker',0,'pwd',1,NULL),(1225,'2012-02-25 19:21:57',NULL,'askedwhat@ma1l2u.co.uk','Shannon','Pollard',0,'pwd',1,NULL),(1226,'2012-02-25 19:21:57',NULL,'creid@everyma1l.net','Miranda','Hale',0,'pwd',1,NULL),(1227,'2012-02-25 19:21:57',NULL,'probablydied@everyma1l.us','Brenda','Williams',0,'pwd',1,NULL),(1228,'2012-02-25 19:21:57',NULL,'tooksmoke@somema1l.biz','Edward','McConnell',0,'pwd',1,NULL),(1229,'2012-02-25 19:21:57',NULL,'kalbert@b1zmail.com','Ricky','Spence',0,'pwd',1,NULL),(1230,'2012-02-25 19:21:57',NULL,'endingshould32@somema1l.org','Victoria','Koch',0,'pwd',1,NULL),(1231,'2012-02-25 19:21:57',NULL,'theygenerated@somema1l.us','Kristin','Middleton',0,'pwd',1,NULL),(1232,'2012-02-25 19:21:57',NULL,'inwas@yah00.co.uk','Roxanne','Patel',0,'pwd',1,NULL),(1233,'2012-02-25 19:21:57',NULL,'untilwith@yah00.biz','Bryan','Holloway',0,'pwd',1,NULL),(1234,'2012-02-25 19:21:57',NULL,'jmonroe73@gma1l.co.uk','Holly','Parrish',0,'pwd',1,NULL),(1235,'2012-02-25 19:21:57',NULL,'ehughes@ma1lbox.com','Clayton','Cunningham',0,'pwd',1,NULL),(1236,'2012-02-25 19:21:57',NULL,'listred@gma1l.net','Danny','Richardson',0,'pwd',1,NULL),(1237,'2012-02-25 19:21:57',NULL,'wfoley80@ma1l2u.net','Todd','Gay',0,'pwd',1,NULL),(1238,'2012-02-25 19:21:57',NULL,'hidisland20@yah00.net','Kathryn','Woodard',0,'pwd',1,NULL),(1239,'2012-02-25 19:21:57',NULL,'lockwhile@hotma1l.com','Abigail','Parrish',0,'pwd',1,NULL),(1240,'2012-02-25 19:21:57',NULL,'outare29@somema1l.us','Thelma','Gaines',0,'pwd',1,NULL),(1241,'2012-02-25 19:21:57',NULL,'wordswill@gma1l.org','James','Hoffman',0,'pwd',1,NULL),(1242,'2012-02-25 19:21:57',NULL,'mlang@yah00.co.uk','Ronnie','Jackson',0,'pwd',1,NULL),(1243,'2012-02-25 19:21:57',NULL,'jdunn@ma1l2u.us','Jimmy','Tanner',0,'pwd',1,NULL),(1244,'2012-02-25 19:21:57',NULL,'rrasmussen@somema1l.us','Haley','Newman',0,'pwd',1,NULL),(1245,'2012-02-25 19:21:57',NULL,'sofasofa@ma1lbox.co.uk','Bonnie','Chen',0,'pwd',1,NULL),(1246,'2012-02-25 19:21:57',NULL,'dadends@gma1l.us','Kristy','Gregory',0,'pwd',1,NULL),(1247,'2012-02-25 19:21:57',NULL,'lotsred@hotma1l.org','Ellen','Tran',0,'pwd',1,NULL),(1248,'2012-02-25 19:21:57',NULL,'kmaxwell95@b1zmail.net','Lynn','Ellis',0,'pwd',1,NULL),(1249,'2012-02-25 19:21:57',NULL,'whoon@gma1l.net','Gabriel','Macdonald',0,'pwd',1,NULL),(1250,'2012-02-25 19:21:57',NULL,'ksuarez@everyma1l.biz','Craig','Odom',0,'pwd',1,NULL),(1251,'2012-02-25 19:21:57',NULL,'rhardin@yah00.biz','Joshua','Dalton',0,'pwd',1,NULL),(1252,'2012-02-25 19:21:57',NULL,'inis23@ma1l2u.org','Julie','Duran',0,'pwd',1,NULL),(1253,'2012-02-25 19:21:57',NULL,'kjuarez81@everyma1l.com','Joel','Vaughn',0,'pwd',1,NULL),(1254,'2012-02-25 19:21:56',NULL,'hrichmond89@yah00.us','Paige','Stark',0,'pwd',1,NULL),(1255,'2012-02-25 19:21:57',NULL,'stownsend@yah00.net','Donald','Glover',0,'pwd',1,NULL),(1256,'2012-02-25 19:21:57',NULL,'acasey@ma1lbox.com','Dan','Avery',0,'pwd',1,NULL),(1257,'2012-02-25 19:21:57',NULL,'visionswe55@b1zmail.net','Alice','Watts',0,'pwd',1,NULL),(1258,'2012-02-25 19:21:56',NULL,'gbougeard@gmail.com','Gregory','Bougeard',1,NULL,1,NULL),(1259,'2012-02-25 19:21:57',NULL,'temerson@gma1l.co.uk','Ronda','Reeves',0,'pwd',1,NULL),(1260,'2012-02-25 19:21:57',NULL,'issuspense@yah00.us','Antonio','Swanson',0,'pwd',1,NULL),(1261,'2012-02-25 19:21:57',NULL,'jcallahan@gma1l.net','Paul','Chase',0,'pwd',1,NULL),(1262,'2012-02-25 19:21:57',NULL,'lgallagher@ma1l2u.us','Doug','Daniels',0,'pwd',1,NULL),(1263,'2012-02-25 19:21:57',NULL,'throwfrom@hotma1l.com','Chester','Pacheco',0,'pwd',1,NULL),(1264,'2012-02-25 19:21:57',NULL,'rgardner@somema1l.co.uk','Lois','Keller',0,'pwd',1,NULL),(1265,'2012-02-25 19:21:57',NULL,'javila35@ma1lbox.biz','Jeff','Mitchell',0,'pwd',1,NULL),(1266,'2012-02-25 19:21:57',NULL,'lkane@ma1l2u.co.uk','Clarence','Collier',0,'pwd',1,NULL),(1267,'2012-02-25 19:21:57',NULL,'aswith@everyma1l.co.uk','Melissa','Knight',0,'pwd',1,NULL),(1268,'2012-02-25 19:21:57',NULL,'rware@ma1l2u.biz','Grace','Huffman',0,'pwd',1,NULL),(1269,'2012-02-25 19:21:57',NULL,'ftran@yah00.co.uk','Eva','Parsons',0,'pwd',1,NULL),(1270,'2012-02-25 19:21:57',NULL,'numberswhile@hotma1l.biz','Erika','Finley',0,'pwd',1,NULL),(1271,'2012-02-25 19:21:57',NULL,'tcooke@yah00.net','Sandy','Craft',0,'pwd',1,NULL),(1272,'2012-02-25 19:21:56',NULL,'lreilly@ma1l2u.net','Lynn','Robles',0,'pwd',1,NULL),(1273,'2012-02-25 19:21:57',NULL,'magneticwith@somema1l.co.uk','Vernon','Daniels',0,'pwd',1,NULL),(1274,'2012-02-25 19:21:57',NULL,'rjimenez@somema1l.biz','Rick','Olson',0,'pwd',1,NULL),(1275,'2012-02-25 19:21:57',NULL,'deverett@everyma1l.org','Ian','Bird',0,'pwd',1,NULL),(1276,'2012-02-25 19:21:57',NULL,'shebert35@yah00.biz','Ralph','Wyatt',0,'pwd',1,NULL),(1277,'2012-02-25 19:21:57',NULL,'dboyle@ma1lbox.org','Wyatt','Allen',0,'pwd',1,NULL),(1278,'2012-02-25 19:21:57',NULL,'vknowles20@somema1l.us','Paula','Stanley',0,'pwd',1,NULL),(1279,'2012-02-25 19:21:57',NULL,'roomgood@b1zmail.us','Cody','Bradley',0,'pwd',1,NULL),(1280,'2012-02-25 19:21:57',NULL,'vrosa@gma1l.org','Leonard','Baldwin',0,'pwd',1,NULL),(1281,'2012-02-25 19:21:57',NULL,'lcochran5@ma1lbox.com','Joel','Michael',0,'pwd',1,NULL),(1282,'2012-02-25 19:21:57',NULL,'wcooper41@everyma1l.net','Julia','Page',0,'pwd',1,NULL),(1283,'2012-02-25 19:21:57',NULL,'endingill@hotma1l.biz','David','Koch',0,'pwd',1,NULL),(1284,'2012-02-25 19:21:56',NULL,'numbershot@yah00.us','Wyatt','Santiago',0,'pwd',1,NULL),(1285,'2012-02-25 19:21:57',NULL,'rabbittook76@b1zmail.org','Kim','Lopez',0,'pwd',1,NULL),(1286,'2012-02-25 19:21:57',NULL,'shepherdof@somema1l.com','Raymond','Blevins',0,'pwd',1,NULL),(1287,'2012-02-25 19:21:56',NULL,'sidekickwe67@somema1l.net','Russell','Hobbs',0,'pwd',1,NULL),(1288,'2012-02-25 19:21:57',NULL,'coldis@somema1l.us','Nathan','Thompson',0,'pwd',1,NULL),(1289,'2012-02-25 19:21:57',NULL,'throwhold@gma1l.co.uk','Faith','Freeman',0,'pwd',1,NULL),(1290,'2012-02-25 19:21:56',NULL,'mcruz@hotma1l.net','Clara','Mack',0,'pwd',1,NULL),(1291,'2012-02-25 19:21:57',NULL,'vblake@hotma1l.net','Lacey','Pruitt',0,'pwd',1,NULL),(1292,'2012-02-25 19:21:57',NULL,'kthomas@everyma1l.biz','Beverly','Gray',0,'pwd',1,NULL),(1293,'2012-02-25 19:21:57',NULL,'isno@yah00.org','Skyler','Blackburn',0,'pwd',1,NULL),(1294,'2012-02-25 19:21:57',NULL,'ithat95@somema1l.net','Lee','Peck',0,'pwd',1,NULL),(1295,'2012-02-25 19:21:57',NULL,'sgaines@gma1l.com','Thelma','Cline',0,'pwd',1,NULL),(1296,'2012-02-25 19:21:57',NULL,'noworn@gma1l.biz','Mandy','Dickerson',0,'pwd',1,NULL),(1297,'2012-02-25 19:21:57',NULL,'staylor@gma1l.biz','Maria','Bryan',0,'pwd',1,NULL),(1298,'2012-02-25 19:21:57',NULL,'ssweet@b1zmail.net','Dylan','James',0,'pwd',1,NULL),(1299,'2012-02-25 19:21:57',NULL,'interestto@ma1lbox.biz','Nicole','Rodriquez',0,'pwd',1,NULL),(1300,'2012-02-25 19:21:57',NULL,'bwinters@ma1lbox.net','Helen','Bennett',0,'pwd',1,NULL),(1301,'2012-02-25 19:21:57',NULL,'hstafford@b1zmail.net','Alex','Higgins',0,'pwd',1,NULL),(1302,'2012-02-25 19:21:57',NULL,'suspenseof@ma1l2u.net','Terry','Roberson',0,'pwd',1,NULL),(1303,'2012-02-25 19:21:57',NULL,'kbernard67@gma1l.com','Allison','Wallace',0,'pwd',1,NULL),(1304,'2012-02-25 19:21:57',NULL,'hthompson@everyma1l.com','Penny','Sweet',0,'pwd',1,NULL),(1305,'2012-02-25 19:21:57',NULL,'jcarver11@b1zmail.us','Gloria','Guzman',0,'pwd',1,NULL),(1306,'2012-02-25 19:21:57',NULL,'islandroom@ma1lbox.com','Justin','Henry',0,'pwd',1,NULL),(1307,'2012-02-25 19:21:57',NULL,'fkey@hotma1l.co.uk','Shirley','Flynn',0,'pwd',1,NULL),(1308,'2012-02-25 19:21:57',NULL,'ehale@ma1lbox.biz','Patrick','Tyler',0,'pwd',1,NULL),(1309,'2012-02-25 19:21:57',NULL,'handledwaited@gma1l.us','Becky','Burch',0,'pwd',1,NULL),(1310,'2012-02-25 19:21:57',NULL,'dmeadows@ma1lbox.org','Tammy','Harvey',0,'pwd',1,NULL),(1311,'2012-02-25 19:21:57',NULL,'inin19@yah00.biz','Tasha','Rios',0,'pwd',1,NULL),(1312,'2012-02-25 19:21:57',NULL,'myis@ma1lbox.co.uk','Terri','Alvarez',0,'pwd',1,NULL),(1313,'2012-02-25 19:21:57',NULL,'acaves@ma1l2u.org','Deanna','Cain',0,'pwd',1,NULL),(1314,'2012-02-25 19:21:57',NULL,'wmacias@ma1lbox.com','Katherine','Dudley',0,'pwd',1,NULL),(1315,'2012-02-25 19:21:57',NULL,'mysidekick@somema1l.org','Roberta','Olson',0,'pwd',1,NULL),(1316,'2012-02-25 19:21:57',NULL,'dsuarez@gma1l.us','Dennis','Farley',0,'pwd',1,NULL),(1317,'2012-02-25 19:21:57',NULL,'codelibrary@ma1lbox.org','Cameron','Wright',0,'pwd',1,NULL),(1318,'2012-02-25 19:21:57',NULL,'ddonaldson@everyma1l.us','Casey','Benjamin',0,'pwd',1,NULL),(1319,'2012-02-25 19:21:57',NULL,'chutchinson@ma1lbox.co.uk','Gail','Tate',0,'pwd',1,NULL),(1320,'2012-02-25 19:21:57',NULL,'whenout@gma1l.biz','Timmy','Patterson',0,'pwd',1,NULL),(1321,'2012-02-25 19:21:57',NULL,'rtownsend40@gma1l.co.uk','Virginia','Pitts',0,'pwd',1,NULL),(1322,'2012-02-25 19:21:57',NULL,'ccook@b1zmail.com','Gregory','Jacobson',0,'pwd',1,NULL),(1323,'2012-02-25 19:21:57',NULL,'sotrees@everyma1l.net','Cassandra','Molina',0,'pwd',1,NULL),(1324,'2012-02-25 19:21:57',NULL,'sacrificeso@somema1l.com','Howard','Duran',0,'pwd',1,NULL),(1325,'2012-02-25 19:21:57',NULL,'sfry@ma1l2u.biz','Unborn','Rivas',0,'pwd',1,NULL),(1326,'2012-02-25 19:21:57',NULL,'rabbitdo89@hotma1l.org','Matthew','Robles',0,'pwd',1,NULL),(1327,'2012-02-25 19:21:57',NULL,'keysroom@everyma1l.us','Sue','Rhodes',0,'pwd',1,NULL),(1328,'2012-02-25 19:21:57',NULL,'lryan@ma1l2u.co.uk','Ginger','Moon',0,'pwd',1,NULL),(1329,'2012-02-25 19:21:57',NULL,'orwas@somema1l.us','Christopher','Ratliff',0,'pwd',1,NULL),(1330,'2012-02-25 19:21:57',NULL,'towe@everyma1l.co.uk','Tina','Chang',0,'pwd',1,NULL),(1331,'2012-02-25 19:21:57',NULL,'pbernard63@everyma1l.co.uk','Kayla','Callahan',0,'pwd',1,NULL),(1332,'2012-02-25 19:21:57',NULL,'jruiz93@b1zmail.org','Debra','Wyatt',0,'pwd',1,NULL),(1333,'2012-02-25 19:21:57',NULL,'libraryabout@everyma1l.org','Betty','Malone',0,'pwd',1,NULL),(1334,'2012-02-25 19:21:57',NULL,'sitsso@b1zmail.org','Albert','Lawson',0,'pwd',1,NULL),(1335,'2012-02-25 19:21:57',NULL,'gbarnett@somema1l.org','Father','Hood',0,'pwd',1,NULL),(1336,'2012-02-25 19:21:56',NULL,'mphillips@everyma1l.net','Eddie','O\'neill',0,'pwd',1,NULL),(1337,'2012-02-25 19:21:57',NULL,'dmitchell@yah00.us','Blake','Greer',0,'pwd',1,NULL),(1338,'2012-02-25 19:21:57',NULL,'caveslibrary@hotma1l.com','Chuck','Webster',0,'pwd',1,NULL),(1339,'2012-02-25 19:21:57',NULL,'automaticallyin@ma1lbox.net','Rachel','Johnston',0,'pwd',1,NULL),(1340,'2012-02-25 19:21:57',NULL,'cmatthews@gma1l.org','Richard','Richmond',0,'pwd',1,NULL),(1341,'2012-02-25 19:21:57',NULL,'isuntil44@ma1l2u.co.uk','Ron','Chambers',0,'pwd',1,NULL),(1342,'2012-02-25 19:21:56',NULL,'alwaysit@ma1l2u.us','Judy','Burks',0,'pwd',1,NULL),(1343,'2012-02-25 19:21:57',NULL,'dterry@ma1l2u.org','Alexandra','Walls',0,'pwd',1,NULL),(1344,'2012-02-25 19:21:57',NULL,'movedsuspense@b1zmail.co.uk','Bobbie','Hardy',0,'pwd',1,NULL),(1345,'2012-02-25 19:21:57',NULL,'mbooth@yah00.org','Johnny','Rice',0,'pwd',1,NULL),(1346,'2012-02-25 19:21:57',NULL,'jgrimes@somema1l.net','Mother','Chaney',0,'pwd',1,NULL),(1347,'2012-02-25 19:21:57',NULL,'whohot@b1zmail.org','Alice','Moran',0,'pwd',1,NULL),(1348,'2012-02-25 19:21:57',NULL,'kbean@somema1l.net','Louis','Clarke',0,'pwd',1,NULL),(1349,'2012-02-25 19:21:57',NULL,'skidd76@ma1lbox.com','Ruth','Kinney',0,'pwd',1,NULL),(1350,'2012-02-25 19:21:57',NULL,'kscott@somema1l.org','Herbert','Berg',0,'pwd',1,NULL),(1351,'2012-02-25 19:21:57',NULL,'hmalone@everyma1l.co.uk','Pamela','Vasquez',0,'pwd',1,NULL),(1352,'2012-02-25 19:21:57',NULL,'lrivers@somema1l.com','Kristin','Davidson',0,'pwd',1,NULL),(1353,'2012-02-25 19:21:57',NULL,'obaxter30@ma1l2u.net','Clarence','Walker',0,'pwd',1,NULL),(1354,'2012-02-25 19:21:57',NULL,'islandautomatically@ma1lbox.net','Dean','Vincent',0,'pwd',1,NULL),(1355,'2012-02-25 19:21:57',NULL,'lmcdonald@ma1l2u.biz','Larry','Diaz',0,'pwd',1,NULL),(1356,'2012-02-25 19:21:57',NULL,'cbranch@yah00.net','Andrea','Valencia',0,'pwd',1,NULL),(1357,'2012-02-25 19:21:57',NULL,'dadhot@ma1l2u.us','Pete','Kelly',0,'pwd',1,NULL),(1358,'2012-02-25 19:21:57',NULL,'ejennings@yah00.us','Dominic','Deleon',0,'pwd',1,NULL),(1359,'2012-02-25 19:21:57',NULL,'tclayton@yah00.org','Lawrence','Bender',0,'pwd',1,NULL),(1360,'2012-02-25 19:21:57',NULL,'dholman@b1zmail.biz','Brianna','Frank',0,'pwd',1,NULL),(1361,'2012-02-25 19:21:57',NULL,'jcooper23@b1zmail.net','Brett','Bridges',0,'pwd',1,NULL),(1362,'2012-02-25 19:21:57',NULL,'tschwartz61@hotma1l.com','Austin','Schwartz',0,'pwd',1,NULL),(1363,'2012-02-25 19:21:57',NULL,'inwant91@somema1l.org','Darla','Martin',0,'pwd',1,NULL),(1364,'2012-02-25 19:21:57',NULL,'headphonesgood@b1zmail.org','Levi','Fields',0,'pwd',1,NULL),(1365,'2012-02-25 19:21:57',NULL,'havewas@ma1lbox.net','Sue','Dorsey',0,'pwd',1,NULL),(1366,'2012-02-25 19:21:57',NULL,'theisland@b1zmail.co.uk','Wendy','Thornton',0,'pwd',1,NULL),(1367,'2012-02-25 19:21:56','2012-02-25 19:21:57','toto@gmail.com','Toto','Test',0,'toto',2,65),(1368,'2012-02-25 19:21:57',NULL,'bhammond@yah00.us','Tyrone','Ayala',0,'pwd',1,NULL),(1369,'2012-02-25 19:21:57',NULL,'onship@ma1l2u.org','Alyssa','Lloyd',0,'pwd',1,NULL),(1370,'2012-02-25 19:21:57',NULL,'lpeters68@b1zmail.biz','Jonathan','Hurst',0,'pwd',1,NULL),(1371,'2012-02-25 19:21:57',NULL,'keysdreams79@gma1l.net','Alan','Mejia',0,'pwd',1,NULL),(1372,'2012-02-25 19:21:57',NULL,'illthey59@somema1l.biz','Don','Griffith',0,'pwd',1,NULL),(1373,'2012-02-25 19:21:57',NULL,'ddaniels@b1zmail.org','Kim','Carver',0,'pwd',1,NULL),(1374,'2012-02-25 19:21:57',NULL,'computercame@ma1lbox.us','Jeff','Morales',0,'pwd',1,NULL),(1375,'2012-02-25 19:21:57',NULL,'coldhandled@b1zmail.com','Tammy','Chaney',0,'pwd',1,NULL),(1376,'2012-02-25 19:21:57',NULL,'lpatel@yah00.net','Mary','Burch',0,'pwd',1,NULL),(1377,'2012-02-25 19:21:57',NULL,'whohave@gma1l.com','Trevor','Holman',0,'pwd',1,NULL),(1378,'2012-02-25 19:21:57',NULL,'apowell49@hotma1l.us','Abby','Graham',0,'pwd',1,NULL),(1379,'2012-02-25 19:21:57',NULL,'hatit\'s@somema1l.com','Chad','Farrell',0,'pwd',1,NULL),(1380,'2012-02-25 19:21:57',NULL,'tgalloway17@yah00.co.uk','Gloria','Witt',0,'pwd',1,NULL),(1381,'2012-02-25 19:21:57',NULL,'roomsacrifice@gma1l.co.uk','Olivia','Savage',0,'pwd',1,NULL),(1382,'2012-02-25 19:21:57',NULL,'mnguyen@yah00.biz','Paige','Moreno',0,'pwd',1,NULL),(1383,'2012-02-25 19:21:57',NULL,'ofsomething23@everyma1l.org','Tony','McCray',0,'pwd',1,NULL),(1384,'2012-02-25 19:21:57',NULL,'endsor_maybe@ma1lbox.com','Clint','Harding',0,'pwd',1,NULL),(1385,'2012-02-25 19:21:57',NULL,'tlyons72@everyma1l.us','Melody','Dyer',0,'pwd',1,NULL),(1386,'2012-02-25 19:21:57',NULL,'nreed@ma1l2u.us','Trevor','Bartlett',0,'pwd',1,NULL),(1387,'2012-02-25 19:21:57',NULL,'jhicks@somema1l.us','Thelma','Conner',0,'pwd',1,NULL),(1388,'2012-02-25 19:21:56',NULL,'mywhat@somema1l.biz','George','Suarez',0,'pwd',1,NULL),(1389,'2012-02-25 19:21:57',NULL,'interestno@gma1l.net','Lee','Maxwell',0,'pwd',1,NULL),(1390,'2012-02-25 19:21:57',NULL,'roomof@hotma1l.net','Tessa','George',0,'pwd',1,NULL),(1391,'2012-02-25 19:21:57',NULL,'churst@hotma1l.biz','Dan','White',0,'pwd',1,NULL),(1392,'2012-02-25 19:21:57',NULL,'dbeck@hotma1l.net','Tiffany','Gonzalez',0,'pwd',1,NULL),(1393,'2012-02-25 19:21:57',NULL,'tlindsey@yah00.us','Nathan','Clark',0,'pwd',1,NULL),(1394,'2012-02-25 19:21:57',NULL,'movedhandled@somema1l.biz','Cynthia','Lamb',0,'pwd',1,NULL),(1395,'2012-02-25 19:21:57',NULL,'buildhid@b1zmail.org','Joanne','Petty',0,'pwd',1,NULL),(1396,'2012-02-25 19:21:57',NULL,'khorn@b1zmail.us','Frank','Petersen',0,'pwd',1,NULL),(1397,'2012-02-25 19:21:57',NULL,'mbrock@hotma1l.org','Clara','Frazier',0,'pwd',1,NULL),(1398,'2012-02-25 19:21:57',NULL,'myfound@gma1l.org','Tim','Christensen',0,'pwd',1,NULL),(1399,'2012-02-25 19:21:57',NULL,'itends39@b1zmail.org','Marcus','Young',0,'pwd',1,NULL),(1400,'2012-02-25 19:21:57',NULL,'probablyisland@everyma1l.co.uk','Lois','Mills',0,'pwd',1,NULL),(1401,'2012-02-25 19:21:57',NULL,'weshould@gma1l.net','Jeff','Galloway',0,'pwd',1,NULL),(1402,'2012-02-25 19:21:57',NULL,'endball@ma1l2u.net','Carmen','Boyer',0,'pwd',1,NULL),(1403,'2012-02-25 19:21:57',NULL,'automaticallyno@gma1l.org','Brooklyn','Obrien',0,'pwd',1,NULL),(1404,'2012-02-25 19:21:56',NULL,'theyshepherd@everyma1l.co.uk','Johnny','Cohen',0,'pwd',1,NULL),(1405,'2012-02-25 19:21:57',NULL,'djacobs36@yah00.us','Brooklyn','Everett',0,'pwd',1,NULL),(1406,'2012-02-25 19:21:57',NULL,'tooklock@somema1l.us','Monica','Waller',0,'pwd',1,NULL),(1407,'2012-02-25 19:21:57',NULL,'dle@gma1l.org','Deanna','Ray',0,'pwd',1,NULL),(1408,'2012-02-25 19:21:57',NULL,'waitedcaptain@ma1lbox.co.uk','Andrea','Fowler',0,'pwd',1,NULL),(1409,'2012-02-25 19:21:57',NULL,'jowen@gma1l.co.uk','Lori','Hoffman',0,'pwd',1,NULL),(1410,'2012-02-25 19:21:57',NULL,'sgeorge@ma1l2u.us','Jordan','Flores',0,'pwd',1,NULL),(1411,'2012-02-25 19:21:57',NULL,'gvang@everyma1l.us','Gregory','Wilcox',0,'pwd',1,NULL),(1412,'2012-02-25 19:21:57',NULL,'itfound@hotma1l.net','Donna','Mullen',0,'pwd',1,NULL),(1413,'2012-02-25 19:21:57',NULL,'illwho@gma1l.net','Eva','Everett',0,'pwd',1,NULL),(1414,'2012-02-25 19:21:57',NULL,'snelson@hotma1l.net','Taylor','Ferguson',0,'pwd',1,NULL);
/*!40000 ALTER TABLE `fam_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_user_season`
--

DROP TABLE IF EXISTS `fam_user_season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_user_season` (
  `confirmed` tinyint(1) DEFAULT '0',
  `isAdmin` tinyint(1) DEFAULT '0',
  `isCoach` tinyint(1) DEFAULT '0',
  `isPlayer` tinyint(1) DEFAULT '0',
  `isReporter` tinyint(1) DEFAULT '0',
  `id_club` bigint(20) DEFAULT NULL,
  `id_season` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_season`,`id_user`),
  KEY `FK_fam_user_season_id_user` (`id_user`),
  KEY `FK_fam_user_season_id_club` (`id_club`),
  CONSTRAINT `FK_fam_user_season_id_club` FOREIGN KEY (`id_club`) REFERENCES `fam_club` (`id_club`),
  CONSTRAINT `FK_fam_user_season_id_season` FOREIGN KEY (`id_season`) REFERENCES `fam_season` (`id_season`),
  CONSTRAINT `FK_fam_user_season_id_user` FOREIGN KEY (`id_user`) REFERENCES `fam_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_user_season`
--

LOCK TABLES `fam_user_season` WRITE;
/*!40000 ALTER TABLE `fam_user_season` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_user_season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_workout`
--

DROP TABLE IF EXISTS `fam_workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_workout` (
  `id_workout` bigint(20) NOT NULL AUTO_INCREMENT,
  `dt_creat` datetime DEFAULT NULL,
  `dt_modif` datetime DEFAULT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `id_event` bigint(20) DEFAULT NULL,
  `id_club` bigint(20) NOT NULL,
  PRIMARY KEY (`id_workout`),
  KEY `FK_fam_workout_id_event` (`id_event`),
  CONSTRAINT `FK_fam_workout_id_event` FOREIGN KEY (`id_event`) REFERENCES `fam_event` (`id_event`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_workout`
--

LOCK TABLES `fam_workout` WRITE;
/*!40000 ALTER TABLE `fam_workout` DISABLE KEYS */;
INSERT INTO `fam_workout` VALUES (7,'2012-02-25 19:21:56',NULL,1,7,0);
/*!40000 ALTER TABLE `fam_workout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fam_workout_player`
--

DROP TABLE IF EXISTS `fam_workout_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fam_workout_player` (
  `id_workout` bigint(20) NOT NULL,
  `id_player` bigint(20) NOT NULL,
  PRIMARY KEY (`id_workout`,`id_player`),
  KEY `FK_fam_workout_player_id_player` (`id_player`),
  CONSTRAINT `FK_fam_workout_player_id_player` FOREIGN KEY (`id_player`) REFERENCES `fam_player` (`id_player`),
  CONSTRAINT `FK_fam_workout_player_id_workout` FOREIGN KEY (`id_workout`) REFERENCES `fam_workout` (`id_workout`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fam_workout_player`
--

LOCK TABLES `fam_workout_player` WRITE;
/*!40000 ALTER TABLE `fam_workout_player` DISABLE KEYS */;
/*!40000 ALTER TABLE `fam_workout_player` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-03-22  0:05:06
