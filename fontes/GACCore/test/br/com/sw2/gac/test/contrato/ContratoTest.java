package br.com.sw2.gac.test.contrato;

import java.util.List;

import org.junit.Test;

import br.com.sw2.gac.business.AtendimentoBusiness;
import br.com.sw2.gac.dao.ContratoDAO;
import br.com.sw2.gac.filtro.FiltroPesquisarPreAtendimento;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.vo.ContratoVO;

public class ContratoTest {

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    private AtendimentoBusiness atendimentoBusiness = new AtendimentoBusiness();

  
    public void getListContratosTest() {
        
        FiltroPesquisarPreAtendimento filtro = new FiltroPesquisarPreAtendimento();
        filtro.setNumeroContrato(97);
        
        List<ContratoVO> lista = this.atendimentoBusiness.pesquisarContratosPreAtendimento(filtro);
        
        ContratoVO x = lista.get(0); 
        this.logger.debug(x.getCliente().getListaFormaContato().get(0).getTelefone());
        
    }

}
