CREATE DATABASE  IF NOT EXISTS `DBGAC` /*!40100 DEFAULT CHARACTER SET latin2 */;
USE `DBGAC`;
-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: DBGAC
-- ------------------------------------------------------
-- Server version	5.5.24-0ubuntu0.12.04.1

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
-- Table structure for table `TblContato`
--

DROP TABLE IF EXISTS `TblContato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblContato` (
  `IdContato` int(11) NOT NULL AUTO_INCREMENT,
  `NomeContato` varchar(60) DEFAULT NULL,
  `GrauParentesco` char(1) DEFAULT NULL,
  `EndContato` varchar(60) DEFAULT NULL,
  `BaiContato` varchar(60) DEFAULT NULL,
  `CidContato` varchar(60) DEFAULT NULL,
  `CEPContato` char(10) DEFAULT NULL,
  `EstadoContato` char(2) DEFAULT NULL,
  `dtaNascimento` date DEFAULT NULL,
  `sqaChamada` int(11) DEFAULT NULL,
  `Contratante` char(1) DEFAULT NULL,
  `Login` char(10) NOT NULL,
  `NmCPFCliente` char(14) NOT NULL,
  PRIMARY KEY (`IdContato`),
  KEY `NomeContato` (`NomeContato`),
  KEY `NomeContatoParentesco` (`NomeContato`,`GrauParentesco`),
  KEY `Ref1027` (`Login`),
  KEY `Ref233` (`NmCPFCliente`),
  CONSTRAINT `RefTblCliente33` FOREIGN KEY (`NmCPFCliente`) REFERENCES `TblCliente` (`NmCPFCliente`),
  CONSTRAINT `RefTblUsuario27` FOREIGN KEY (`Login`) REFERENCES `TblUsuario` (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblContato`
--

LOCK TABLES `TblContato` WRITE;
/*!40000 ALTER TABLE `TblContato` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblContato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-28 15:56:40