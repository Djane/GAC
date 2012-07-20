package br.com.smartangel.pulseira.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;

import br.com.smartangel.pulseira.vo.DispositivoVO;
import br.com.smartangel.pulseira.vo.DoencaVO;

@ManagedBean
@ViewScoped
public class ContratoBean extends BaseBean {

    private Date dtNascimentoContratante;

    private DualListModel<DoencaVO> listaDoencas;

    public ContratoBean() {
        setTituloCabecalho("Contrato");
        this.dtNascimentoContratante = new Date();
        
        //popula picklist
        List<DoencaVO> source = new ArrayList<DoencaVO>();  
        List<DoencaVO> target = new ArrayList<DoencaVO>();
        
        DoencaVO doenca = new DoencaVO();
        doenca.setIdDoenca(1);
        doenca.setNomeDoenca("Doença 1");
        source.add(doenca);
        doenca = new DoencaVO();
        doenca.setIdDoenca(2);
        doenca.setNomeDoenca("Doença 2");
        source.add(doenca);
        doenca = new DoencaVO();
        doenca.setIdDoenca(3);
        doenca.setNomeDoenca("Doença 3");
        source.add(doenca);
        
        this.listaDoencas = new DualListModel<DoencaVO>(source, target);
        
    }

    public String iniciarPagina() {
        setTituloCabecalho("Contrato");
        return "contrato";
    }

    public String novoContrato() {
        setTituloCabecalho("Contrato");
        return "contrato";
    }

    public Date getDtNascimentoContratante() {
        return dtNascimentoContratante;
    }

    public void setDtNascimentoContratante(Date dtNascimentoContratante) {
        this.dtNascimentoContratante = dtNascimentoContratante;
    }

    public DualListModel<DoencaVO> getListaDoencas() {
        return listaDoencas;
    }

    public void setListaDoencas(DualListModel<DoencaVO> listaDoencas) {
        this.listaDoencas = listaDoencas;
    }

}
