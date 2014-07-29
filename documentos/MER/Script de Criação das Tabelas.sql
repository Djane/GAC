--
-- er/studio 8.0 sql code generation
-- company :      sw2
-- project :      pulseiras.dm1
-- author :       marcelo santos
--
-- date created : saturday, july 26, 2014 15:38:44
-- target dbms : mysql 5.x
--

-- 
-- table: ligacao 
--

create table ligacao(
    idligacao          int         auto_increment,
    iduniqueid         char(20),
    numatendente       char(10),
    numramal           char(10),
    numtelefone        char(15),
    codpulseira        char(30),
    dthrligacao        datetime,
    dthratendimento    datetime,
    primary key (idligacao)
)
;



-- 
-- table: tblacionamento 
--

create table tblacionamento(
    idaciona         int             auto_increment,
    idocorrencia     int             not null,
    idcontato        int             not null,
    dtahoraaciona    datetime        not null,
    acaopedida       text            not null,
    dtahorainicio    datetime,
    dtahorafinal     datetime,
    idsms            int,
    textolivresms    varchar(100),
    sucesso          char(1),
    primary key (idaciona)
)engine=innodb
;



-- 
-- table: tblaplicamedico 
--

create table tblaplicamedico(
    hraplicacao     timestamp    not null,
    idtratamento    int          not null,
    nmcpfcliente    char(14)     not null,
    primary key (hraplicacao, idtratamento, nmcpfcliente)
)engine=innodb
;



-- 
-- table: tblcid 
--

create table tblcid(
    cdcid           char(6)         not null,
    cdtipodoenca    int,
    nmdoenca        varchar(100),
    primary key (cdcid)
)engine=innodb
;



-- 
-- table: tblcliente 
--

create table tblcliente(
    nmcpfcliente             char(14)       not null,
    nmcontrato               int,
    nmcliente                varchar(60)    not null,
    dsendereco               varchar(60)    not null,
    dsbairro                 varchar(60)    not null,
    dscidade                 varchar(60)    not null,
    dsestado                 char(2)        not null,
    dscep                    char(10)       not null,
    nrrg                     char(14)       not null,
    tpsexo                   int,
    dtnascimento             date           not null,
    nmnecessidadeespecial    text,
    nmplanosaude             varchar(60),
    dscobertura              text,
    dtaproxbemestar          date,
    login                    char(10)       not null,
    primary key (nmcpfcliente)
)engine=innodb
;



-- 
-- table: tblclientexdispositivo 
--

create table tblclientexdispositivo(
    nmcpfcliente      char(14)    not null,
    iddispositivo     char(13)    not null,
    numdispositivo    int,
    primary key (nmcpfcliente, iddispositivo)
)engine=innodb
;



-- 
-- table: tblcontato 
--

create table tblcontato(
    idcontato         int            auto_increment,
    nomecontato       varchar(60)    not null,
    grauparentesco    char(1)        not null,
    endcontato        varchar(60)    not null,
    baicontato        varchar(60)    not null,
    cidcontato        varchar(60)    not null,
    cepcontato        char(10)       not null,
    estadocontato     char(2)        not null,
    dtanascimento     date,
    sqachamada        int            not null,
    contratante       char(1)        not null,
    login             char(10)       not null,
    nmcpfcliente      char(14)       not null,
    primary key (idcontato)
)engine=innodb
;



-- 
-- table: tblcontrato 
--

create table tblcontrato(
    nmcontrato           int            auto_increment,
    dtiniciovalidade     date           not null,
    dtfinalvalidade      date,
    dtsuspensao          date,
    login                char(10)       not null,
    nmcpfcontratante     char(14)       not null,
    nmnomecontratante    varchar(60)    not null,
    dtnasccontratante    date,
    nmrgcontratante      char(14),
    dtproxatual          date           not null,
    idservico            int            not null,
    idvendedor           int            not null,
    hrimobilidade        int            default 12 not null,
    primary key (nmcontrato)
)engine=innodb
;



-- 
-- table: tbldispositivo 
--

create table tbldispositivo(
    iddispositivo      char(13)    not null,
    tpdispositivo      int,
    dtafabrica         date,
    dtaentrada         date,
    tpestado           int         not null,
    dtaproximamanut    date,
    dtasucata          date,
    local              int,
    login              char(10)    not null,
    primary key (iddispositivo)
)engine=innodb
;



-- 
-- table: tblformacomunica 
--

create table tblformacomunica(
    idformacomunica    int             auto_increment,
    idcontato          int,
    tpcontato          char(14),
    fonecontato        char(15),
    mailcontato        varchar(100),
    nmcpfcliente       char(14),
    idvendedor         int,
    primary key (idformacomunica)
)engine=innodb
;



-- 
-- table: tblgaclog 
--

create table tblgaclog(
    idregistro          int              not null,
    datahoraregistro    datetime,
    mensagem            varchar(1024),
    usuariogac          char(10),
    primary key (idregistro)
)
;



-- 
-- table: tblhistdispositivo 
--

create table tblhistdispositivo(
    dthrmudaestado      timestamp    not null,
    iddispositivo       char(13)     not null,
    cdestadoanterior    int,
    login               char(10)     not null,
    primary key (dthrmudaestado, iddispositivo)
)engine=innodb
;



-- 
-- table: tblmonitoramento 
--

create table tblmonitoramento(
    dtainiciomonitora    datetime    not null,
    tpmonitora           int,
    acontecimento        int,
    nmcpfcliente         char(14)    not null,
    primary key (dtainiciomonitora)
)engine=innodb
;



-- 
-- table: tblocorrencia 
--

create table tblocorrencia(
    idocorrencia         int         auto_increment,
    tpocorrencia         int         not null,
    statusocorre         int         not null,
    codprioridade        int         not null,
    login                char(10)    not null,
    dtaatend             datetime    not null,
    acaoocorrencia       text,
    reclocorrencia       text,
    dtahoraabertura      datetime    not null,
    dtahorafechamento    datetime,
    dtahorainicio        datetime,
    dtahoratermino       datetime,
    conclusao            text,
    sndispositivo        int,
    nrtelefoneligado     char(15),
    nmcpfcliente         char(14),
    idscript             int,
    idligacao            int,
    primary key (idocorrencia)
)engine=innodb
;


-- 
-- table: tblpacoteservico 
--

create table tblpacoteservico(
    idservico           int               auto_increment,
    dstitulo            varchar(60)       not null,
    dsservico           varchar(100),
    dtiniciovalidade    date              not null,
    dtfinalvalidade     date,
    prcmensal           decimal(10, 2),
    prcinscricao        decimal(10, 2),
    primary key (idservico)
)engine=innodb
;



-- 
-- table: tblpacxdoenca 
--

create table tblpacxdoenca(
    nmcpfcliente    char(14)    not null,
    cdcid           char(6)     not null,
    primary key (nmcpfcliente, cdcid)
)engine=innodb
;



-- 
-- table: tblparametro 
--

create table tblparametro(
    idparametro            int         auto_increment,
    diasdados              int         default 90 not null,
    diasbemestar           int         default 90 not null,
    tolerarotinacliente    int,
    hrkeepalive            int         default 24 not null,
    nrfonecentral1         char(16)    default 0000000000000000 not null,
    nrfonecentral2         char(16)    default 0000000000000000,
    nrfonecentral3         char(16)    default 0000000000000000,
    nrfonecentral4         char(16)    default 0000000000000000,
    nrfonecentral5         char(16)    default 0000000000000000,
    nrfonecentral6         char(16)    default 0000000000000000,
    hrgsm                  int         default 24,
    dtultimaaltera         datetime    not null,
    primary key (idparametro)
)engine=innodb
;



-- 
-- table: tblscript 
--

create table tblscript(
    idscript            int             auto_increment,
    nmtitulo            varchar(60)     not null,
    dsprocesso          text,
    dsdescricao         varchar(100),
    dtiniciovalidade    date            not null,
    dtfinalvalidade     date,
    primary key (idscript)
)engine=innodb
;



-- 
-- table: tblsms 
--

create table tblsms(
    idsms                int             auto_increment,
    tpmensagem           varchar(20)     not null,
    dsmensagem           varchar(100)    not null,
    idmomento            int,
    dtiniciovalidade     date            not null,
    dtterminovalidade    date,
    primary key (idsms)
)engine=innodb
;



-- 
-- table: tbltipodoenca 
--

create table tbltipodoenca(
    cdtipodoenca    int             auto_increment,
    dstipodoenca    varchar(100),
    nmcapitulo      int,
    catinic         char(5),
    catfinal        char(5),
    primary key (cdtipodoenca)
)engine=innodb
;



-- 
-- table: tbltratamento 
--

create table tbltratamento(
    idtratamento    int            auto_increment,
    nmcpfcliente    char(14)       not null,
    nometrata       varchar(60),
    descrtrata      varchar(60),
    horainicial     timestamp,
    tpfrequencia    int,
    primary key (idtratamento, nmcpfcliente)
)engine=innodb
;



-- 
-- table: tblusuario 
--

create table tblusuario(
    login           char(10)       not null,
    nmusuario       varchar(60)    not null,
    senha           char(70)       not null,
    nmtelfixo       char(15),
    nmtelcelular    char(15),
    nmfuncao        int,
    cdperfil        int,
    nmregistro      int,
    nmramal         int,
    primary key (login)
)engine=innodb
;



-- 
-- table: tblvendedor 
--

create table tblvendedor(
    idvendedor    int             auto_increment,
    nmvendedor    varchar(60),
    dsendereco    varchar(100),
    nmcidade      varchar(60),
    dsestado      char(2),
    dsbairro      varchar(60),
    primary key (idvendedor)
)
;



-- 
-- index: listaacionamento 
--

create index listaacionamento on tblacionamento(idcontato, dtahoraaciona)
;
-- 
-- index: nomedoenca 
--

create unique index nomedoenca on tblcid(nmdoenca)
;
-- 
-- index: nomepaciente 
--

create index nomepaciente on tblcliente(nmcliente)
;
-- 
-- index: nomecontato 
--

create index nomecontato on tblcontato(nomecontato)
;
-- 
-- index: nomecontatoparentesco 
--

create index nomecontatoparentesco on tblcontato(nomecontato, grauparentesco)
;
-- 
-- index: nomecontratante 
--

create index nomecontratante on tblcontrato(nmnomecontratante)
;
-- 
-- index: dataocorrencia 
--

create index dataocorrencia on tblocorrencia(dtahoraabertura)
;
-- 
-- index: atendimento 
--

create index atendimento on tblocorrencia(dtaatend)
;
-- 
-- index: idxtituloservico 
--

create unique index idxtituloservico on tblpacoteservico(dstitulo)
;
-- 
-- index: idxtituloscript 
--

create unique index idxtituloscript on tblscript(nmtitulo)
;
-- 
-- index: nomeusuario 
--

create index nomeusuario on tblusuario(nmusuario)
;
-- 
-- table: tblacionamento 
--

alter table tblacionamento add constraint reftblocorrencia22 
    foreign key (idocorrencia)
    references tblocorrencia(idocorrencia)
;

alter table tblacionamento add constraint reftblcontato23 
    foreign key (idcontato)
    references tblcontato(idcontato)
;

alter table tblacionamento add constraint reftblsms24 
    foreign key (idsms)
    references tblsms(idsms)
;


-- 
-- table: tblaplicamedico 
--

alter table tblaplicamedico add constraint reftbltratamento40 
    foreign key (idtratamento, nmcpfcliente)
    references tbltratamento(idtratamento, nmcpfcliente)
;


-- 
-- table: tblcid 
--

alter table tblcid add constraint reftbltipodoenca1 
    foreign key (cdtipodoenca)
    references tbltipodoenca(cdtipodoenca)
;


-- 
-- table: tblcliente 
--

alter table tblcliente add constraint reftblusuario26 
    foreign key (login)
    references tblusuario(login)
;

alter table tblcliente add constraint reftblcontrato32 
    foreign key (nmcontrato)
    references tblcontrato(nmcontrato)
;


-- 
-- table: tblclientexdispositivo 
--

alter table tblclientexdispositivo add constraint reftblcliente38 
    foreign key (nmcpfcliente)
    references tblcliente(nmcpfcliente)
;

alter table tblclientexdispositivo add constraint reftbldispositivo39 
    foreign key (iddispositivo)
    references tbldispositivo(iddispositivo)
;


-- 
-- table: tblcontato 
--

alter table tblcontato add constraint reftblusuario27 
    foreign key (login)
    references tblusuario(login)
;

alter table tblcontato add constraint reftblcliente33 
    foreign key (nmcpfcliente)
    references tblcliente(nmcpfcliente)
;


-- 
-- table: tblcontrato 
--

alter table tblcontrato add constraint reftblusuario29 
    foreign key (login)
    references tblusuario(login)
;

alter table tblcontrato add constraint reftblpacoteservico35 
    foreign key (idservico)
    references tblpacoteservico(idservico)
;

-- 
-- table: tbldispositivo 
--

alter table tbldispositivo add constraint reftblusuario30 
    foreign key (login)
    references tblusuario(login)
;


-- 
-- table: tblformacomunica 
--

alter table tblformacomunica add constraint reftblcontato37 
    foreign key (idcontato)
    references tblcontato(idcontato)
;

alter table tblformacomunica add constraint reftblcliente41 
    foreign key (nmcpfcliente)
    references tblcliente(nmcpfcliente)
;


-- 
-- table: tblhistdispositivo 
--

alter table tblhistdispositivo add constraint reftbldispositivo42 
    foreign key (iddispositivo)
    references tbldispositivo(iddispositivo)
;

alter table tblhistdispositivo add constraint reftblusuario43 
    foreign key (login)
    references tblusuario(login)
;


-- 
-- table: tblmonitoramento 
--

alter table tblmonitoramento add constraint reftblcliente34 
    foreign key (nmcpfcliente)
    references tblcliente(nmcpfcliente)
;


-- 
-- table: tblocorrencia 
--

alter table tblocorrencia add constraint reftblcliente20 
    foreign key (nmcpfcliente)
    references tblcliente(nmcpfcliente)
;

alter table tblocorrencia add constraint reftblscript21 
    foreign key (idscript)
    references tblscript(idscript)
;

alter table tblocorrencia add constraint reftblusuario25 
    foreign key (login)
    references tblusuario(login)
;


-- 
-- table: tblpacxdoenca 
--

alter table tblpacxdoenca add constraint reftblcliente3 
    foreign key (nmcpfcliente)
    references tblcliente(nmcpfcliente)
;

alter table tblpacxdoenca add constraint reftblcid4 
    foreign key (cdcid)
    references tblcid(cdcid)
;


-- 
-- table: tbltratamento 
--

alter table tbltratamento add constraint reftblcliente15 
    foreign key (nmcpfcliente)
    references tblcliente(nmcpfcliente)
;

