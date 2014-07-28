package br.com.sw2.gac.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.dao.ContratoDAO;
import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.ContratanteNaoEncontradoException;
import br.com.sw2.gac.exception.DadosIncompletosException;
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
import br.com.sw2.gac.tools.TipoDispositivo;
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
import br.com.sw2.gac.vo.HorarioVO;
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

    /** Atributo contrato dao. */
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
        } else if (StringUtil.isEmpty(cpf, true)) {
            list = this.contratoDAO.filtarContratosPorNomeContratante(nomeContratante);
        } else {
            list = this.contratoDAO.filtarContratosPorCPFContratante(cpf);
        }
        return ParseUtils.parseContratoEntityList(list);
    }

    /**
     * Nome: pesquisarContratosPorCliente
     * Pesquisar contratos por cliente.
     *
     * @param cpfCliente the cpf cliente
     * @param nomeCliente the nome cliente
     * @return list
     * @see
     */
    public List<ContratoVO> pesquisarContratosPorCliente(String cpfCliente,
        String nomeCliente) {
        List<Contrato> list = new ArrayList<Contrato>();
        if (StringUtil.isEmpty(cpfCliente, true)) {
            list = this.contratoDAO.filtarContratosPorNomeCliente(nomeCliente);
        } else {
            list = this.contratoDAO.filtarContratosPorCPFCliente(cpfCliente);
        }
        return ParseUtils.parseContratoEntityList(list);
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
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils.findByAttribute(listaMovimentacaoCliente, "dia", dia);
            if (null != diaEncontrado) {
                diaEncontrado.setCancelado(qtde);
            }
        }
        if (null != listaContratosSuspensos) {
            retorno.setQtdeSuspensosMes(listaContratosSuspensos.size());

            // Porcentagem de contratos cancelados
            if (retorno.getQtdClientesInicioMes() > 0) {
                BigDecimal porcentagemSuspensosMes = new BigDecimal((retorno.getQtdeSuspensosMes() * limiteBasePorcentagem) / retorno.getQtdClientesInicioMes()).setScale(2, BigDecimal.ROUND_HALF_UP);
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
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils.findByAttribute(listaMovimentacaoCliente, "dia", dia);
            if (null != diaEncontrado) {
                diaEncontrado.setCancelado(qtde);
            }
        }
        if (null != listaContratosCancelados) {
            retorno.setQtdeCanceladosMes(listaContratosCancelados.size());

            // Porcentagem de contratos cancelados
            if (retorno.getQtdClientesInicioMes() > 0) {
                BigDecimal porcentagemcanceladosMes = new BigDecimal((retorno.getQtdeCanceladosMes() * limiteBasePorcentagem) / retorno.getQtdClientesInicioMes()).setScale(2, BigDecimal.ROUND_HALF_UP);
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
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils.findByAttribute(listaMovimentacaoCliente, "dia", dia);
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
        List<Dispositivo> listEntity = this.dispositivoDAO.recuperaDispositivosSelecionaveis(filtro);
        for (Dispositivo entity : listEntity) {
            listVO.add(ObjectUtils.parse(entity));
        }
        return listVO;
    }


    /**
     * Nome: obterListaCentraisSelecionaveis
     * Obter lista centrais selecionaveis.
     *
     * @param filtro the filtro
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<DispositivoVO> obterListaCentraisSelecionaveis(String filtro)
        throws BusinessException {
        List<DispositivoVO> listVO = new ArrayList<DispositivoVO>();
        try {
            List<Dispositivo> listEntity = this.dispositivoDAO
                .recuperaCentraisSelecionaveis(filtro);
            for (Dispositivo entity : listEntity) {
                listVO.add(ObjectUtils.parse(entity));
            }
        } catch (Exception e) {
            throw new BusinessException(e);
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
        
        List<DoencaVO> listaDoencasParaGravar = new ArrayList<DoencaVO>();
        for (DoencaVO doenca : contrato.getCliente().getListaDoencas()) {               
            if (null != doenca.getCrud() && doenca.getCrud().equals(Crud.Delete.getValue())) {
                this.contratoDAO.deleteDoenca(doenca.getCodigoCID(), contrato.getCliente().getCpf());
            }  else {
                listaDoencasParaGravar.add(doenca);
            }           
        }
        
        contrato.getCliente().setListaDoencas(new ArrayList<DoencaVO>());
        if (CollectionUtils.isNotEmptyOrNull(listaDoencasParaGravar)) {
            contrato.getCliente().getListaDoencas().addAll(listaDoencasParaGravar);
        }    
        
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
     * Nome: atualizarContrato Atualizar contrato.
     * @param contrato the contrato
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarContrato(ContratoVO contrato) throws BusinessException {
        
        List<String> keys = new ArrayList<String>();
        if (CollectionUtils.isEmptyOrNull(contrato.getCliente().getListaFormaContato())
            || obterDiffItensExcluidosDaLista(contrato.getCliente().getListaFormaContato()) < 1) {
            keys.add("message.generic.field.formacontato.cliente.required");
        }

        if ((CollectionUtils.isEmptyOrNull(contrato.getCliente().getListaDispositivos()) || CollectionUtils
            .isEmptyOrNull(contrato.getCliente().getListaCentrais()))
            || obterDiffItensExcluidosDaLista(contrato.getCliente().getListaCentrais()) < 1) {
            keys.add("message.generic.field.centraldispositivo.required");
        }

        if ((CollectionUtils.isEmptyOrNull(contrato.getCliente().getListaContatos()))
            || obterDiffItensExcluidosDaLista(contrato.getCliente().getListaContatos()) < 1) {
            keys.add("message.generic.field.pessoacontato.required");
        }


        if (CollectionUtils.isNotEmptyOrNull(keys)) {
            throw new DadosIncompletosException(keys);
        }

        try {
            
            if (!this.contratoDAO.getEntityManager().getTransaction().isActive()) {
                this.contratoDAO.getEntityManager().getTransaction().begin();
            }
            
            List<DoencaVO> listaDoencasParaGravar = new ArrayList<DoencaVO>();
            for (DoencaVO doenca : contrato.getCliente().getListaDoencas()) {               
                if (null != doenca.getCrud() && doenca.getCrud().equals(Crud.Delete.getValue())) {
                    this.contratoDAO.deleteDoenca(doenca.getCodigoCID(), contrato.getCliente().getCpf());
                }  else {
                    listaDoencasParaGravar.add(doenca);
                }           
            }
            
            contrato.getCliente().setListaDoencas(new ArrayList<DoencaVO>());
            if (CollectionUtils.isNotEmptyOrNull(listaDoencasParaGravar)) {
                contrato.getCliente().getListaDoencas().addAll(listaDoencasParaGravar);
            }    
            Contrato contratoEntity = ParseUtils.parse(contrato);



            Cliente clienteEntity = contratoEntity.getCliente();
            clienteEntity.getFormaComunicaList().clear();
            clienteEntity.getTratamentoList().clear();
            clienteEntity.getClienteDispositivoList().clear();
            clienteEntity.getContatoList().clear();

            this.contratoDAO.getEntityManager().merge(clienteEntity);
            this.contratoDAO.getEntityManager().flush();

            // Promove exclusões e updates necessárias e que não podem ser feitos em cascata
            tratarAtualizacaoDadosDispositivos(contrato);

            tratarTratamentosCliente(contrato);
            tratarFormaContatocomCliente(contrato, clienteEntity);
            tratarAtualizacaoContato(contrato, clienteEntity);


            contratoEntity.setCliente(null);
            contratoEntity.setNmNomeContratante(contrato.getContratante().getNomeContratante());
            this.contratoDAO.atualizarContrato(contratoEntity);
            contrato.setNumeroContrato(contratoEntity.getNmContrato());
            this.contratoDAO.getEntityManager().getTransaction().commit();
            this.logger.registrarAcao(contrato.getUsuario().getLogin(), "UPDATE: Os dados para o contrato " + contrato.getNumeroContrato() + " foram salvos na base de dados.");
        } catch (Exception e) {
            this.logger.registrarAcao(contrato.getUsuario().getLogin(), "Não foi possível salvar os dados contrato " + contrato.getNumeroContrato() + " exception: " + e.getMessage());
            if (this.contratoDAO.getEntityManager().getTransaction().isActive()) {
                this.contratoDAO.getEntityManager().getTransaction().rollback();
            }
            throw new BusinessException(e);
        }
    }

    /**
     * Nome: obterDiffItensExcluidosDaLista
     * Obter diff itens excluidos da lista.
     *
     * @param list the list
     * @return int
     * @see
     */
    private int obterDiffItensExcluidosDaLista(List<?> list) {
        EqualPredicate equalPredicate = new EqualPredicate(Crud.Delete.getValue());
        BeanPredicate predicate = new BeanPredicate("crud", equalPredicate);
        Collection<?> coll = CollectionUtils.select(list,
            predicate);
        int dif = list.size() - coll.size();

        return dif;
    }

    /**
     * Nome: tratarTratamentosCliente
     * Tratar tratamentos cliente.
     *
     * @param contrato the contrato
     * @throws BusinessException the business exception
     * @see
     */
    private void tratarTratamentosCliente(ContratoVO contrato) throws BusinessException {
        List<TratamentoVO> listaTratamentoTemp = new ArrayList<TratamentoVO>();
        listaTratamentoTemp.addAll(contrato.getCliente().getListaTratamentos());
        try {
            for (TratamentoVO tratamentoVO : listaTratamentoTemp) {
                Tratamento tratamentoEntity = ParseUtils.parse(tratamentoVO, contrato.getCliente()
                    .getCpf());
                if (tratamentoVO.getCrud().equals(Crud.Delete.getValue())) {
                    this.contratoDAO.excluirTratamento(tratamentoEntity);
                    contrato.getCliente().getListaTratamentos().remove(tratamentoVO);
                } else if (tratamentoVO.getCrud().equals(Crud.Update.getValue())) {
                    this.contratoDAO.atualizarTratamento(tratamentoEntity);
                    List<HorarioVO> listaTempHoraio = new ArrayList<HorarioVO>();
                    listaTempHoraio.addAll(tratamentoVO.getListaHorarios());
                    for (HorarioVO horario : listaTempHoraio) {
                        if (horario.getCrud().equals(Crud.Delete.getValue())) {
                            this.contratoDAO.excluirHorarioTratamento(
                                tratamentoVO.getIdTratamento(), horario.getHorario());
                            tratamentoVO.getListaHorarios().remove(horario);
                        } else if (horario.getCrud().equals(Crud.Create.getValue())) {
                            this.contratoDAO.incluirHorarioTratamento(
                                tratamentoVO.getIdTratamento(), contrato.getCliente().getCpf(),
                                horario.getHorario());
                        }
                    }
                } else if (tratamentoVO.getCrud().equals(Crud.Create.getValue())) {
                    this.contratoDAO.incluirTratamento(tratamentoEntity);
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Nome: atualizarDadosListaPessoasDeContatoDoCliente
     * Atualizar dados lista pessoas de contato cliente.
     *
     * @param contrato the contrato
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarDadosListaPessoasDeContatoDoCliente(ContratoVO contrato)
        throws BusinessException {

        validarDadosContratante(contrato);

        Cliente clienteEntity = ParseUtils.parse(contrato.getCliente());

        if (!this.contratoDAO.getEntityManager().getTransaction().isActive()) {
            this.contratoDAO.getEntityManager().getTransaction().begin();
        }
        try {
            this.tratarAtualizacaoContato(contrato, clienteEntity);
            this.contratoDAO.getEntityManager().getTransaction().commit();
        } catch (BusinessException e) {
            if (this.contratoDAO.getEntityManager().getTransaction().isActive()) {
                this.contratoDAO.getEntityManager().getTransaction().rollback();
            }
            throw e;
        }
    }

    /**
     * Nome: validarDadosContratante
     * Validar dados contratante.
     *
     * @param contrato the contrato
     * @see
     */
    public void validarDadosContratante(ContratoVO contrato) {
        ContatoVO contato = (ContatoVO) CollectionUtils.findByAttribute(contrato.getCliente()
            .getListaContatos(), "contratante", true);

        if (null == contato) {
            throw new ContratanteNaoEncontradoException(
                "message.contrato.rule.contratante.uninformed");
        } else {
            contrato.getContratante().setNomeContratante(contato.getNome());
            contrato.getContratante().setContato(contato);
        }
    }

    /**
     * Nome: tratarAtualizacaoContatoEFormaContato Tratar atualizacao contato e forma contato.
     *
     * @param contrato the contrato
     * @param clienteEntity the cliente entity
     * @throws BusinessException the business exception
     * @see
     */
    private void tratarAtualizacaoContato(ContratoVO contrato, Cliente clienteEntity)
        throws BusinessException {
        try {
            List<ContatoVO> listTemp = new ArrayList<ContatoVO>();
            for (ContatoVO contatoVO : contrato.getCliente().getListaContatos()) {
                Contato contatoEntity = ParseUtils.parse(contatoVO);
                if (contatoVO.isContratante()) {
                    contrato.getContratante().setNomeContratante(contatoVO.getNome());
                }
                if (contatoVO.getCrud().equals(Crud.Delete.getValue())) {
                    this.contratoDAO.excluirContato(contatoEntity);
                    listTemp.add(contatoVO);
                } else if (contatoVO.getCrud().equals(Crud.Update.getValue())) {
                    this.contratoDAO.atualizarContato(contatoEntity);
                    tratarFormaComunicacaoContato(clienteEntity, contatoVO, contatoEntity);
                } else if (contatoVO.getCrud().equals(Crud.Create.getValue())) {
                    contatoEntity.setNmCPFCliente(clienteEntity);
                    contatoVO.setIdContato(this.contratoDAO.incluirContato(contatoEntity));
                    contatoVO.setCrud(Crud.Update.getValue());
                }
            }
            if (CollectionUtils.isNotEmptyOrNull(listTemp)) {
                contrato.getCliente().getListaContatos().removeAll(listTemp);
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Nome: tratarFormaComunicacaoContato
     * Tratar forma comunicacao contato.
     *
     * @param clienteEntity the cliente entity
     * @param contatoVO the contato vo
     * @param contatoEntity the contato entity
     * @see
     */
    private void tratarFormaComunicacaoContato(Cliente clienteEntity, ContatoVO contatoVO,
        Contato contatoEntity) {
        // Trata as formas de comunicação com o contato
        List<FormaContatoVO> listaTempFormaContado = new ArrayList<FormaContatoVO>();
        listaTempFormaContado.addAll(contatoVO.getListaFormaContato());
        for (FormaContatoVO formaContatoVO : listaTempFormaContado) {
            formaContatoVO.setIdContato(contatoVO.getIdContato());
            FormaComunica formaContatoEntity = ParseUtils.parse(formaContatoVO);
            formaContatoEntity.setIdContato(contatoEntity);
            formaContatoEntity.setNmCPFCliente(clienteEntity);
            if (formaContatoVO.getCrud().equals(Crud.Delete.getValue())) {
                this.contratoDAO.excluirFormaComunicacao(formaContatoEntity);
                contatoVO.getListaFormaContato().remove(formaContatoVO);
            } else if (formaContatoVO.getCrud().equals(Crud.Update.getValue())) {
                this.contratoDAO.atualizarFormaComunicacao(formaContatoEntity);
            } else if (formaContatoVO.getCrud().equals(Crud.Create.getValue())) {
                this.contratoDAO.getEntityManager().persist(formaContatoEntity);
                this.contratoDAO.getEntityManager().flush();
            }
        }
    }

    /**
     * Nome: tratarFormaContatocomCliente
     * Tratar forma contatocom cliente.
     *
     * @param contrato the contrato
     * @param clienteEntity the cliente entity
     * @throws BusinessException the business exception
     * @see
     */
    private void tratarFormaContatocomCliente(ContratoVO contrato, Cliente clienteEntity)
        throws BusinessException {
        List<FormaContatoVO> listaFormaContatoClienteTemp = new ArrayList<FormaContatoVO>();
        listaFormaContatoClienteTemp.addAll(contrato.getCliente().getListaFormaContato());
        try {
            for (FormaContatoVO formaContatoVO : listaFormaContatoClienteTemp) {
                FormaComunica formaContatoEntity = ParseUtils.parse(formaContatoVO);
                if (formaContatoVO.getCrud().equals(Crud.Delete.getValue())) {
                    this.contratoDAO.excluirFormaComunicacao(formaContatoEntity);
                    contrato.getCliente().getListaFormaContato().remove(formaContatoVO);
                } else if (formaContatoVO.getCrud().equals(Crud.Update.getValue())) {
                    this.contratoDAO.atualizarFormaComunicacao(formaContatoEntity);
                } else if (formaContatoVO.getCrud().equals(Crud.Create.getValue())) {
                    formaContatoEntity.setNmCPFCliente(clienteEntity);
                    this.contratoDAO.getEntityManager().persist(formaContatoEntity);
                    this.contratoDAO.getEntityManager().flush();

                }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Nome: atualizarDadosDispositivos
     * Atualizar dados dispositivos.
     *
     * @param contrato the contrato
     * @see
     */
    public void atualizarDadosDispositivos(ContratoVO contrato) {
        if (!this.contratoDAO.getEntityManager().getTransaction().isActive()) {
            this.contratoDAO.getEntityManager().getTransaction().begin();
        }
        try {
            this.tratarAtualizacaoDadosDispositivos(contrato);
            this.contratoDAO.getEntityManager().getTransaction().commit();
            this.logger.registrarAcao(contrato.getUsuario().getLogin(), "UPDATE: Os dados do dispositivo foram salvos para o contrato " + contrato.getNumeroContrato());
        } catch (BusinessException e) {
            this.logger.registrarAcao(contrato.getUsuario().getLogin(), "UPDATE: Não foi possível salvar os dados do dispositivo para o contrato " + contrato.getNumeroContrato() + " exception: " + e.getMessage());
            if (this.contratoDAO.getEntityManager().getTransaction().isActive()) {
                this.contratoDAO.getEntityManager().getTransaction().rollback();
            }
            throw e;
        }
    }

    /**
     * Nome: tratarAtualizacaoDadosDispositivos Tratar atualizacao dados dispositivos.
     * @param contrato the contrato
     * @see
     */
    private void tratarAtualizacaoDadosDispositivos(ContratoVO contrato) {
        List<DispositivoVO> todosDispositivos = new ArrayList<DispositivoVO>();
        todosDispositivos.addAll(contrato.getCliente().getListaDispositivos());
        todosDispositivos.addAll(contrato.getCliente().getListaCentrais());
        for (DispositivoVO dispositivoVO : todosDispositivos) {
            if (dispositivoVO.getCrud().equals(Crud.Delete.getValue())) {
                this.contratoDAO.excluirDispositivosContrato(dispositivoVO.getIdDispositivo(),  contrato.getCliente().getCpf());
                contrato.getCliente().getListaDispositivos().remove(dispositivoVO);
                contrato.getCliente().getListaCentrais().remove(dispositivoVO);
            }
        }

        // Atualiza os dados da central e dispositivo do cliente.
        String idCentral = null;
        for (DispositivoVO central : contrato.getCliente().getListaCentrais()) {
            idCentral = central.getIdDispositivo();
            if (central.getCrud().equals(Crud.Create.getValue())) {
                if (!this.contratoDAO.existeDispositivoCliente(central.getIdDispositivo(), contrato
                    .getCliente().getCpf())) {
                    this.contratoDAO.incluirDispositivosContrato(central.getIdDispositivo(),
                        contrato.getCliente().getCpf(), null);
                }
            }
        }

        // Dispositivos do cliente;
        for (DispositivoVO dispositivo : contrato.getCliente().getListaDispositivos()) {
            if (!this.contratoDAO.existeDispositivoCliente(dispositivo.getIdDispositivo(), contrato
                .getCliente().getCpf())) {
                if (dispositivo.getCrud().equals(Crud.Create.getValue())) {
                        this.contratoDAO.incluirDispositivosContrato(dispositivo.getIdDispositivo(), contrato.getCliente().getCpf(), -1);                   
                }
            }
        }
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
            List<ClienteDispositivo> retorno = (List<ClienteDispositivo>) this.contratoDAO.getListaCentraisPorEndereco(clienteEntity);
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
            contratosAVencer.add(relatorioVO);
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

    public void atualizarNumeroSequencialDispositivo(String cpf, String idDispositivo, Integer numeroSequencial) throws BusinessException {
        if (!this.contratoDAO.getEntityManager().getTransaction().isActive()) {
            this.contratoDAO.getEntityManager().getTransaction().begin();
        }
        try {
            this.contratoDAO.updateNumeroSequencialDispositivo(cpf, idDispositivo, numeroSequencial);
            this.contratoDAO.getEntityManager().flush();
            this.contratoDAO.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO registrar acao
        }
    }
    
}
