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
-- Table structure for table `fam_country`
--


--
-- Dumping data for table `fam_country`
--

LOCK TABLES `fam_country` WRITE;
/*!40000 ALTER TABLE `fam_country` DISABLE KEYS */;
INSERT INTO `fam_country` VALUES (1,'fr','2012-02-25 19:20:28',NULL,'France','france','FRANCE',1),(2,'af','2012-02-25 19:20:28',NULL,'Afghanistan','afghanistan','AFGHANISTAN',1),(3,'za','2012-02-25 19:20:28',NULL,'Afrique du sud','afrique-du-sud','AFRIQUE DU SUD',1),(4,'al','2012-02-25 19:20:28',NULL,'Albanie','albanie','ALBANIE',1),(5,'dz','2012-02-25 19:20:28',NULL,'Algérie','algerie','ALGÉRIE',1),(6,'de','2012-02-25 19:20:28',NULL,'Allemagne','allemagne','ALLEMAGNE',1),(7,'sa','2012-02-25 19:20:28',NULL,'Arabie saoudite','arabie-saoudite','ARABIE SAOUDITE',1),(8,'ar','2012-02-25 19:20:28',NULL,'Argentine','argentine','ARGENTINE',1),(9,'au','2012-02-25 19:20:28',NULL,'Australie','australie','AUSTRALIE',1),(10,'at','2012-02-25 19:20:29',NULL,'Autriche','autriche','AUTRICHE',1),(11,'be','2012-02-25 19:20:29',NULL,'Belgique','belgique','BELGIQUE',1),(12,'br','2012-02-25 19:20:29',NULL,'Brésil','bresil','BRÉSIL',1),(13,'bg','2012-02-25 19:20:29',NULL,'Bulgarie','bulgarie','BULGARIE',1),(14,'ca','2012-02-25 19:20:29',NULL,'Canada','canada','CANADA',1),(15,'cl','2012-02-25 19:20:29',NULL,'Chili','chili','CHILI',1),(16,'cn','2012-02-25 19:20:29',NULL,'Chine (Rép. pop.)','chine-rep-pop','CHINE (RÉP. POP.)',1),(17,'co','2012-02-25 19:20:29',NULL,'Colombie','colombie','COLOMBIE',1),(18,'kr','2012-02-25 19:20:29',NULL,'Corée, Sud','coree-sud','CORÉE, SUD',1),(19,'cr','2012-02-25 19:20:29',NULL,'Costa Rica','costa-rica','COSTA RICA',1),(20,'hr','2012-02-25 19:20:29',NULL,'Croatie','croatie','CROATIE',1),(21,'dk','2012-02-25 19:20:29',NULL,'Danemark','danemark','DANEMARK',1),(22,'eg','2012-02-25 19:20:29',NULL,'Égypte','egypte','ÉGYPTE',1),(23,'ae','2012-02-25 19:20:29',NULL,'Émirats arabes unis','emirats-arabes-unis','ÉMIRATS ARABES UNIS',1),(24,'ec','2012-02-25 19:20:29',NULL,'Équateur','equateur','ÉQUATEUR',1),(25,'us','2012-02-25 19:20:29',NULL,'États-Unis','etats-unis','ÉTATS-UNIS',1),(26,'sv','2012-02-25 19:20:29',NULL,'El Salvador','el-salvador','EL SALVADOR',1),(27,'es','2012-02-25 19:20:29',NULL,'Espagne','espagne','ESPAGNE',1),(28,'fi','2012-02-25 19:20:29',NULL,'Finlande','finlande','FINLANDE',1),(29,'gr','2012-02-25 19:20:29',NULL,'Grèce','grece','GRÈCE',1),(30,'hk','2012-02-25 19:20:29',NULL,'Hong Kong','hong-kong','HONG KONG',1),(31,'hu','2012-02-25 19:20:29',NULL,'Hongrie','hongrie','HONGRIE',1),(32,'in','2012-02-25 19:20:29',NULL,'Inde','inde','INDE',1),(33,'id','2012-02-25 19:20:29',NULL,'Indonésie','indonesie','INDONÉSIE',1),(34,'ie','2012-02-25 19:20:29',NULL,'Irlande','irlande','IRLANDE',1),(35,'il','2012-02-25 19:20:29',NULL,'Israël','israel','ISRAËL',1),(36,'it','2012-02-25 19:20:29',NULL,'Italie','italie','ITALIE',1),(37,'jp','2012-02-25 19:20:29',NULL,'Japon','japon','JAPON',1),(38,'jo','2012-02-25 19:20:29',NULL,'Jordanie','jordanie','JORDANIE',1),(39,'lb','2012-02-25 19:20:29',NULL,'Liban','liban','LIBAN',1),(40,'my','2012-02-25 19:20:29',NULL,'Malaisie','malaisie','MALAISIE',1),(41,'ma','2012-02-25 19:20:29',NULL,'Maroc','maroc','MAROC',1),(42,'mx','2012-02-25 19:20:29',NULL,'Mexique','mexique','MEXIQUE',1),(43,'no','2012-02-25 19:20:29',NULL,'Norvège','norvege','NORVÈGE',1),(44,'nz','2012-02-25 19:20:29',NULL,'Nouvelle-Zélande','nouvelle-zelande','NOUVELLE-ZÉLANDE',1),(45,'pe','2012-02-25 19:20:29',NULL,'Pérou','perou','PÉROU',1),(46,'pk','2012-02-25 19:20:29',NULL,'Pakistan','pakistan','PAKISTAN',1),(47,'nl','2012-02-25 19:20:29',NULL,'Pays-Bas','pays-bas','PAYS-BAS',1),(48,'ph','2012-02-25 19:20:29',NULL,'Philippines','philippines','PHILIPPINES',1),(49,'pl','2012-02-25 19:20:29',NULL,'Pologne','pologne','POLOGNE',1),(50,'pr','2012-02-25 19:20:29',NULL,'Porto Rico','porto-rico','PORTO RICO',1),(51,'pt','2012-02-25 19:20:29',NULL,'Portugal','portugal','PORTUGAL',1),(52,'cz','2012-02-25 19:20:29',NULL,'République tchèque','republique-tcheque','RÉPUBLIQUE TCHÈQUE',1),(53,'ro','2012-02-25 19:20:29',NULL,'Roumanie','roumanie','ROUMANIE',1),(54,'uk','2012-02-25 19:20:29',NULL,'Royaume-Uni','royaume-uni','ROYAUME-UNI',1),(55,'ru','2012-02-25 19:20:29',NULL,'Russie','russie','RUSSIE',1),(56,'sg','2012-02-25 19:20:29',NULL,'Singapour','singapour','SINGAPOUR',1),(57,'se','2012-02-25 19:20:29',NULL,'Suède','suede','SUÈDE',1),(58,'ch','2012-02-25 19:20:29',NULL,'Suisse','suisse','SUISSE',1),(59,'tw','2012-02-25 19:20:29',NULL,'Taiwan','taiwan','TAIWAN',1),(60,'th','2012-02-25 19:20:29',NULL,'Thailande','thailande','THAILANDE',1),(61,'tr','2012-02-25 19:20:29',NULL,'Turquie','turquie','TURQUIE',1),(62,'ua','2012-02-25 19:20:29',NULL,'Ukraine','ukraine','UKRAINE',1),(63,'ve','2012-02-25 19:20:29',NULL,'Venezuela','venezuela','VENEZUELA',1),(64,'yu','2012-02-25 19:20:29',NULL,'Yougoslavie','yougoslavie','YOUGOSLAVIE',1),(65,'as','2012-02-25 19:20:29',NULL,'Samoa','samoa','SAMOA',1),(66,'ad','2012-02-25 19:20:29',NULL,'Andorre','andorre','ANDORRE',1),(67,'ao','2012-02-25 19:20:29',NULL,'Angola','angola','ANGOLA',1),(68,'ai','2012-02-25 19:20:29',NULL,'Anguilla','anguilla','ANGUILLA',1),(69,'aq','2012-02-25 19:20:29',NULL,'Antarctique','antarctique','ANTARCTIQUE',1),(70,'ag','2012-02-25 19:20:29',NULL,'Antigua et Barbuda','antigua-et-barbuda','ANTIGUA ET BARBUDA',1),(71,'am','2012-02-25 19:20:29',NULL,'Arménie','armenie','ARMÉNIE',1),(72,'aw','2012-02-25 19:20:29',NULL,'Aruba','aruba','ARUBA',1),(73,'az','2012-02-25 19:20:29',NULL,'Azerbaïdjan','azerbaidjan','AZERBAÏDJAN',1),(74,'bs','2012-02-25 19:20:29',NULL,'Bahamas','bahamas','BAHAMAS',1),(75,'bh','2012-02-25 19:20:29',NULL,'Bahrain','bahrain','BAHRAIN',1),(76,'bd','2012-02-25 19:20:29',NULL,'Bangladesh','bangladesh','BANGLADESH',1),(77,'by','2012-02-25 19:20:29',NULL,'Biélorussie','bielorussie','BIÉLORUSSIE',1),(78,'bz','2012-02-25 19:20:29',NULL,'Belize','belize','BELIZE',1),(79,'bj','2012-02-25 19:20:29',NULL,'Benin','benin','BENIN',1),(80,'bm','2012-02-25 19:20:29',NULL,'Bermudes (Les)','bermudes-les','BERMUDES (LES)',1),(81,'bt','2012-02-25 19:20:29',NULL,'Bhoutan','bhoutan','BHOUTAN',1),(82,'bo','2012-02-25 19:20:29',NULL,'Bolivie','bolivie','BOLIVIE',1),(83,'ba','2012-02-25 19:20:29',NULL,'Bosnie-Herzégovine','bosnie-herzegovine','BOSNIE-HERZÉGOVINE',1),(84,'bw','2012-02-25 19:20:29',NULL,'Botswana','botswana','BOTSWANA',1),(85,'bv','2012-02-25 19:20:29',NULL,'Bouvet (Îles)','bouvet-iles','BOUVET (ÎLES)',1),(86,'io','2012-02-25 19:20:29',NULL,'Territoire britannique de l\'océan Indien','territoire-britannique-de-locean-indien','TERRITOIRE BRITANNIQUE DE L\'OCÉAN INDIEN',1),(87,'vg','2012-02-25 19:20:29',NULL,'Vierges britanniques (Îles)','vierges-britanniques-iles','VIERGES BRITANNIQUES (ÎLES)',1),(88,'bn','2012-02-25 19:20:29',NULL,'Brunei','brunei','BRUNEI',1),(89,'bf','2012-02-25 19:20:29',NULL,'Burkina Faso','burkina-faso','BURKINA FASO',1),(90,'bi','2012-02-25 19:20:29',NULL,'Burundi','burundi','BURUNDI',1),(91,'kh','2012-02-25 19:20:29',NULL,'Cambodge','cambodge','CAMBODGE',1),(92,'cm','2012-02-25 19:20:29',NULL,'Cameroun','cameroun','CAMEROUN',1),(93,'cv','2012-02-25 19:20:29',NULL,'Cap Vert','cap-vert','CAP VERT',1),(94,'ky','2012-02-25 19:20:29',NULL,'Cayman (Îles)','cayman-iles','CAYMAN (ÎLES)',1),(95,'cf','2012-02-25 19:20:29',NULL,'République centrafricaine','republique-centrafricaine','RÉPUBLIQUE CENTRAFRICAINE',1),(96,'td','2012-02-25 19:20:29',NULL,'Tchad','tchad','TCHAD',1),(97,'cx','2012-02-25 19:20:29',NULL,'Christmas (Île)','christmas-ile','CHRISTMAS (ÎLE)',1),(98,'cc','2012-02-25 19:20:29',NULL,'Cocos (Îles)','cocos-iles','COCOS (ÎLES)',1),(99,'km','2012-02-25 19:20:29',NULL,'Comores','comores','COMORES',1),(100,'cg','2012-02-25 19:20:29',NULL,'Rép. Dém. du Congo','rep-dem-du-congo','RÉP. DÉM. DU CONGO',1),(101,'ck','2012-02-25 19:20:29',NULL,'Cook (Îles)','cook-iles','COOK (ÎLES)',1),(102,'cu','2012-02-25 19:20:29',NULL,'Cuba','cuba','CUBA',1),(103,'cy','2012-02-25 19:20:29',NULL,'Chypre','chypre','CHYPRE',1),(104,'dj','2012-02-25 19:20:29',NULL,'Djibouti','djibouti','DJIBOUTI',1),(105,'dm','2012-02-25 19:20:29',NULL,'Dominique','dominique','DOMINIQUE',1),(106,'do','2012-02-25 19:20:29',NULL,'République Dominicaine','republique-dominicaine','RÉPUBLIQUE DOMINICAINE',1),(107,'tp','2012-02-25 19:20:29',NULL,'Timor','timor','TIMOR',1),(108,'gq','2012-02-25 19:20:29',NULL,'Guinée Equatoriale','guinee-equatoriale','GUINÉE EQUATORIALE',1),(109,'er','2012-02-25 19:20:29',NULL,'Érythrée','erythree','ÉRYTHRÉE',1),(110,'ee','2012-02-25 19:20:29',NULL,'Estonie','estonie','ESTONIE',1),(111,'et','2012-02-25 19:20:29',NULL,'Ethiopie','ethiopie','ETHIOPIE',1),(112,'fk','2012-02-25 19:20:29',NULL,'Falkland (Île)','falkland-ile','FALKLAND (ÎLE)',1),(113,'fo','2012-02-25 19:20:29',NULL,'Féroé (Îles)','feroe-iles','FÉROÉ (ÎLES)',1),(114,'fj','2012-02-25 19:20:29',NULL,'Fidji (République des)','fidji-republique-des','FIDJI (RÉPUBLIQUE DES)',1),(115,'gf','2012-02-25 19:20:29',NULL,'Guyane française','guyane-francaise','GUYANE FRANÇAISE',1),(116,'pf','2012-02-25 19:20:29',NULL,'Polynésie française','polynesie-francaise','POLYNÉSIE FRANÇAISE',1),(117,'tf','2012-02-25 19:20:29',NULL,'Territoires français du sud','territoires-francais-du-sud','TERRITOIRES FRANÇAIS DU SUD',1),(118,'ga','2012-02-25 19:20:29',NULL,'Gabon','gabon','GABON',1),(119,'gm','2012-02-25 19:20:29',NULL,'Gambie','gambie','GAMBIE',1),(120,'ge','2012-02-25 19:20:29',NULL,'Géorgie','georgie','GÉORGIE',1),(121,'gh','2012-02-25 19:20:29',NULL,'Ghana','ghana','GHANA',1),(122,'gi','2012-02-25 19:20:29',NULL,'Gibraltar','gibraltar','GIBRALTAR',1),(123,'gl','2012-02-25 19:20:29',NULL,'Groenland','groenland','GROENLAND',1),(124,'gd','2012-02-25 19:20:29',NULL,'Grenade','grenade','GRENADE',1),(125,'gp','2012-02-25 19:20:29',NULL,'Guadeloupe','guadeloupe','GUADELOUPE',1),(126,'gu','2012-02-25 19:20:29',NULL,'Guam','guam','GUAM',1),(127,'gt','2012-02-25 19:20:29',NULL,'Guatemala','guatemala','GUATEMALA',1),(128,'gn','2012-02-25 19:20:29',NULL,'Guinée','guinee','GUINÉE',1),(129,'gw','2012-02-25 19:20:29',NULL,'Guinée-Bissau','guinee-bissau','GUINÉE-BISSAU',1),(130,'gy','2012-02-25 19:20:29',NULL,'Guyane','guyane','GUYANE',1),(131,'ht','2012-02-25 19:20:29',NULL,'Haïti','haiti','HAÏTI',1),(132,'hm','2012-02-25 19:20:29',NULL,'Heard et McDonald (Îles)','heard-et-mcdonald-iles','HEARD ET MCDONALD (ÎLES)',1),(133,'hn','2012-02-25 19:20:29',NULL,'Honduras','honduras','HONDURAS',1),(134,'is','2012-02-25 19:20:29',NULL,'Islande','islande','ISLANDE',1),(135,'ir','2012-02-25 19:20:29',NULL,'Iran','iran','IRAN',1),(136,'iq','2012-02-25 19:20:29',NULL,'Irak','irak','IRAK',1),(137,'ci','2012-02-25 19:20:29',NULL,'Côte d\'Ivoire','cote-divoire','CÔTE D\'IVOIRE',1),(138,'jm','2012-02-25 19:20:29',NULL,'Jamaïque','jamaique','JAMAÏQUE',1),(139,'kz','2012-02-25 19:20:29',NULL,'Kazakhstan','kazakhstan','KAZAKHSTAN',1),(140,'ke','2012-02-25 19:20:29',NULL,'Kenya','kenya','KENYA',1),(141,'ki','2012-02-25 19:20:29',NULL,'Kiribati','kiribati','KIRIBATI',1),(142,'kp','2012-02-25 19:20:29',NULL,'Corée du Nord','coree-du-nord','CORÉE DU NORD',1),(143,'kw','2012-02-25 19:20:29',NULL,'Koweit','koweit','KOWEIT',1),(144,'kg','2012-02-25 19:20:29',NULL,'Kirghizistan','kirghizistan','KIRGHIZISTAN',1),(145,'la','2012-02-25 19:20:29',NULL,'Laos','laos','LAOS',1),(146,'lv','2012-02-25 19:20:29',NULL,'Lettonie','lettonie','LETTONIE',1),(147,'ls','2012-02-25 19:20:29',NULL,'Lesotho','lesotho','LESOTHO',1),(148,'lr','2012-02-25 19:20:29',NULL,'Libéria','liberia','LIBÉRIA',1),(149,'ly','2012-02-25 19:20:29',NULL,'Libye','libye','LIBYE',1),(150,'li','2012-02-25 19:20:29',NULL,'Liechtenstein','liechtenstein','LIECHTENSTEIN',1),(151,'lt','2012-02-25 19:20:29',NULL,'Lithuanie','lithuanie','LITHUANIE',1),(152,'lu','2012-02-25 19:20:29',NULL,'Luxembourg','luxembourg','LUXEMBOURG',1),(153,'mo','2012-02-25 19:20:29',NULL,'Macau','macau','MACAU',1),(154,'mk','2012-02-25 19:20:29',NULL,'Macédoine','macedoine','MACÉDOINE',1),(155,'mg','2012-02-25 19:20:29',NULL,'Madagascar','madagascar','MADAGASCAR',1),(156,'mw','2012-02-25 19:20:29',NULL,'Malawi','malawi','MALAWI',1),(157,'mv','2012-02-25 19:20:29',NULL,'Maldives (Îles)','maldives-iles','MALDIVES (ÎLES)',1),(158,'ml','2012-02-25 19:20:29',NULL,'Mali','mali','MALI',1),(159,'mt','2012-02-25 19:20:29',NULL,'Malte','malte','MALTE',1),(160,'mh','2012-02-25 19:20:29',NULL,'Marshall (Îles)','marshall-iles','MARSHALL (ÎLES)',1),(161,'mq','2012-02-25 19:20:29',NULL,'Martinique','martinique','MARTINIQUE',1),(162,'mr','2012-02-25 19:20:29',NULL,'Mauritanie','mauritanie','MAURITANIE',1),(163,'mu','2012-02-25 19:20:29',NULL,'Maurice','maurice','MAURICE',1),(164,'yt','2012-02-25 19:20:29',NULL,'Mayotte','mayotte','MAYOTTE',1),(165,'fm','2012-02-25 19:20:29',NULL,'Micronésie (États fédérés de)','micronesie-etats-federes-de','MICRONÉSIE (ÉTATS FÉDÉRÉS DE)',1),(166,'md','2012-02-25 19:20:29',NULL,'Moldavie','moldavie','MOLDAVIE',1),(167,'mc','2012-02-25 19:20:29',NULL,'Monaco','monaco','MONACO',1),(168,'mn','2012-02-25 19:20:29',NULL,'Mongolie','mongolie','MONGOLIE',1),(169,'ms','2012-02-25 19:20:29',NULL,'Montserrat','montserrat','MONTSERRAT',1),(170,'mz','2012-02-25 19:20:29',NULL,'Mozambique','mozambique','MOZAMBIQUE',1),(171,'mm','2012-02-25 19:20:29',NULL,'Myanmar','myanmar','MYANMAR',1),(172,'na','2012-02-25 19:20:29',NULL,'Namibie','namibie','NAMIBIE',1),(173,'nr','2012-02-25 19:20:29',NULL,'Nauru','nauru','NAURU',1),(174,'np','2012-02-25 19:20:29',NULL,'Nepal','nepal','NEPAL',1),(175,'an','2012-02-25 19:20:29',NULL,'Antilles néerlandaises','antilles-neerlandaises','ANTILLES NÉERLANDAISES',1),(176,'nc','2012-02-25 19:20:29',NULL,'Nouvelle Calédonie','nouvelle-caledonie','NOUVELLE CALÉDONIE',1),(177,'ni','2012-02-25 19:20:29',NULL,'Nicaragua','nicaragua','NICARAGUA',1),(178,'ne','2012-02-25 19:20:29',NULL,'Niger','niger','NIGER',1),(179,'ng','2012-02-25 19:20:29',NULL,'Nigeria','nigeria','NIGERIA',1),(180,'nu','2012-02-25 19:20:29',NULL,'Niue','niue','NIUE',1),(181,'nf','2012-02-25 19:20:29',NULL,'Norfolk (Îles)','norfolk-iles','NORFOLK (ÎLES)',1),(182,'mp','2012-02-25 19:20:29',NULL,'Mariannes du Nord (Îles)','mariannes-du-nord-iles','MARIANNES DU NORD (ÎLES)',1),(183,'om','2012-02-25 19:20:29',NULL,'Oman','oman','OMAN',1),(184,'pw','2012-02-25 19:20:29',NULL,'Palau','palau','PALAU',1),(185,'pa','2012-02-25 19:20:29',NULL,'Panama','panama','PANAMA',1),(186,'pg','2012-02-25 19:20:29',NULL,'Papouasie-Nouvelle-Guinée','papouasie-nouvelle-guinee','PAPOUASIE-NOUVELLE-GUINÉE',1),(187,'py','2012-02-25 19:20:29',NULL,'Paraguay','paraguay','PARAGUAY',1),(188,'pn','2012-02-25 19:20:29',NULL,'Pitcairn (Îles)','pitcairn-iles','PITCAIRN (ÎLES)',1),(189,'qa','2012-02-25 19:20:29',NULL,'Qatar','qatar','QATAR',1),(190,'re','2012-02-25 19:20:29',NULL,'Réunion (La)','reunion-la','RÉUNION (LA)',1),(191,'rw','2012-02-25 19:20:29',NULL,'Rwanda','rwanda','RWANDA',1),(192,'gs','2012-02-25 19:20:29',NULL,'Géorgie du Sud et Sandwich du Sud (Îles)','georgie-du-sud-et-sandwich-du-sud-iles','GÉORGIE DU SUD ET SANDWICH DU SUD (ÎLES)',1),(193,'kn','2012-02-25 19:20:29',NULL,'Saint-Kitts et Nevis','saint-kitts-et-nevis','SAINT-KITTS ET NEVIS',1),(194,'lc','2012-02-25 19:20:29',NULL,'Sainte Lucie','sainte-lucie','SAINTE LUCIE',1),(195,'vc','2012-02-25 19:20:29',NULL,'Saint Vincent et les Grenadines','saint-vincent-et-les-grenadines','SAINT VINCENT ET LES GRENADINES',1),(196,'ws','2012-02-25 19:20:29',NULL,'Samoa','samoa','SAMOA',1),(197,'sm','2012-02-25 19:20:29',NULL,'Saint-Marin (Rép. de)','saint-marin-rep-de','SAINT-MARIN (RÉP. DE)',1),(198,'st','2012-02-25 19:20:29',NULL,'São Tomé et Príncipe (Rép.)','sao-tome-et-principe-rep','SÃO TOMÉ ET PRÍNCIPE (RÉP.)',1),(199,'sn','2012-02-25 19:20:29',NULL,'Sénégal','senegal','SÉNÉGAL',1),(200,'sc','2012-02-25 19:20:29',NULL,'Seychelles','seychelles','SEYCHELLES',1),(201,'sl','2012-02-25 19:20:29',NULL,'Sierra Leone','sierra-leone','SIERRA LEONE',1),(202,'sk','2012-02-25 19:20:29',NULL,'Slovaquie','slovaquie','SLOVAQUIE',1),(203,'si','2012-02-25 19:20:29',NULL,'Slovénie','slovenie','SLOVÉNIE',1),(204,'so','2012-02-25 19:20:29',NULL,'Somalie','somalie','SOMALIE',1),(205,'lk','2012-02-25 19:20:29',NULL,'Sri Lanka','sri-lanka','SRI LANKA',1),(206,'sh','2012-02-25 19:20:29',NULL,'Sainte Hélène','sainte-helene','SAINTE HÉLÈNE',1),(207,'pm','2012-02-25 19:20:29',NULL,'Saint Pierre et Miquelon','saint-pierre-et-miquelon','SAINT PIERRE ET MIQUELON',1),(208,'sd','2012-02-25 19:20:29',NULL,'Soudan','soudan','SOUDAN',1),(209,'sr','2012-02-25 19:20:29',NULL,'Suriname','suriname','SURINAME',1),(210,'sj','2012-02-25 19:20:29',NULL,'Svalbard et Jan Mayen (Îles)','svalbard-et-jan-mayen-iles','SVALBARD ET JAN MAYEN (ÎLES)',1),(211,'sz','2012-02-25 19:20:29',NULL,'Swaziland','swaziland','SWAZILAND',1),(212,'sy','2012-02-25 19:20:29',NULL,'Syrie','syrie','SYRIE',1),(213,'tj','2012-02-25 19:20:29',NULL,'Tadjikistan','tadjikistan','TADJIKISTAN',1),(214,'tz','2012-02-25 19:20:29',NULL,'Tanzanie','tanzanie','TANZANIE',1),(215,'tg','2012-02-25 19:20:29',NULL,'Togo','togo','TOGO',1),(216,'tk','2012-02-25 19:20:29',NULL,'Tokelau','tokelau','TOKELAU',1),(217,'to','2012-02-25 19:20:29',NULL,'Tonga','tonga','TONGA',1),(218,'tt','2012-02-25 19:20:29',NULL,'Trinité et Tobago','trinite-et-tobago','TRINITÉ ET TOBAGO',1),(219,'tn','2012-02-25 19:20:29',NULL,'Tunisie','tunisie','TUNISIE',1),(220,'tm','2012-02-25 19:20:29',NULL,'Turkménistan','turkmenistan','TURKMÉNISTAN',1),(221,'tc','2012-02-25 19:20:29',NULL,'Turks et Caïques (Îles)','turks-et-caiques-iles','TURKS ET CAÏQUES (ÎLES)',1),(222,'tv','2012-02-25 19:20:29',NULL,'Tuvalu','tuvalu','TUVALU',1),(223,'um','2012-02-25 19:20:29',NULL,'Îles Mineures Éloignées des États-Unis','iles-mineures-eloignees-des-etats-unis','ÎLES MINEURES ÉLOIGNÉES DES ÉTATS-UNIS',1),(224,'ug','2012-02-25 19:20:29',NULL,'Ouganda','ouganda','OUGANDA',1),(225,'uy','2012-02-25 19:20:29',NULL,'Uruguay','uruguay','URUGUAY',1),(226,'uz','2012-02-25 19:20:29',NULL,'Ouzbékistan','ouzbekistan','OUZBÉKISTAN',1),(227,'vu','2012-02-25 19:20:29',NULL,'Vanuatu','vanuatu','VANUATU',1),(228,'va','2012-02-25 19:20:29',NULL,'Vatican (Etat du)','vatican-etat-du','VATICAN (ETAT DU)',1),(229,'vn','2012-02-25 19:20:29',NULL,'Vietnam','vietnam','VIETNAM',1),(230,'vi','2012-02-25 19:20:29',NULL,'Vierges (Îles)','vierges-iles','VIERGES (ÎLES)',1),(231,'wf','2012-02-25 19:20:29',NULL,'Wallis et Futuna (Îles)','wallis-et-futuna-iles','WALLIS ET FUTUNA (ÎLES)',1),(232,'eh','2012-02-25 19:20:29',NULL,'Sahara Occidental','sahara-occidental','SAHARA OCCIDENTAL',1),(233,'ye','2012-02-25 19:20:29',NULL,'Yemen','yemen','YEMEN',1),(234,'zr','2012-02-25 19:20:29',NULL,'Zaïre','zaire','ZAÏRE',1),(235,'zm','2012-02-25 19:20:29',NULL,'Zambie','zambie','ZAMBIE',1),(236,'zw','2012-02-25 19:20:29',NULL,'Zimbabwe','zimbabwe','ZIMBABWE',1),(237,'bb','2012-02-25 19:20:29',NULL,'La Barbad','la-barbad','LA BARBAD',1);
/*!40000 ALTER TABLE `fam_country` ENABLE KEYS */;
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
