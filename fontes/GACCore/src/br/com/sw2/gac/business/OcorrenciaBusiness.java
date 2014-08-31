package br.com.sw2.gac.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.dao.ContratoDAO;
import br.com.sw2.gac.dao.OcorrenciaDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.StatusOcorrenciaException;
import br.com.sw2.gac.filtro.FiltroPesquisarPreAtendimento;
import br.com.sw2.gac.modelo.Acionamento;
import br.com.sw2.gac.modelo.Cliente;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.Ocorrencia;
import br.com.sw2.gac.tools.StatusOcorrencia;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.AcionamentoVO;
import br.com.sw2.gac.vo.ClienteVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.RelChamadasPorOrigemVO;
import br.com.sw2.gac.vo.RelOcorrenciasAbertoVO;
import br.com.sw2.gac.vo.ResumoOcorrenciaVO;
import br.com.sw2.mock.DataSourceChamadasOrigem;

/**
 * <b>Descrição: Classe de negócio para operações relacionadas com os atendimentos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaBusiness extends BaseBusiness implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -9063997160471980539L;

    /** Atributo ocorrencia dao. */
    private OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

    /** Atributo contrato dao. */
    private ContratoDAO contratoDAO = new ContratoDAO();
    /**
     * Nome: obterOcorrenciasEmAberto Obter resumo das ocorrencias em aberto .
     * @return list
     * @see
     */
    @SuppressWarnings("unchecked")
    public RelOcorrenciasAbertoVO obterOcorrenciasEmAberto() {

        List<Ocorrencia> listEntity = this.ocorrenciaDAO.filtarOcorrenciasPorDataFechamento(null);

        List<OcorrenciaVO> listVO = new ArrayList<OcorrenciaVO>();
        for (Ocorrencia item : listEntity) {
            listVO.add(ParseUtils.parse(item));
        }

        List<OcorrenciaVO> listOcorrenciasAgrupadas = new ArrayList<OcorrenciaVO>();
        List<ResumoOcorrenciaVO> listResumo = new ArrayList<ResumoOcorrenciaVO>();
        RelOcorrenciasAbertoVO ocorrenciasEmAberto = new RelOcorrenciasAbertoVO();
        for (TipoOcorrencia item : TipoOcorrencia.values()) {

            EqualPredicate equalPredicate = new EqualPredicate(item.getValue());
            BeanPredicate predicate = new BeanPredicate("tipoOcorrencia.codigoTipoOcorrencia",
                equalPredicate);

            List<OcorrenciaVO> list = ((List<OcorrenciaVO>) CollectionUtils.select(listVO,
                predicate));
            if (CollectionUtils.isNotEmptyOrNull(list)) {
                ResumoOcorrenciaVO resumo = new ResumoOcorrenciaVO();
                resumo.setQuantidadeOcorrencias(list.size());
                resumo.setTipoOcorrencia(list.get(0).getTipoOcorrencia());
                resumo.setPorcentagem(super.calcularPorcentagem(list.size(), listVO.size()));
                listResumo.add(resumo);
                listOcorrenciasAgrupadas.addAll(list);
            }

        }
        ocorrenciasEmAberto.setResumo(listResumo);
        ocorrenciasEmAberto.setOcorrencias(listOcorrenciasAgrupadas);

        return ocorrenciasEmAberto;
    }

    /**
     * Retorna uma lista com o histórico de dispositivos agrupado por dispositivo.
     *
     * @param relatorio VO do relatorio
     * @return list RelHistDispositivoVO
     * @throws BusinessException the business exception
     * @see
     */
    public List<RelChamadasPorOrigemVO> recuperaChamadasPorOrigem(RelChamadasPorOrigemVO relatorio) throws BusinessException {
        Date periodoInicial = relatorio.getPerInicio();
        Date periodoFinal = relatorio.getPerFim();

        List<RelChamadasPorOrigemVO> listaRetorno = new ArrayList<RelChamadasPorOrigemVO>();
        // Pelo menos um dos campos da tela deve estar preenchido
        if ((periodoInicial == null) || (periodoFinal == null)) {
            throw new BusinessException(
                BusinessExceptionMessages.PARAMETRO_OBRIGATORIO_RELATORIO_CHAMADAS_ORIGEM);
        } else {
            listaRetorno.addAll((List<RelChamadasPorOrigemVO>) DataSourceChamadasOrigem.createBeanCollection());
        }

        /*
        List<Object[]> lista = this.ocorrenciaDAO.recuperaRelChamadasOrigem(periodoInicial, periodoFinal);

        List<RelChamadasPorOrigemVO> listaRelatorios = new ArrayList<RelChamadasPorOrigemVO>();
        for (Object[] item : lista) {
            RelChamadasPorOrigemVO relChamadasPorOrigem = new RelChamadasPorOrigemVO();
            int coluna = 0;
            relHistChamadasPorOrigem.set.setIdDispositivo((String) item[coluna++]);
            relHistChamadasPorOrigem.setDataMovimentacao((Date) item[coluna++]);
            relHistChamadasPorOrigem estado = EstadoDispositivo.getEstadoPeloValor((Integer) item[coluna++]);
            relHistChamadasPorOrigem.setEstadoAtual(estado.getLabel());
            relHistChamadasPorOrigem = EstadoDispositivo.getEstadoPeloValor((Integer) item[coluna++]);
            relHistChamadasPorOrigem.setEstadoOrigem(estado.getLabel());
            relHistChamadasPorOrigem.setLogin((String) item[coluna]);

            listaRelatorios.add(relHistDispositivo);
        }*/

        return listaRetorno;
    }

    /**
     * Nome: gravarNovaOcorrencia
     * Gravar nova ocorrencia.
     *
     * @param ocorrencia the ocorrencia
     * @return integer
     * @throws BusinessException the business exception
     * @see
     */
    public Integer gravarNovaOcorrencia(OcorrenciaVO ocorrencia) throws BusinessException {
        Ocorrencia entity = ParseUtils.parse(ocorrencia);
        try {
            this.ocorrenciaDAO.inserir(entity);
        } catch (BusinessException e) {
            throw new BusinessException(e);
        }

        return entity.getIdOcorrencia();
    }

    /**
     * Nome: salvarDadosOcorrenciaEmAtendimento
     * Salvar dados ocorrencia em atendimento.
     *
     * @param ocorrencia the ocorrencia
     * @throws BusinessException the business exception
     * @see
     */
    public void salvarDadosOcorrenciaEmAtendimento(OcorrenciaVO ocorrencia)
        throws BusinessException {
        Ocorrencia entity = ParseUtils.parse(ocorrencia);

        if (ocorrencia.getStatusOcorrencia().intValue() != StatusOcorrencia.Fechado.getValue().intValue()
            && ocorrencia.getStatusOcorrencia().intValue() != StatusOcorrencia.EmEspera.getValue().intValue()) {
            throw new StatusOcorrenciaException("O status informado não é valido para gravação da ocorrencia !");
        } else {

            try {
                if (ocorrencia.getStatusOcorrencia().intValue() == StatusOcorrencia.Fechado.getValue().intValue()) {
                    Date dataFechamento = new Date();
                    
                    if (null == ocorrencia.getDataHoraFechamentoOcorrencia()) {
                        ocorrencia.setDataHoraFechamentoOcorrencia(dataFechamento);    
                    }
                    
                    if (null == ocorrencia.getDataHoraTerminoContato()) {
                        
                        if (ocorrencia.getSnDispositivo() != null && 
                            (ocorrencia.getSnDispositivo().intValue() != 92 && 
                            ocorrencia.getSnDispositivo().intValue() != 93 && 
                            ocorrencia.getSnDispositivo().intValue() != 94 && 
                            ocorrencia.getSnDispositivo().intValue() != 96 && 
                            ocorrencia.getSnDispositivo().intValue() != 97)) {
                        
                            entity.setDtaHoraFechamento(dataFechamento);
                        }
                    }
                    
                    if (null == entity.getDtaHoraFechamento()) {
                        entity.setDtaHoraFechamento(dataFechamento);
                    }
                }
                
                
                if (null != ocorrencia.getDataHoraInicioContato()  && 
                    (ocorrencia.getSnDispositivo().intValue() == 92 || 
                    ocorrencia.getSnDispositivo().intValue() == 93 || 
                    ocorrencia.getSnDispositivo().intValue() == 94 || 
                    ocorrencia.getSnDispositivo().intValue() == 96 || 
                    ocorrencia.getSnDispositivo().intValue() == 97)) {
                    
                    entity.setDtaHoraInicio(null);
                }                
                
                this.ocorrenciaDAO.gravar(entity);
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }

    }

    /**
     * Nome: pesquisarContratosPreAtendimento Pesquisar contratos pre atendimento.
     * @param filtro the filtro
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<ContratoVO> pesquisarContratosPreAtendimento(FiltroPesquisarPreAtendimento filtro)
        throws BusinessException {

        if (null == filtro) {
            throw new BusinessException(BusinessExceptionMessages.FILTRO_PESQUISA_PRE_ATENDIMENTO_NAO_INFORMADO);
        } else {

            if (null == filtro.getNumeroContrato()
                && StringUtil.isEmpty(filtro.getNomeCliente(), true)
                && StringUtil.isEmpty(filtro.getTelefone(), true)
                && StringUtil.isEmpty(filtro.getNumeroCPFCliente(), true)) {
                throw new BusinessException(BusinessExceptionMessages.FILTRO_PESQUISA_PRE_ATENDIMENTO_NAO_INFORMADO);
            }

            List<Contrato> listEntity = this.contratoDAO.getListaContratos(filtro);

            return ParseUtils.parseContratoEntityList(listEntity);
        }

    }

    public List<ContratoVO> pesquisarContratosPorNumeroContatoCliente(String numeroTelefone) throws BusinessException {
            List<Contrato> listEntity = this.contratoDAO.getListaContratos(numeroTelefone);
            return ParseUtils.parseContratoEntityList(listEntity);
    }

    
    /**
     * Nome: obterOcorrenciaPendenteDoCliente
     * Obter ocorrencia pendente do cliente.
     *
     * @param cliente the cliente
     * @return ocorrencia vo
     * @throws BusinessException the business exception
     * @see
     */
    public OcorrenciaVO obterOcorrenciaPendenteDoCliente(ClienteVO cliente)
        throws BusinessException {
        OcorrenciaVO vo = null;
        Cliente entityParam = ParseUtils.parse(cliente);
        try {
            Ocorrencia entity = this.ocorrenciaDAO.obterOcorrenciaPendenteDoCliente(entityParam);
            if (null != entity) {
                vo = ParseUtils.parse(entity);
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        return vo;
    }

    /**
     * Nome: obterListaOcorrenciaDoCliente
     * Obter lista ocorrencia do cliente.
     *
     * @param cliente the cliente
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<OcorrenciaVO> obterListaOcorrenciaDoCliente(ClienteVO cliente) throws BusinessException {
        List<OcorrenciaVO> list = new ArrayList<OcorrenciaVO>();
        Cliente entity = ParseUtils.parse(cliente);
        try {
            List<Ocorrencia> listEntity = this.ocorrenciaDAO.obterOcorrenciasDoCliente(entity);

            for (Ocorrencia ocorrenciaEntity : listEntity) {
                list.add(ParseUtils.parse(ocorrenciaEntity));
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        return list;
    }

    /**
     * Nome: registrarNovaLigacaoPessoaDeContatoCliente
     * Registrar nova ligacao pessoa de contato cliente.
     *
     * @param vo the vo
     * @return integer
     * @see
     */
    public AcionamentoVO registrarNovaLigacaoPessoaDeContatoCliente(AcionamentoVO vo) {

        Acionamento entity = ParseUtils.parse(vo);

        if (null == vo.getDataHoraInicioConversa()) {
            entity.setSucesso('N');
        } else {
            entity.setSucesso('S');
        }

        if (!this.ocorrenciaDAO.getEntityManager().getTransaction().isActive()) {
            this.ocorrenciaDAO.getEntityManager().getTransaction().begin();
        }
        this.ocorrenciaDAO.getEntityManager().persist(entity);
        this.ocorrenciaDAO.getEntityManager().flush();
        this.ocorrenciaDAO.getEntityManager().getTransaction().commit();

        vo.setIdAcionamento(entity.getIdAciona());
        return vo;

    }


    /**
     * Nome: encerrarLigacaoPessoaDeContatoCliente
     * Encerrar ligacao pessoa de contato cliente.
     *
     * @param vo the vo
     * @throws BusinessException the business exception
     * @see
     */
    public void encerrarLigacaoPessoaDeContatoCliente(AcionamentoVO vo) throws BusinessException {

        try {
            if (!this.ocorrenciaDAO.getEntityManager().getTransaction().isActive()) {
                this.ocorrenciaDAO.getEntityManager().getTransaction().begin();
            }
            this.ocorrenciaDAO.encerrarLigacaoPessoaDeContatoCliente(vo.getIdAcionamento(),
                    vo.getDataHoraFinalConversa());
            this.ocorrenciaDAO.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (this.ocorrenciaDAO.getEntityManager().getTransaction().isActive()) {
                this.ocorrenciaDAO.getEntityManager().getTransaction().rollback();
            }
            throw new BusinessException(e);
        }

    }

    /**
     * Nome: registrarEnvioSmsPessoaDeContatoCliente
     * Registrar envio sms pessoa de contato cliente.
     *
     * @param vo the vo
     * @throws BusinessException the business exception
     * @see
     */
    public void registrarEnvioSmsPessoaDeContatoCliente(AcionamentoVO vo) throws BusinessException {

        Acionamento entity = ParseUtils.parse(vo);
        try {
            this.ocorrenciaDAO.getEntityManager().getTransaction().begin();
            this.ocorrenciaDAO.getEntityManager().persist(entity);
            this.ocorrenciaDAO.getEntityManager().flush();
            this.ocorrenciaDAO.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (!this.ocorrenciaDAO.getEntityManager().getTransaction().isActive()) {
                this.ocorrenciaDAO.getEntityManager().getTransaction().rollback();
            }
            throw new BusinessException(e);
        }

    }

    /**
     * Nome: obterDadosNovaLigacaoAtendente
     * Obter dados nova ligacao atendente.
     *
     * @param idUniqueid the id uniqueid
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
 /*   public LigacaoVO obterDadosNovaLigacaoAtendente(String idUniqueid) throws BusinessException {

        try {
            Ligacao entity = this.telefoniaDAO.obterDadosNovaLigacaoAtendente(idUniqueid);

            List<ContratoVO> contratosCliente = new ArrayList<ContratoVO>();
            LigacaoVO ligacaoVO = null;
            if (null != entity) {
                ligacaoVO = new LigacaoVO();
                ligacaoVO.setIdUniqueid(entity.getIdUniqueid());
                ligacaoVO.setNumeroTelefoneOrigem(entity.getNumTelefone());
                ligacaoVO.setCodigoEnviadoPulseira(entity.getCodPulseira());
                FiltroPesquisarPreAtendimento filtro = new FiltroPesquisarPreAtendimento();
                filtro.setTelefone(ligacaoVO.getNumeroTelefoneOrigem());

                contratosCliente = this.pesquisarContratosPreAtendimento(filtro);
                ligacaoVO.setListaContratosCliente(contratosCliente);
            }
            return ligacaoVO;

        } catch (Exception e) {
            throw new BusinessException("Não é possível oter os dados da ligação na base de dados do intelix");
        }
    }
*/

}
