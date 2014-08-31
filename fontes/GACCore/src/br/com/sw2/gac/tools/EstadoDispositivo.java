package br.com.sw2.gac.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sw2.gac.business.InventarioDispositivoBusiness;
import br.com.sw2.gac.business.state.DispositivoState;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * <b>Descrição: ENUM que indica os Status possíveis dos Dispositivos</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum EstadoDispositivo {

    /** Atributo Novo. */
    Novo(1, "Novo") {
		@Override
		public DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business) {
			return business.getNovoState();
		}

		@Override
		public void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) {
			business.novo();
		}

		@Override
		public List<EstadoDispositivo> recuperaEstadosSeguintes() {
			List<EstadoDispositivo> lista = new ArrayList<EstadoDispositivo>();
			lista.add(EstadoDispositivo.Manutencao);
			return lista;
		}
	},

    /** Atributo Pronto. */
    Pronto(2, "Pronto") {
		@Override
		public DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business) {
			return business.getProntoState();
		}

		@Override
		public void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) {
			business.pronto(dispositivos);
		}

		@Override
		public List<EstadoDispositivo> recuperaEstadosSeguintes() {
			List<EstadoDispositivo> lista = new ArrayList<EstadoDispositivo>();
			lista.add(EstadoDispositivo.Uso);
			return lista;
		}
    },

    /** Atributo Uso. */
    Uso(3, "Uso") {
    	@Override
		public DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business) {
			return business.getUsoState();
		}

    	@Override
		public void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) {
			business.uso(dispositivos);
		}

		@Override
		public List<EstadoDispositivo> recuperaEstadosSeguintes() {
			List<EstadoDispositivo> lista = new ArrayList<EstadoDispositivo>();
			lista.add(EstadoDispositivo.Devolvido);
			return lista;
		}
   },

    /** Atributo Devolvido. */
    Devolvido(4, "Devolvido") {
		@Override
		public DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business) {
			return business.getDevolvidoState();
		}

		@Override
		public void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) {
			business.devolvido(dispositivos);
		}

		@Override
		public List<EstadoDispositivo> recuperaEstadosSeguintes() {
			List<EstadoDispositivo> lista = new ArrayList<EstadoDispositivo>();
			lista.add(EstadoDispositivo.Manutencao);
			return lista;
		}
	},

    /** Atributo Manutencao. */
    Manutencao(5, "Manutenção") {
		@Override
		public DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business) {
			return business.getManutencaoState();
		}

		@Override
		public void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) {
			business.manutencao(dispositivos);
		}

		@Override
		public List<EstadoDispositivo> recuperaEstadosSeguintes() {
			List<EstadoDispositivo> lista = new ArrayList<EstadoDispositivo>();
			lista.add(EstadoDispositivo.Defeito);
			lista.add(EstadoDispositivo.Descarte);
			lista.add(EstadoDispositivo.Pronto);
			return lista;
		}
	},
    /** Atributo Defeito. */
    Defeito(6, "Defeito") {
		@Override
		public DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business) {
			return business.getDefeitoState();
		}

		@Override
		public void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) {
			business.defeito(dispositivos);
		}

		@Override
		public List<EstadoDispositivo> recuperaEstadosSeguintes() {
			List<EstadoDispositivo> lista = new ArrayList<EstadoDispositivo>();
			lista.add(EstadoDispositivo.Fabrica);
			return lista;
		}
	},
    /** Atributo Descarte. */
    Descarte(7, "Descarte") {
		@Override
		public DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business) {
			return business.getDescarteState();
		}

		@Override
		public void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) {
			business.descarte(dispositivos);
		}

		@Override
		public List<EstadoDispositivo> recuperaEstadosSeguintes() {
			return new ArrayList<>();
		}
	},
    /** Atributo Fabrica. */
    Fabrica(8, "Fabrica") {
		@Override
		public DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business) {
			return business.getFabricaState();
		}

		@Override
		public void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) {
			business.fabrica(dispositivos);
		}

		@Override
		public List<EstadoDispositivo> recuperaEstadosSeguintes() {
			return new ArrayList<>();
		}
	};

	/** Atributo value. */
    private Integer value;

    /** Atributo label. */
    private String label;

    /**
     * Guarda as relacoes entre a o valor e um elemento da enum.
     */
    private static Map<Integer, EstadoDispositivo> relacao;

    /**
     * Construtor Padrao Instancia um novo objeto EstadoDispositivo.
     * @param value the value
     * @param label the label
     */
    private EstadoDispositivo(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
	 * Define qual classe State implementa o estado.
     * @param business InventarioDispositivoBusiness
	 * @return DispositivoState
	 */
    public abstract DispositivoState recuperarDispositivoState(InventarioDispositivoBusiness business);

    /**
     * Define qual método de movimentação chamar.
     * @param dispositivos lista de dispositivos
     * @param business business
     * @throws BusinessException exception
     */
    public abstract void movimentarDispositivo(List<DispositivoVO> dispositivos, InventarioDispositivoBusiness business) throws BusinessException;

    /**
     * Recupera uma lista de dos estados seguintes válidos.
     * @return List<EstadoDispositivo>
     */
    public abstract List<EstadoDispositivo> recuperaEstadosSeguintes();

    /**
     * Recupera o EstadoEnum a partir de seu valor Inteiro.
     * @param valor número que representa o Enum
     * @return EstadoDispositivo enum correspondente
     */
    public static EstadoDispositivo getEstadoPeloValor(Integer valor) {
    	return relacao.get(valor);
    }

    /**
     * Bloco estatico que popula o hashmap com as relacoes entre value e elementos da enum.
     */
    static {
        relacao = new HashMap<Integer, EstadoDispositivo>();
        for (EstadoDispositivo estado : values()) {
        	relacao.put(estado.getValue(), estado);
        }
    }

    /**
     * Nome: getValue Recupera o valor do atributo 'value'.
     * @return valor do atributo 'value'
     * @see
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Nome: setValue Registra o valor do atributo 'value'.
     * @param value valor do atributo value
     * @see
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Nome: getLabel Recupera o valor do atributo 'label'.
     * @return valor do atributo 'label'
     * @see
     */
    public String getLabel() {
        return label;
    }

    /**
     * Nome: setLabel Registra o valor do atributo 'label'.
     * @param label valor do atributo label
     * @see
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
