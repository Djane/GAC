--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      SW2
-- Project :      PULSEIRAS.DM1
-- Author :       Marcelo Santos
--
-- Date Created : Thursday, June 19, 2014 09:42:40
-- Target DBMS : MySQL 5.x
--

-- 
-- TABLE: Ligacao 
--

CREATE TABLE Ligacao(
    idLigacao          INT         AUTO_INCREMENT,
    idUniqueid         CHAR(20),
    numAtendente       CHAR(10),
    numRamal           CHAR(10),
    numTelefone        CHAR(15),
    codPulseira        CHAR(30),
    dtHrLigacao        DATETIME,
    dtHrAtendimento    DATETIME,
    PRIMARY KEY (idLigacao)
)
;



-- 
-- TABLE: TblAcionamento 
--

CREATE TABLE TblAcionamento(
    IdAciona         INT             AUTO_INCREMENT,
    idOcorrencia     INT             NOT NULL,
    idContato        INT             NOT NULL,
    dtaHoraAciona    DATETIME        NOT NULL,
    acaoPedida       TEXT            NOT NULL,
    dtaHoraInicio    DATETIME,
    dtaHoraFinal     DATETIME,
    idSMS            INT,
    textoLivreSMS    VARCHAR(100),
    sucesso          CHAR(1),
    PRIMARY KEY (IdAciona)
)ENGINE=INNODB
;



-- 
-- TABLE: TblAplicaMedico 
--

CREATE TABLE TblAplicaMedico(
    hrAplicacao     TIMESTAMP    NOT NULL,
    idTratamento    INT          NOT NULL,
    nmCPFCliente    CHAR(14)     NOT NULL,
    PRIMARY KEY (hrAplicacao, idTratamento, nmCPFCliente)
)ENGINE=INNODB
;



-- 
-- TABLE: TblCID 
--

CREATE TABLE TblCID(
    cdCID           CHAR(6)         NOT NULL,
    cdTipoDoenca    INT,
    nmDoenca        VARCHAR(100),
    PRIMARY KEY (cdCID)
)ENGINE=INNODB
;



-- 
-- TABLE: TblCliente 
--

CREATE TABLE TblCliente(
    nmCPFCliente             CHAR(14)       NOT NULL,
    nmContrato               INT,
    nmCliente                VARCHAR(60)    NOT NULL,
    dsEndereco               VARCHAR(60)    NOT NULL,
    dsBairro                 VARCHAR(60)    NOT NULL,
    dsCidade                 VARCHAR(60)    NOT NULL,
    dsEstado                 CHAR(2)        NOT NULL,
    dsCEP                    CHAR(10)       NOT NULL,
    nrRG                     CHAR(14)       NOT NULL,
    tpSexo                   INT,
    dtNascimento             DATE           NOT NULL,
    nmNecessidadeEspecial    TEXT,
    nmPlanoSaude             VARCHAR(60),
    dsCobertura              TEXT,
    dtaProxBemEstar          DATE,
    login                    CHAR(10)       NOT NULL,
    PRIMARY KEY (nmCPFCliente)
)ENGINE=INNODB
;



-- 
-- TABLE: TblClientexDispositivo 
--

CREATE TABLE TblClientexDispositivo(
    nmCPFCliente      CHAR(14)    NOT NULL,
    idDispositivo     CHAR(13)    NOT NULL,
    numDispositivo    INT,
    PRIMARY KEY (nmCPFCliente, idDispositivo)
)ENGINE=INNODB
;



-- 
-- TABLE: TblContato 
--

CREATE TABLE TblContato(
    idContato         INT            AUTO_INCREMENT,
    nomeContato       VARCHAR(60)    NOT NULL,
    grauParentesco    CHAR(1)        NOT NULL,
    endContato        VARCHAR(60)    NOT NULL,
    baiContato        VARCHAR(60)    NOT NULL,
    cidContato        VARCHAR(60)    NOT NULL,
    cepContato        CHAR(10)       NOT NULL,
    estadoContato     CHAR(2)        NOT NULL,
    dtaNascimento     DATE,
    sqaChamada        INT            NOT NULL,
    contratante       CHAR(1)        NOT NULL,
    login             CHAR(10)       NOT NULL,
    nmCPFCliente      CHAR(14)       NOT NULL,
    PRIMARY KEY (idContato)
)ENGINE=INNODB
;



-- 
-- TABLE: TblContrato 
--

CREATE TABLE TblContrato(
    nmContrato           INT            AUTO_INCREMENT,
    dtInicioValidade     DATE           NOT NULL,
    dtFinalValidade      DATE,
    dtSuspensao          DATE,
    login                CHAR(10)       NOT NULL,
    nmCPFContratante     CHAR(14)       NOT NULL,
    nmNomeContratante    VARCHAR(60)    NOT NULL,
    dtNascContratante    DATE,
    nmRGContratante      CHAR(14),
    dtProxAtual          DATE           NOT NULL,
    idServico            INT            NOT NULL,
    idVendedor           INT            NOT NULL,
    hrImobilidade        INT            DEFAULT 12 NOT NULL,
    PRIMARY KEY (nmContrato)
)ENGINE=INNODB
;



-- 
-- TABLE: TblDispositivo 
--

CREATE TABLE TblDispositivo(
    idDispositivo      CHAR(13)    NOT NULL,
    tpDispositivo      INT,
    dtaFabrica         DATE,
    dtaEntrada         DATE,
    tpEstado           INT         NOT NULL,
    dtaProximaManut    DATE,
    dtaSucata          DATE,
    local              INT,
    login              CHAR(10)    NOT NULL,
    PRIMARY KEY (idDispositivo)
)ENGINE=INNODB
;



-- 
-- TABLE: TblFormaComunica 
--

CREATE TABLE TblFormaComunica(
    idFormaComunica    INT             AUTO_INCREMENT,
    idContato          INT,
    tpContato          CHAR(14),
    foneContato        CHAR(15),
    mailContato        VARCHAR(100),
    nmCPFCliente       CHAR(14),
    idVendedor         INT,
    PRIMARY KEY (idFormaComunica)
)ENGINE=INNODB
;



-- 
-- TABLE: TblHistDispositivo 
--

CREATE TABLE TblHistDispositivo(
    dthrMudaEstado      TIMESTAMP    NOT NULL,
    idDispositivo       CHAR(13)     NOT NULL,
    cdEstadoAnterior    INT,
    login               CHAR(10)     NOT NULL,
    PRIMARY KEY (dthrMudaEstado, idDispositivo)
)ENGINE=INNODB
;



-- 
-- TABLE: TblMonitoramento 
--

CREATE TABLE TblMonitoramento(
    dtaInicioMonitora    DATETIME    NOT NULL,
    tpMonitora           INT,
    acontecimento        INT,
    nmCPFCliente         CHAR(14)    NOT NULL,
    PRIMARY KEY (dtaInicioMonitora)
)ENGINE=INNODB
;



-- 
-- TABLE: TblOcorrencia 
--

CREATE TABLE TblOcorrencia(
    idOcorrencia         INT         AUTO_INCREMENT,
    tpOcorrencia         INT         NOT NULL,
    statusOcorre         INT         NOT NULL,
    codPrioridade        INT         NOT NULL,
    login                CHAR(10)    NOT NULL,
    dtaAtend             DATETIME    NOT NULL,
    acaoOcorrencia       TEXT        NOT NULL,
    reclOcorrencia       TEXT,
    dtaHoraAbertura      DATETIME    NOT NULL,
    dtaHoraFechamento    DATETIME,
    dtaHoraInicio        DATETIME,
    dtaHoraTermino       DATETIME,
    conclusao            TEXT,
    snDispositivo        INT,
    nrTelefoneLigado     CHAR(15),
    nmCPFCliente         CHAR(14),
    idScript             INT         NOT NULL,
    idLigacao            INT,
    PRIMARY KEY (idOcorrencia)
)ENGINE=INNODB
;



-- 
-- TABLE: TblPacoteServico 
--

CREATE TABLE TblPacoteServico(
    idServico           INT               AUTO_INCREMENT,
    dsTitulo            VARCHAR(60)       NOT NULL,
    dsServico           VARCHAR(100),
    dtInicioValidade    DATE              NOT NULL,
    dtFinalValidade     DATE,
    prcMensal           DECIMAL(10, 2),
    prcInscricao        DECIMAL(10, 2),
    PRIMARY KEY (idServico)
)ENGINE=INNODB
;



-- 
-- TABLE: TblPacXDoenca 
--

CREATE TABLE TblPacXDoenca(
    nmCPFCliente    CHAR(14)    NOT NULL,
    cdCID           CHAR(6)     NOT NULL,
    PRIMARY KEY (nmCPFCliente, cdCID)
)ENGINE=INNODB
;



-- 
-- TABLE: TblParametro 
--

CREATE TABLE TblParametro(
    idParametro            INT         AUTO_INCREMENT,
    diasDados              INT         DEFAULT 90 NOT NULL,
    diasBemEstar           INT         DEFAULT 90 NOT NULL,
    toleraRotinaCliente    INT,
    hrKeepAlive            INT         DEFAULT 24 NOT NULL,
    nrFoneCentral1         CHAR(16)    DEFAULT 0000000000000000 NOT NULL,
    nrFoneCentral2         CHAR(16)    DEFAULT 0000000000000000,
    nrFoneCentral3         CHAR(16)    DEFAULT 0000000000000000,
    nrFoneCentral4         CHAR(16)    DEFAULT 0000000000000000,
    nrFoneCentral5         CHAR(16)    DEFAULT 0000000000000000,
    nrFoneCentral6         CHAR(16)    DEFAULT 0000000000000000,
    hrGSM                  INT         DEFAULT 24,
    dtUltimaAltera         DATETIME    NOT NULL,
    PRIMARY KEY (idParametro)
)ENGINE=INNODB
;



-- 
-- TABLE: TblScript 
--

CREATE TABLE TblScript(
    idScript            INT             AUTO_INCREMENT,
    nmTitulo            VARCHAR(60)     NOT NULL,
    dsProcesso          TEXT,
    dsDescricao         VARCHAR(100),
    dtInicioValidade    DATE            NOT NULL,
    dtFinalValidade     DATE,
    PRIMARY KEY (idScript)
)ENGINE=INNODB
;



-- 
-- TABLE: TblSMS 
--

CREATE TABLE TblSMS(
    idSMS                INT             AUTO_INCREMENT,
    tpMensagem           VARCHAR(20)     NOT NULL,
    dsMensagem           VARCHAR(100)    NOT NULL,
    idMomento            INT,
    dtInicioValidade     DATE            NOT NULL,
    dtTerminoValidade    DATE,
    PRIMARY KEY (idSMS)
)ENGINE=INNODB
;



-- 
-- TABLE: TblTipoDoenca 
--

CREATE TABLE TblTipoDoenca(
    cdTipoDoenca    INT             AUTO_INCREMENT,
    dsTipoDoenca    VARCHAR(100),
    nmCapitulo      INT,
    catInic         CHAR(5),
    catFinal        CHAR(5),
    PRIMARY KEY (cdTipoDoenca)
)ENGINE=INNODB
;



-- 
-- TABLE: TblTratamento 
--

CREATE TABLE TblTratamento(
    idTratamento    INT            AUTO_INCREMENT,
    nmCPFCliente    CHAR(14)       NOT NULL,
    nomeTrata       VARCHAR(60),
    descrTrata      VARCHAR(60),
    horaInicial     TIMESTAMP,
    tpFrequencia    INT,
    PRIMARY KEY (idTratamento, nmCPFCliente)
)ENGINE=INNODB
;



-- 
-- TABLE: TblUsuario 
--

CREATE TABLE TblUsuario(
    login           CHAR(10)       NOT NULL,
    nmUsuario       VARCHAR(60)    NOT NULL,
    senha           CHAR(70)       NOT NULL,
    nmTelFixo       CHAR(15),
    nmTelCelular    CHAR(15),
    nmFuncao        INT,
    cdPerfil        INT,
    nmRegistro      INT,
    nmRamal         INT,
    PRIMARY KEY (login)
)ENGINE=INNODB
;



-- 
-- TABLE: TblVendedor 
--

CREATE TABLE TblVendedor(
    idVendedor    INT             AUTO_INCREMENT,
    nmVendedor    VARCHAR(60),
    dsEndereco    VARCHAR(100),
    nmCidade      VARCHAR(60),
    dsEstado      CHAR(2),
    dsBairro      VARCHAR(60),
    PRIMARY KEY (idVendedor)
)
;



-- 
-- INDEX: ListaAcionamento 
--

CREATE INDEX ListaAcionamento ON TblAcionamento(idContato, dtaHoraAciona)
;
-- 
-- INDEX: NomeDoenca 
--

CREATE UNIQUE INDEX NomeDoenca ON TblCID(nmDoenca)
;
-- 
-- INDEX: NomePaciente 
--

CREATE INDEX NomePaciente ON TblCliente(nmCliente)
;
-- 
-- INDEX: NomeContato 
--

CREATE INDEX NomeContato ON TblContato(nomeContato)
;
-- 
-- INDEX: NomeContatoParentesco 
--

CREATE INDEX NomeContatoParentesco ON TblContato(nomeContato, grauParentesco)
;
-- 
-- INDEX: NomeContratante 
--

CREATE INDEX NomeContratante ON TblContrato(nmNomeContratante)
;
-- 
-- INDEX: DataOcorrencia 
--

CREATE INDEX DataOcorrencia ON TblOcorrencia(dtaHoraAbertura)
;
-- 
-- INDEX: Atendimento 
--

CREATE INDEX Atendimento ON TblOcorrencia(dtaAtend)
;
-- 
-- INDEX: idxTituloServico 
--

CREATE UNIQUE INDEX idxTituloServico ON TblPacoteServico(dsTitulo)
;
-- 
-- INDEX: idxTituloScript 
--

CREATE UNIQUE INDEX idxTituloScript ON TblScript(nmTitulo)
;
-- 
-- INDEX: NomeUsuario 
--

CREATE INDEX NomeUsuario ON TblUsuario(nmUsuario)
;
-- 
-- TABLE: TblAcionamento 
--

ALTER TABLE TblAcionamento ADD CONSTRAINT RefTblOcorrencia22 
    FOREIGN KEY (idOcorrencia)
    REFERENCES TblOcorrencia(idOcorrencia)
;

ALTER TABLE TblAcionamento ADD CONSTRAINT RefTblContato23 
    FOREIGN KEY (idContato)
    REFERENCES TblContato(idContato)
;

ALTER TABLE TblAcionamento ADD CONSTRAINT RefTblSMS24 
    FOREIGN KEY (idSMS)
    REFERENCES TblSMS(idSMS)
;


-- 
-- TABLE: TblAplicaMedico 
--

ALTER TABLE TblAplicaMedico ADD CONSTRAINT RefTblTratamento40 
    FOREIGN KEY (idTratamento, nmCPFCliente)
    REFERENCES TblTratamento(idTratamento, nmCPFCliente)
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
    FOREIGN KEY (login)
    REFERENCES TblUsuario(login)
;

ALTER TABLE TblCliente ADD CONSTRAINT RefTblContrato32 
    FOREIGN KEY (nmContrato)
    REFERENCES TblContrato(nmContrato)
;


-- 
-- TABLE: TblClientexDispositivo 
--

ALTER TABLE TblClientexDispositivo ADD CONSTRAINT RefTblCliente38 
    FOREIGN KEY (nmCPFCliente)
    REFERENCES TblCliente(nmCPFCliente)
;

ALTER TABLE TblClientexDispositivo ADD CONSTRAINT RefTblDispositivo39 
    FOREIGN KEY (idDispositivo)
    REFERENCES TblDispositivo(idDispositivo)
;


-- 
-- TABLE: TblContato 
--

ALTER TABLE TblContato ADD CONSTRAINT RefTblUsuario27 
    FOREIGN KEY (login)
    REFERENCES TblUsuario(login)
;

ALTER TABLE TblContato ADD CONSTRAINT RefTblCliente33 
    FOREIGN KEY (nmCPFCliente)
    REFERENCES TblCliente(nmCPFCliente)
;


-- 
-- TABLE: TblContrato 
--

ALTER TABLE TblContrato ADD CONSTRAINT RefTblUsuario29 
    FOREIGN KEY (login)
    REFERENCES TblUsuario(login)
;

ALTER TABLE TblContrato ADD CONSTRAINT RefTblPacoteServico35 
    FOREIGN KEY (idServico)
    REFERENCES TblPacoteServico(idServico)
;

ALTER TABLE TblContrato ADD CONSTRAINT RefTblVendedor46 
    FOREIGN KEY (idVendedor)
    REFERENCES TblVendedor(idVendedor)
;


-- 
-- TABLE: TblDispositivo 
--

ALTER TABLE TblDispositivo ADD CONSTRAINT RefTblUsuario30 
    FOREIGN KEY (login)
    REFERENCES TblUsuario(login)
;


-- 
-- TABLE: TblFormaComunica 
--

ALTER TABLE TblFormaComunica ADD CONSTRAINT RefTblContato37 
    FOREIGN KEY (idContato)
    REFERENCES TblContato(idContato)
;

ALTER TABLE TblFormaComunica ADD CONSTRAINT RefTblCliente41 
    FOREIGN KEY (nmCPFCliente)
    REFERENCES TblCliente(nmCPFCliente)
;

ALTER TABLE TblFormaComunica ADD CONSTRAINT RefTblVendedor47 
    FOREIGN KEY (idVendedor)
    REFERENCES TblVendedor(idVendedor)
;


-- 
-- TABLE: TblHistDispositivo 
--

ALTER TABLE TblHistDispositivo ADD CONSTRAINT RefTblDispositivo42 
    FOREIGN KEY (idDispositivo)
    REFERENCES TblDispositivo(idDispositivo)
;

ALTER TABLE TblHistDispositivo ADD CONSTRAINT RefTblUsuario43 
    FOREIGN KEY (login)
    REFERENCES TblUsuario(login)
;


-- 
-- TABLE: TblMonitoramento 
--

ALTER TABLE TblMonitoramento ADD CONSTRAINT RefTblCliente34 
    FOREIGN KEY (nmCPFCliente)
    REFERENCES TblCliente(nmCPFCliente)
;


-- 
-- TABLE: TblOcorrencia 
--

ALTER TABLE TblOcorrencia ADD CONSTRAINT RefTblCliente20 
    FOREIGN KEY (nmCPFCliente)
    REFERENCES TblCliente(nmCPFCliente)
;

ALTER TABLE TblOcorrencia ADD CONSTRAINT RefTblScript21 
    FOREIGN KEY (idScript)
    REFERENCES TblScript(idScript)
;

ALTER TABLE TblOcorrencia ADD CONSTRAINT RefTblUsuario25 
    FOREIGN KEY (login)
    REFERENCES TblUsuario(login)
;


-- 
-- TABLE: TblPacXDoenca 
--

ALTER TABLE TblPacXDoenca ADD CONSTRAINT RefTblCliente3 
    FOREIGN KEY (nmCPFCliente)
    REFERENCES TblCliente(nmCPFCliente)
;

ALTER TABLE TblPacXDoenca ADD CONSTRAINT RefTblCID4 
    FOREIGN KEY (cdCID)
    REFERENCES TblCID(cdCID)
;


-- 
-- TABLE: TblTratamento 
--

ALTER TABLE TblTratamento ADD CONSTRAINT RefTblCliente15 
    FOREIGN KEY (nmCPFCliente)
    REFERENCES TblCliente(nmCPFCliente)
;


