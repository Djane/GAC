package br.com.sw2.gac.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.dao.ContratoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Contato;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.PacoteServico;
import br.com.sw2.gac.modelo.TipoDoenca;
import br.com.sw2.gac.modelo.Tratamento;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.vo.CentralVO;
import br.com.sw2.gac.vo.ClientesAtivosVO;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DesempenhoComercialVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.MovimentacaoClienteVO;
import br.com.sw2.gac.vo.PacoteServicoVO;
import br.com.sw2.gac.vo.TratamentoVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author castilhodaniel
 */
public class ContratoBusiness {

    /** Atributo dao. */
    private final ContratoDAO dao = new ContratoDAO();

    /** Constante TRES. */
    private static final int TRES = 3;

    /**
     * Recupera a lista de pacotes de servicos.
     * @return List<PacoteServicoVO>
     * @see
     */
    public List<PacoteServicoVO> recuperaListaPacoteServico() {
        // Recupera a lista de pacote do banco
        List<PacoteServico> listaPacoteServico = dao.recuperaListaPacoteServico();

        List<PacoteServicoVO> listaVO = new ArrayList<PacoteServicoVO>();

        // Transforma a lista de entities em VOS
        for (PacoteServico pacoteServico : listaPacoteServico) {
            PacoteServicoVO vo = new PacoteServicoVO();
            vo = ObjectUtils.parse(pacoteServico);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Recupera a lista de tratamento.
     * @return List<TratamentoVO>
     * @see
     */
    public List<TratamentoVO> recuperaListaTratamento() {
        // Recupera a lista de tratamento do banco
        List<Tratamento> listaTratamento = dao.recuperaListaTratamento();

        List<TratamentoVO> listaVO = new ArrayList<TratamentoVO>();

        // Transforma a lista de entities em VOS
        for (Tratamento tratamento : listaTratamento) {
            TratamentoVO vo = new TratamentoVO();
            vo = ObjectUtils.parse(tratamento);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Recupera a lista de contato.
     * @return List<ContatoVO>
     * @see
     */
    public List<ContatoVO> recuperaListaContato() {
        // Recupera a lista de contato do banco
        List<Contato> listaContato = dao.recuperaListaContato();

        List<ContatoVO> listaVO = new ArrayList<ContatoVO>();

        // Transforma a lista de entities em VOS
        for (Contato contato : listaContato) {
            ContatoVO vo = new ContatoVO();
            vo = ObjectUtils.parse(contato);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Recupera o valor do atributo 'listaCentral'.
     * @return List<CentralVO>
     * @see
     */
    public List<CentralVO> recuperaListaVO() {
        List<CentralVO> lista = new ArrayList<CentralVO>();
        CentralVO central = new CentralVO();
        central.setIdCentral(1);
        central.setDescricaoCentral("Doença 1");
        lista.add(central);
        central = new CentralVO();
        central.setIdCentral(2);
        central.setDescricaoCentral("Doença 2");
        lista.add(central);
        central = new CentralVO();
        central.setIdCentral(TRES);
        central.setDescricaoCentral("Doença 3");
        lista.add(central);

        return lista;
    }

    /**
     * Recupera a lista de dispositivo.
     * @return List<DispositivoVO>
     * @see
     */
    public List<DispositivoVO> recuperaListaDispositivo() {
        // Recupera a lista de dispositivo do banco
        List<Dispositivo> listaDispositivo = dao.recuperaListaDispositivo();

        List<DispositivoVO> listaVO = new ArrayList<DispositivoVO>();

        // Transforma a lista de entities em VOS
        for (Dispositivo dispositivo : listaDispositivo) {
            DispositivoVO vo = new DispositivoVO();
            vo = ObjectUtils.parse(dispositivo);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Recupera a lista de doenca.
     * @return List<DoencaVO>
     * @see
     */
    public List<DoencaVO> recuperaListaDoenca() {
        // Recupera a lista de doenca do banco
        List<TipoDoenca> listaDoenca = dao.recuperaListaDoenca();

        List<DoencaVO> listaVO = new ArrayList<DoencaVO>();

        // Transforma a lista de entities em VOS
        for (TipoDoenca doenca : listaDoenca) {
            DoencaVO vo = new DoencaVO();
            vo = ObjectUtils.parse(doenca);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Recupera a lista de contrato.
     * @return List<ContratoVO>
     * @see
     */
    public List<ContratoVO> recuperaListaContrato() {
        // Recupera a lista de doenca do banco
        List<Contrato> listaContrato = dao.recuperaListaContrato();

        List<ContratoVO> listaVO = new ArrayList<ContratoVO>();

        // Transforma a lista de entities em VOS
        for (Contrato contrato : listaContrato) {
            ContratoVO vo = new ContratoVO();
            vo = ObjectUtils.parse(contrato);
            listaVO.add(vo);
        }
        return listaVO;
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
            dao.gravar(entity);
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

        DesempenhoComercialVO retorno = new DesempenhoComercialVO();

        Date inicioPeriodo = DateUtil.getPrimeiroDiaMes(dataReferencia);
        Date fimPeriodo = DateUtil.getUltimoDiaMes(dataReferencia);

        // Lista com a movimentação diária de contratos/Clientes
        List<MovimentacaoClienteVO> listaMovimentacaoCliente = new ArrayList<MovimentacaoClienteVO>();
        int primeiroDia = DateUtil.getDia(inicioPeriodo);
        int ultimoDia = DateUtil.getDia(fimPeriodo);
        // Monta a lista com todos os dias do mês;
        for (int i = primeiroDia; i <= ultimoDia; i++) {
            MovimentacaoClienteVO movimentacao = new MovimentacaoClienteVO();
            movimentacao.setDia(i);
            listaMovimentacaoCliente.add(movimentacao);
        }

        // Recuperar os contratos iniciados (Entrantes) no periodo informado
        List<Object[]> listaContratosNovos = (List<Object[]>) this.dao.getNovosContratosPeriodo(
                inicioPeriodo, fimPeriodo);
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

        // Obter lista de contratos cancelados no periodo informado
        List<Object[]> listaContratosCancelados = (List<Object[]>) this.dao
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

        // Obtem a lista de clientes ativos agrupados por pacotes de serviço
        List<Object[]> listaClientesAtivosPorPacote = (List<Object[]>) this.dao
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

        // Obter a quantidade de contratos/Clientes ativos no mês.
        retorno.setQtdClientesInicioMes(dao.getListaContratosAtivosInicioMes(inicioPeriodo));
        retorno.setMovimentacaoClientes(listaMovimentacaoCliente);
        retorno.setClientesAtivos(listaClientesAtivos);
        retorno.setQtdeClientesAtivos(qtdeTotalClienteAtivos);

        return retorno;

    }
}
