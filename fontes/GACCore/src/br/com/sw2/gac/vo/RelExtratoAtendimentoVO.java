package br.com.sw2.gac.vo;

import java.util.List;

/**
 * VO que representa o relatório de Fila de Atendimentos.
 * @author ddiniz
 *
 */
public class RelExtratoAtendimentoVO {

	/** Atributo clientes ativos. */
    private List<FilaAtendimentoVO> filaAtendimento;

    /** Atributo movimentacao clientes. */
    private List<AtendimentoEmAndamentoVO> atendimentoEmAndamento;

	public List<FilaAtendimentoVO> getFilaAtendimento() {
		return filaAtendimento;
	}

	public void setFilaAtendimento(List<FilaAtendimentoVO> filaAtendimento) {
		this.filaAtendimento = filaAtendimento;
	}

	public List<AtendimentoEmAndamentoVO> getAtendimentoEmAndamento() {
		return atendimentoEmAndamento;
	}

	public void setAtendimentoEmAndamento(
			List<AtendimentoEmAndamentoVO> atendimentoEmAndamento) {
		this.atendimentoEmAndamento = atendimentoEmAndamento;
	}
}
