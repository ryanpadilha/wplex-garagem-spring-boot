-- 
-- WPLEX Garagem
-- MySQL Database 5.7
-- Ryan Padilha <ryan.padilha@wplex.com.br>
-- Data: 03/02/2017
--

CREATE SCHEMA `wplex` DEFAULT CHARACTER SET latin1 ;

-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: wplex
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `wp_company`
--

DROP TABLE IF EXISTS `wp_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wp_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `initials` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wp_company`
--

LOCK TABLES `wp_company` WRITE;
/*!40000 ALTER TABLE `wp_company` DISABLE KEYS */;
INSERT INTO `wp_company` VALUES (1,'2017-02-05 22:00:00','FB1','Empresa 1'),(2,'2017-02-05 22:00:00','FB2','Empresa 2'),(3,'2017-02-05 22:00:00','WPLEX','WPLEX Company'),(6,'2017-02-03 19:06:14','FLN','Floripa Company'),(14,'2017-04-06 10:20:07','OPJ','Empresa OPJ');
/*!40000 ALTER TABLE `wp_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wp_garage`
--

DROP TABLE IF EXISTS `wp_garage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wp_garage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `initials` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9w5995ex2pxg26il2n034t9an` (`company_id`),
  CONSTRAINT `FK9w5995ex2pxg26il2n034t9an` FOREIGN KEY (`company_id`) REFERENCES `wp_company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wp_garage`
--

LOCK TABLES `wp_garage` WRITE;
/*!40000 ALTER TABLE `wp_garage` DISABLE KEYS */;
INSERT INTO `wp_garage` VALUES (1,'2017-02-03 19:22:54','GR_1','Garagem 1',1),(2,'2017-02-03 19:23:01','GR_2','Garagem 2',1),(4,'2017-02-03 19:37:51','G_FLN','Garagem Floripa',3),(5,'2017-02-03 19:40:21','FLN','Garagem Legal',3),(9,'2017-04-05 16:54:37','G1','Garagem Empresa1',1),(10,'2017-04-05 16:55:16','DS','ds',1),(11,'2017-04-05 17:11:10','G-FLN','Garagem Floripa 1',6),(12,'2017-04-06 10:20:31','OPJ-G','Garagem OPJ-G-1',14);
/*!40000 ALTER TABLE `wp_garage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-06 10:31:03
