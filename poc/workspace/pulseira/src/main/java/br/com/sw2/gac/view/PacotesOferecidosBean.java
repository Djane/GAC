package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.vo.PacoteVO;

@ManagedBean
@ViewScoped
public class PacotesOferecidosBean extends BaseBean {

    private Integer idPacote = 0;
    private String tituloPacote = "";
    private String descricaoPacote = "";
    private List<PacoteVO> listaPacotes;

    public PacotesOferecidosBean() {
        this.listaPacotes = popularlistaPacotes();
    }

    public String iniciarPagina() {
        setTituloCabecalho("Cadastro de pacotes oferecidos");
        this.listaPacotes = popularlistaPacotes();
        return "pacotesoferecidos";
    }

    public void novo(ActionEvent actionEvent) {
        limparAtributos();
    }

    private void limparAtributos() {
        this.idPacote = 0;
        this.tituloPacote = "";
        this.descricaoPacote = "";
    }

    public void editar(ActionEvent actionEvent) {

       int id =  Integer.parseInt(getRequestParameter("idPacote"));
       PacoteVO edit = null;
       for (PacoteVO item : this.listaPacotes) {           
           if (item.getIdPacote().intValue() == id ) {
               edit = item;
               break;
           }           
       }    
        this.tituloPacote = edit.getTitulo();
        this.descricaoPacote = edit.getDescricao();
        this.idPacote = edit.getIdPacote();
    }

    public void salvar(ActionEvent actionEvent) {
  
        setFacesMessage("message.pacotesoferecidos.save.sucess");               
        if (this.idPacote > 0) {                  
             for (PacoteVO item : this.listaPacotes) {
                 if (item.getIdPacote().equals(this.idPacote)) {
                     item.setIdPacote(this.idPacote);
                     item.setTitulo(this.tituloPacote);
                     item.setDescricao(this.descricaoPacote);
                 }
             }
        } else {
            PacoteVO vo = new PacoteVO();
            vo.setTitulo(this.tituloPacote);
            vo.setDescricao(this.descricaoPacote);
            vo.setIdPacote(this.listaPacotes.size());
            this.listaPacotes.add(vo);
        }
        limparAtributos();
        
    }

    public void excluir(ActionEvent actionEvent) {
        PacoteVO remover = null;
        for (PacoteVO item : this.listaPacotes) {
            if (item.getIdPacote().equals(this.idPacote)) {
                remover = item;
            }
        }
        if (null != remover) {
            this.listaPacotes.remove(remover);
        }
        limparAtributos();
    }

    private List<PacoteVO> popularlistaPacotes() {
        this.listaPacotes = new ArrayList<PacoteVO>();
        PacoteVO pacote = new PacoteVO();
        pacote.setIdPacote(1);
        pacote.setTitulo("Titulo pacote 1");
        pacote.setDescricao("Descrição pacote 1");
        this.listaPacotes.add(pacote);
        return listaPacotes;
    }

    public Integer getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    public String getTituloPacote() {
        return tituloPacote;
    }

    public void setTituloPacote(String tituloPacote) {
        this.tituloPacote = tituloPacote;
    }

    public String getDescricaoPacote() {
        return descricaoPacote;
    }

    public void setDescricaoPacote(String descricaoPacote) {
        this.descricaoPacote = descricaoPacote;
    }

    public List<PacoteVO> getListaPacotes() {
        return listaPacotes;
    }

    public void setListaPacotes(List<PacoteVO> listaPacotes) {
        this.listaPacotes = listaPacotes;
    }
}
