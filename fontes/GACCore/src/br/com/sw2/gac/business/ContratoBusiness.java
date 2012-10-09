package br.com.sw2.gac.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sw2.gac.dao.ContratoDAO;
import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.CID;
import br.com.sw2.gac.modelo.Cliente;
import br.com.sw2.gac.modelo.ClienteDispositivo;
import br.com.sw2.gac.modelo.Contato;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.FormaComunica;
import br.com.sw2.gac.modelo.Tratamento;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.ClienteVO;
import br.com.sw2.gac.vo.ClientesAtivosVO;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DesempenhoComercialVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.MovimentacaoClienteVO;
import br.com.sw2.gac.vo.RelContratosAVencerVO;
import br.com.sw2.gac.vo.TratamentoVO;

/**
 * <b>Descrição: Classe de negócio responsável pelas regras relativas aos contratos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratoBusiness {

    /** Atributo dao. */
    private final ContratoDAO contratoDAO = new ContratoDAO();

    /** Atributo dispositivo dao. */
    private final DispositivoDAO dispositivoDAO = new DispositivoDAO();

    /** Atributo limite porcentagem. */
    private final int limiteBasePorcentagem = 100;

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /** Constante LIMITE_DISPOSITIVOS_POR_CENTRAL. */
    public static final int LIMITE_DISPOSITIVOS_POR_CENTRAL = 8;

    /**
     * Nome: pesquisarContratos Pesquisar contratos.
     * @param numeroContrato the numero contrato
     * @param cpf the cpf
     * @param nomeContratante the nome contratante
     * @return list
     * @see
     */
    public List<ContratoVO> pesquisarContratos(Integer numeroContrato, String cpf,
        String nomeContratante) {
        List<Contrato> list = new ArrayList<Contrato>();
        if (numeroContrato != null) {
            Contrato contrato = (Contrato) this.contratoDAO.getEntityById(numeroContrato);
            list.add(contrato);
        } else if (StringUtil.isVazio(cpf, true)) {
            list = this.contratoDAO.filtarContratosPorNomeContratante(nomeContratante);
        } else {
            list = this.contratoDAO.filtarContratosPorCPFContratante(cpf);
        }
        return parseList(list);
    }

    /**
     * Nome: parseList Parses the list.
     * @param list the list
     * @return list
     * @see
     */
    private List<ContratoVO> parseList(List<Contrato> list) {
        List<ContratoVO> retorno = new ArrayList<ContratoVO>();
        for (Contrato entity : list) {
            retorno.add(ParseUtils.parse(entity));
        }
        return retorno;
    }

    /**
     * Nome: obterDadosDesempenhoComercial Obter dados desempenho comercial.
     * @param dataReferencia the data referencia
     * @return desempenho comercial vo
     * @see
     */
    public DesempenhoComercialVO obterDadosDesempenhoComercial(Date dataReferencia) {

        Date inicioPeriodo = DateUtil.getPrimeiroDiaMes(dataReferencia);
        Date fimPeriodo = DateUtil.getUltimoDiaMes(dataReferencia);

        DesempenhoComercialVO retorno = new DesempenhoComercialVO();
        retorno
            .setQtdClientesInicioMes(contratoDAO.getListaContratosAtivosInicioMes(inicioPeriodo));

        List<MovimentacaoClienteVO> listaMovimentacaoCliente = inicializarListaMovimentacaoCliente(
            inicioPeriodo, fimPeriodo);

        // Recuperar os contratos iniciados (Entrantes) no periodo informado
        tratarListaClientesNovos(inicioPeriodo, fimPeriodo, retorno, listaMovimentacaoCliente);

        // Obter lista de contratos cancelados no periodo informado
        tratarListaContratosCancelados(inicioPeriodo, fimPeriodo, retorno, listaMovimentacaoCliente);

        // Obter contratos suspensos no periodo
        tratarListaContratosSuspensos(inicioPeriodo, fimPeriodo, retorno, listaMovimentacaoCliente);

        // Obtem a lista de clientes ativos agrupados por pacotes de serviço
        List<Object[]> listaClientesAtivosPorPacote = (List<Object[]>) this.contratoDAO
            .getListaContratosAtivosPorPacote(inicioPeriodo, fimPeriodo);
        List<ClientesAtivosVO> listaClientesAtivos = new ArrayList<ClientesAtivosVO>();
        int qtdeTotalClienteAtivos = 0;
        for (Object[] item : listaClientesAtivosPorPacote) {
            ClientesAtivosVO clienteAtivo = new ClientesAtivosVO();
            clienteAtivo.setNomePacote(item[1].toString());
            clienteAtivo.setQtdeCliente((long) item[0]);
            qtdeTotalClienteAtivos += clienteAtivo.getQtdeCliente();
            clienteAtivo.setPorcCliente(null);
            listaClientesAtivos.add(clienteAtivo);
        }

        logger.debug("Obtido contratos ativos agrupados por pacotes de serviço");

        // Atualizar a % da lista de clientes por ativos
        for (ClientesAtivosVO item : listaClientesAtivos) {
            double porcentagem = (item.getQtdeCliente() * limiteBasePorcentagem)
                / qtdeTotalClienteAtivos;
            item.setPorcCliente(new BigDecimal(porcentagem).setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        logger.debug("Calculado porcentagem  dos clientes ativos");

        retorno.setDataApuracao(dataReferencia);

        retorno.setMovimentacaoClientes(listaMovimentacaoCliente);
        retorno.setClientesAtivos(listaClientesAtivos);
        retorno.setQtdeClientesAtivos(qtdeTotalClienteAtivos);

        return retorno;

    }

    /**
     * Nome: tratarListaContratosSuspensos Tratar lista contratos suspensos para geração do VO de
     * Desempenho comercial.
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @param retorno the retorno
     * @param listaMovimentacaoCliente the lista movimentacao cliente
     * @see
     */
    private void tratarListaContratosSuspensos(Date inicioPeriodo, Date fimPeriodo,
        DesempenhoComercialVO retorno, List<MovimentacaoClienteVO> listaMovimentacaoCliente) {
        List<Object[]> listaContratosSuspensos = (List<Object[]>) this.contratoDAO
            .getContratosSuspensosPeriodo(inicioPeriodo, fimPeriodo);
        for (Object[] item : listaContratosSuspensos) {
            int qtde = Integer.parseInt(item[0].toString());
            int dia = DateUtil.getDia(item[1]);
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils
                .findByAttribute(listaMovimentacaoCliente, "dia", dia);
            if (null != diaEncontrado) {
                diaEncontrado.setCancelado(qtde);
            }
        }
        if (null != listaContratosSuspensos) {
            retorno.setQtdeSuspensosMes(listaContratosSuspensos.size());

            // Porcentagem de contratos cancelados
            if (retorno.getQtdClientesInicioMes() > 0) {
                BigDecimal porcentagemSuspensosMes = new BigDecimal(
                    (retorno.getQtdeSuspensosMes() * limiteBasePorcentagem)
                        / retorno.getQtdClientesInicioMes()).setScale(2, BigDecimal.ROUND_HALF_UP);

                retorno.setPorcentagemSuspensosMes(porcentagemSuspensosMes);
            } else {
                retorno.setPorcentagemSuspensosMes(new BigDecimal(limiteBasePorcentagem));
            }
        }
        logger.debug("Obtido contratos suspensos");
    }

    /**
     * Nome: tratarListaContratosCancelados Tratar lista contratos cancelados para geração do VO de
     * DesempenhoComercial.
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @param retorno the retorno
     * @param listaMovimentacaoCliente the lista movimentacao cliente
     * @see
     */
    private void tratarListaContratosCancelados(Date inicioPeriodo, Date fimPeriodo,
        DesempenhoComercialVO retorno, List<MovimentacaoClienteVO> listaMovimentacaoCliente) {
        List<Object[]> listaContratosCancelados = (List<Object[]>) this.contratoDAO
            .getContratosCanceladosPeriodo(inicioPeriodo, fimPeriodo);
        for (Object[] item : listaContratosCancelados) {
            int qtde = Integer.parseInt(item[0].toString());
            int dia = DateUtil.getDia(item[1]);
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils
                .findByAttribute(listaMovimentacaoCliente, "dia", dia);
            if (null != diaEncontrado) {
                diaEncontrado.setCancelado(qtde);
            }
        }
        if (null != listaContratosCancelados) {
            retorno.setQtdeCanceladosMes(listaContratosCancelados.size());

            // Porcentagem de contratos cancelados
            if (retorno.getQtdClientesInicioMes() > 0) {
                BigDecimal porcentagemcanceladosMes = new BigDecimal(
                    (retorno.getQtdeCanceladosMes() * limiteBasePorcentagem)
                        / retorno.getQtdClientesInicioMes()).setScale(2, BigDecimal.ROUND_HALF_UP);

                retorno.setPorcentagemCanceladosMes(porcentagemcanceladosMes);
            } else {
                retorno.setPorcentagemCanceladosMes(new BigDecimal(limiteBasePorcentagem));
            }
        }
        logger.debug("Obtido contratos cancelados");
    }

    /**
     * Nome: tratarListaClientesNovos Tratar lista clientes novos para geração do VO de desempenho
     * comercial.
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @param retorno the retorno
     * @param listaMovimentacaoCliente the lista movimentacao cliente
     * @see
     */
    private void tratarListaClientesNovos(Date inicioPeriodo, Date fimPeriodo,
        DesempenhoComercialVO retorno, List<MovimentacaoClienteVO> listaMovimentacaoCliente) {
        List<Object[]> listaContratosNovos = (List<Object[]>) this.contratoDAO
            .getNovosContratosPeriodo(inicioPeriodo, fimPeriodo);
        for (Object[] item : listaContratosNovos) {
            int qtde = Integer.parseInt(item[0].toString());
            int dia = DateUtil.getDia(item[1]);
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils
                .findByAttribute(listaMovimentacaoCliente, "dia", dia);
            if (null != diaEncontrado) {
                diaEncontrado.setEntrante(qtde);
            }
        }
        if (null != listaContratosNovos) {
            retorno.setQtdeEntrantesMes(listaContratosNovos.size());

            // Porcentagem de contratos novos
            if (retorno.getQtdClientesInicioMes() > 0) {
                BigDecimal porcentagemEntratesMes = new BigDecimal(
                    (retorno.getQtdeEntrantesMes() * limiteBasePorcentagem)
                        / retorno.getQtdClientesInicioMes()).setScale(2, BigDecimal.ROUND_HALF_UP);
                retorno.setPorcentagemEntrantesMes(porcentagemEntratesMes);
            } else {
                retorno.setPorcentagemEntrantesMes(new BigDecimal(limiteBasePorcentagem));
            }

        }

        logger.debug("Obtido contratos novos");
    }

    /**
     * Nome: inicializarListaMovimentacaoCliente Inicializar lista movimentacao cliente, utilizada
     * para montagem do VO de desempenho comercial.
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @return list
     * @see
     */
    private List<MovimentacaoClienteVO> inicializarListaMovimentacaoCliente(Date inicioPeriodo,
        Date fimPeriodo) {
        // Lista com a movimentação diária de contratos/Clientes
        List<MovimentacaoClienteVO> listaMovimentacaoCliente = new ArrayList<MovimentacaoClienteVO>();
        int primeiroDia = DateUtil.getDia(inicioPeriodo);
        int ultimoDia = DateUtil.getDia(fimPeriodo);
        // Monta a lista com todos os dias do mês;
        for (int i = primeiroDia; i <= ultimoDia; i++) {
            MovimentacaoClienteVO movimentacao = new MovimentacaoClienteVO();
            movimentacao.setDia(i);
            movimentacao.setEntrante(0);
            movimentacao.setCancelado(0);
            movimentacao.setSuspenso(0);
            listaMovimentacaoCliente.add(movimentacao);
        }
        return listaMovimentacaoCliente;
    }

    /**
     * Nome: obterListaDispositivosSelecionaveis Obter lista dispositivos disponíveis para uso.
     * @param filtro the filtro
     * @return list
     * @see
     */
    public List<DispositivoVO> obterListaDispositivosSelecionaveis(String filtro) {
        List<DispositivoVO> listVO = new ArrayList<DispositivoVO>();
        List<Dispositivo> listEntity = this.dispositivoDAO
            .recuperaDispositivosSelecionaveis(filtro);
        for (Dispositivo entity : listEntity) {
            listVO.add(ObjectUtils.parse(entity));
        }
        return listVO;
    }

    /**
     * Nome: obterListaCentraisSelecionaveis Obter lista centrais selecionaveis.
     * @param filtro the filtro
     * @return list
     * @see
     */
    public List<DispositivoVO> obterListaCentraisSelecionaveis(String filtro) {
        List<DispositivoVO> listVO = new ArrayList<DispositivoVO>();
        List<Dispositivo> listEntity = this.dispositivoDAO.recuperaCentraisSelecionaveis(filtro);
        for (Dispositivo entity : listEntity) {
            listVO.add(ObjectUtils.parse(entity));
        }
        return listVO;
    }

    /**
     * Nome: gravarNovoContrato Gravar novo contrato.
     * @param contrato the contrato
     * @return contrato vo
     * @throws BusinessException the business exception
     * @see
     */
    public ContratoVO gravarNovoContrato(ContratoVO contrato) throws BusinessException {
        ContratoVO retorno = contrato;
        Contrato entity = ParseUtils.parse(retorno);
        try {
            entity = this.contratoDAO.gravarNovoContrato(entity);
            retorno.setNumeroContrato(entity.getNmContrato());
        } catch (DataBaseException e) {
            throw new BusinessException(e);
        }
        return retorno;
    }


    /**
     * Nome: atualizarContrato
     * Atualizar contrato.
     *
     * @param contrato the contrato
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarContrato(ContratoVO contrato) throws BusinessException {

        this.contratoDAO.getEntityManager().getTransaction().begin();
        // Promove exclusões e updates necessárias e que não podem ser feitos em cascata

        tratarAtualizacaoDadosDispositivos(contrato);

        for (TratamentoVO tratamentoVO : contrato.getCliente().getListaTratamentos()) {
            Tratamento formaContatoEntity = ParseUtils.parse(tratamentoVO);
            if (tratamentoVO.getCrud().equals(Crud.Delete.getValue())) {
                this.contratoDAO.excluirTratamento(formaContatoEntity);
            } else if (tratamentoVO.getCrud().equals(Crud.Update.getValue())) {
                this.contratoDAO.atualizarTratamento(formaContatoEntity);
            }
        }

        tratarAtualizacaoContatoEFormaContato(contrato);

        Contrato entity = ParseUtils.parse(contrato);
        try {
            this.contratoDAO.atualizarContrato(entity);
            contrato.setNumeroContrato(entity.getNmContrato());
            this.contratoDAO.getEntityManager().getTransaction().commit();
        } catch (DataBaseException e) {
            if (this.contratoDAO.getEntityManager().getTransaction().isActive()) {
                this.contratoDAO.getEntityManager().getTransaction().rollback();
            }
            throw new BusinessException(e);
        }
    }

    /**
     * Nome: tratarAtualizacaoContatoEFormaContato
     * Tratar atualizacao contato e forma contato.
     *
     * @param contrato the contrato
     * @see
     */
    private void tratarAtualizacaoContatoEFormaContato(ContratoVO contrato) {
        for (FormaContatoVO formaContatoVO : contrato.getCliente().getListaFormaContato()) {
            FormaComunica formaContatoEntity = ParseUtils.parse(formaContatoVO);
            if (formaContatoVO.getCrud().equals(Crud.Delete.getValue())) {
                this.contratoDAO.excluirFormaComunicacao(formaContatoEntity);
            } else if (formaContatoVO.getCrud().equals(Crud.Update.getValue())) {
                this.contratoDAO.atualizarFormaComunicacao(formaContatoEntity);
            }
        }

        for (ContatoVO contatoVO : contrato.getCliente().getListaContatos()) {
            Contato contatoEntity = ParseUtils.parse(contatoVO);

            for (FormaContatoVO formaContatoVO : contatoVO.getListaFormaContato()) {
                FormaComunica formaContatoEntity = ParseUtils.parse(formaContatoVO);
                if (formaContatoVO.getCrud().equals(Crud.Delete.getValue())) {
                    this.contratoDAO.excluirFormaComunicacao(formaContatoEntity);
                } else if (formaContatoVO.getCrud().equals(Crud.Update.getValue())) {
                    this.contratoDAO.atualizarFormaComunicacao(formaContatoEntity);
                }
            }

            if (contatoVO.getCrud().equals(Crud.Delete.getValue())) {
                this.contratoDAO.excluirContato(contatoEntity);
            } else if (contatoVO.getCrud().equals(Crud.Update.getValue())) {
                this.contratoDAO.atualizarContato(contatoEntity);
            }
        }
    }

    /**
     * Nome: tratarAtualizacaoDadosDispositivos
     * Tratar atualizacao dados dispositivos.
     *
     * @param contrato the contrato
     * @see
     */
    private void tratarAtualizacaoDadosDispositivos(ContratoVO contrato) {
        List<DispositivoVO> todosDispositivos = new ArrayList<DispositivoVO>();
        todosDispositivos.addAll(contrato.getCliente().getListaDispositivos());
        todosDispositivos.addAll(contrato.getCliente().getListaCentrais());
        for (DispositivoVO dispositivoVO : todosDispositivos) {
            if (dispositivoVO.getCrud().equals(Crud.Delete.getValue())) {
                this.contratoDAO.excluirDispositivosContrato(dispositivoVO.getIdDispositivo(),
                    contrato.getCliente().getCpf());
                contrato.getCliente().getListaDispositivos().remove(dispositivoVO);
                contrato.getCliente().getListaCentrais().remove(dispositivoVO);
            }
        }

        //Atualiza os dados da central e dispositivo do cliente.
        String idCentral = null;
        for (DispositivoVO central : contrato.getCliente().getListaCentrais()) {
            idCentral = central.getIdDispositivo();
            if (central.getCrud().equals(Crud.Create.getValue())) {
                this.contratoDAO.incluirDispositivosContrato(central.getIdDispositivo(), contrato.getCliente().getCpf(), null);
            }
        }

        // Dispositivos do cliente;
        for (DispositivoVO dispositivo : contrato.getCliente().getListaDispositivos()) {
            if (dispositivo.getCrud().equals(Crud.Create.getValue())) {
                // Verifica quantos clientes ja estão usando esta central;
                List<Cliente> clientesNaCentral = this.contratoDAO
                    .getListaClientesPorCentral(idCentral);
                int numDispositivo = clientesNaCentral.size();

                //Se possui menos de 8 dispositivos pode assossiar mais.
                if (numDispositivo < ContratoBusiness.LIMITE_DISPOSITIVOS_POR_CENTRAL) {
                    numDispositivo++;

                    this.contratoDAO.incluirDispositivosContrato(dispositivo.getIdDispositivo(),
                        contrato.getCliente().getCpf(), numDispositivo);

                } else {
                    this.contratoDAO.getEntityManager().getTransaction().rollback();
                    throw new BusinessException("Limite de dispositivos por central foi atindigo");
                }
            }
        }

        contrato.getCliente().getListaDispositivos().clear();
        contrato.getCliente().getListaCentrais().clear();
    }

    /**
     * Nome: centralAceitaNovosDispositivos Central aceita novos dispositivos.
     * @param idCentral the id central
     * @return true, se sucesso, senão false
     * @throws BusinessException the business exception
     * @see
     */
    public boolean centralAceitaNovosDispositivos(String idCentral) throws BusinessException {
        List<Cliente> clientes = this.contratoDAO.getListaClientesPorCentral(idCentral);
        boolean retorno = false;
        if (clientes.size() < LIMITE_DISPOSITIVOS_POR_CENTRAL) {
            retorno = true;
        }
        return retorno;
    }

    /**
     * Nome: obterCentralDisponivelParaEndereco Obter central disponivel para endereco.
     * @param cliente the cliente
     * @return DispositivoVO
     * @throws BusinessException the business exception
     * @see
     */
    public DispositivoVO obterCentralDisponivelParaEndereco(ClienteVO cliente)
        throws BusinessException {
        Cliente clienteEntity = ParseUtils.parse(cliente);
        DispositivoVO dispositivo = null;
        try {
            List<ClienteDispositivo> retorno = (List<ClienteDispositivo>) this.contratoDAO
                .getListaCentraisPorEndereco(clienteEntity);
            for (ClienteDispositivo cd : retorno) {
                if (this.centralAceitaNovosDispositivos(cd.getDispositivo().getIdDispositivo())) {
                    dispositivo = new DispositivoVO();
                    dispositivo.setIdDispositivo(cd.getDispositivo().getIdDispositivo());
                    break;
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return dispositivo;
    }

    /**
     * Recupera os contratos a vencer em (n) dias.
     * @param diasAVencer dias a vencer
     * @return contratos a vencer
     * @see
     */
    public List<RelContratosAVencerVO> recuperarContratosAtivosAVencerEm(final Integer diasAVencer) {
        // Lista com a movimentação diária de contratos/Clientes
        List<RelContratosAVencerVO> contratosAVencer = new ArrayList<RelContratosAVencerVO>();
        List<Contrato> contratos = this.contratoDAO.recuperarContratosAtivosAVencerEm(diasAVencer);
        for (Contrato contrato : contratos) {
            RelContratosAVencerVO relatorioVO = new RelContratosAVencerVO();
            relatorioVO.setNroContrato(contrato.getNmContrato().longValue());
            relatorioVO.setInicioVigencia(contrato.getDtInicioValidade());
            relatorioVO.setFimVigencia(contrato.getDtFinalValidade());
            relatorioVO.setPacote(contrato.getIdServico().getDsTitulo());
        }
        return contratosAVencer;
    }

    /**
     * Nome: obtertListaDoencas Obtert lista doencas.
     * @param filtro the filtro
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<DoencaVO> obtertListaDoencas(String filtro) throws BusinessException {
        List<DoencaVO> list = new ArrayList<DoencaVO>();
        try {
            List<CID> listEntity = (List<CID>) this.contratoDAO.getListaDoencas(filtro);
            for (CID entity : listEntity) {
                list.add(ParseUtils.parse(entity));
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return list;
    }

    /**
     * Nome: excluirContrato Excluir contrato.
     * @param contrato the contrato
     * @throws BusinessException the business exception
     * @see
     */
    public void excluirContrato(ContratoVO contrato) throws BusinessException {
        try {
            Contrato parametro = new Contrato();
            parametro.setNmContrato(contrato.getNumeroContrato());
            this.contratoDAO.excluirContrato(parametro);
        } catch (DataBaseException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Nome: obterDadosContrato Obter dados contrato.
     * @param numeroContrato the numero contrato
     * @return contrato
     * @throws BusinessException the business exception
     * @see
     */
    public ContratoVO obterDadosContrato(Integer numeroContrato) throws BusinessException {
        Contrato parametro = new Contrato();
        parametro.setNmContrato(numeroContrato);
        ContratoVO vo = null;
        try {
            Contrato contratoEntity = this.contratoDAO.getEntityById(numeroContrato);
            vo = ParseUtils.parse(contratoEntity);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return vo;
    }

}
