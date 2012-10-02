package br.com.sw2.gac.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.dao.ContratoDAO;
import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Cliente;
import br.com.sw2.gac.modelo.ClienteDispositivo;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.ClienteVO;
import br.com.sw2.gac.vo.ClientesAtivosVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DesempenhoComercialVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.MovimentacaoClienteVO;
import br.com.sw2.gac.vo.RelContratosAVencerVO;

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
            Contrato contrato = (Contrato) this.contratoDAO.getEnityById(numeroContrato);
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
            retorno.add(ObjectUtils.parse(entity));
        }
        return retorno;
    }

    /**
     * Adicionar Novo Contrato.
     * @param contrato VO do contrato
     * @throws BusinessException Exception do business
     * @see
     */
    public void adicionarNovoContrato(ContratoVO contrato) throws BusinessException {

        Contrato entity = ObjectUtils.parse(contrato);

        try {
            contratoDAO.gravar(entity);
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
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
            EqualPredicate equalPredicate = new EqualPredicate(dia);
            BeanPredicate beanPredicate = new BeanPredicate("dia", equalPredicate);
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils.find(
                listaMovimentacaoCliente, beanPredicate);
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
            EqualPredicate equalPredicate = new EqualPredicate(dia);
            BeanPredicate beanPredicate = new BeanPredicate("dia", equalPredicate);
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils.find(
                listaMovimentacaoCliente, beanPredicate);
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
            EqualPredicate equalPredicate = new EqualPredicate(dia);
            BeanPredicate beanPredicate = new BeanPredicate("dia", equalPredicate);
            MovimentacaoClienteVO diaEncontrado = (MovimentacaoClienteVO) CollectionUtils.find(
                listaMovimentacaoCliente, beanPredicate);
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
     * @throws BusinessException the business exception
     * @see
     */
    public void gravarNovoContrato(ContratoVO contrato) throws BusinessException {
        Contrato entity = ObjectUtils.parse(contrato);
        this.contratoDAO.gravarNovoContrato(entity);
    }

    /**
     * Nome: centralAceitaNovosDispositivos Central aceita novos dispositivos.
     * @param idCentral the id central
     * @return true, se sucesso, senão false
     * @see
     */
    public boolean centralAceitaNovosDispositivos(String idCentral) {
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
     * @see
     */
    public DispositivoVO obterCentralDisponivelParaEndereco(ClienteVO cliente) {
        Cliente clienteEntity = ObjectUtils.parse(cliente);
        DispositivoVO dispositivo = null;
        List<ClienteDispositivo> retorno = (List<ClienteDispositivo>) this.contratoDAO
            .getListaCentraisPorEndereco(clienteEntity);
        for (ClienteDispositivo cd : retorno) {
            if (this.centralAceitaNovosDispositivos(cd.getDispositivo().getIdDispositivo())) {
                dispositivo = new DispositivoVO();
                dispositivo.setIdDispositivo(cd.getDispositivo().getIdDispositivo());
                break;
            }
        }
        return dispositivo;
    }

	/**
	 * Recupera os contratos a vencer em (n) dias.
	 * @param diasAVencer dias a vencer
	 * @return contratos a vencer
	 */
	public List<RelContratosAVencerVO> recuperarContratosAtivosAVencerEm(
			final Integer diasAVencer) {
		// Lista com a movimentação diária de contratos/Clientes
		List<RelContratosAVencerVO> contratosAVencer = new ArrayList<RelContratosAVencerVO>();
		List<Object[]> contratos = this.contratoDAO
				.recuperarContratosAtivosAVencerEm(diasAVencer);
		for (Object[] contrato : contratos) {
			RelContratosAVencerVO relatorioVO = new RelContratosAVencerVO();
			relatorioVO.setNroContrato(Long.valueOf(contrato[0].toString()));
			relatorioVO.setInicioVigencia((Date) contrato[0]);
			relatorioVO.setFimVigencia((Date) contrato[0]);
			relatorioVO.setPacote(contrato[0].toString());
		}
		return contratosAVencer;
	}
}
