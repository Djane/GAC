package br.com.sw2.test.contratos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.vo.ClienteVO;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.EnderecoVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.HorarioVO;
import br.com.sw2.gac.vo.PacoteServicoVO;
import br.com.sw2.gac.vo.TratamentoVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratoTest {

    /** Atributo contrato business. */
    private ContratoBusiness contratoBusiness = new ContratoBusiness();

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(DataBaseException.class);

    private ContratoVO contratoBase;

    @Before
    public void setup() {
        UsuarioVO usuario = new UsuarioVO();
        usuario.setLogin("admin");

        this.contratoBase = new ContratoVO();
        this.contratoBase.setCpfContratante("25776498864");
        this.contratoBase.setRgContratante("12112");
        this.contratoBase.setDtInicioValidade(new Date());
        this.contratoBase.setUsuario(usuario);
        this.contratoBase.setNomeContratante("Contratente");
        // Serviço contratado
        this.contratoBase.setPacoteServico(new PacoteServicoVO());
        this.contratoBase.getPacoteServico().setIdPacote(2);
        this.contratoBase.setDtProxAtual(new Date());
        // Dados do cliente
        ClienteVO cliente = new ClienteVO();
        cliente.setNome("Rogerio");
        cliente.setCpf("25776498864");
        cliente.setRg("251111");
        cliente.setSexo("1");
        cliente.setUsuario(usuario);
        cliente.setDataNascimento(new Date());

        // Define uma forma de contato com o cliente
        FormaContatoVO formaContato = new FormaContatoVO();
        formaContato.setTipoContato(TipoContato.TelefoneResidencial.getValue());
        formaContato.setTelefone("(11)44224588");
        cliente.setListaFormaContato(new ArrayList<FormaContatoVO>());
        cliente.getListaFormaContato().add(formaContato);
        formaContato = new FormaContatoVO();
        formaContato.setTipoContato(TipoContato.Email.getValue());
        formaContato.setEmail("teste@uol.com.br");
        cliente.getListaFormaContato().add(formaContato);

        // Dados do endereço do cliente
        cliente.setEndereco(new EnderecoVO());
        cliente.getEndereco().setEndereco("Rua Teffe, 100");
        cliente.getEndereco().setBairro("Sant Maria");
        cliente.getEndereco().setCep("09560-340");
        cliente.getEndereco().setCidade("São caetano");
        cliente.getEndereco().setUf("SP");

        // Central do cliente
        List<DispositivoVO> listaCentrais = new ArrayList<DispositivoVO>();
        DispositivoVO dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo("9876765342282");
        listaCentrais.add(dispositivo);
        // Pulseira do cliente
        List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo("9876765342281");
        listaDispositivos.add(dispositivo);
        cliente.setListaCentrais(listaCentrais);
        cliente.setListaDispositivos(listaDispositivos);

        // Doenças
        List<DoencaVO> listaDoenca = new ArrayList<DoencaVO>();
        DoencaVO doenca = new DoencaVO();
        doenca.setCodigoCID("CID3");
        listaDoenca.add(doenca);
        doenca = new DoencaVO();
        doenca.setCodigoCID("CID1");
        listaDoenca.add(doenca);
        cliente.setListaDoencas(listaDoenca);

        // Tratamentos
        List<TratamentoVO> listaTratamento = new ArrayList<TratamentoVO>();
        TratamentoVO tratamento = new TratamentoVO();
        tratamento.setDescricaoTratamento("Descricao 1");
        tratamento.setFrequencia(1);
        tratamento.setNomeTratamento("Tratamento 1");
        tratamento.setDataHoraInicial(new Date());
        List<HorarioVO> horarios = new ArrayList<HorarioVO>();
        horarios.add(new HorarioVO("11:11"));
        horarios.add(new HorarioVO("00:12"));
        tratamento.setListaHorarios(horarios);
        listaTratamento.add(tratamento);
        tratamento = new TratamentoVO();
        tratamento.setDescricaoTratamento("Descricao 2");
        tratamento.setFrequencia(2);
        tratamento.setNomeTratamento("Tratamento 2");
        horarios = new ArrayList<HorarioVO>();
        horarios.add(new HorarioVO("15:10"));
        horarios.add(new HorarioVO("08:15"));
        tratamento.setListaHorarios(horarios);
        listaTratamento.add(tratamento);

        // Contatos com o cliente
        List<ContatoVO> listaContatos = new ArrayList<ContatoVO>();
        ContatoVO contato = new ContatoVO();
        listaContatos.add(contato);
        contato.setNome("Rogerio Santos");
        contato.setContratante(true);
        FormaContatoVO formaContato2 = new FormaContatoVO();
        formaContato2.setTipoContato(TipoContato.TelefoneResidencial.getValue());
        formaContato2.setTelefone("(11)44224588");
        contato.getListaFormaContato().add(formaContato2);
        formaContato2 = new FormaContatoVO();
        formaContato2.setTipoContato(TipoContato.Email.getValue());
        formaContato2.setEmail("teste@uol.com.br");
        contato.getListaFormaContato().add(formaContato2);
        cliente.setListaContatos(listaContatos);
        cliente.setListaTratamentos(listaTratamento);
        this.contratoBase.setCliente(cliente);
    }

    /**
     * Nome: atualizarContrato Atualizar contratoBase.
     * @see
     */
    @Test
    public void atualizarContrato() {

        // ContratoVO gravado = this.contratoBusiness.gravarNovoContrato(contratoBase);

        // System.out.println(gravado.getNumeroContrato());
        ContratoVO vo = contratoBusiness.obterDadosContrato(96);
        vo.getCliente().getListaTratamentos().get(0).setCrud("U");
        TratamentoVO tratamento = new TratamentoVO();
        tratamento.setDescricaoTratamento("Descricao 1");
        tratamento.setFrequencia(1);
        tratamento.setNomeTratamento("Tratamento 1");
        tratamento.setDataHoraInicial(new Date());
        List<HorarioVO> horarios = new ArrayList<HorarioVO>();
        horarios.add(new HorarioVO("11:11"));
        horarios.get(0).setCrud("C");
        horarios.add(new HorarioVO("00:12"));
        horarios.get(1).setCrud("C");
        vo.getCliente().getListaTratamentos().get(1).setCrud("U");
        vo.getCliente().getListaTratamentos().get(1).getListaHorarios().addAll(horarios);
        logger.debug("OK");

        this.contratoBusiness.atualizarContrato(vo);

    }

    /**
     * Nome: recuperarDadosContrato Recuperar dados contratoBase.
     * @param numeroContrato the numero contrato
     * @see
     */
    public void recuperarDadosContrato(int numeroContrato) {
        ContratoVO vo = contratoBusiness.obterDadosContrato(numeroContrato);

        logger.debug("********** Obtendo dados do contrato " + numeroContrato);
        logger.debug("********** nomecontratante: " + vo.getNomeContratante());
        logger.debug("********** numeroContrato: " + vo.getNumeroContrato());
        logger.debug("********** cpfContratante: " + vo.getCpfContratante());
        logger.debug("********** rgContratante: " + vo.getRgContratante());
        logger.debug("********** dtInicioValidade: " + vo.getDtInicioValidade());
        logger.debug("********** dtFinalValidade: " + vo.getDtFinalValidade());
        logger.debug("********** Date dtSuspensao: " + vo.getDtSuspensao());
        logger.debug("********** Date prox. Aualização: " + vo.getDtProxAtual());
        logger.debug("********** Data de Nascimento: " + vo.getDtNascimentoContratante());
        logger.debug("********** Servico: " + vo.getPacoteServico().getIdPacote());

        logger.debug("********** Dados do cliente do contrato: " + vo.getCliente().getNome());
        logger.debug("********** Sexo: " + vo.getCliente().getSexo());
        logger.debug("********** Doenças");
        for (DoencaVO doenca : vo.getCliente().getListaDoencas()) {
            logger.debug("********** : " + doenca.getCodigoCID());
            logger.debug("********** : " + doenca.getTipoDoenca().getCdTipoDoenca());
        }

        for (DispositivoVO central : vo.getCliente().getListaCentrais()) {
            logger.debug("********** ID central: " + central.getIdDispositivo());
        }

        for (DispositivoVO dispositivo : vo.getCliente().getListaDispositivos()) {
            logger.debug("********** ID dispositivo: " + dispositivo.getIdDispositivo());
        }

        for (ContatoVO contato : vo.getCliente().getListaContatos()) {
            logger.debug("********** Nome do contato: " + contato.getNome());
            for (FormaContatoVO formaContato : contato.getListaFormaContato()) {
                logger.debug("********** Tipo Contato: " + formaContato.getTipoContato());
            }
        }

        for (TratamentoVO tratamento : vo.getCliente().getListaTratamentos()) {
            logger.debug("********** Tratamento: " + tratamento.getNomeTratamento());
            for (HorarioVO horario : tratamento.getListaHorarios()) {
                logger.debug("********** Horario tratamento: " + horario.getHorario());
            }
        }

        for (FormaContatoVO formaContato : vo.getCliente().getListaFormaContato()) {
            logger.debug("********** Tipo de contato: " + formaContato.getTipoContato());
        }

    }

    /**
     * Nome: inserirContrato Inserir contratoBase.
     * @see
     */
    public void inserirContrato() {

        ContatoVO contatos = (ContatoVO) CollectionUtils.findByAttribute(contratoBase.getCliente()
            .getListaContatos(), "contratante", true);
        try {
            ContratoVO gravado = this.contratoBusiness.gravarNovoContrato(contratoBase);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }

    }

    /**
     * Nome: excluirContrato Excluir contratoBase.
     * @see
     */
    public void excluirContrato() {
        ContratoBusiness business = new ContratoBusiness();
        ContratoVO c = new ContratoVO();

        // bu2.excluirContrato(c);
    }

    /*
     * @Test public void pesquisarCentral() { ContratoDAO dao = new ContratoDAO(); Cliente entity =
     * new Cliente(); entity.setDsEndereco("Rua Teffe 100"); //Obtem codigo da central do endereço
     * informado List<ClienteDispositivo> retorno = (List<ClienteDispositivo>)
     * dao.getListaCentraisPorEndereco(entity); for (ClienteDispositivo cd : retorno) {
     * List<Cliente> clientes =
     * dao.getListaClientesPorCentral(cd.getDispositivo().getIdDispositivo());
     * System.out.println(clientes.size()); } }
     */
}
