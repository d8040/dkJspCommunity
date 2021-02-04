-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: dkJspCommunity
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `memberId` int(10) unsigned NOT NULL,
  `boardId` int(10) unsigned NOT NULL,
  `title` char(100) NOT NULL,
  `body` longtext NOT NULL,
  `hitsCount` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'2021-01-19 03:20:53','2021-01-19 20:33:14',1,1,'asdfasd','asdfasdf',2),(2,'2021-01-19 03:20:53','2021-01-19 20:33:23',1,1,'asdfasdf','asdfasdfadsf',0),(3,'2021-01-19 03:20:53','2021-01-19 03:20:53',1,1,'제목3','내용3',0),(4,'2021-01-19 03:20:53','2021-01-19 03:20:53',2,1,'제목4','내용4',0),(5,'2021-01-19 03:20:53','2021-01-19 03:20:53',2,1,'제목5','내용5',0),(7,'2021-01-21 02:42:03','2021-01-21 02:42:03',9,1,'asdfa','asdfasdf',0),(9,'2021-01-27 10:06:13','2021-01-27 10:06:13',1,1,'제목1','내용1',0),(10,'2021-01-27 10:06:13','2021-01-27 10:06:13',1,1,'제목2','내용2',0),(11,'2021-01-27 10:06:13','2021-01-27 10:06:13',1,1,'제목3','내용3',0),(12,'2021-01-27 10:06:13','2021-01-27 10:06:13',2,1,'제목4','내용4',0),(13,'2021-01-27 10:06:13','2021-01-27 10:06:13',2,1,'제목5','내용5',0),(14,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목1','내용1',0),(15,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목2','내용2',0),(16,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목3','내용3',0),(17,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목4','내용4',0),(18,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목5','내용5',0),(19,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목1','내용1',0),(20,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목2','내용2',0),(21,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목3','내용3',0),(22,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목4','내용4',0),(23,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목5','내용5',0),(24,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목1','내용1',0),(25,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목2','내용2',0),(26,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목3','내용3',0),(27,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목4','내용4',0),(28,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목5','내용5',0),(29,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목1','내용1',0),(30,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목2','내용2',0),(31,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목3','내용3',0),(32,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목4','내용4',0),(33,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목5','내용5',0),(34,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목1','내용1',0),(35,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목2','내용2',0),(36,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목3','내용3',0),(37,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목4','내용4',0),(38,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목5','내용5',0),(39,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목1','내용1',0),(40,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목2','내용2',0),(41,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목3','내용3',0),(42,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목4','내용4',0),(43,'2021-01-27 10:06:14','2021-01-27 10:06:14',2,1,'제목5','내용5',0),(44,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목1','내용1',0),(45,'2021-01-27 10:06:14','2021-01-27 10:06:14',1,1,'제목2','내용2',0),(46,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(47,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(48,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(49,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(50,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(51,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(52,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(53,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(54,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(55,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(56,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(57,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(58,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(59,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(60,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(61,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(62,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(63,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(64,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(65,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(66,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(67,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(68,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(69,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(70,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(71,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(72,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(73,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(74,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(75,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(76,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(77,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(78,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(79,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(80,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(81,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(82,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(83,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(84,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(85,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(86,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(87,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(88,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(89,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(90,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(91,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(92,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(93,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(94,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(95,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(96,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(97,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(98,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(99,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목1','내용1',0),(100,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목2','내용2',0),(101,'2021-01-27 10:06:15','2021-01-27 10:06:15',1,1,'제목3','내용3',0),(102,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목4','내용4',0),(103,'2021-01-27 10:06:15','2021-01-27 10:06:15',2,1,'제목5','내용5',0),(104,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(105,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(106,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(107,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(108,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(109,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(110,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(111,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(112,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(113,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(114,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(115,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(116,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(117,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(118,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(119,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(120,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(121,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(122,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(123,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(124,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(125,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(126,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(127,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(128,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(129,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(130,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(131,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(132,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',1),(133,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(134,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(135,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(136,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(137,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(138,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(139,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(140,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(141,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(142,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(143,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(144,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',0),(145,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',0),(146,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',0),(147,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(148,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(149,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',1),(150,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',5),(151,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',2),(152,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',0),(153,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',0),(154,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목1','내용1',7),(155,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목2','내용2',11),(156,'2021-01-27 10:06:16','2021-01-27 10:06:16',1,1,'제목3','내용3',1),(157,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목4','내용4',3),(158,'2021-01-27 10:06:16','2021-01-27 10:06:16',2,1,'제목5','내용5',2),(159,'2021-01-27 23:25:22','2021-01-27 23:27:19',1,1,'asdfasdfds','#내용 \r\n--- \r\n',6),(162,'2021-01-28 02:41:04','2021-01-28 02:41:04',1,1,'테스트','# 테스트\r\n- 잘작동 하는지 확인',6),(164,'2021-01-29 01:01:12','2021-01-29 01:01:12',1,1,'제목1','내용1',73),(165,'2021-01-29 18:13:56','2021-01-29 18:19:02',11,1,'테스트','# 테스트\r\n테스트 1\r\n- 테스트 \r\n- > 테스트 \r\n- >>테스트 \r\n- >>>테스트 테스트\r\n-',0),(166,'2021-02-01 00:42:34','2021-02-01 00:42:34',9,1,'테스트 1','# 테스트 중 테스트\r\n테스트 테스트\r\n- 테스트\r\n    - ㅇㅇㅇㅇ',14),(167,'2021-02-01 10:48:40','2021-02-01 10:48:40',1,1,'asdfasdf','---\r\nadsf\r\nasdfasd\r\nasdf\r\nasdf\r\nasdf\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\naa\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\na\r\naa\r\n\r\na\r\na\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n---\r\nasdfasdf',15),(169,'2021-02-02 09:15:23','2021-02-02 09:15:23',9,1,'임시 게시물','3agiqf',48);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attr`
--

DROP TABLE IF EXISTS `attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attr` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `relTypeCode` char(20) NOT NULL,
  `relId` int(10) unsigned NOT NULL,
  `typeCode` char(30) NOT NULL,
  `type2Code` char(30) NOT NULL,
  `value` text NOT NULL,
  `expireDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `relTypeCode` (`relTypeCode`,`relId`,`typeCode`,`type2Code`),
  KEY `relTypeCode_2` (`relTypeCode`,`typeCode`,`type2Code`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attr`
--

LOCK TABLES `attr` WRITE;
/*!40000 ALTER TABLE `attr` DISABLE KEYS */;
INSERT INTO `attr` VALUES (1,'2021-01-30 11:44:40','2021-01-31 22:17:03','member',9,'extra','isUsingTempPassword','1',NULL),(2,'2021-01-30 11:44:46','2021-01-30 11:47:54','member',27,'extra','isUsingTempPassword','0',NULL),(8,'2021-01-30 11:47:54','2021-01-30 11:47:54','member',27,'extra','expireDateOfPw','1','2021-07-30 11:47:54'),(10,'2021-01-30 11:48:42','2021-01-30 17:57:37','member',9,'extra','expireDateOfPw','1','2021-08-13 17:53:56'),(11,'2021-01-30 11:51:10','2021-01-30 11:51:10','member',28,'extra','expireDateOfPw','1','2021-07-30 11:51:10'),(18,'2021-01-30 14:19:13','2021-02-01 11:47:55','member',1,'extra','isUsingTempPassword','0',NULL),(19,'2021-01-30 14:19:13','2021-02-01 11:47:55','member',1,'extra','expireDateOfPw','1','2021-08-01 11:47:55'),(26,'2021-01-30 17:58:32','2021-01-30 17:58:32','member',29,'extra','expireDateOfPw','1','2021-07-30 17:58:32');
/*!40000 ALTER TABLE `attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `code` char(10) NOT NULL,
  `name` char(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'2021-01-19 03:20:53','2021-01-19 03:20:53','notice','공지사항'),(2,'2021-01-19 03:20:53','2021-01-19 03:20:53','guestBook','방명록'),(3,'2021-01-19 03:20:53','2021-01-19 03:20:53','free','자유');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `memberId` int(10) unsigned NOT NULL,
  `articleId` int(10) unsigned NOT NULL,
  `like` int(10) unsigned NOT NULL,
  `unlike` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `memberId` (`memberId`,`articleId`),
  KEY `memberId_2` (`memberId`,`articleId`,`like`,`unlike`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (1,'2021-02-04 08:54:39','2021-02-04 09:08:06',1,169,1,0),(7,'2021-02-04 09:08:35','2021-02-04 09:08:35',2,169,1,0),(8,'2021-02-04 09:09:39','2021-02-04 09:09:39',9,169,1,0),(9,'2021-02-04 10:31:36','2021-02-04 10:31:36',0,169,1,0);
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `name` char(50) NOT NULL,
  `nickname` char(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `cellphoneNo` varchar(100) NOT NULL,
  `loginId` char(50) NOT NULL,
  `loginPw` varchar(200) NOT NULL,
  `adminLevel` tinyint(1) unsigned NOT NULL DEFAULT 2 COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증된,4=관리자',
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2021-01-19 03:20:53','2021-02-01 11:48:14','김민수','강바람','phoneus.adm@gmail.com','010-1234-1421','user1','0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90',2),(2,'2021-01-19 03:20:53','2021-01-19 03:20:53','김미소','이또한지나가리라','phoneus.adm@gmail.com','010-1234-1234','user2','6025d18fe48abd45168528f18a82e265dd98d421a7084aa09f61b341703901a3',2),(9,'2021-01-21 02:35:40','2021-01-31 22:17:41','saslkdj','aaa','phoneus.adm@gmail.com','3023228792','aaa','b125f282d8703e86274f49e63b469105c6cb0073e3a86dbe2ef99eda0cf9df61',2),(25,'2021-01-30 01:57:00','2021-01-30 01:57:03','bbb','bbb','phoneus.adm@gmail.com','3023228792','bbb','76f017a444af7f14c846002f392f5fe9bb520cec6d818454e9ff3ffe3fbe444d',2),(27,'2021-01-30 04:45:35','2021-01-30 11:47:54','ccc','ccc','phoneus.adm@gmail.com','3023228792','ccc','64daa44ad493ff28a96effab6e77f1732a3d97d83241581b37dbd70a7a4900fe',2),(28,'2021-01-30 11:51:05','2021-01-30 11:51:09','ddd','ddd','phoneus.adm@gmail.com','3023228792','ddd','4eb823d0efaaf64c1c851324f991bf78196cd94aa7c0a5277d25f3dcf1262edb',2),(29,'2021-01-30 17:58:28','2021-01-30 17:58:32','ttt','ttt','phoneus.adm@gmail.com','ttt','ttt','70628b6da5651b190086d2ce16c48d1a78cf6bce14b7da4b62c29d6bb09cf416',2);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `memberId` int(10) unsigned NOT NULL,
  `articleId` int(10) unsigned NOT NULL,
  `parentReplyId` int(10) unsigned NOT NULL DEFAULT 0,
  `body` longtext NOT NULL,
  `hitsCount` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,'2021-02-04 09:07:42','2021-02-04 09:07:42',1,169,0,'sadfasdf',0),(2,'2021-02-04 09:07:46','2021-02-04 09:07:46',1,169,0,'asdfasdf',0),(3,'2021-02-04 09:08:14','2021-02-04 09:08:14',1,169,0,'테스트',0),(4,'2021-02-04 09:08:43','2021-02-04 09:08:43',2,169,0,'테스트 ',0),(5,'2021-02-04 09:09:47','2021-02-04 09:09:47',9,169,0,'테스트',0),(6,'2021-02-04 09:29:28','2021-02-04 09:29:28',9,169,0,'테스트 \r\n테스트 \r\n테스트',0);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-04 12:47:15
