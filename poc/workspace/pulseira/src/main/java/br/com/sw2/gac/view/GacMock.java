package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sw2.gac.vo.ArquivoVO;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.SmsPadraoVO;
import br.com.sw2.gac.vo.TratamentoVO;
import br.com.sw2.gac.vo.UsuarioVO;

// TODO: Auto-generated Javadoc
/**
 * <b>Descrição: Mock para prover dados ficticios a poc</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public abstract class GacMock {

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
        doenca.setIdDoenca(3);
        doenca.setNomeDoenca("Doença 3");
        lista.add(doenca);

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
        item.setFrequenciaMinutos(10);
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
        item.setEndereco("Alameda dos Arapanés, 125");
        item.setBairro("Moema");
        item.setCidade("São Paulo");
        item.setEstado("SP");
        item.setCep("07456-000");
        item.setContratante(true);
        item.setDataNascimento(new Date());
        item.setSqaChamada(1);

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
        List<DispositivoVO> target = new ArrayList<DispositivoVO>();
        DispositivoVO dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(1);
        dispositivo.setDescricaoDispositivo("Dispositivo 1");
        list.add(dispositivo);

        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(2);
        dispositivo.setDescricaoDispositivo("Dispositivo 2");
        list.add(dispositivo);

        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(3);
        dispositivo.setDescricaoDispositivo("Dispositivo 3");
        list.add(dispositivo);

        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(4);
        dispositivo.setDescricaoDispositivo("Dispositivo 4");
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
        item.setDtInicioValidade(new Date(112, 9, 10));
        item.setDtFinalValidade(new Date(113, 9, 9));
        item.setNomeContratante("Carlos Luciano de Souza");
        item.setCpfContratante("123.456.789-00");
        lista.add(item);
        item = new ContratoVO();
        item.setNumeroContrato("0345/2012");
        item.setDtInicioValidade(new Date(112, 10, 14));
        item.setDtFinalValidade(new Date(113, 10, 13));
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
        sms.setIdSms(3);
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
        arq.setDataEnvio(new Date(112, 06, 17));
        arq.setStatus("Processado");
        arq.setCaminho("/temp/planilha.txt");
        lista.add(arq);

        return lista;

    }

    /**
     * Nome: getListaUsuarios
     * Recupera o valor do atributo 'listaUsuarios'.
     *
     * @return valor do atributo 'listaUsuarios'
     * @see
     */
    public static List<UsuarioVO> getListaUsuarios() {
        List<UsuarioVO> lista = new ArrayList<UsuarioVO>();
        UsuarioVO item = new UsuarioVO();
        item.setIdPerfil(1);
        item.setLogin("Admin");
        item.setSenha("admin");
        lista.add(item);

        return lista;
    }
}
