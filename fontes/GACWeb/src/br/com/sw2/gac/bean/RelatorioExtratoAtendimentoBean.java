package br.com.sw2.gac.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

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
	private static final String EXTRATO_ATENDIMENTO = "filaAtendimento.jasper";
	private RelExtratoAtendimentoVO relatorio;


	/**
     * Imprimir relatório Extrato de atendimento.
     * @param ae ActionEvent
     */
    public void imprimirExtratoAtendimento(ActionEvent ae) {
    	this.getLogger().debug("Iniciando imprimirExtratoAtendimento");
        //Obtem os dados que serão exibidos no relatório
        List<RelExtratoAtendimentoVO> lista = popularDadosMock();
		// TODO Implementar o negócio quando estiver disponível
		// lista = business.recuperaExtratoAtendimento(this.relatorio);
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
	private List<RelExtratoAtendimentoVO> popularDadosMock() {
		List<RelExtratoAtendimentoVO> lista = new ArrayList<RelExtratoAtendimentoVO>();

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

		for (int i = 0; i < 10; i++) {
			RelExtratoAtendimentoVO relatorioA = new RelExtratoAtendimentoVO();
			relatorioA.setIdAtendimento(i);
			relatorioA.setContrato(i * 2);
			relatorioA.setPrioridade(1);
			relatorioA.setInicioAtendimento(data);
			relatorioA.setTempoDecorridoAtendimento(hora);
			lista.add(relatorioA);
		}

		return lista;
	}

}
