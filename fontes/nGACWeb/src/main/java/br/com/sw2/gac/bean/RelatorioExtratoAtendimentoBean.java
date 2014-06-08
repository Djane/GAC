package br.com.sw2.gac.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.util.JasperHelper;
import br.com.sw2.gac.vo.AtendimentoEmAndamentoVO;
import br.com.sw2.gac.vo.FilaAtendimentoVO;
import br.com.sw2.gac.vo.RelExtratoAtendimentoVO;

/**
 * Bean para tratar relatório Extrato de atendimento.
 * @author ddiniz
 *
 */
@ManagedBean
@ViewScoped
public class RelatorioExtratoAtendimentoBean extends MenuBean {

	private static final long serialVersionUID = 4296260430231929979L;

	private RelExtratoAtendimentoVO relatorio;


	/**
     * Imprimir relatório Extrato de atendimento.
     * @param ae ActionEvent
     */
    public void imprimirExtratoAtendimento(ActionEvent ae) {
    	this.getLogger().debug("***** Iniciando método imprimirExtratoAtendimento(ActionEvent ae) *****");

        try {
        	//Obtem os dados que serão exibidos no relatório
            relatorio = popularDadosMock();
    		// TODO Implementar o negócio quando estiver disponível
    		// relatorio = business.recuperaExtratoAtendimento(this.relatorio);
            List<RelExtratoAtendimentoVO> lista = new ArrayList<RelExtratoAtendimentoVO>();
            lista.add(relatorio);
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("TOTAL_EM_ANDAMENTO", relatorio.getAtendimentoEmAndamento().size());
            parameters.put("TOTAL_EM_FILA", relatorio.getFilaAtendimento().size());

            //Determinar a localização do subreport
            String reportdir = JasperHelper.getRealPathReport("br/com/sw2/gac/jasper/report/", "extratoAtendimento.jasper");
            parameters.put("SUBREPORT_DIR", reportdir);

            //super.imprimirRelatorioPadrao(EXTRATO_ATENDIMENTO, lista, parameters);

            JasperHelper.saveSessionAtributes(getHttpServLetRequest(),
                "extratoAtendimento.jasper", parameters, lista);
            addCallbackValidationError(false);

        } catch (Exception e) {
            this.getLogger().error("***** Erro ao imprimir relatorio de desempenho comercial *****");
            this.getLogger().error(e);
            addCallbackValidationError(true);
        }
        this.getLogger().debug("***** Finalizado método imprimirExtratoAtendimento(ActionEvent ae) *****");
    }

	public RelExtratoAtendimentoVO getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(RelExtratoAtendimentoVO relatorio) {
		this.relatorio = relatorio;
	}

	// TODO REMOVER QUANDO A IMPLEMENTAÇÃO DO NEGÓCIO ESTIVER DISPONÍVEL
	// Método criado apenas para testar o relatório
	private RelExtratoAtendimentoVO popularDadosMock() {
		Date data = null, hora = null;
		try {
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			data = formatador.parse("09/10/2012 18:15:00");
			formatador = new SimpleDateFormat("HH:mm:ss");
			hora = formatador.parse("01:12:46");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		List<FilaAtendimentoVO> listaFila = new ArrayList<FilaAtendimentoVO>();
		for (int i = 0; i < 5; i++) {
			FilaAtendimentoVO fila = new FilaAtendimentoVO();
			fila.setIdAtendimento(i);
			fila.setContrato(i * 2);
			if (i == 0)	{
				fila.setPrioridade("Em Tratamento");
				fila.setContatoCliente("Não iniciado");
				fila.setSituacao("Conectado");
			}
			if (i == 1)	{
				fila.setPrioridade("Emergência");
				fila.setContatoCliente("Retomar");
				fila.setSituacao("A contatar");
			}
			if (i == 2)	{
				fila.setPrioridade("Em Tratamento");
				fila.setContatoCliente("Retomar");
				fila.setSituacao("Conectado");
			}
			if (i == 3) {
				fila.setPrioridade("Preventiva");
				fila.setContatoCliente("Não iniciado");
				fila.setSituacao("A contatar");
			}
			if (i == 4)	{
				fila.setPrioridade("Preventiva");
				fila.setContatoCliente("Não iniciado");
				fila.setSituacao("Conectado");
			}
			fila.setInicioFila(data);
			fila.setTempoDecorridoFila(hora);
			listaFila.add(fila);
		}

		List<AtendimentoEmAndamentoVO> listaAtendimento = new ArrayList<AtendimentoEmAndamentoVO>();
		for (int i = 5; i < 9; i++) {
			AtendimentoEmAndamentoVO atend = new AtendimentoEmAndamentoVO();
			atend.setIdAtendimento(i);
			atend.setContrato(i * 2);
            if (i == 5) {
                atend.setPrioridade("Emergência");
            }
            if (i == 6) {
                atend.setPrioridade("Em Tratamento");
            }
            if (i == 7) {
                atend.setPrioridade("Preventiva");
            }
            if (i == 8) {
                atend.setPrioridade("Preventiva");
            }
            if (i == 9) {
                atend.setPrioridade("Emergência");
            }
			atend.setInicioAtendimento(data);
			atend.setTempoDecorridoAtendimento(hora);
			listaAtendimento.add(atend);
		}

		RelExtratoAtendimentoVO relatorioA = new RelExtratoAtendimentoVO();
		relatorioA.setFilaAtendimento(listaFila);
		relatorioA.setAtendimentoEmAndamento(listaAtendimento);

		return relatorioA;
	}

}
