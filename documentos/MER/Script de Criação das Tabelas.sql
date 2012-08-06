--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Buki Consultoria
-- Project :      PULSEIRAS.DM1
-- Author :       Marcelo Santos
--
-- Date Created : Sunday, August 05, 2012 08:32:41
-- Target DBMS : MySQL 5.x
--

DROP TABLE TblAcionamento
;
DROP TABLE TblAplicaMedico
;
DROP TABLE TblCID
;
DROP TABLE TblCliente
;
DROP TABLE TblClientexDispositivo
;
DROP TABLE TblContato
;
DROP TABLE TblContrato
;
DROP TABLE TblDispositivo
;
DROP TABLE TblFormaComunica
;
DROP TABLE TblMonitoramento
;
DROP TABLE TblOcorrencia
;
DROP TABLE TblPacoteServico
;
DROP TABLE TblPacXDoenca
;
DROP TABLE TblParametro
;
DROP TABLE TblScript
;
DROP TABLE TblSMS
;
DROP TABLE TblTipoDoenca
;
DROP TABLE TblTratamento
;
DROP TABLE TblUsuario
;
-- 
-- TABLE: TblAcionamento 
--

CREATE TABLE TblAcionamento(
    IdAciona         INT             AUTO_INCREMENT,
    IdOcorrencia     INT             NOT NULL,
    IdContato        INT             NOT NULL,
    dtaHoraAciona    DATETIME,
    AcaoPedida       TEXT,
    dtaHoraInicio    DATETIME,
    DtaHoraFinal     DATETIME,
    IdSMS            INT,
    TextoLivreSMS    VARCHAR(100),
    Sucesso          CHAR(1),
    PRIMARY KEY (IdAciona)
)ENGINE=INNODB
;



-- 
-- TABLE: TblAplicaMedico 
--

CREATE TABLE TblAplicaMedico(
    hrAplicacao     TIMESTAMP    NOT NULL,
    IdTratamento    INT          NOT NULL,
    NmCPFCliente    CHAR(14)     NOT NULL,
    PRIMARY KEY (hrAplicacao, IdTratamento, NmCPFCliente)
)
;



-- 
-- TABLE: TblCID 
--

CREATE TABLE TblCID(
    CdCID           CHAR(10)       NOT NULL,
    cdTipoDoenca    CHAR(10)       NOT NULL,
    nmDoenca        VARCHAR(60),
    PRIMARY KEY (CdCID)
)ENGINE=INNODB
;



-- 
-- TABLE: TblCliente 
--

CREATE TABLE TblCliente(
    NmCPFCliente             CHAR(14)        NOT NULL,
    nmContrato               CHAR(10),
    nmCliente                VARCHAR(60)     NOT NULL,
    dsEndereco               VARCHAR(60)     NOT NULL,
    dsBairro                 VARCHAR(60)     NOT NULL,
    dsCidade                 VARCHAR(60)     NOT NULL,
    dsEstado                 CHAR(2)         NOT NULL,
    dsCEP                    CHAR(10)        NOT NULL,
    nrRG                     CHAR(14)        NOT NULL,
    tpSexo                   CHAR(1),
    nrTelefone               CHAR(12),
    nrCelular                CHAR(12),
    dtNascimento             DATE            NOT NULL,
    nmNecessidadeEspecial    TEXT,
    nmPlanoSaude             VARCHAR(60),
    dsCobertura              TEXT,
    dsEmail                  VARCHAR(100),
    dtaProxBemEstar          DATE,
    Login                    CHAR(10)        NOT NULL,
    PRIMARY KEY (NmCPFCliente)
)ENGINE=INNODB
;



-- 
-- TABLE: TblClientexDispositivo 
--

CREATE TABLE TblClientexDispositivo(
    NmCPFCliente     CHAR(14)    NOT NULL,
    IdDispositivo    CHAR(13)    NOT NULL,
    PRIMARY KEY (NmCPFCliente, IdDispositivo)
)ENGINE=INNODB
;



-- 
-- TABLE: TblContato 
--

CREATE TABLE TblContato(
    IdContato         INT            AUTO_INCREMENT,
    NomeContato       VARCHAR(60),
    GrauParentesco    CHAR(1),
    EndContato        VARCHAR(60),
    BaiContato        VARCHAR(60),
    CidContato        VARCHAR(60),
    CEPContato        CHAR(10),
    EstadoContato     CHAR(2),
    dtaNascimento     DATE,
    sqaChamada        INT,
    Contratante       CHAR(1),
    Login             CHAR(10)       NOT NULL,
    NmCPFCliente      CHAR(14)       NOT NULL,
    PRIMARY KEY (IdContato)
)ENGINE=INNODB
;



-- 
-- TABLE: TblContrato 
--

CREATE TABLE TblContrato(
    nmContrato               CHAR(10)        NOT NULL,
    dtInicioValidade         DATE            NOT NULL,
    dtFinalValidade          DATE,
    dtSuspensao              DATE,
    Login                    CHAR(10)        NOT NULL,
    nmCPFContratante         CHAR(14)        NOT NULL,
    nmNomeContratante        VARCHAR(60)     NOT NULL,
    dsEnderecoContratante    VARCHAR(60)     NOT NULL,
    dsBairroContratante      VARCHAR(60)     NOT NULL,
    dsCidadeContratante      VARCHAR(60)     NOT NULL,
    dsUFContratante          CHAR(2)         NOT NULL,
    nmCEPContratante         CHAR(10)        NOT NULL,
    dtNascContratante        DATE,
    dsEMailContratante       VARCHAR(100),
    nmRGContratante          CHAR(14),
    dtProxAtual              DATE            NOT NULL,
    IdServico                INT             NOT NULL,
    PRIMARY KEY (nmContrato)
)ENGINE=INNODB
;



-- 
-- TABLE: TblDispositivo 
--

CREATE TABLE TblDispositivo(
    IdDispositivo      CHAR(13)    NOT NULL,
    TpDispositivo      INT,
    dtaFabrica         DATE,
    dtaEntrada         DATE,
    tpEstado           CHAR(1),
    dtaProximaManut    DATE,
    dtaSucata          DATE,
    Local              CHAR(1),
    Login              CHAR(10)    NOT NULL,
    PRIMARY KEY (IdDispositivo)
)ENGINE=INNODB
;



-- 
-- TABLE: TblFormaComunica 
--

CREATE TABLE TblFormaComunica(
    idFormaComunica    INT             AUTO_INCREMENT,
    IdContato          INT             NOT NULL,
    tpContato          CHAR(14),
    foneContato        CHAR(12),
    mailContato        VARCHAR(100),
    PRIMARY KEY (idFormaComunica, IdContato)
)ENGINE=INNODB
;



-- 
-- TABLE: TblMonitoramento 
--

CREATE TABLE TblMonitoramento(
    dtaInicioMonitora    DATETIME    NOT NULL,
    tpMonitora           CHAR(1),
    Acontecimento        CHAR(1),
    NmCPFCliente         CHAR(14)    NOT NULL,
    PRIMARY KEY (dtaInicioMonitora)
)ENGINE=INNODB
;



-- 
-- TABLE: TblOcorrencia 
--

CREATE TABLE TblOcorrencia(
    IdOcorrencia         INT         AUTO_INCREMENT,
    tpOcorrencia         INT,
    StatusOcorre         CHAR(1),
    Login                CHAR(10)    NOT NULL,
    dtaAtend             DATETIME,
    AcaoOcorrencia       TEXT,
    ReclOcorrencia       TEXT,
    dtaHoraAbertura      DATETIME,
    dtaHoraFechamento    DATETIME,
    dtaHoraInicio        DATETIME,
    dtaHoraTermino       DATETIME,
    Conclusao            TEXT,
    NmCPFCliente         CHAR(14)    NOT NULL,
    IdScript             INT         NOT NULL,
    PRIMARY KEY (IdOcorrencia)
)ENGINE=INNODB
;



-- 
-- TABLE: TblPacoteServico 
--

CREATE TABLE TblPacoteServico(
    IdServico           INT             AUTO_INCREMENT,
    dsServico           VARCHAR(100),
    dtInicioValidade    DATE            NOT NULL,
    dtFinalValidade     DATE,
    PRIMARY KEY (IdServico)
)ENGINE=INNODB
;



-- 
-- TABLE: TblPacXDoenca 
--

CREATE TABLE TblPacXDoenca(
    NmCPFCliente    CHAR(14)    NOT NULL,
    CdCID           CHAR(10)    NOT NULL,
    PRIMARY KEY (NmCPFCliente, CdCID)
)ENGINE=INNODB
;



-- 
-- TABLE: TblParametro 
--

CREATE TABLE TblParametro(
    IdParametro            INT    AUTO_INCREMENT,
    DiasDados              INT    DEFAULT 90 NOT NULL,
    DiasBemEstar           INT    DEFAULT 90 NOT NULL,
    ToleraRotinaCliente    INT,
    PRIMARY KEY (IdParametro)
)ENGINE=INNODB
;



-- 
-- TABLE: TblScript 
--

CREATE TABLE TblScript(
    IdScript            INT             AUTO_INCREMENT,
    nmTitulo            VARCHAR(60)     NOT NULL,
    dsProcesso          TEXT,
    dsDescricao         VARCHAR(100),
    dtInicioValidade    DATE            NOT NULL,
    dtFinalValidade     DATE,
    PRIMARY KEY (IdScript)
)ENGINE=INNODB
;



-- 
-- TABLE: TblSMS 
--

CREATE TABLE TblSMS(
    IdSMS                INT             AUTO_INCREMENT,
    tpMensagem           VARCHAR(20)     NOT NULL,
    dsMensagem           VARCHAR(100)    NOT NULL,
    idMomento            CHAR(1),
    dtInicioValidade     DATE            NOT NULL,
    dtTerminoValidade    DATE,
    PRIMARY KEY (IdSMS)
)ENGINE=INNODB
;



-- 
-- TABLE: TblTipoDoenca 
--

CREATE TABLE TblTipoDoenca(
    cdTipoDoenca    CHAR(10)       NOT NULL,
    dsTipoDoenca    VARCHAR(60),
    PRIMARY KEY (cdTipoDoenca)
)ENGINE=INNODB
;



-- 
-- TABLE: TblTratamento 
--

CREATE TABLE TblTratamento(
    IdTratamento    INT            AUTO_INCREMENT,
    NmCPFCliente    CHAR(14)       NOT NULL,
    NomeTrata       VARCHAR(60),
    DescrTrata      VARCHAR(60),
    HoraInicial     TIMESTAMP,
    PRIMARY KEY (IdTratamento, NmCPFCliente)
)ENGINE=INNODB
;



-- 
-- TABLE: TblUsuario 
--

CREATE TABLE TblUsuario(
    Login           CHAR(10)       NOT NULL,
    nmUsuario       VARCHAR(60)    NOT NULL,
    senha           CHAR(70)       NOT NULL,
    nmTelFixo       CHAR(12),
    nmTelCelular    CHAR(12),
    nmFuncao        INT,
    CdPerfil        INT,
    PRIMARY KEY (Login)
)ENGINE=INNODB
;



-- 
-- TABLE: TblAcionamento 
--

ALTER TABLE TblAcionamento ADD CONSTRAINT RefTblOcorrencia22 
    FOREIGN KEY (IdOcorrencia)
    REFERENCES TblOcorrencia(IdOcorrencia)
;

ALTER TABLE TblAcionamento ADD CONSTRAINT RefTblContato23 
    FOREIGN KEY (IdContato)
    REFERENCES TblContato(IdContato)
;

ALTER TABLE TblAcionamento ADD CONSTRAINT RefTblSMS24 
    FOREIGN KEY (IdSMS)
    REFERENCES TblSMS(IdSMS)
;


-- 
-- TABLE: TblAplicaMedico 
--

ALTER TABLE TblAplicaMedico ADD CONSTRAINT RefTblTratamento41 
    FOREIGN KEY (IdTratamento, NmCPFCliente)
    REFERENCES TblTratamento(IdTratamento, NmCPFCliente)
;


-- 
-- TABLE: TblCID 
--

ALTER TABLE TblCID ADD CONSTRAINT RefTblTipoDoenca1 
    FOREIGN KEY (cdTipoDoenca)
    REFERENCES TblTipoDoenca(cdTipoDoenca)
;


-- 
-- TABLE: TblCliente 
--

ALTER TABLE TblCliente ADD CONSTRAINT RefTblUsuario26 
    FOREIGN KEY (Login)
    REFERENCES TblUsuario(Login)
;

ALTER TABLE TblCliente ADD CONSTRAINT RefTblContrato32 
    FOREIGN KEY (nmContrato)
    REFERENCES TblContrato(nmContrato)
;


-- 
-- TABLE: TblClientexDispositivo 
--

ALTER TABLE TblClientexDispositivo ADD CONSTRAINT RefTblCliente38 
    FOREIGN KEY (NmCPFCliente)
    REFERENCES TblCliente(NmCPFCliente)
;

ALTER TABLE TblClientexDispositivo ADD CONSTRAINT RefTblDispositivo39 
    FOREIGN KEY (IdDispositivo)
    REFERENCES TblDispositivo(IdDispositivo)
;


-- 
-- TABLE: TblContato 
--

ALTER TABLE TblContato ADD CONSTRAINT RefTblCliente33 
    FOREIGN KEY (NmCPFCliente)
    REFERENCES TblCliente(NmCPFCliente)
;

ALTER TABLE TblContato ADD CONSTRAINT RefTblUsuario27 
    FOREIGN KEY (Login)
    REFERENCES TblUsuario(Login)
;


-- 
-- TABLE: TblContrato 
--

ALTER TABLE TblContrato ADD CONSTRAINT RefTblPacoteServico35 
    FOREIGN KEY (IdServico)
    REFERENCES TblPacoteServico(IdServico)
;

ALTER TABLE TblContrato ADD CONSTRAINT RefTblUsuario29 
    FOREIGN KEY (Login)
    REFERENCES TblUsuario(Login)
;


-- 
-- TABLE: TblDispositivo 
--

ALTER TABLE TblDispositivo ADD CONSTRAINT RefTblUsuario30 
    FOREIGN KEY (Login)
    REFERENCES TblUsuario(Login)
;


-- 
-- TABLE: TblFormaComunica 
--

ALTER TABLE TblFormaComunica ADD CONSTRAINT RefTblContato37 
    FOREIGN KEY (IdContato)
    REFERENCES TblContato(IdContato)
;


-- 
-- TABLE: TblMonitoramento 
--

ALTER TABLE TblMonitoramento ADD CONSTRAINT RefTblCliente34 
    FOREIGN KEY (NmCPFCliente)
    REFERENCES TblCliente(NmCPFCliente)
;


-- 
-- TABLE: TblOcorrencia 
--

ALTER TABLE TblOcorrencia ADD CONSTRAINT RefTblCliente20 
    FOREIGN KEY (NmCPFCliente)
    REFERENCES TblCliente(NmCPFCliente)
;

ALTER TABLE TblOcorrencia ADD CONSTRAINT RefTblScript21 
    FOREIGN KEY (IdScript)
    REFERENCES TblScript(IdScript)
;

ALTER TABLE TblOcorrencia ADD CONSTRAINT RefTblUsuario25 
    FOREIGN KEY (Login)
    REFERENCES TblUsuario(Login)
;


-- 
-- TABLE: TblPacXDoenca 
--

ALTER TABLE TblPacXDoenca ADD CONSTRAINT RefTblCliente3 
    FOREIGN KEY (NmCPFCliente)
    REFERENCES TblCliente(NmCPFCliente)
;

ALTER TABLE TblPacXDoenca ADD CONSTRAINT RefTblCID4 
    FOREIGN KEY (CdCID)
    REFERENCES TblCID(CdCID)
;


-- 
-- TABLE: TblTratamento 
--

ALTER TABLE TblTratamento ADD CONSTRAINT RefTblCliente15 
    FOREIGN KEY (NmCPFCliente)
    REFERENCES TblCliente(NmCPFCliente)
;


