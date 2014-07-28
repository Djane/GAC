package br.com.sw2.gac.dao;

import org.junit.Test;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;

public class ClienteBusiness {

    
    public void teste() {        
       ContratoDAO contratoDAO = new ContratoDAO();
       Contrato contratoEntity = contratoDAO.getEntityById(101);
        
       ContratoVO contrato = ParseUtils.parse(contratoEntity);
       
       for (DispositivoVO dispositivo : contrato.getCliente().getListaDispositivos()) {
           System.out.println(dispositivo.getNumeroSequencialDisponisitoNaCentralInteger());
       }
    }
    
    @Test
    public void salvarNumeroSequencialDispositivo() {        
        ContratoBusiness business = new ContratoBusiness();
        business.atualizarNumeroSequencialDispositivo("803.781.396/73","8888888888888",10);        
    }
    
    
    public void testeD() {
        
            String codigoEnviadoPulseira = "A000000012345690D";
            System.out.println(Integer.parseInt(codigoEnviadoPulseira.substring(codigoEnviadoPulseira.length() - 3, codigoEnviadoPulseira.length() - 2)));
            System.out.println(Integer.parseInt(codigoEnviadoPulseira.substring(codigoEnviadoPulseira.length() - 2, codigoEnviadoPulseira.length() - 1)));
    }
    
}
