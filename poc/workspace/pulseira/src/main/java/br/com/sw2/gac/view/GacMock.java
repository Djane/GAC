package br.com.sw2.gac.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.sw2.gac.tools.MesesDoAno;
import br.com.sw2.gac.vo.ArquivoVO;
import br.com.sw2.gac.vo.CentralVO;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.PacoteServicoVO;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.SmsPadraoVO;
import br.com.sw2.gac.vo.TratamentoVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Mock para prover dados ficticios a poc</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public abstract class GacMock {

    private static final double BD_85_52 = 85.52;
    private static final double BD_558_74 = 558.74;
    private static final double BD_12548_12 = 12548.12;
    private static final int TRES = 3;
    private static final int ANO_ATUAL = 2012;

    private static Date getData(int ano, int mes, int dia) {

        Calendar data = new GregorianCalendar();
        data.set(ano, mes, dia);

        return data.getTime();
    }

    /**
     * Nome: getListaDoencas Recupera o valor do atributo 'listaDoencas'.
     * @return valor do atributo 'listaDoencas'
     * @see
     */
    public static List<DoencaVO> getListaDoencas() {

        List<DoencaVO> lista = new ArrayList<DoencaVO>();
        DoencaVO doenca = new DoencaVO();
        doenca.setIdDoenca(1);
        doenca.setNomeDoenca("Doença 1");
        lista.add(doenca);
        doenca = new DoencaVO();
        doenca.setIdDoenca(2);
        doenca.setNomeDoenca("Doença 2");
        lista.add(doenca);
        doenca = new DoencaVO();
        doenca.setIdDoenca(TRES);
        doenca.setNomeDoenca("Doença 3");
        lista.add(doenca);

        return lista;
    }

    /**
     * Nome: getListaCentral Recupera o valor do atributo 'listaCentral'.
     * @return valor do atributo 'listaCentral'
     * @see
     */
    public static List<CentralVO> getListaCentral() {

        List<CentralVO> lista = new ArrayList<CentralVO>();
        CentralVO central = new CentralVO();
        central.setIdCentral(1);
        central.setDescricaoCentral("Central 1");
        lista.add(central);
        central = new CentralVO();
        central.setIdCentral(2);
        central.setDescricaoCentral("Central 2");
        lista.add(central);
        central = new CentralVO();
        central.setIdCentral(TRES);
        central.setDescricaoCentral("Central 3");
        lista.add(central);

        return lista;
    }

    /**
     * Nome: getListaTratamentos Recupera o valor do atributo 'listaTratamentos'.
     * @return valor do atributo 'listaTratamentos'
     * @see
     */
    public static List<TratamentoVO> getListaTratamentos() {
        List<TratamentoVO> lista = new ArrayList<TratamentoVO>();
        TratamentoVO item = new TratamentoVO();
        item.setIdTratamento(1);
        item.setNomeTratamento("Tratamento 1");
        item.setDescricaoTratamento("Descrição do tratamento 1");
        item.setDataHoraInicial(new Date());
        lista.add(item);
        return lista;
    }

    /**
     * Nome: getListaContatos Recupera o valor do atributo 'listaContatos'.
     * @return valor do atributo 'listaContatos'
     * @see
     */
    public static List<ContatoVO> getListaContatos() {
        List<ContatoVO> lista = new ArrayList<ContatoVO>();
        ContatoVO item = new ContatoVO();
        item.setIdContato(1);
        item.setNome("Josue de Jesus Soares");
        item.setGrauParentesco("3");
        item.setEndereco("Alameda dos ArapanÃ©s, 125");
        item.setBairro("Moema");
        item.setCidade("São Paulo");
        item.setEstado("SP");
        item.setCep("07456-000");
        item.setContratante(true);
        item.setDataNascimento(new Date());
        item.setSqaChamada(1);
        lista.add(item);

        item = new ContatoVO();
        item.setIdContato(2);
        item.setNome("Janaina Se Oliveira");
        item.setGrauParentesco("1");
        item.setEndereco("Rua Silvia, 100");
        item.setBairro("Vila Gerty");
        item.setCidade("São Caetano do Sul");
        item.setEstado("SP");
        item.setCep("02993-000");
        item.setContratante(true);
        item.setDataNascimento(new Date());
        item.setSqaChamada(2);
        lista.add(item);

        return lista;
    }

    /**
     * Nome: getListaDispositivos Recupera o valor do atributo 'listaDispositivos'.
     * @return valor do atributo 'listaDispositivos'
     * @see
     */
    public static List<DispositivoVO> getListaDispositivos() {
        List<DispositivoVO> list = new ArrayList<DispositivoVO>();
        DispositivoVO dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(1);
        dispositivo.setDescricaoDispositivo("Dispositivo 1");
        dispositivo.setEstado("1");
        dispositivo.setLocal("1");
        dispositivo.setTipoDispositivo("1");
        dispositivo.setDataFabricacao(getData(ANO_ATUAL, MesesDoAno.Janeiro.getValue(), 1));
        dispositivo.setDataEntrada(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        dispositivo
                .setDataProximaManutencao(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        dispositivo.setDataSucata(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        list.add(dispositivo);

        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(2);
        dispositivo.setDescricaoDispositivo("Dispositivo 2");
        dispositivo.setEstado("2");
        dispositivo.setLocal("2");
        dispositivo.setTipoDispositivo("2");
        dispositivo.setDataFabricacao(getData(ANO_ATUAL, MesesDoAno.Janeiro.getValue(), 1));
        dispositivo.setDataEntrada(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        dispositivo
                .setDataProximaManutencao(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        dispositivo.setDataSucata(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        list.add(dispositivo);

        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(TRES);
        dispositivo.setDescricaoDispositivo("Dispositivo 3");
        dispositivo.setEstado("3");
        dispositivo.setLocal("3");
        dispositivo.setTipoDispositivo("3");
        dispositivo.setDataFabricacao(getData(ANO_ATUAL, MesesDoAno.Janeiro.getValue(), 1));
        dispositivo.setDataEntrada(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        dispositivo
                .setDataProximaManutencao(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        dispositivo.setDataSucata(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        list.add(dispositivo);

        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(TRES + 1);
        dispositivo.setDescricaoDispositivo("Dispositivo 4");
        dispositivo.setEstado("4");
        dispositivo.setLocal("4");
        dispositivo.setTipoDispositivo("4");
        dispositivo.setDataFabricacao(getData(ANO_ATUAL, MesesDoAno.Janeiro.getValue(), 1));
        dispositivo.setDataEntrada(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        dispositivo
                .setDataProximaManutencao(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        dispositivo.setDataSucata(getData(ANO_ATUAL, MesesDoAno.Fevereiro.getValue(), 1));
        list.add(dispositivo);

        return list;
    }

    /**
     * Nome: getListaContratos Recupera o valor do atributo 'listaContratos'.
     * @return valor do atributo 'listaContratos'
     * @see
     */
    public static List<ContratoVO> getListaContratos() {
        List<ContratoVO> lista = new ArrayList<ContratoVO>();
        ContratoVO item = new ContratoVO();
        item.setNumeroContrato("0127/2012");
        item.setDtInicioValidade(getData(ANO_ATUAL, MesesDoAno.Setembro.getValue(), 1));
        item.setDtFinalValidade(getData(ANO_ATUAL + 1, MesesDoAno.Setembro.getValue(), 1));
        item.setNomeContratante("Carlos Luciano de Souza");
        item.setCpfContratante("123.456.789-00");
        lista.add(item);
        item = new ContratoVO();
        item.setNumeroContrato("0345/2012");
        item.setDtInicioValidade(getData(ANO_ATUAL, MesesDoAno.Setembro.getValue(), 1));
        item.setDtFinalValidade(getData(ANO_ATUAL + 1, MesesDoAno.Setembro.getValue(), 1));
        item.setNomeContratante("Juliana Isabel Mendes");
        item.setCpfContratante("987.123.456-87");
        lista.add(item);

        return lista;
    }

    /**
     * Nome: getListaMensagensSMS Recupera o valor do atributo 'listaMensagensSMS'.
     * @return valor do atributo 'listaMensagensSMS'
     * @see
     */
    public static List<SmsPadraoVO> getListaMensagensSMS() {
        List<SmsPadraoVO> lista = new ArrayList<SmsPadraoVO>();
        SmsPadraoVO sms = new SmsPadraoVO();

        sms = new SmsPadraoVO();
        sms.setIdSms(1);
        sms.setTitulo("Titulo Mensagem 1");
        sms.setDescricao("Desccricao mensagem 2");
        lista.add(sms);

        sms = new SmsPadraoVO();
        sms.setIdSms(2);
        sms.setTitulo("Titulo Mensagem 2");
        sms.setDescricao("Desccricao mensagem 2");
        lista.add(sms);

        sms = new SmsPadraoVO();
        sms.setIdSms(TRES);
        sms.setTitulo("Titulo Mensagem 3");
        sms.setDescricao("Desccricao mensagem 3");
        lista.add(sms);

        return lista;
    }

    /**
     * Nome: getListaArquivosUpload Recupera o valor do atributo 'listaArquivosUpload'.
     * @return valor do atributo 'listaArquivosUpload'
     * @see
     */
    public static List<ArquivoVO> getListaArquivosUpload() {

        List<ArquivoVO> lista = new ArrayList<ArquivoVO>();
        ArquivoVO arq = new ArquivoVO();
        arq.setDataEnvio(getData(ANO_ATUAL, MesesDoAno.Junho.getValue(), 1));
        arq.setStatus("Processado");
        arq.setCaminho("/temp/planilha.txt");
        lista.add(arq);

        return lista;

    }

    /**
     * Nome: getListaUsuarios Recupera o valor do atributo 'listaUsuarios'.
     * @return valor do atributo 'listaUsuarios'
     * @see
     */
    public static List<UsuarioVO> getListaUsuarios() {
        List<UsuarioVO> lista = new ArrayList<UsuarioVO>();
        UsuarioVO item = new UsuarioVO();
        PerfilVO perfil = new PerfilVO();
        perfil.setIdPerfil(1);
        item.setPerfil(perfil);
        item.setLogin("admin");
        item.setSenha("admin");
        lista.add(item);

        return lista;
    }

    /**
     * Nome: getListaFormaContatos Recupera o valor do atributo 'listaFormaContatos'.
     * @return valor do atributo 'listaFormaContatos'
     * @see
     */
    public static List<FormaContatoVO> getListaFormaContatos() {
        List<FormaContatoVO> lista = new ArrayList<FormaContatoVO>();
        FormaContatoVO item = new FormaContatoVO();
        item.setEmail("emai@gmail.com");
        item.setTelefone("(11)98145-4434");
        item.setTipoContato("1");
        lista.add(item);
        return lista;

    }

    /**
     * Nome: getListaPacotesServico Recupera o valor do atributo 'listaPacotesServico'.
     * @return valor do atributo 'listaPacotesServico'
     * @see
     */
    public static List<PacoteServicoVO> getListaPacotesServico() {

        List<PacoteServicoVO> lista = new ArrayList<PacoteServicoVO>();
        PacoteServicoVO pacote = new PacoteServicoVO();
        pacote.setIdPacote(1);
        pacote.setTitulo("Titulo pacote 1");
        pacote.setDescricao("Descrição pacote 1");
        pacote.setPreco(new BigDecimal(BD_12548_12));
        lista.add(pacote);
        pacote = new PacoteServicoVO();
        pacote.setIdPacote(2);
        pacote.setTitulo("Titulo pacote 2");
        pacote.setDescricao("Descrição pacote 2");
        pacote.setPreco(new BigDecimal(BD_558_74));
        lista.add(pacote);
        pacote = new PacoteServicoVO();
        pacote.setIdPacote(TRES);
        pacote.setTitulo("Titulo pacote 3");
        pacote.setDescricao("Descrição pacote 3");
        pacote.setPreco(new BigDecimal(BD_85_52));
        lista.add(pacote);
        return lista;

    }

    /**
     * Nome: getContrato Recupera o valor do atributo 'contrato'.
     * @return valor do atributo 'contrato'
     * @see
     */
    public static ContratoVO getContrato() {

        ContratoVO contrato = new ContratoVO();
        contrato.setNumeroContrato("0127/2012");

        contrato.setDtInicioValidade(getData(ANO_ATUAL, MesesDoAno.Outubro.getValue(), 1));
        contrato.setDtFinalValidade(getData(ANO_ATUAL + 1, MesesDoAno.Setembro.getValue(), 1));
        contrato.setNomeContratante("Carlos Luciano de Souza");
        contrato.setCpfContratante("123.456.789-00");

        // DEFINE CONTATOS PARA ESTE CONTRATO
        contrato.setListaContatos(getListaContatos());
        List<FormaContatoVO> listaFormaContato = new ArrayList<FormaContatoVO>();
        FormaContatoVO formaContato = new FormaContatoVO();
        formaContato.setIdContato(1);
        formaContato.setTelefone("(11)98282-3939");
        formaContato.setTipoContato("1");
        listaFormaContato.add(formaContato);
        formaContato = new FormaContatoVO();
        formaContato.setIdContato(2);
        formaContato.setTelefone("(11) 4567-0039");
        formaContato.setTipoContato("2");
        listaFormaContato.add(formaContato);
        formaContato = new FormaContatoVO();
        formaContato.setIdContato(TRES);
        formaContato.setEmail("email@gmail.com");
        formaContato.setTipoContato("4");
        listaFormaContato.add(formaContato);
        contrato.getListaContatos().get(0).setListaFormaContato(listaFormaContato);
        listaFormaContato = new ArrayList<FormaContatoVO>();
        formaContato = new FormaContatoVO();
        formaContato.setIdContato(1);
        formaContato.setTelefone("(11)98832-1554");
        formaContato.setTipoContato("1");
        listaFormaContato.add(formaContato);
        contrato.getListaContatos().get(1).setListaFormaContato(listaFormaContato);

        // DEFINE LISTA DE DOENï¿½AS PARA O PACIENTE DESTE CONTRATO
        List<DoencaVO> listaDoencas = new ArrayList<DoencaVO>();
        DoencaVO doenca = new DoencaVO();
        doenca.setIdDoenca(1);
        doenca.setNomeDoenca("Osteoporose");
        listaDoencas.add(doenca);
        doenca = new DoencaVO();
        doenca.setIdDoenca(2);
        doenca.setNomeDoenca("Hipertensão Arterial");
        listaDoencas.add(doenca);

        contrato.setListaDoencas(listaDoencas);

        return contrato;
    }

    /**
     * Nome: getHistoricoOcorrencias Recupera o valor do atributo 'historicoOcorrencias'.
     * @return valor do atributo 'historicoOcorrencias'
     * @see
     */
    public static List<OcorrenciaVO> getHistoricoOcorrencias() {

        UsuarioVO usuario = new UsuarioVO();
        usuario.setLogin("admin");

        OcorrenciaVO ocorrencia;
        List<OcorrenciaVO> lista = new ArrayList<OcorrenciaVO>();

        ocorrencia = new OcorrenciaVO();
        ocorrencia.setIdOcorrencia(1);
        ocorrencia.setUsuario(usuario);
        ocorrencia.setTpOcorrencia(1);
        ocorrencia.setDtaHoraAbertura(getData(ANO_ATUAL, MesesDoAno.Junho.getValue(), 1));
        ocorrencia.setDtaHoraFechamento(getData(ANO_ATUAL + 1, MesesDoAno.Junho.getValue(), 1));
        ocorrencia.setConclusao("Texto de conclusão  da ocorrÃªncia 1");
        ocorrencia.setReclOcorrencia("Texto para descrição da ocorrÃªncia 1");
        lista.add(ocorrencia);

        ocorrencia = new OcorrenciaVO();
        ocorrencia.setIdOcorrencia(2);
        ocorrencia.setUsuario(usuario);
        ocorrencia.setTpOcorrencia(2);
        ocorrencia.setDtaHoraAbertura(getData(ANO_ATUAL, MesesDoAno.Marco.getValue(), 1));
        ocorrencia.setDtaHoraFechamento(getData(ANO_ATUAL + 1, MesesDoAno.Marco.getValue(), 1));
        ocorrencia.setConclusao("Texto de conclusão  da ocorrÃªncia 2");
        ocorrencia.setReclOcorrencia("Texto para descrição da ocorrÃªncia 2");
        lista.add(ocorrencia);

        return lista;

    }
}
