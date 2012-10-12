package br.com.sw2.gac.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

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
	private static final String EXTRATO_ATENDIMENTO = "extratoAtendimento.jasper";
	private RelExtratoAtendimentoVO relatorio;


	/**
     * Imprimir relatório Extrato de atendimento.
     * @param ae ActionEvent
     */
    public void imprimirExtratoAtendimento(ActionEvent ae) {
    	this.getLogger().debug("Iniciando imprimirExtratoAtendimento");
        //Obtem os dados que serão exibidos no relatório
        relatorio = popularDadosMock();
		// TODO Implementar o negócio quando estiver disponível
		// relatorio = business.recuperaExtratoAtendimento(this.relatorio);
        List<RelExtratoAtendimentoVO> lista = new ArrayList<RelExtratoAtendimentoVO>();
        lista.add(relatorio);
	    super.imprimirRelatorioPadrao(EXTRATO_ATENDIMENTO, lista, null);
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
			fila.setPrioridade(1);
			fila.setInicioFila(data);
			fila.setTempoDecorridoFila(hora);
			fila.setContatoCliente(2);
			fila.setSituacao(2);
			listaFila.add(fila);
		}

		List<AtendimentoEmAndamentoVO> listaAtendimento = new ArrayList<AtendimentoEmAndamentoVO>();
		for (int i = 5; i < 9; i++) {
			AtendimentoEmAndamentoVO atend = new AtendimentoEmAndamentoVO();
			atend.setIdAtendimento(i);
			atend.setContrato(i * 2);
			atend.setPrioridade(2);
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
