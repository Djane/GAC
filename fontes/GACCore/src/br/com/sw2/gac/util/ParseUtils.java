package br.com.sw2.gac.util;

import java.util.ArrayList;
import java.util.List;

import br.com.sw2.gac.modelo.Acionamento;
import br.com.sw2.gac.modelo.AplicaMedico;
import br.com.sw2.gac.modelo.AplicaMedicoPK;
import br.com.sw2.gac.modelo.CID;
import br.com.sw2.gac.modelo.Cliente;
import br.com.sw2.gac.modelo.ClienteDispositivo;
import br.com.sw2.gac.modelo.ClienteDispositivoPK;
import br.com.sw2.gac.modelo.Contato;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.FormaComunica;
import br.com.sw2.gac.modelo.Ocorrencia;
import br.com.sw2.gac.modelo.PacoteServico;
import br.com.sw2.gac.modelo.Parametro;
import br.com.sw2.gac.modelo.SMS;
import br.com.sw2.gac.modelo.Script;
import br.com.sw2.gac.modelo.Tratamento;
import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.vo.AcionamentoVO;
import br.com.sw2.gac.vo.ClienteVO;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratanteVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.EnderecoVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.HorarioVO;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.PacoteServicoVO;
import br.com.sw2.gac.vo.ParametroVO;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.ScriptVO;
import br.com.sw2.gac.vo.SmsVO;
import br.com.sw2.gac.vo.TipoDoencaVO;
import br.com.sw2.gac.vo.TipoOcorrenciaVO;
import br.com.sw2.gac.vo.TratamentoVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Classe util para conversão de Entity em VO e vice-versa</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class ParseUtils {

    /**
     * Construtor Padrao Instancia um novo objeto ParseUtils.
     */
    private ParseUtils() {
        super();
    }

    /**
     * Nome: parse Converte o objeto UsuarioVO em uma entity Usuario.
     * @param vo the vo
     * @return usuario
     * @see
     */
    public static Usuario parse(UsuarioVO vo) {
        Usuario entity = null;
        if (null != vo) {
            String senhaCriptografada = null;
            if (null != vo.getSenha()) {
                senhaCriptografada = StringUtil.encriptarTexto(vo.getSenha());
            }
            entity = new Usuario();
            entity.setSenha(senhaCriptografada);
            entity.setLogin(vo.getLogin());
            entity.setNmUsuario(vo.getNomeUsuario());
            entity.setNmRamal(vo.getRamal());
            entity.setNmRegistro(vo.getRegistroAtendente());
            if (null != vo.getPerfil() && null != vo.getPerfil().getIdPerfil()) {
                entity.setCdPerfil(vo.getPerfil().getIdPerfil());
            }
        }
        return entity;
    }

    /**
     * Nome: parse Converte uma entity Usuario em um objeto UsuarioVO.
     * @param entity the entity
     * @return usuario vo
     * @see
     */
    public static UsuarioVO parse(Usuario entity) {
        UsuarioVO vo = null;
        if (entity != null) {
            vo = new UsuarioVO();
            vo.setSenha(entity.getSenha());
            vo.setLogin(entity.getLogin());
            vo.setNomeUsuario(entity.getNmUsuario());
            PerfilVO perfil = new PerfilVO();
            perfil.setIdPerfil(entity.getCdPerfil());
            vo.setPerfil(perfil);
            vo.setRamal(entity.getNmRamal());
            vo.setRegistroAtendente(entity.getNmRegistro());
        }
        return vo;
    }

    /**
     * Nome: Converte um vo de Contrato em uma entity.
     * @param vo ContratoVO
     * @return Contrato entity
     * @see
     */
    public static Contrato parse(ContratoVO vo) {
        Contrato entity = new Contrato();
        entity.setNmContrato(vo.getNumeroContrato());
        entity.setNmRGContratante(vo.getContratante().getRgContratante());
        entity.setNmNomeContratante(vo.getContratante().getNomeContratante());
        entity.setNmCPFContratante(vo.getContratante().getCpfContratante());
        entity.setDtFinalValidade(vo.getDtFinalValidade());
        entity.setDtInicioValidade(vo.getDtInicioValidade());
        entity.setDtSuspensao(vo.getDtSuspensao());
        PacoteServico pacoteServico = new PacoteServico();
        pacoteServico.setIdServico(vo.getPacoteServico().getIdPacote());
        entity.setIdServico(pacoteServico);
        entity.setLogin(parse(vo.getUsuario()));
        entity.setDtProxAtual(vo.getDtProxAtual());
        if (null != vo.getCliente()) {
            Cliente cliente = parse(vo.getCliente());
            cliente.setNmContrato(entity);
            entity.setCliente(cliente);
        }
        return entity;
    }

    /**
     * Nome: Converte Entity em ContratoVO.
     * @param entity Contrato
     * @return ContratoVO contrato
     * @see
     */
    public static ContratoVO parse(Contrato entity) {

        ContratoVO vo = null;
        if (null != entity) {
            vo = new ContratoVO();
            vo.setNumeroContrato(entity.getNmContrato());
            vo.setDtFinalValidade(entity.getDtFinalValidade());
            vo.setDtInicioValidade(entity.getDtInicioValidade());
            vo.setDtSuspensao(entity.getDtSuspensao());
            vo.setDtProxAtual(entity.getDtProxAtual());
            vo.setPacoteServico(new PacoteServicoVO());
            vo.getPacoteServico().setIdPacote(entity.getIdServico().getIdServico());
            vo.getPacoteServico().setDescricao(entity.getIdServico().getDsServico());
            vo.getPacoteServico().setTitulo(entity.getIdServico().getDsTitulo());
            vo.getPacoteServico().setPreco(entity.getIdServico().getPrcMensal());
            vo.getPacoteServico()
                .setDataInicioValidade(entity.getIdServico().getDtInicioValidade());
            vo.setUsuario(parse(entity.getLogin()));
            vo.setCliente(parse(entity.getCliente()));
            vo.setContratante(new ContratanteVO());
            vo.getContratante().setCpfContratante(entity.getNmCPFContratante());
            vo.getContratante().setRgContratante(entity.getNmRGContratante());
            vo.getContratante().setNomeContratante(entity.getNmNomeContratante());
            if (null != vo.getCliente()) {
                ContatoVO contatoContratante = (ContatoVO) CollectionUtils.findByAttribute(vo
                    .getCliente().getListaContatos(), "contratante", true);
                vo.getContratante().setContato(contatoContratante);
            }

        }
        return vo;
    }

    /**
     * Nome: parse Parses the.
     * @param entity the entity
     * @return cliente vo
     * @see
     */
    public static ClienteVO parse(Cliente entity) {
        ClienteVO vo = null;
        if (null != entity) {
            vo = new ClienteVO();
            vo.setCpf(entity.getNmCPFCliente());
            vo.setRg(entity.getNrRG());
            vo.setNome(entity.getNmCliente());
            vo.getEndereco().setEndereco(entity.getDsEndereco());
            vo.getEndereco().setBairro(entity.getDsBairro());
            vo.getEndereco().setCidade(entity.getDsCidade());
            vo.getEndereco().setCep(entity.getDsCEP());
            vo.getEndereco().setUf(entity.getDsEstado());
            vo.setDataNascimento(entity.getDtNascimento());
            vo.setSexo(entity.getTpSexo().toString());
            vo.setNecessidadeEspecial(entity.getNmNecessidadeEspecial());
            vo.setPlanoSaude(entity.getNmPlanoSaude());
            vo.setCobertura(entity.getDsCobertura());
            vo.setUsuario(parse(entity.getLogin()));

            List<FormaContatoVO> listaFormaContatoCliente = new ArrayList<FormaContatoVO>();
            for (FormaComunica formaComunica : entity.getFormaComunicaList()) {
                if (null == formaComunica.getIdContato()) {
                    listaFormaContatoCliente.add(parse(formaComunica));
                }
            }
            vo.setListaFormaContato(listaFormaContatoCliente);

            // Lista de doencas do cliente
            List<DoencaVO> listaDoencas = new ArrayList<DoencaVO>();
            if (!CollectionUtils.isEmptyOrNull(entity.getCIDList())) {
                for (CID cidEntity : entity.getCIDList()) {
                    listaDoencas.add(parse(cidEntity));
                }
            }
            vo.setListaDoencas(listaDoencas);

            // Obtem os dispositivos do cliente
            List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
            List<DispositivoVO> listaCentrais = new ArrayList<DispositivoVO>();
            for (ClienteDispositivo clienteDispositivoentity : entity.getClienteDispositivoList()) {
                if (clienteDispositivoentity.getDispositivo().getTpDispositivo().intValue() == TipoDispositivo.CentralEletronica
                    .getValue().intValue()) {
                    listaCentrais.add(ObjectUtils.parse(clienteDispositivoentity.getDispositivo()));
                } else {
                    listaDispositivos.add(ObjectUtils.parse(clienteDispositivoentity
                        .getDispositivo()));
                }
            }
            vo.setListaCentrais(listaCentrais);
            vo.setListaDispositivos(listaDispositivos);

            // Seta contato do cliente
            List<ContatoVO> listaContatos = new ArrayList<ContatoVO>();
            for (Contato contatoEntity : entity.getContatoList()) {
                listaContatos.add(parse(contatoEntity));
            }
            vo.setListaContatos(listaContatos);

            // Lista de tratamentos
            List<TratamentoVO> listaTratamentos = new ArrayList<TratamentoVO>();
            for (Tratamento tratamentoEntity : entity.getTratamentoList()) {
                listaTratamentos.add(parse(tratamentoEntity));
            }
            vo.setListaTratamentos(listaTratamentos);

        }
        return vo;
    }

    /**
     * Nome: parse Converte um Vo de cliente em uma entity.
     * @param vo the vo
     * @return cliente
     * @see
     */
    public static Cliente parse(ClienteVO vo) {

        Cliente entity = null;
        if (null != vo) {
            entity = new Cliente();
            entity.setNmCPFCliente(vo.getCpf());
            entity.setNrRG(vo.getRg());
            entity.setNmCliente(vo.getNome());
            entity.setDsEndereco(vo.getEndereco().getEndereco());
            entity.setDsBairro(vo.getEndereco().getBairro());
            entity.setDsCidade(vo.getEndereco().getCidade());
            entity.setDsCEP(vo.getEndereco().getCep());
            entity.setDsEstado(vo.getEndereco().getUf());
            entity.setDtNascimento(vo.getDataNascimento());
            entity.setTpSexo(Integer.parseInt(vo.getSexo()));
            entity.setNmNecessidadeEspecial(vo.getNecessidadeEspecial());
            entity.setNmPlanoSaude(vo.getPlanoSaude());
            entity.setDsCobertura(vo.getCobertura());
            entity.setLogin(parse(vo.getUsuario()));

            // Formas de contato com o cliente
            entity.setFormaComunicaList(parseToListFormaComunicacaoEntity(
                vo.getListaFormaContato(), entity));

            // Lista de contatos do cliente
            List<Contato> listaContatos = new ArrayList<Contato>();
            for (ContatoVO contatoVO : vo.getListaContatos()) {
                Contato contatoEntity = parse(contatoVO);
                contatoEntity.setLogin(entity.getLogin());
                contatoEntity.setNmCPFCliente(entity);
                contatoEntity.setFormaComunicaList(parseToListFormaComunicacaoEntity(
                    contatoVO.getListaFormaContato(), entity));
                listaContatos.add(contatoEntity);
            }
            entity.setContatoList(listaContatos);

            // Lsita de dispositivo do cliente
            List<ClienteDispositivo> listaClienteDispositivo = new ArrayList<ClienteDispositivo>();
            for (DispositivoVO item : vo.getListaDispositivos()) {
                ClienteDispositivo cd = new ClienteDispositivo();
                cd.setCliente(entity);
                ClienteDispositivoPK cdpk = new ClienteDispositivoPK();
                cdpk.setIdDispositivo(item.getIdDispositivo());
                cdpk.setNmCPFCliente(entity.getNmCPFCliente());
                cd.setClienteDispositivoPK(cdpk);
                Dispositivo dispEntity = new Dispositivo();
                dispEntity.setIdDispositivo(item.getIdDispositivo());
                cd.setDispositivo(dispEntity);
                listaClienteDispositivo.add(cd);

            }
            // Lista de centrais do cliente
            for (DispositivoVO item : vo.getListaCentrais()) {
                ClienteDispositivo cd = new ClienteDispositivo();
                cd.setCliente(entity);
                ClienteDispositivoPK cdpk = new ClienteDispositivoPK();
                cdpk.setIdDispositivo(item.getIdDispositivo());
                cdpk.setNmCPFCliente(entity.getNmCPFCliente());
                cd.setClienteDispositivoPK(cdpk);
                Dispositivo dispEntity = new Dispositivo();
                dispEntity.setTpDispositivo(TipoDispositivo.CentralEletronica.getValue());
                dispEntity.setIdDispositivo(item.getIdDispositivo());
                cd.setDispositivo(dispEntity);
                listaClienteDispositivo.add(cd);
            }

            // Lista de doenças
            List<CID> listaDoencasCliente = new ArrayList<CID>();
            for (DoencaVO item : vo.getListaDoencas()) {
                CID doencaEntity = new CID();
                doencaEntity.setCdCID(item.getCodigoCID());
                listaDoencasCliente.add(doencaEntity);
            }

            // Lista de tratamentos
            List<Tratamento> listaTratamento = new ArrayList<Tratamento>();
            if (!CollectionUtils.isEmptyOrNull(vo.getListaTratamentos())) {
                for (TratamentoVO item : vo.getListaTratamentos()) {
                    Tratamento tratamento = parse(item, entity.getNmCPFCliente());
                    tratamento.setCliente(entity);
                    listaTratamento.add(tratamento);
                }
            }
            entity.setCIDList(listaDoencasCliente);
            entity.setClienteDispositivoList(listaClienteDispositivo);
            entity.setTratamentoList(listaTratamento);
        }
        return entity;
    }

    /**
     * Nome: parseListFormaComunica Converte uma List de FormaComunicacaoVO para uma lista de
     * FormaComunica.
     * @param list the list
     * @param entity the entity
     * @return list
     * @see
     */
    private static List<FormaComunica> parseToListFormaComunicacaoEntity(List<FormaContatoVO> list,
        Cliente entity) {
        List<FormaComunica> listFormaComunica = new ArrayList<FormaComunica>();
        for (FormaContatoVO item : list) {
            FormaComunica formaComunica = parse(item);
            formaComunica.setNmCPFCliente(entity);
            listFormaComunica.add(formaComunica);
        }
        return listFormaComunica;
    }

    /**
     * Nome: parse Parses the.
     * @param vo the vo
     * @param nmCPFCliente the nm cpf cliente
     * @return tratamento
     * @see
     */
    public static Tratamento parse(TratamentoVO vo, String nmCPFCliente) {
        Tratamento entity = null;
        if (null != vo) {
            entity = new Tratamento();
            entity.setNomeTrata(vo.getNomeTratamento());
            entity.setDescrTrata(vo.getDescricaoTratamento());
            entity.setTpFrequencia(vo.getFrequencia());
            entity.setHoraInicial(vo.getDataHoraInicial());
            entity.setIdTratamento(vo.getIdTratamento());
            Cliente cliente = new Cliente();
            cliente.setNmCPFCliente(nmCPFCliente);
            entity.setCliente(cliente);
            entity.setAplicaMedicoList(new ArrayList<AplicaMedico>());
            if (!CollectionUtils.isEmptyOrNull(vo.getListaHorarios())) {
                for (HorarioVO horario : vo.getListaHorarios()) {
                    AplicaMedico aplicaMedico = new AplicaMedico();
                    AplicaMedicoPK aplicaMedicopk = new AplicaMedicoPK();
                    aplicaMedicopk.setHrAplicacao(horario.getHorario());
                    aplicaMedicopk.setIdTratamento(entity.getIdTratamento());
                    aplicaMedicopk.setNmCPFCliente(nmCPFCliente);
                    aplicaMedicopk.setIdTratamento(vo.getIdTratamento());
                    aplicaMedico.setAplicaMedicoPK(aplicaMedicopk);
                    entity.getAplicaMedicoList().add(aplicaMedico);
                }
            }
        }
        return entity;
    }

    /**
     * Nome: parse Parses the.
     * @param entity the entity
     * @return tratamento vo
     * @see
     */
    public static TratamentoVO parse(Tratamento entity) {
        TratamentoVO vo = null;
        if (null != entity) {
            vo = new TratamentoVO();
            vo.setDataHoraInicial(entity.getHoraInicial());
            vo.setDescricaoTratamento(entity.getDescrTrata());
            vo.setFrequencia(entity.getTpFrequencia());
            vo.setIdTratamento(entity.getIdTratamento());
            vo.setNomeTratamento(entity.getNomeTrata());

            List<HorarioVO> listaHorarios = new ArrayList<HorarioVO>();
            for (AplicaMedico aplicaMedicoEntity : entity.getAplicaMedicoList()) {
                HorarioVO horario = new HorarioVO();
                horario.setHorario(aplicaMedicoEntity.getAplicaMedicoPK().getHrAplicacao());
                listaHorarios.add(horario);
            }
            vo.setListaHorarios(listaHorarios);

        }
        return vo;
    }

    /**
     * Nome: parse Parses the.
     * @param entity the entity
     * @return contato vo
     * @see
     */
    public static ContatoVO parse(Contato entity) {
        ContatoVO vo = null;
        if (null != entity) {
            vo = new ContatoVO();
            if (entity.getContratante().equals("1")) {
                vo.setContratante(true);
            } else {
                vo.setContratante(false);
            }
            vo.setCpfPaciente(entity.getNmCPFCliente().getNmCPFCliente());
            vo.setDataNascimento(entity.getDtaNascimento());
            vo.setEndereco(new EnderecoVO());
            vo.getEndereco().setBairro(entity.getBaiContato());
            vo.getEndereco().setCep(entity.getCepContato());
            vo.getEndereco().setCidade(entity.getCidContato());
            vo.getEndereco().setEndereco(entity.getEndContato());
            vo.getEndereco().setUf(entity.getEstadoContato());
            vo.setGrauParentesco(entity.getGrauParentesco());
            vo.setIdContato(entity.getIdContato());
            vo.setLogin(entity.getLogin().getLogin());
            vo.setNome(entity.getNomeContato());
            vo.setSqaChamada(entity.getSqaChamada());
            List<FormaContatoVO> listaFormaContato = new ArrayList<FormaContatoVO>();
            for (FormaComunica formaComunica : entity.getFormaComunicaList()) {
                if (null != formaComunica.getIdContato()) {
                    listaFormaContato.add(parse(formaComunica));
                }
            }
            vo.setListaFormaContato(listaFormaContato);

        }
        return vo;
    }

    /**
     * Nome: parse parse Converte uma entity Contato em um objeto ContatoVO.
     * @param vo the vo
     * @return contato
     * @see
     */
    public static Contato parse(ContatoVO vo) {
        Contato entity = new Contato();
        entity.setNomeContato(vo.getNome());
        entity.setGrauParentesco(vo.getGrauParentesco());
        entity.setDtaNascimento(vo.getDataNascimento());
        if (null != vo.getEndereco()) {
            entity.setEndContato(vo.getEndereco().getEndereco());
            entity.setBaiContato(vo.getEndereco().getBairro());
            entity.setCidContato(vo.getEndereco().getCidade());
            entity.setCepContato(vo.getEndereco().getCep());
            entity.setEstadoContato(vo.getEndereco().getUf());
        }
        if (vo.isContratante()) {
            entity.setContratante("1");
        } else {
            entity.setContratante("0");
        }
        entity.setSqaChamada(vo.getSqaChamada());

        if (!CollectionUtils.isEmptyOrNull(vo.getListaFormaContato())) {
            List<FormaComunica> listaFormaComunica = new ArrayList<FormaComunica>();
            for (FormaContatoVO item : vo.getListaFormaContato()) {
                FormaComunica formaComunica = parse(item);
                listaFormaComunica.add(formaComunica);
            }
            entity.setFormaComunicaList(listaFormaComunica);
        }

        if (null != vo.getLogin()) {
            Usuario login = new Usuario();
            login.setLogin(vo.getLogin());
            entity.setLogin(login);
        }

        entity.setIdContato(vo.getIdContato());
        return entity;
    }

    /**
     * Nome: parse Converte um VO de FormaContato em uma entity.
     * @param vo the vo
     * @return forma comunica
     * @see
     */
    public static FormaComunica parse(FormaContatoVO vo) {
        FormaComunica entity = new FormaComunica();
        if (!StringUtil.isEmpty(vo.getTelefone(), true)) {
            entity.setFoneContato(vo.getTelefone().replace("-", "").replace("(", "")
                .replace(")", ""));
        }
        entity.setIdFormaComunica(vo.getIdFormaContato());
        entity.setMailContato(vo.getEmail());
        entity.setTpContato(vo.getTipoContato());

        return entity;

    }

    /**
     * Nome: parse Parses the.
     * @param entity the entity
     * @return forma contato vo
     * @see
     */
    public static FormaContatoVO parse(FormaComunica entity) {
        FormaContatoVO vo = null;
        if (null != entity) {
            vo = new FormaContatoVO();
            vo.setEmail(entity.getMailContato());
            vo.setIdFormaContato(entity.getIdFormaComunica());
            if (null != entity.getIdContato()) {
                vo.setIdContato(entity.getIdContato().getIdContato());
            }
            vo.setTelefone(entity.getFoneContato());
            vo.setTipoContato(entity.getTpContato());
        }
        return vo;
    }

    /**
     * Nome: parse Parses the.
     * @param entity the entity
     * @return doenca vo
     * @see
     */
    public static DoencaVO parse(CID entity) {
        DoencaVO vo = null;
        if (null != entity) {
            vo = new DoencaVO();
            vo.setCodigoCID(entity.getCdCID());
            if (null != entity.getCdTipoDoenca()) {
                vo.setTipoDoenca(new TipoDoencaVO());
                vo.getTipoDoenca().setCdTipoDoenca(entity.getCdTipoDoenca().getCdTipoDoenca());
                vo.getTipoDoenca().setCatInic(entity.getCdTipoDoenca().getCatInic());
                vo.getTipoDoenca().setCatFinal(entity.getCdTipoDoenca().getCatFinal());
                vo.getTipoDoenca().setDsTipoDoenca(entity.getCdTipoDoenca().getDsTipoDoenca());
                vo.getTipoDoenca().setNmCapitulo(entity.getCdTipoDoenca().getNmCapitulo());
            }
            vo.setNomeDoenca(entity.getNmDoenca());
            entity.getNmDoenca();
        }
        return vo;
    }

    /**
     * Nome: Converte um vo de ParametroVO em uma entity de Parametro Parses the.
     * @param vo the parametro
     * @return parametro
     * @see
     */
    public static Parametro parse(ParametroVO vo) {

        Parametro entity = new Parametro();
        entity.setIdParametro(vo.getIdParametro());
        entity.setDiasDados(vo.getDiasDados());
        entity.setDiasBemEstar(vo.getDiasBemEstar());
        entity.setToleraRotinaCliente(vo.getToleraRotinaCliente());

        return entity;
    }

    /**
     * Nome: parse Parses the.
     * @param entity the entity
     * @return parametro vo
     * @see
     */
    public static ParametroVO parse(Parametro entity) {

        ParametroVO vo = new ParametroVO();
        vo.setIdParametro(entity.getIdParametro());
        vo.setDiasDados(entity.getDiasDados());
        vo.setDiasBemEstar(entity.getDiasBemEstar());
        vo.setToleraRotinaCliente(entity.getToleraRotinaCliente());

        return vo;
    }

    /**
     * Nome: Converte Entity em PacoteServicoVO.
     * @param entity PacoteServico
     * @return PacoteServicoVO vo
     * @see
     */
    public static PacoteServicoVO parse(PacoteServico entity) {

        PacoteServicoVO pacoteServico = new PacoteServicoVO();
        pacoteServico.setIdPacote(entity.getIdServico());
        pacoteServico.setDescricao(entity.getDsServico());
        pacoteServico.setPreco(entity.getPrcMensal());
        pacoteServico.setTitulo(entity.getDsTitulo());
        pacoteServico.setDataInicioValidade(entity.getDtInicioValidade());
        pacoteServico.setDataFinalValidade(entity.getDtFinalValidade());

        return pacoteServico;
    }

    /**
     * Nome: parse Parses the.
     * @param vo the vo
     * @return pacote servico
     * @see
     */
    public static PacoteServico parse(PacoteServicoVO vo) {

        PacoteServico entity = new PacoteServico();
        entity.setIdServico(vo.getIdPacote());
        entity.setDsTitulo(vo.getTitulo());
        entity.setDsServico(vo.getDescricao());
        entity.setPrcMensal(vo.getPreco());
        entity.setDtInicioValidade(vo.getDataInicioValidade());
        entity.setDtFinalValidade(vo.getDataFinalValidade());

        return entity;
    }

    /**
     * Nome: parse Converte uma entity SMS em um objeto SmsVO.
     * @param entity the entity
     * @return sms vo
     * @see
     */
    public static SmsVO parse(SMS entity) {

        SmsVO vo = new SmsVO();
        vo.setIdSms(entity.getIdSMS());
        vo.setTitulo(entity.getTpMensagem());
        vo.setTexto(entity.getDsMensagem());
        vo.setDtInicioValidade(entity.getDtInicioValidade());
        vo.setDtTerminoValidade(entity.getDtTerminoValidade());

        return vo;
    }

    /**
     * Nome: parse Converte o objeto SmsVO em uma entity SMS.
     * @param vo the vo
     * @return sms
     * @see
     */
    public static SMS parse(SmsVO vo) {

        SMS entity = new SMS();
        entity.setTpMensagem(vo.getTitulo());
        entity.setDsMensagem(vo.getTexto());
        entity.setDtInicioValidade(vo.getDtInicioValidade());
        entity.setDtTerminoValidade(vo.getDtTerminoValidade());

        return entity;

    }

    /**
     * Nome: parse Converte o objeto Ocorrencia em um vo de OcorrenciaVO.
     * @param entity the entity
     * @return ocorrencia vo
     * @see
     */
    public static OcorrenciaVO parse(Ocorrencia entity) {

        OcorrenciaVO vo = new OcorrenciaVO();
        vo.setDescricao(entity.getAcaoOcorrencia());
        vo.setStatusOcorrencia(entity.getStatusOcorre());
        vo.setResolucao(entity.getConclusao());
        vo.setDataAbertura(entity.getDtaAtend());
        vo.setDataHoraAberturaOcorrencia(entity.getDtaHoraAbertura());
        vo.setDataHoraInicioContato(entity.getDtaHoraInicio());
        vo.setDataHoraFechamentoOcorrencia(entity.getDtaHoraFechamento());
        vo.setDataHoraTerminoContato(entity.getDtaHoraTermino());
        vo.setIdOcorrencia(entity.getIdOcorrencia());
        if (null != entity.getIdScript() && entity.getIdScript().getIdScript() > 0) {
            vo.setScript(parse(entity.getIdScript()));
        }
        vo.setUsuario(new UsuarioVO());
        vo.setUsuario(parse(entity.getLogin()));
        vo.setCliente(parse(entity.getNmCPFCliente()));
        vo.setSnDispositivo(entity.getSnDispositivo());
        vo.setIdLigacao(entity.getIdLigacao());
        vo.setNumerorTelefoneLigado(entity.getNrTelefoneLigado());
        vo.setCodigoPrioridade(entity.getCodPrioridade());
        vo.setTipoOcorrencia(new TipoOcorrenciaVO());
        vo.getTipoOcorrencia().setCodigoTipoOcorrencia(entity.getTpOcorrencia());
        for (TipoOcorrencia item : TipoOcorrencia.values()) {
            if (item.getValue().intValue() == entity.getTpOcorrencia().intValue()) {
                vo.getTipoOcorrencia().setDescricaoTipoOcorrencia(item.getLabel());
            }
        }

        return vo;
    }

    /**
     * Nome: parse Parses the.
     * @param vo the vo
     * @return ocorrencia
     * @see
     */
    public static Ocorrencia parse(OcorrenciaVO vo) {
        Ocorrencia entity = new Ocorrencia();

        entity.setAcaoOcorrencia(vo.getDescricao());
        entity.setStatusOcorre(vo.getStatusOcorrencia());
        entity.setConclusao(vo.getResolucao());
        entity.setDtaAtend(vo.getDataAbertura());
        entity.setDtaHoraAbertura(vo.getDataHoraAberturaOcorrencia());
        entity.setDtaHoraInicio(vo.getDataHoraInicioContato());
        entity.setDtaHoraFechamento(vo.getDataHoraFechamentoOcorrencia());
        entity.setDtaHoraTermino(vo.getDataHoraTerminoContato());
        entity.setIdOcorrencia(vo.getIdOcorrencia());
        if (null != vo.getScript() && vo.getScript().getIdScript() > 0) {
            entity.setIdScript(parse(vo.getScript()));
        }
        entity.setLogin(parse(vo.getUsuario()));
        entity.setNmCPFCliente(parse(vo.getCliente()));
        entity.setSnDispositivo(vo.getSnDispositivo());
        entity.setIdLigacao(vo.getIdLigacao());
        entity.setNrTelefoneLigado(vo.getNumerorTelefoneLigado());
        entity.setCodPrioridade(vo.getCodigoPrioridade());
        entity.setTpOcorrencia(vo.getTipoOcorrencia().getCodigoTipoOcorrencia());
        if (null != vo.getContrato() && null != vo.getContrato().getCliente()) {
            Cliente nmCPFCliente = ParseUtils.parse(vo.getContrato().getCliente());
            entity.setNmCPFCliente(nmCPFCliente);
        }
        return entity;
    }

    /**
     * Nome: parse Converte o objeto ScriptVO em uma entity Script.
     * @param vo the vo
     * @return script
     * @see
     */
    public static Script parse(ScriptVO vo) {
        Script entity = null;
        if (null != vo) {
            entity = new Script();
            entity.setIdScript(vo.getIdScript());
            entity.setNmTitulo(vo.getTituloScript());
            entity.setDsDescricao(vo.getDescricaoScript());
            entity.setDsProcesso(vo.getProcessoSeguir());
            entity.setDtInicioValidade(vo.getDtInicioValidade());
            entity.setDtFinalValidade(vo.getDtFinalValidade());
        }
        return entity;
    }

    /**
     * Nome: parse Converte uma entity Script em um objeto ScriptVO.
     * @param entity the entity
     * @return script vo
     * @see
     */
    public static ScriptVO parse(Script entity) {

        ScriptVO vo = null;
        if (null != entity) {
            vo = new ScriptVO();
            vo.setIdScript(entity.getIdScript());
            vo.setTituloScript(entity.getNmTitulo());
            vo.setDescricaoScript(entity.getDsDescricao());
            vo.setProcessoSeguir(entity.getDsProcesso());
            vo.setDtInicioValidade(entity.getDtInicioValidade());
            vo.setDtFinalValidade(entity.getDtFinalValidade());
        }
        return vo;
    }


    /**
     * Nome: parse
     * Parses the.
     *
     * @param vo the vo
     * @return acionamento
     * @see
     */
    public static Acionamento parse(AcionamentoVO vo) {
        Acionamento entity = new Acionamento();
        entity.setIdContato(new Contato());
        entity.getIdContato().setIdContato(vo.getIdContato());
        entity.setIdOcorrencia(new Ocorrencia());
        entity.getIdOcorrencia().setIdOcorrencia(vo.getIdOcorrencia());
        entity.setDtaHoraAciona(vo.getDataHoraDoAcionamento());
        entity.setDtaHoraInicio(vo.getDataHoraInicioConversa());
        entity.setTextoLivreSMS(vo.getTextLivreSMS());

        if (vo.isSucesso()) {
            entity.setSucesso('S');
        } else {
            entity.setSucesso('N');
        }

        if (null != vo.getIdSMSPadrao() && vo.getIdSMSPadrao() > 0) {
            entity.setIdSMS(new SMS());
            entity.getIdSMS().setIdSMS(vo.getIdSMSPadrao());
        }

        return entity;
    }

    /**
     * Nome: parseContratoEntityList Parses the list.
     * @param list the list
     * @return list
     * @see
     */
    public static List<ContratoVO> parseContratoEntityList(List<Contrato> list) {
        List<ContratoVO> retorno = new ArrayList<ContratoVO>();
        for (Contrato entity : list) {
            retorno.add(ParseUtils.parse(entity));
        }
        return retorno;
    }

}
