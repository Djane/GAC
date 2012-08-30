package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: The persistent class for the TblPaciente database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblPaciente")
public class Paciente implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo nm cpf paciente. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "NmCPFPaciente", unique = true, nullable = false)
    private String nmCPFPaciente;

    /** Atributo ds bairro. */
    @Column(nullable = false)
    private String dsBairro;

    /** Atributo ds cep. */
    @Column(nullable = false)
    private String dsCEP;

    /** Atributo ds cidade. */
    @Column(nullable = false)
    private String dsCidade;

    /** Atributo ds cobertura. */
    @Lob
    private String dsCobertura;

    /** Atributo ds email. */
    @Column()
    private String dsEmail;

    /** Atributo ds endereco. */
    @Column(nullable = false)
    private String dsEndereco;

    /** Atributo ds estado. */
    @Column(nullable = false)
    private String dsEstado;

    /** Atributo dt nascimento. */
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtNascimento;

    /** Atributo nm celular. */
    @Column()
    private String nmCelular;

    /** Atributo nm necessidade especial. */
    @Lob
    @Column(name = "NmNecessidadeEspecial")
    private String nmNecessidadeEspecial;

    /** Atributo nm paciente. */
    @Column(name = "NmPaciente", nullable = false)
    private String nmPaciente;

    /** Atributo nm plano saude. */
    @Column()
    private String nmPlanoSaude;

    /** Atributo nm rg. */
    @Column(nullable = false)
    private String nmRG;

    /** Atributo nm telefone. */
    @Column()
    private String nmTelefone;

    /** Atributo tp sexo. */
    @Column(name = "TpSexo")
    private String tpSexo;

    // bi-directional many-to-one association to TblContato
    /** Atributo tbl contatos. */
    @OneToMany(mappedBy = "tblPaciente")
    private List<Contato> tblContatos;

    // bi-directional many-to-one association to TblMonitoramento
    /** Atributo tbl monitoramentos. */
    @OneToMany(mappedBy = "tblPaciente")
    private List<Monitoramento> tblMonitoramentos;

    // bi-directional many-to-one association to TblOcorrencia
    /** Atributo tbl ocorrencias. */
    @OneToMany(mappedBy = "tblPaciente")
    private List<Ocorrencia> tblOcorrencias;

    // bi-directional many-to-many association to CID
    /** Atributo tb cids. */
    @ManyToMany
    @JoinTable(name = "TblPacXDoenca",
    joinColumns = { @JoinColumn(name = "NmCPFPaciente", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "CdCID", nullable = false) })
    private List<CID> tbCids;

    // bi-directional many-to-one association to TblDispositivo
    /** Atributo tbl dispositivo1. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCentral", nullable = false)
    private Dispositivo tblDispositivo1;

    // bi-directional many-to-one association to TblContrato
    /** Atributo tbl contrato. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nmContrato")
    private Contrato tblContrato;

    // bi-directional many-to-one association to TblDispositivo
    /** Atributo tbl dispositivo2. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDispositivo")
    private Dispositivo tblDispositivo2;

    // bi-directional many-to-one association to TbUsuario
    /** Atributo tb usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Login", nullable = false)
    private Usuario tbUsuario;

    // bi-directional many-to-one association to TblTratamento
    /** Atributo tbl tratamentos. */
    @OneToMany(mappedBy = "tblPaciente")
    private List<Tratamento> tblTratamentos;

    /**
     * Construtor Padrao Instancia um novo objeto Paciente.
     */
    public Paciente() {
    }

    /**
     * Nome: getNmCPFPaciente Recupera o valor do atributo 'nmCPFPaciente'.
     * @return valor do atributo 'nmCPFPaciente'
     * @see
     */
    public String getNmCPFPaciente() {
        return this.nmCPFPaciente;
    }

    /**
     * Nome: setNmCPFPaciente Registra o valor do atributo 'nmCPFPaciente'.
     * @param nmCPFPaciente valor do atributo nm cpf paciente
     * @see
     */
    public void setNmCPFPaciente(String nmCPFPaciente) {
        this.nmCPFPaciente = nmCPFPaciente;
    }

    /**
     * Nome: getDsBairro Recupera o valor do atributo 'dsBairro'.
     * @return valor do atributo 'dsBairro'
     * @see
     */
    public String getDsBairro() {
        return this.dsBairro;
    }

    /**
     * Nome: setDsBairro Registra o valor do atributo 'dsBairro'.
     * @param dsBairro valor do atributo ds bairro
     * @see
     */
    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    /**
     * Nome: getDsCEP Recupera o valor do atributo 'dsCEP'.
     * @return valor do atributo 'dsCEP'
     * @see
     */
    public String getDsCEP() {
        return this.dsCEP;
    }

    /**
     * Nome: setDsCEP Registra o valor do atributo 'dsCEP'.
     * @param dsCEP valor do atributo ds cep
     * @see
     */
    public void setDsCEP(String dsCEP) {
        this.dsCEP = dsCEP;
    }

    /**
     * Nome: getDsCidade Recupera o valor do atributo 'dsCidade'.
     * @return valor do atributo 'dsCidade'
     * @see
     */
    public String getDsCidade() {
        return this.dsCidade;
    }

    /**
     * Nome: setDsCidade Registra o valor do atributo 'dsCidade'.
     * @param dsCidade valor do atributo ds cidade
     * @see
     */
    public void setDsCidade(String dsCidade) {
        this.dsCidade = dsCidade;
    }

    /**
     * Nome: getDsCobertura Recupera o valor do atributo 'dsCobertura'.
     * @return valor do atributo 'dsCobertura'
     * @see
     */
    public String getDsCobertura() {
        return this.dsCobertura;
    }

    /**
     * Nome: setDsCobertura Registra o valor do atributo 'dsCobertura'.
     * @param dsCobertura valor do atributo ds cobertura
     * @see
     */
    public void setDsCobertura(String dsCobertura) {
        this.dsCobertura = dsCobertura;
    }

    /**
     * Nome: getDsEmail Recupera o valor do atributo 'dsEmail'.
     * @return valor do atributo 'dsEmail'
     * @see
     */
    public String getDsEmail() {
        return this.dsEmail;
    }

    /**
     * Nome: setDsEmail Registra o valor do atributo 'dsEmail'.
     * @param dsEmail valor do atributo ds email
     * @see
     */
    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    /**
     * Nome: getDsEndereco Recupera o valor do atributo 'dsEndereco'.
     * @return valor do atributo 'dsEndereco'
     * @see
     */
    public String getDsEndereco() {
        return this.dsEndereco;
    }

    /**
     * Nome: setDsEndereco Registra o valor do atributo 'dsEndereco'.
     * @param dsEndereco valor do atributo ds endereco
     * @see
     */
    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    /**
     * Nome: getDsEstado Recupera o valor do atributo 'dsEstado'.
     * @return valor do atributo 'dsEstado'
     * @see
     */
    public String getDsEstado() {
        return this.dsEstado;
    }

    /**
     * Nome: setDsEstado Registra o valor do atributo 'dsEstado'.
     * @param dsEstado valor do atributo ds estado
     * @see
     */
    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    /**
     * Nome: getDtNascimento Recupera o valor do atributo 'dtNascimento'.
     * @return valor do atributo 'dtNascimento'
     * @see
     */
    public Date getDtNascimento() {
        return this.dtNascimento;
    }

    /**
     * Nome: setDtNascimento Registra o valor do atributo 'dtNascimento'.
     * @param dtNascimento valor do atributo dt nascimento
     * @see
     */
    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    /**
     * Nome: getNmCelular Recupera o valor do atributo 'nmCelular'.
     * @return valor do atributo 'nmCelular'
     * @see
     */
    public String getNmCelular() {
        return this.nmCelular;
    }

    /**
     * Nome: setNmCelular Registra o valor do atributo 'nmCelular'.
     * @param nmCelular valor do atributo nm celular
     * @see
     */
    public void setNmCelular(String nmCelular) {
        this.nmCelular = nmCelular;
    }

    /**
     * Nome: getNmNecessidadeEspecial Recupera o valor do atributo 'nmNecessidadeEspecial'.
     * @return valor do atributo 'nmNecessidadeEspecial'
     * @see
     */
    public String getNmNecessidadeEspecial() {
        return this.nmNecessidadeEspecial;
    }

    /**
     * Nome: setNmNecessidadeEspecial Registra o valor do atributo 'nmNecessidadeEspecial'.
     * @param nmNecessidadeEspecial valor do atributo nm necessidade especial
     * @see
     */
    public void setNmNecessidadeEspecial(String nmNecessidadeEspecial) {
        this.nmNecessidadeEspecial = nmNecessidadeEspecial;
    }

    /**
     * Nome: getNmPaciente Recupera o valor do atributo 'nmPaciente'.
     * @return valor do atributo 'nmPaciente'
     * @see
     */
    public String getNmPaciente() {
        return this.nmPaciente;
    }

    /**
     * Nome: setNmPaciente Registra o valor do atributo 'nmPaciente'.
     * @param nmPaciente valor do atributo nm paciente
     * @see
     */
    public void setNmPaciente(String nmPaciente) {
        this.nmPaciente = nmPaciente;
    }

    /**
     * Nome: getNmPlanoSaude Recupera o valor do atributo 'nmPlanoSaude'.
     * @return valor do atributo 'nmPlanoSaude'
     * @see
     */
    public String getNmPlanoSaude() {
        return this.nmPlanoSaude;
    }

    /**
     * Nome: setNmPlanoSaude Registra o valor do atributo 'nmPlanoSaude'.
     * @param nmPlanoSaude valor do atributo nm plano saude
     * @see
     */
    public void setNmPlanoSaude(String nmPlanoSaude) {
        this.nmPlanoSaude = nmPlanoSaude;
    }

    /**
     * Nome: getNmRG Recupera o valor do atributo 'nmRG'.
     * @return valor do atributo 'nmRG'
     * @see
     */
    public String getNmRG() {
        return this.nmRG;
    }

    /**
     * Nome: setNmRG Registra o valor do atributo 'nmRG'.
     * @param nmRG valor do atributo nm rg
     * @see
     */
    public void setNmRG(String nmRG) {
        this.nmRG = nmRG;
    }

    /**
     * Nome: getNmTelefone Recupera o valor do atributo 'nmTelefone'.
     * @return valor do atributo 'nmTelefone'
     * @see
     */
    public String getNmTelefone() {
        return this.nmTelefone;
    }

    /**
     * Nome: setNmTelefone Registra o valor do atributo 'nmTelefone'.
     * @param nmTelefone valor do atributo nm telefone
     * @see
     */
    public void setNmTelefone(String nmTelefone) {
        this.nmTelefone = nmTelefone;
    }

    /**
     * Nome: getTpSexo Recupera o valor do atributo 'tpSexo'.
     * @return valor do atributo 'tpSexo'
     * @see
     */
    public String getTpSexo() {
        return this.tpSexo;
    }

    /**
     * Nome: setTpSexo Registra o valor do atributo 'tpSexo'.
     * @param tpSexo valor do atributo tp sexo
     * @see
     */
    public void setTpSexo(String tpSexo) {
        this.tpSexo = tpSexo;
    }

    /**
     * Nome: getTblContatos Recupera o valor do atributo 'tblContatos'.
     * @return valor do atributo 'tblContatos'
     * @see
     */
    public List<Contato> getTblContatos() {
        return this.tblContatos;
    }

    /**
     * Nome: setTblContatos Registra o valor do atributo 'tblContatos'.
     * @param tblContatos valor do atributo tbl contatos
     * @see
     */
    public void setTblContatos(List<Contato> tblContatos) {
        this.tblContatos = tblContatos;
    }

    /**
     * Nome: getTblMonitoramentos Recupera o valor do atributo 'tblMonitoramentos'.
     * @return valor do atributo 'tblMonitoramentos'
     * @see
     */
    public List<Monitoramento> getTblMonitoramentos() {
        return this.tblMonitoramentos;
    }

    /**
     * Nome: setTblMonitoramentos Registra o valor do atributo 'tblMonitoramentos'.
     * @param tblMonitoramentos valor do atributo tbl monitoramentos
     * @see
     */
    public void setTblMonitoramentos(List<Monitoramento> tblMonitoramentos) {
        this.tblMonitoramentos = tblMonitoramentos;
    }

    /**
     * Nome: getTblOcorrencias Recupera o valor do atributo 'tblOcorrencias'.
     * @return valor do atributo 'tblOcorrencias'
     * @see
     */
    public List<Ocorrencia> getTblOcorrencias() {
        return this.tblOcorrencias;
    }

    /**
     * Nome: setTblOcorrencias Registra o valor do atributo 'tblOcorrencias'.
     * @param tblOcorrencias valor do atributo tbl ocorrencias
     * @see
     */
    public void setTblOcorrencias(List<Ocorrencia> tblOcorrencias) {
        this.tblOcorrencias = tblOcorrencias;
    }

    /**
     * Nome: getTbCids Recupera o valor do atributo 'tbCids'.
     * @return valor do atributo 'tbCids'
     * @see
     */
    public List<CID> getTbCids() {
        return this.tbCids;
    }

    /**
     * Nome: setTbCids Registra o valor do atributo 'tbCids'.
     * @param tbCids valor do atributo tb cids
     * @see
     */
    public void setTbCids(List<CID> tbCids) {
        this.tbCids = tbCids;
    }

    /**
     * Nome: getTblDispositivo1 Recupera o valor do atributo 'tblDispositivo1'.
     * @return valor do atributo 'tblDispositivo1'
     * @see
     */
    public Dispositivo getTblDispositivo1() {
        return this.tblDispositivo1;
    }

    /**
     * Nome: setTblDispositivo1 Registra o valor do atributo 'tblDispositivo1'.
     * @param tblDispositivo1 valor do atributo tbl dispositivo1
     * @see
     */
    public void setTblDispositivo1(Dispositivo tblDispositivo1) {
        this.tblDispositivo1 = tblDispositivo1;
    }

    /**
     * Nome: getTblContrato Recupera o valor do atributo 'tblContrato'.
     * @return valor do atributo 'tblContrato'
     * @see
     */
    public Contrato getTblContrato() {
        return this.tblContrato;
    }

    /**
     * Nome: setTblContrato Registra o valor do atributo 'tblContrato'.
     * @param tblContrato valor do atributo tbl contrato
     * @see
     */
    public void setTblContrato(Contrato tblContrato) {
        this.tblContrato = tblContrato;
    }

    /**
     * Nome: getTblDispositivo2 Recupera o valor do atributo 'tblDispositivo2'.
     * @return valor do atributo 'tblDispositivo2'
     * @see
     */
    public Dispositivo getTblDispositivo2() {
        return this.tblDispositivo2;
    }

    /**
     * Nome: setTblDispositivo2 Registra o valor do atributo 'tblDispositivo2'.
     * @param tblDispositivo2 valor do atributo tbl dispositivo2
     * @see
     */
    public void setTblDispositivo2(Dispositivo tblDispositivo2) {
        this.tblDispositivo2 = tblDispositivo2;
    }

    /**
     * Nome: getTbUsuario Recupera o valor do atributo 'tbUsuario'.
     * @return valor do atributo 'tbUsuario'
     * @see
     */
    public Usuario getTbUsuario() {
        return this.tbUsuario;
    }

    /**
     * Nome: setTbUsuario Registra o valor do atributo 'tbUsuario'.
     * @param tbUsuario valor do atributo tb usuario
     * @see
     */
    public void setTbUsuario(Usuario tbUsuario) {
        this.tbUsuario = tbUsuario;
    }

    /**
     * Nome: getTblTratamentos Recupera o valor do atributo 'tblTratamentos'.
     * @return valor do atributo 'tblTratamentos'
     * @see
     */
    public List<Tratamento> getTblTratamentos() {
        return this.tblTratamentos;
    }

    /**
     * Nome: setTblTratamentos Registra o valor do atributo 'tblTratamentos'.
     * @param tblTratamentos valor do atributo tbl tratamentos
     * @see
     */
    public void setTblTratamentos(List<Tratamento> tblTratamentos) {
        this.tblTratamentos = tblTratamentos;
    }

}