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
  `pais` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nacimiento` date DEFAULT NULL,
  `nombre` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sal` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `about` mediumtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,'euskal herria','2015-04-29','alberto','abcd','efg','esta es la primera prueba de creaci¢n de libros con itext');
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
  `nombre` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `texto` mediumtext COLLATE utf8_unicode_ci,
  `comentarios_autor` mediumtext COLLATE utf8_unicode_ci,
  `fecha_comentario` date DEFAULT NULL,
  PRIMARY KEY (`id`,`obra`),
  KEY `obra` (`obra`),
  CONSTRAINT `capitulo_ibfk_1` FOREIGN KEY (`obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capitulo`
--

LOCK TABLES `capitulo` WRITE;
/*!40000 ALTER TABLE `capitulo` DISABLE KEYS */;
INSERT INTO `capitulo` VALUES (6,3,'Capitulo 1','La rápida incorporación de nuevos derechos, la aparición de un Parlamento legítimo y la dinámica revolucionaria generaron tensión en un sistema judicial, quizás dispuesto a acompañar el proceso, pero que se encontraba atrapado en los vicios de su estructura colonial. La creación del Código de Trabajo fue un logró evidente, pero también mostró que el sistema judicial tenía problemas para ajustarse a la rapidez del cambio.','Sin comentarios ',NULL),(7,3,'Capitulo 2','Entre las reformas que se pusieron en marcha desde la caída del gobierno de Ponce Vaides y que se trataron de consolidar con la Constitución de 1945, la reestructuración del Ejército tuvo gran trascendencia: la suspensión del generalato, decretada desde los primeros momentos del triunfo del movimiento revolucionario, simbolizó esta transformación, que se completó con una inquietud por modernizar, profesionalizar e institucionalizar el Ejército. Por primera vez en la historia del país una Constitución concedió todo un capítulo y 13 artículos al tema del Ejército, asentando un modelo que sería retomado en las Constituciones posteriores. La norma constitucional estableció una reorganización del Ejército que resultó compleja y no siempre operativa: buscaba confirmar la autonomía funcional que por primera vez se le confería. Creó el Consejo Superior de la Defensa Nacional, órgano de consulta y colegiado, constituido por 15 miembros, algunos por elección y dentro de los cuales no se incluía al presidente de la República, a pesar de considerársele comandante en jefe del Ejército.','Sin comentarios',NULL),(8,3,'Capitulo 3','3º Capítulo de prueba de prueba de itext \"Mr. Black has called you,\" he says, stating the obvious. Entering the house, he wonders why','3º capn',NULL),(11,3,'Capítulo 4','La rápida incorporación de nuevos derechos, la aparición de un Parlamento legítimo y la dinámica revolucionaria generaron tensión en un sistema judicial, quizás dispuesto a acompañar el proceso, pero que se encontraba atrapado en los vicios de su estructura colonial. La creación del Código de Trabajo fue un logró evidente, pero también mostró que el sistema judicial tenía problemas para ajustarse a la rapidez del cambio.\nEntre las reformas que se pusieron en marcha desde la caída del gobierno de Ponce Vaides y que se trataron de consolidar con la Constitución de 1945, la reestructuración del Ejército tuvo gran trascendencia: la suspensión del generalato, decretada desde los primeros momentos del triunfo del movimiento revolucionario, simbolizó esta transformación, que se completó con una inquietud por modernizar, profesionalizar e institucionalizar el Ejército. Por primera vez en la historia del país una Constitución concedió todo un capítulo y 13 artículos al tema del Ejército, asentando un modelo que sería retomado en las Constituciones posteriores. La norma constitucional estableció una reorganización del Ejército que resultó compleja y no siempre operativa: buscaba confirmar la autonomía funcional que por primera vez se le confería. Creó el Consejo Superior de la Defensa Nacional, órgano de consulta y colegiado, constituido por 15 miembros, algunos por elección y dentro de los cuales no se incluía al presidente de la República, a pesar de considerársele comandante en jefe del Ejército.\nEn el otoño de 1947, la oposición de Árbenz como ministro de Defensa a la deportación de varios obreros acusados de comunismo intrigó al antiguo miembro del Frente Popular Libertador, José Manuel Fortuny, por lo inesperado de dicho comportamiento, y éste decidió visitarlo, descubriendo en aquella entrevista «a un hombre distinto del estereotipo del militar» centroamericano. A ese primer encuentro siguieron otros hasta que el propio Árbenz invitó a Fortuny a su casa, donde las discusiones y conversaciones se hicieron comunes prolongándose habitualmente por horas. Al igual que Árbenz, Fortuny estaba inspirado por un fiero nacionalismo y un ardiente deseo de mejorar la suerte del pueblo guatemalteco; como Árbenz, buscaba respuestas en la teoría marxista. Se trató de una relación solo comparable a la que tendría con María Vilanova; José Manuel Fortuny sería su amigo más cercano.\nLa rápida incorporación de nuevos derechos, la aparición de un Parlamento legítimo y la dinámica revolucionaria generaron tensión en un sistema judicial, quizás dispuesto a acompañar el proceso, pero que se encontraba atrapado en los vicios de su estructura colonial. La creación del Código de Trabajo fue un logró evidente, pero también mostró que el sistema judicial tenía problemas para ajustarse a la rapidez del cambio.\nEntre las reformas que se pusieron en marcha desde la caída del gobierno de Ponce Vaides y que se trataron de consolidar con la Constitución de 1945, la reestructuración del Ejército tuvo gran trascendencia: la suspensión del generalato, decretada desde los primeros momentos del triunfo del movimiento revolucionario, simbolizó esta transformación, que se completó con una inquietud por modernizar, profesionalizar e institucionalizar el Ejército. Por primera vez en la historia del país una Constitución concedió todo un capítulo y 13 artículos al tema del Ejército, asentando un modelo que sería retomado en las Constituciones posteriores. La norma constitucional estableció una reorganización del Ejército que resultó compleja y no siempre operativa: buscaba confirmar la autonomía funcional que por primera vez se le confería. Creó el Consejo Superior de la Defensa Nacional, órgano de consulta y colegiado, constituido por 15 miembros, algunos por elección y dentro de los cuales no se incluía al presidente de la República, a pesar de considerársele comandante en jefe del Ejército.\nEn el otoño de 1947, la oposición de Árbenz como ministro de Defensa a la deportación de varios obreros acusados de comunismo intrigó al antiguo miembro del Frente Popular Libertador, José Manuel Fortuny, por lo inesperado de dicho comportamiento, y éste decidió visitarlo, descubriendo en aquella entrevista «a un hombre distinto del estereotipo del militar» centroamericano. A ese primer encuentro siguieron otros hasta que el propio Árbenz invitó a Fortuny a su casa, donde las discusiones y conversaciones se hicieron comunes prolongándose habitualmente por horas. Al igual que Árbenz, Fortuny estaba inspirado por un fiero nacionalismo y un ardiente deseo de mejorar la suerte del pueblo guatemalteco; como Árbenz, buscaba respuestas en la teoría marxista. Se trató de una relación solo comparable a la que tendría con María Vilanova; José Manuel Fortuny sería su amigo más cercano.\nLa rápida incorporación de nuevos derechos, la aparición de un Parlamento legítimo y la dinámica revolucionaria generaron tensión en un sistema judicial, quizás dispuesto a acompañar el proceso, pero que se encontraba atrapado en los vicios de su estructura colonial. La creación del Código de Trabajo fue un logró evidente, pero también mostró que el sistema judicial tenía problemas para ajustarse a la rapidez del cambio.\nEntre las reformas que se pusieron en marcha desde la caída del gobierno de Ponce Vaides y que se trataron de consolidar con la Constitución de 1945, la reestructuración del Ejército tuvo gran trascendencia: la suspensión del generalato, decretada desde los primeros momentos del triunfo del movimiento revolucionario, simbolizó esta transformación, que se completó con una inquietud por modernizar, profesionalizar e institucionalizar el Ejército. Por primera vez en la historia del país una Constitución concedió todo un capítulo y 13 artículos al tema del Ejército, asentando un modelo que sería retomado en las Constituciones posteriores. La norma constitucional estableció una reorganización del Ejército que resultó compleja y no siempre operativa: buscaba confirmar la autonomía funcional que por primera vez se le confería. Creó el Consejo Superior de la Defensa Nacional, órgano de consulta y colegiado, constituido por 15 miembros, algunos por elección y dentro de los cuales no se incluía al presidente de la República, a pesar de considerársele comandante en jefe del Ejército.\nEn el otoño de 1947, la oposición de Árbenz como ministro de Defensa a la deportación de varios obreros acusados de comunismo intrigó al antiguo miembro del Frente Popular Libertador, José Manuel Fortuny, por lo inesperado de dicho comportamiento, y éste decidió visitarlo, descubriendo en aquella entrevista «a un hombre distinto del estereotipo del militar» centroamericano. A ese primer encuentro siguieron otros hasta que el propio Árbenz invitó a Fortuny a su casa, donde las discusiones y conversaciones se hicieron comunes prolongándose habitualmente por horas. Al igual que Árbenz, Fortuny estaba inspirado por un fiero nacionalismo y un ardiente deseo de mejorar la suerte del pueblo guatemalteco; como Árbenz, buscaba respuestas en la teoría marxista. Se trató de una relación solo comparable a la que tendría con María Vilanova; José Manuel Fortuny sería su amigo más cercano.',NULL,NULL);
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
  `texto` mediumtext COLLATE utf8_unicode_ci,
  `fecha_comentario` date DEFAULT NULL,
  PRIMARY KEY (`comentario`,`autor`,`obra`,`capitulo`),
  KEY `autor` (`autor`),
  KEY `obra` (`obra`),
  KEY `capitulo` (`capitulo`),
  CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`autor`) REFERENCES `autor` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`obra`) REFERENCES `obra` (`id`) ON DELETE CASCADE,
  CONSTRAINT `comentario_ibfk_3` FOREIGN KEY (`capitulo`) REFERENCES `capitulo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `titulo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resumen` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha_in` date DEFAULT NULL,
  `fecha_mod` date DEFAULT NULL,
  PRIMARY KEY (`id`,`autor`),
  KEY `autor` (`autor`),
  CONSTRAINT `obra_ibfk_1` FOREIGN KEY (`autor`) REFERENCES `autor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obra`
--

LOCK TABLES `obra` WRITE;
/*!40000 ALTER TABLE `obra` DISABLE KEYS */;
INSERT INTO `obra` VALUES (3,1,'Obra de prueba','Primera obra generada para probar los servlets de exportación',NULL,NULL);
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

-- Dump completed on 2015-04-30 18:18:05
