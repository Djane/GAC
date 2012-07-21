CREATE DATABASE  IF NOT EXISTS `dbGAC` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbGAC`;
-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: dbGAC
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
-- Table structure for table `TblContrato`
--

DROP TABLE IF EXISTS `TblContrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblContrato` (
  `nmContrato` char(10) NOT NULL,
  `dtInicioValidade` date NOT NULL,
  `dtFinalValidade` date DEFAULT NULL,
  `dtSuspensao` date DEFAULT NULL,
  `Login` char(10) NOT NULL,
  `nmCPFContratante` char(14) NOT NULL,
  `nmNomeContratante` varchar(60) NOT NULL,
  `dsEnderecoContratante` varchar(60) NOT NULL,
  `dsBairroContratante` varchar(60) NOT NULL,
  `dsCidadeContratante` varchar(60) NOT NULL,
  `dsUFContratante` char(2) NOT NULL,
  `nmCEPContratante` char(10) NOT NULL,
  `dtNascContratante` date DEFAULT NULL,
  `dsEMailContratante` varchar(100) DEFAULT NULL,
  `nmRGContratante` char(14) DEFAULT NULL,
  `dtProxAtual` date NOT NULL,
  `IdServico` int(11) NOT NULL,
  PRIMARY KEY (`nmContrato`),
  KEY `NomeContratante` (`nmNomeContratante`),
  KEY `Ref1029` (`Login`),
  KEY `Ref1135` (`IdServico`),
  CONSTRAINT `RefTblPacoteServico35` FOREIGN KEY (`IdServico`) REFERENCES `TblPacoteServico` (`IdServico`),
  CONSTRAINT `RefTbUsuario29` FOREIGN KEY (`Login`) REFERENCES `TbUsuario` (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblContrato`
--

LOCK TABLES `TblContrato` WRITE;
/*!40000 ALTER TABLE `TblContrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblContrato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-21 11:13:59
