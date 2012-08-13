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
-- Table structure for table `TblParametro`
--

DROP TABLE IF EXISTS `TblParametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblParametro` (
  `idParametro` int(11) NOT NULL AUTO_INCREMENT,
  `diasDados` int(11) NOT NULL DEFAULT '90',
  `diasBemEstar` int(11) NOT NULL DEFAULT '90',
  `toleraRotinaCliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`idParametro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblParametro`
--

LOCK TABLES `TblParametro` WRITE;
/*!40000 ALTER TABLE `TblParametro` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblParametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblAplicaMedico`
--

DROP TABLE IF EXISTS `TblAplicaMedico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblAplicaMedico` (
  `hrAplicacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idTratamento` int(11) NOT NULL,
  `nmCPFCliente` char(14) NOT NULL,
  PRIMARY KEY (`hrAplicacao`,`idTratamento`,`nmCPFCliente`),
  KEY `Ref840` (`idTratamento`,`nmCPFCliente`),
  CONSTRAINT `RefTblTratamento40` FOREIGN KEY (`idTratamento`, `nmCPFCliente`) REFERENCES `TblTratamento` (`idTratamento`, `nmCPFCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblAplicaMedico`
--

LOCK TABLES `TblAplicaMedico` WRITE;
/*!40000 ALTER TABLE `TblAplicaMedico` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblAplicaMedico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblDispositivo`
--

DROP TABLE IF EXISTS `TblDispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblDispositivo` (
  `idDispositivo` char(13) NOT NULL,
  `tpDispositivo` int(11) DEFAULT NULL,
  `dtaFabrica` date DEFAULT NULL,
  `dtaEntrada` date DEFAULT NULL,
  `tpEstado` int(11) NOT NULL,
  `dtaProximaManut` date DEFAULT NULL,
  `dtaSucata` date DEFAULT NULL,
  `local` int(11) DEFAULT NULL,
  `login` char(10) NOT NULL,
  PRIMARY KEY (`idDispositivo`),
  KEY `Ref1030` (`login`),
  CONSTRAINT `RefTblUsuario30` FOREIGN KEY (`login`) REFERENCES `TblUsuario` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblDispositivo`
--

LOCK TABLES `TblDispositivo` WRITE;
/*!40000 ALTER TABLE `TblDispositivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblDispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblCliente`
--

DROP TABLE IF EXISTS `TblCliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblCliente` (
  `nmCPFCliente` char(14) NOT NULL,
  `nmContrato` int(11) DEFAULT NULL,
  `nmCliente` varchar(60) NOT NULL,
  `dsEndereco` varchar(60) NOT NULL,
  `dsBairro` varchar(60) NOT NULL,
  `dsCidade` varchar(60) NOT NULL,
  `dsEstado` char(2) NOT NULL,
  `dsCEP` char(10) NOT NULL,
  `nrRG` char(14) NOT NULL,
  `tpSexo` int(11) DEFAULT NULL,
  `nrTelefone` char(12) DEFAULT NULL,
  `nrCelular` char(12) DEFAULT NULL,
  `dtNascimento` date NOT NULL,
  `nmNecessidadeEspecial` text,
  `nmPlanoSaude` varchar(60) DEFAULT NULL,
  `dsCobertura` text,
  `dsEmail` varchar(100) DEFAULT NULL,
  `dtaProxBemEstar` date DEFAULT NULL,
  `login` char(10) NOT NULL,
  PRIMARY KEY (`nmCPFCliente`),
  KEY `NomePaciente` (`nmCliente`),
  KEY `Ref1026` (`login`),
  KEY `Ref332` (`nmContrato`),
  CONSTRAINT `RefTblContrato32` FOREIGN KEY (`nmContrato`) REFERENCES `TblContrato` (`nmContrato`),
  CONSTRAINT `RefTblUsuario26` FOREIGN KEY (`login`) REFERENCES `TblUsuario` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblCliente`
--

LOCK TABLES `TblCliente` WRITE;
/*!40000 ALTER TABLE `TblCliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblCliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblSMS`
--

DROP TABLE IF EXISTS `TblSMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblSMS` (
  `idSMS` int(11) NOT NULL AUTO_INCREMENT,
  `tpMensagem` varchar(20) NOT NULL,
  `dsMensagem` varchar(100) NOT NULL,
  `idMomento` int(11) DEFAULT NULL,
  `dtInicioValidade` date NOT NULL,
  `dtTerminoValidade` date DEFAULT NULL,
  PRIMARY KEY (`idSMS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblSMS`
--

LOCK TABLES `TblSMS` WRITE;
/*!40000 ALTER TABLE `TblSMS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblSMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblTratamento`
--

DROP TABLE IF EXISTS `TblTratamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblTratamento` (
  `idTratamento` int(11) NOT NULL AUTO_INCREMENT,
  `nmCPFCliente` char(14) NOT NULL,
  `nomeTrata` varchar(60) DEFAULT NULL,
  `descrTrata` varchar(60) DEFAULT NULL,
  `horaInicial` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idTratamento`,`nmCPFCliente`),
  KEY `Ref215` (`nmCPFCliente`),
  CONSTRAINT `RefTblCliente15` FOREIGN KEY (`nmCPFCliente`) REFERENCES `TblCliente` (`nmCPFCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblTratamento`
--

LOCK TABLES `TblTratamento` WRITE;
/*!40000 ALTER TABLE `TblTratamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblTratamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblContrato`
--

DROP TABLE IF EXISTS `TblContrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblContrato` (
  `nmContrato` int(11) NOT NULL AUTO_INCREMENT,
  `dtInicioValidade` date NOT NULL,
  `dtFinalValidade` date DEFAULT NULL,
  `dtSuspensao` date DEFAULT NULL,
  `login` char(10) NOT NULL,
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
  `idServico` int(11) NOT NULL,
  PRIMARY KEY (`nmContrato`),
  KEY `NomeContratante` (`nmNomeContratante`),
  KEY `Ref1029` (`login`),
  KEY `Ref1135` (`idServico`),
  CONSTRAINT `RefTblPacoteServico35` FOREIGN KEY (`idServico`) REFERENCES `TblPacoteServico` (`idServico`),
  CONSTRAINT `RefTblUsuario29` FOREIGN KEY (`login`) REFERENCES `TblUsuario` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblContrato`
--

LOCK TABLES `TblContrato` WRITE;
/*!40000 ALTER TABLE `TblContrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblContrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblOcorrencia`
--

DROP TABLE IF EXISTS `TblOcorrencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblOcorrencia` (
  `idOcorrencia` int(11) NOT NULL AUTO_INCREMENT,
  `tpOcorrencia` int(11) DEFAULT NULL,
  `statusOcorre` int(11) DEFAULT NULL,
  `login` char(10) NOT NULL,
  `dtaAtend` datetime DEFAULT NULL,
  `acaoOcorrencia` text,
  `reclOcorrencia` text,
  `dtaHoraAbertura` datetime DEFAULT NULL,
  `dtaHoraFechamento` datetime DEFAULT NULL,
  `dtaHoraInicio` datetime DEFAULT NULL,
  `dtaHoraTermino` datetime DEFAULT NULL,
  `conclusao` text,
  `nmCPFCliente` char(14) NOT NULL,
  `idScript` int(11) NOT NULL,
  PRIMARY KEY (`idOcorrencia`),
  KEY `DataOcorrencia` (`dtaHoraAbertura`),
  KEY `Atendimento` (`dtaAtend`),
  KEY `Ref220` (`nmCPFCliente`),
  KEY `Ref1821` (`idScript`),
  KEY `Ref1025` (`login`),
  CONSTRAINT `RefTblUsuario25` FOREIGN KEY (`login`) REFERENCES `TblUsuario` (`login`),
  CONSTRAINT `RefTblCliente20` FOREIGN KEY (`nmCPFCliente`) REFERENCES `TblCliente` (`nmCPFCliente`),
  CONSTRAINT `RefTblScript21` FOREIGN KEY (`idScript`) REFERENCES `TblScript` (`idScript`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblOcorrencia`
--

LOCK TABLES `TblOcorrencia` WRITE;
/*!40000 ALTER TABLE `TblOcorrencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblOcorrencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblUsuario`
--

DROP TABLE IF EXISTS `TblUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblUsuario` (
  `login` char(10) NOT NULL,
  `nmUsuario` varchar(60) NOT NULL,
  `senha` char(70) NOT NULL,
  `nmTelFixo` char(12) DEFAULT NULL,
  `nmTelCelular` char(12) DEFAULT NULL,
  `nmFuncao` int(11) DEFAULT NULL,
  `cdPerfil` int(11) DEFAULT NULL,
  PRIMARY KEY (`login`),
  KEY `NomeUsuario` (`nmUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblUsuario`
--

LOCK TABLES `TblUsuario` WRITE;
/*!40000 ALTER TABLE `TblUsuario` DISABLE KEYS */;
INSERT INTO `TblUsuario` VALUES ('admin','admin','8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918','','',0,1);
/*!40000 ALTER TABLE `TblUsuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblContato`
--

DROP TABLE IF EXISTS `TblContato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblContato` (
  `idContato` int(11) NOT NULL AUTO_INCREMENT,
  `nomeContato` varchar(60) DEFAULT NULL,
  `grauParentesco` char(1) DEFAULT NULL,
  `endContato` varchar(60) DEFAULT NULL,
  `baiContato` varchar(60) DEFAULT NULL,
  `cidContato` varchar(60) DEFAULT NULL,
  `cepContato` char(10) DEFAULT NULL,
  `estadoContato` char(2) DEFAULT NULL,
  `dtaNascimento` date DEFAULT NULL,
  `sqaChamada` int(11) DEFAULT NULL,
  `contratante` char(1) DEFAULT NULL,
  `login` char(10) NOT NULL,
  `nmCPFCliente` char(14) NOT NULL,
  PRIMARY KEY (`idContato`),
  KEY `NomeContato` (`nomeContato`),
  KEY `NomeContatoParentesco` (`nomeContato`,`grauParentesco`),
  KEY `Ref1027` (`login`),
  KEY `Ref233` (`nmCPFCliente`),
  CONSTRAINT `RefTblCliente33` FOREIGN KEY (`nmCPFCliente`) REFERENCES `TblCliente` (`nmCPFCliente`),
  CONSTRAINT `RefTblUsuario27` FOREIGN KEY (`login`) REFERENCES `TblUsuario` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblContato`
--

LOCK TABLES `TblContato` WRITE;
/*!40000 ALTER TABLE `TblContato` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblContato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblAcionamento`
--

DROP TABLE IF EXISTS `TblAcionamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblAcionamento` (
  `IdAciona` int(11) NOT NULL AUTO_INCREMENT,
  `idOcorrencia` int(11) NOT NULL,
  `idContato` int(11) NOT NULL,
  `dtaHoraAciona` datetime DEFAULT NULL,
  `acaoPedida` text,
  `dtaHoraInicio` datetime DEFAULT NULL,
  `dtaHoraFinal` datetime DEFAULT NULL,
  `idSMS` int(11) DEFAULT NULL,
  `textoLivreSMS` varchar(100) DEFAULT NULL,
  `sucesso` char(1) DEFAULT NULL,
  PRIMARY KEY (`IdAciona`),
  KEY `ListaAcionamento` (`idContato`,`dtaHoraAciona`),
  KEY `Ref1922` (`idOcorrencia`),
  KEY `Ref1323` (`idContato`),
  KEY `Ref1624` (`idSMS`),
  CONSTRAINT `RefTblSMS24` FOREIGN KEY (`idSMS`) REFERENCES `TblSMS` (`idSMS`),
  CONSTRAINT `RefTblContato23` FOREIGN KEY (`idContato`) REFERENCES `TblContato` (`idContato`),
  CONSTRAINT `RefTblOcorrencia22` FOREIGN KEY (`idOcorrencia`) REFERENCES `TblOcorrencia` (`idOcorrencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblAcionamento`
--

LOCK TABLES `TblAcionamento` WRITE;
/*!40000 ALTER TABLE `TblAcionamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblAcionamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblPacXDoenca`
--

DROP TABLE IF EXISTS `TblPacXDoenca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblPacXDoenca` (
  `nmCPFCliente` char(14) NOT NULL,
  `cdCID` char(10) NOT NULL,
  PRIMARY KEY (`nmCPFCliente`,`cdCID`),
  KEY `Ref23` (`nmCPFCliente`),
  KEY `Ref54` (`cdCID`),
  CONSTRAINT `RefTblCID4` FOREIGN KEY (`cdCID`) REFERENCES `TblCID` (`cdCID`),
  CONSTRAINT `RefTblCliente3` FOREIGN KEY (`nmCPFCliente`) REFERENCES `TblCliente` (`nmCPFCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblPacXDoenca`
--

LOCK TABLES `TblPacXDoenca` WRITE;
/*!40000 ALTER TABLE `TblPacXDoenca` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblPacXDoenca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblFormaComunica`
--

DROP TABLE IF EXISTS `TblFormaComunica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblFormaComunica` (
  `idFormaComunica` int(11) NOT NULL AUTO_INCREMENT,
  `idContato` int(11) NOT NULL,
  `tpContato` char(14) DEFAULT NULL,
  `foneContato` char(12) DEFAULT NULL,
  `mailContato` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idFormaComunica`,`idContato`),
  KEY `Ref1337` (`idContato`),
  CONSTRAINT `RefTblContato37` FOREIGN KEY (`idContato`) REFERENCES `TblContato` (`idContato`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblFormaComunica`
--

LOCK TABLES `TblFormaComunica` WRITE;
/*!40000 ALTER TABLE `TblFormaComunica` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblFormaComunica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblMonitoramento`
--

DROP TABLE IF EXISTS `TblMonitoramento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblMonitoramento` (
  `dtaInicioMonitora` datetime NOT NULL,
  `tpMonitora` int(11) DEFAULT NULL,
  `acontecimento` int(11) DEFAULT NULL,
  `nmCPFCliente` char(14) NOT NULL,
  PRIMARY KEY (`dtaInicioMonitora`),
  KEY `Ref234` (`nmCPFCliente`),
  CONSTRAINT `RefTblCliente34` FOREIGN KEY (`nmCPFCliente`) REFERENCES `TblCliente` (`nmCPFCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblMonitoramento`
--

LOCK TABLES `TblMonitoramento` WRITE;
/*!40000 ALTER TABLE `TblMonitoramento` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblMonitoramento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblClientexDispositivo`
--

DROP TABLE IF EXISTS `TblClientexDispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblClientexDispositivo` (
  `nmCPFCliente` char(14) NOT NULL,
  `idDispositivo` char(13) NOT NULL,
  PRIMARY KEY (`nmCPFCliente`,`idDispositivo`),
  KEY `Ref238` (`nmCPFCliente`),
  KEY `Ref1439` (`idDispositivo`),
  CONSTRAINT `RefTblDispositivo39` FOREIGN KEY (`idDispositivo`) REFERENCES `TblDispositivo` (`idDispositivo`),
  CONSTRAINT `RefTblCliente38` FOREIGN KEY (`nmCPFCliente`) REFERENCES `TblCliente` (`nmCPFCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblClientexDispositivo`
--

LOCK TABLES `TblClientexDispositivo` WRITE;
/*!40000 ALTER TABLE `TblClientexDispositivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblClientexDispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblPacoteServico`
--

DROP TABLE IF EXISTS `TblPacoteServico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblPacoteServico` (
  `idServico` int(11) NOT NULL AUTO_INCREMENT,
  `dsServico` varchar(100) DEFAULT NULL,
  `dtInicioValidade` date NOT NULL,
  `dtFinalValidade` date DEFAULT NULL,
  `prcMensal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idServico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblPacoteServico`
--

LOCK TABLES `TblPacoteServico` WRITE;
/*!40000 ALTER TABLE `TblPacoteServico` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblPacoteServico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblCID`
--

DROP TABLE IF EXISTS `TblCID`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblCID` (
  `cdCID` char(10) NOT NULL,
  `cdTipoDoenca` char(10) NOT NULL,
  `nmDoenca` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`cdCID`),
  UNIQUE KEY `NomeDoenca` (`nmDoenca`),
  KEY `Ref41` (`cdTipoDoenca`),
  CONSTRAINT `RefTblTipoDoenca1` FOREIGN KEY (`cdTipoDoenca`) REFERENCES `TblTipoDoenca` (`cdTipoDoenca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblCID`
--

LOCK TABLES `TblCID` WRITE;
/*!40000 ALTER TABLE `TblCID` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblCID` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblScript`
--

DROP TABLE IF EXISTS `TblScript`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblScript` (
  `idScript` int(11) NOT NULL AUTO_INCREMENT,
  `nmTitulo` varchar(60) NOT NULL,
  `dsProcesso` text,
  `dsDescricao` varchar(100) DEFAULT NULL,
  `dtInicioValidade` date NOT NULL,
  `dtFinalValidade` date DEFAULT NULL,
  PRIMARY KEY (`idScript`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblScript`
--

LOCK TABLES `TblScript` WRITE;
/*!40000 ALTER TABLE `TblScript` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblScript` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TblTipoDoenca`
--

DROP TABLE IF EXISTS `TblTipoDoenca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TblTipoDoenca` (
  `cdTipoDoenca` char(10) NOT NULL,
  `dsTipoDoenca` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`cdTipoDoenca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TblTipoDoenca`
--

LOCK TABLES `TblTipoDoenca` WRITE;
/*!40000 ALTER TABLE `TblTipoDoenca` DISABLE KEYS */;
/*!40000 ALTER TABLE `TblTipoDoenca` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-08-12 21:57:11
