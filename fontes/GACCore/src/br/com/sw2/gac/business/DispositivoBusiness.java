package br.com.sw2.gac.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.DispositivoEstadoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.RelHistDispositivoVO;

/**
 * <b>Descrição: Classe de negócio responsável por ações com dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DispositivoBusiness {

    /** Constante TAMANHO_ID_DISPOSITIVO. */
    private static final int TAMANHO_ID_DISPOSITIVO = 13;

    /** Atributo dao. */
    private DispositivoDAO dao = new DispositivoDAO();

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(this);

    /**
     * Adicionar um dispositivo.
     * @param dispositivo VO do Dispositivo
     * @param idOriginal Envia o id original do dispositivo em caso de edição, ou null se for novo
     *            dispositivo
     * @throws BusinessException Exceção do business
     * @see
     */
    public void adicionarNovoDispositivo(DispositivoVO dispositivo, String idOriginal)
        throws BusinessException {

        verificarDispositivoValido(dispositivo);

        // Se dispositivo é novo ou se editou o id de um dispositivo existente, deve validar
        // duplicidade
        if (!dispositivo.getIdDispositivo().equalsIgnoreCase(idOriginal)) {
            verificarDispositivoDuplicado(dispositivo);
        }

        Dispositivo entity = ObjectUtils.parse(dispositivo);

        try {
            dao.gravar(entity);
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }

    }

    /**
     * Verifica se o dispositivo e seu ID não são nulos e se seu ID tem o tamanho esperado.
     * @param dispositivo VO do Dispositivo
     * @throws BusinessException the business exception
     * @see
     */
    public void verificarDispositivoValido(DispositivoVO dispositivo) throws BusinessException {

		if (null == dispositivo || StringUtil.isVazio(dispositivo.getIdDispositivo(), true)) {
            throw new BusinessException(BusinessExceptionMessages.SALVAR_DISPOSITIVO_DADOS_INVALIDOS);
        }

        if (dispositivo.getIdDispositivo().length() != TAMANHO_ID_DISPOSITIVO) {
            throw new BusinessException(BusinessExceptionMessages.ID_DISPOSITIVO_TAMANHO_INVALIDO);
        }

        try {
			Long.parseLong(dispositivo.getIdDispositivo());
		} catch (NumberFormatException e) {
			throw new BusinessException(BusinessExceptionMessages.ID_DISPOSITIVO_VALOR_INVALIDO);
		}
    }

    /**
     * Verifica se já não existe um Dispositivo com o Id informado.
     * @param dispositivo Vo do Dispositivo
     * @throws BusinessException the business exception
     * @see
     */
    public void verificarDispositivoDuplicado(DispositivoVO dispositivo) throws BusinessException {

        Dispositivo existeId = this.dao.recuperaDispositivoPeloId(dispositivo.getIdDispositivo());

        if (null != existeId) {
            throw new BusinessException(BusinessExceptionMessages.DISPOSITIVO_DUPLICADO);
        }
    }

    /**
     * Exclusão do dispositivo.
     * @param id ID do dispositivo
     * @throws BusinessException exceção
     * @see
     */
    public void apagarDispositivo(String id) throws BusinessException {

        try {
            dao.apagar(id);
        } catch (DataBaseException exception) {
            if (exception.getExceptionCode() == DataBaseException.DELETE_VIOLACAO_CONSTRAINT) {
                throw new BusinessException(BusinessExceptionMessages.DELETE_DISPOSITIVO_EM_USO);
            }
        }
    }

    /**
     * Recupera a lista de Dispositivos.
     * @return List<DispositivoVO>
     * @see
     */
    public List<DispositivoVO> recuperaListaDispositivos() {
        // Recupera a lista de dispositivos do banco
        List<Dispositivo> listaDispositivos = dao.recuperaListaDispositivos();

        List<DispositivoVO> listaVO = new ArrayList<DispositivoVO>();

        // Transforma a lista de entities em VOS
        for (Dispositivo dispositivo : listaDispositivos) {
            DispositivoVO vo = new DispositivoVO();
            vo = ObjectUtils.parse(dispositivo);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Recupera a lista de Dispositivos que estão em determinado estado.
     * @param estado a ser pesquisado
     * @return List<DispositivoVO>
     * @see
     */
    public List<DispositivoVO> recuperaListaPulseiraECentralPorEstado(Integer estado) {
        // Recupera a lista de dispositivos do banco
        List<Dispositivo> listaDispositivos = dao.recuperaPulseiraECentralPeloEstado(estado);

        List<DispositivoVO> listaVO = new ArrayList<DispositivoVO>();

        // Transforma a lista de entities em VOS
        for (Dispositivo dispositivo : listaDispositivos) {
            DispositivoVO vo = new DispositivoVO();
            vo = ObjectUtils.parse(dispositivo);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Nome: recuperaQtdeDispositivosPorEstado Retorna uma lista com as quantidade de dispositivos
     * agrupados por tipo de dispositivo e estado do dispositivo.
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<DispositivoEstadoVO> recuperaQtdeDispositivosPorEstado() throws BusinessException {
        this.logger.debug("***** Iniciando método recuperaQtdeDispositivosPorEstado *****");
        final int tetoPorcentagem = 100;
        List<Object[]> list = this.dao.recuperaQtdeDispositivosPorEstado();
        List<DispositivoEstadoVO> listaDispositivosEstado = new ArrayList<DispositivoEstadoVO>();
        Long qtdeDispositivosTotal = 0L;
        int coluna = 0;
        if (list == null || list.isEmpty()) {
            this.logger.debug("Não há registros");
        } else {
            try {
                this.logger.debug("Encontrado " + list.size()
                        + " registros ");
                for (Object[] item : list) {
                    this.logger.debug(" Iterando/Lendo dados da linha ... ");
                    this.logger.debug(" Valor da coluna 1: " + item[coluna]);
                    String descricaoTipo = getDescricaoTipoDispositivo((Integer) item[coluna]);
                    coluna++;
                    this.logger.debug(" Valor da coluna 2: " + item[coluna]);
                    String descricaoEstado = getDescricaoEstado((Integer) item[coluna]);
                    coluna++;
                    this.logger.debug(" Valor da coluna 3: " + item[coluna]);
                    Long qtdeDispositivosDoTipo = (Long) item[coluna];
                    coluna++;
                    this.logger.debug(" Valor da coluna 4: " + item[coluna]);
                    qtdeDispositivosTotal = (Long) item[coluna];
                    coluna = 0;
                    double porcentagemDotipo = 0;
                    if (qtdeDispositivosTotal > 0) {
                        porcentagemDotipo = (qtdeDispositivosDoTipo * tetoPorcentagem)
                                / qtdeDispositivosTotal;
                    }
                    listaDispositivosEstado.add(new DispositivoEstadoVO(descricaoTipo, descricaoEstado,
                            qtdeDispositivosDoTipo.intValue(), new BigDecimal(porcentagemDotipo)));
                    this.logger.debug(" Fim da linha ... ");
                }
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }
        this.logger.debug("***** Finalizado método recuperaQtdeDispositivosPorEstado *****");
        return listaDispositivosEstado;
    }

    /**
     * Nome: getDescricaoEstado Recupera a descrição de um estado de dispositivo através de seu
     * código.
     * @param tpEstado the tp estado
     * @return valor do atributo 'descricaoEstado'
     * @see
     */
    public String getDescricaoEstado(Integer tpEstado) {
        String retorno = "";
        for (EstadoDispositivo item : EstadoDispositivo.values()) {
            if (item.getValue().equals(tpEstado)) {
                retorno = item.getLabel();
                break;
            }
        }
        return retorno;
    }

    /**
     * Nome: getDescricaoTipoDispositivo Recupera a descrição de um dispositivo atraves de seu
     * código.
     * @param tpDispositivo the tp dispositivo
     * @return valor do atributo 'descricaoTipoDispositivo'
     * @see
     */
    public String getDescricaoTipoDispositivo(Integer tpDispositivo) {
        String retorno = "";
        for (TipoDispositivo item : TipoDispositivo.values()) {
            if (item.getValue().equals(tpDispositivo)) {
                retorno = item.getLabel();
                break;
            }
        }
        return retorno;
    }

    /**
     * Retorna uma lista com o histórico de dispositivos agrupado por dispositivo.
     * @param relatorio VO do relatorio
     * @return list RelHistDispositivoVO
     * @throws BusinessException the business exception
     */
    public List<RelHistDispositivoVO> recuperaHistDispositivos(RelHistDispositivoVO relatorio) throws BusinessException {
    	String id = relatorio.getIdDispositivo();
    	Integer estadoAtual = relatorio.getEstadoAtualParam();
    	String login = relatorio.getLogin();
    	Date dataMovimentacao = relatorio.getDataMovimentacao();

        // Pelo menos um dos campos da tela deve estar preenchido
    	if ((id == null || id.isEmpty()) && estadoAtual == null
    			&& dataMovimentacao == null && (login == null || login.isEmpty())) {
    		throw new BusinessException(BusinessExceptionMessages.PARAMETRO_OBRIGATORIO_RELATORIO_HISTDISPOSITIVO);
    	}

    	List<Object[]> lista = dao.recuperaRelHistDispositivo(id, estadoAtual, dataMovimentacao, login);

    	List<RelHistDispositivoVO> listaRelatorios = new ArrayList<RelHistDispositivoVO>();
    	for (Object[] item : lista) {
    		RelHistDispositivoVO relHistDispositivo = new RelHistDispositivoVO();
        	int coluna = 0;
        	relHistDispositivo.setIdDispositivo((String) item[coluna++]);
        	relHistDispositivo.setDataMovimentacao((Date) item[coluna++]);
        	EstadoDispositivo estado = EstadoDispositivo.getEstadoPeloValor((Integer) item[coluna++]);
        	relHistDispositivo.setEstadoAtual(estado.getLabel());
        	estado = EstadoDispositivo.getEstadoPeloValor((Integer) item[coluna++]);
        	relHistDispositivo.setEstadoOrigem(estado.getLabel());
        	relHistDispositivo.setLogin((String) item[coluna]);

        	listaRelatorios.add(relHistDispositivo);
		}

        return listaRelatorios;
    }


}
