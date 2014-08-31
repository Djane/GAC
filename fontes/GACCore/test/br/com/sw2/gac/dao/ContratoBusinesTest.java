package br.com.sw2.gac.dao;

import org.junit.Test;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DoencaVO;

public class ContratoBusinesTest {

    

    @Test
    public void salvarNumeroSequencialDispositivo() {        
        ContratoBusiness business = new ContratoBusiness();
        ContratoVO contrato = business.obterDadosContrato(1);
        
        //774.819.121/22
        System.out.println(contrato.getCliente().getCpf());
        DoencaVO doenca = new DoencaVO();
        doenca.setCodigoCID("A90");
        doenca.setNomeDoenca("Dengue ff");
        
        contrato.getCliente().getListaDoencas().add(doenca);
        
        business.atualizarContrato(contrato);
        
        
    }
    
    
   
}
