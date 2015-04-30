-- MySQL dump 10.13  Distrib 5.5.43, for Win64 (x86)
--
-- Host: localhost    Database: tfg
-- ------------------------------------------------------
-- Server version	5.5.43

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
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autor` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `pais` varchar(20) DEFAULT NULL,
  `nacimiento` date DEFAULT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `password` varchar(512) DEFAULT NULL,
  `sal` varchar(512) DEFAULT NULL,
  `about` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capitulo`
--

DROP TABLE IF EXISTS `capitulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capitulo` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `obra` int(6) NOT NULL DEFAULT '0',
  `nombre` varchar(25) DEFAULT NULL,
  `texto` text,
  `comentarios_autor` text,
  `fecha_comentario` date DEFAULT NULL,
  PRIMARY KEY (`id`,`obra`),
  KEY `obra` (`obra`),
  CONSTRAINT `capitulo_ibfk_1` FOREIGN KEY (`obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capitulo`
--

LOCK TABLES `capitulo` WRITE;
/*!40000 ALTER TABLE `capitulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `capitulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentario` (
  `autor` int(6) NOT NULL DEFAULT '0',
  `obra` int(6) NOT NULL DEFAULT '0',
  `capitulo` int(6) NOT NULL DEFAULT '0',
  `comentario` int(6) NOT NULL AUTO_INCREMENT,
  `texto` text,
  `fecha_comentario` date DEFAULT NULL,
  PRIMARY KEY (`comentario`,`autor`,`obra`,`capitulo`),
  KEY `autor` (`autor`),
  KEY `obra` (`obra`),
  KEY `capitulo` (`capitulo`),
  CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`autor`) REFERENCES `autor` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comentario_ibfk_3` FOREIGN KEY (`capitulo`) REFERENCES `capitulo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obra`
--

DROP TABLE IF EXISTS `obra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obra` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `autor` int(6) NOT NULL DEFAULT '0',
  `titulo` varchar(50) DEFAULT NULL,
  `resumen` varchar(512) DEFAULT NULL,
  `fecha_in` date DEFAULT NULL,
  `fecha_mod` date DEFAULT NULL,
  PRIMARY KEY (`id`,`autor`),
  KEY `autor` (`autor`),
  CONSTRAINT `obra_ibfk_1` FOREIGN KEY (`autor`) REFERENCES `autor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obra`
--

LOCK TABLES `obra` WRITE;
/*!40000 ALTER TABLE `obra` DISABLE KEYS */;
/*!40000 ALTER TABLE `obra` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-29 13:41:00
